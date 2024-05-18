import { ref, computed, watch, reactive } from 'vue'
import { defineStore } from 'pinia'
import type { Bunch, Product } from '../types'
import { z } from 'zod'

export const useProductsStore = defineStore('products', () => {
  const productsFetched = ref(false)
  const products = ref<Product[]>([])
  const carted = reactive<{
    content: {
      products: { productId: number; quantity: number }[]
      bunches: { bunch: Bunch; quantity: number }[]
    }
    lastChange: number
  }>({
    content: {
      products: [],
      bunches: []
    },
    lastChange: -1
  })
  watch(carted, handleCartChange)

  async function init() {
    try {
      const stored = localStorage.getItem('carted')
      if (stored) {
        const parsed = JSON.parse(stored) as typeof carted
        if (!parsed.content) throw new Error('Invalid carted content')
        if (!parsed.lastChange) throw new Error('Invalid carted lastChange')
        carted.content = parsed.content
        carted.lastChange = parsed.lastChange
      }
    } catch {
      localStorage.removeItem('carted')
    }
    const fetchedProducts = await (await fetch(`http://localhost:8080/flowers`)).json()
    products.value = fetchedProducts
    productsFetched.value = true
  }

  async function handleCartChange(val: typeof carted) {
    console.log('handleCartChange', val)
    localStorage.setItem(
      'carted',
      JSON.stringify(
        z
          .object({
            content: z.object({
              products: z.array(
                z.object({
                  productId: z.number(),
                  quantity: z.number()
                })
              ),
              bunches: z.array(
                z.object({
                  bunch: z.object({
                    id: z.number().nullable(),
                    products: z.array(
                      z.object({
                        id: z.number(),
                        x: z.number(),
                        y: z.number()
                      })
                    )
                  }),
                  quantity: z.number()
                })
              )
            }),
            lastChange: z.number()
          })
          .parse(val)
      )
    )
  }

  const cartedProducts = computed(() => {
    let toReturn: Array<Product & { quantity: number }> = []
    carted.content.products.map((el) => ({
      ...products.value.find((prod) => prod.id == el.productId)!,
      quantity: el.quantity
    }))
    return toReturn
  })
  const cartedBunches = computed(() => {
    let toReturn: Array<
      Bunch & {
        products: Array<
          NonNullable<Bunch['products']>[number] & {
            product: Product
          }
        >
        quantity: number
      }
    > = []
    return toReturn
  })
  const cartSum = computed(() => {
    let sum = 0
    carted.content.products.forEach((el) => {
      let product = products.value.find((prod) => prod.id == el.productId)
      if (product) sum += product.price * el.quantity
    })
    carted.content.bunches.forEach((bunch) => {
      if (bunch?.bunch?.products?.length)
        sum +=
          bunch?.bunch.products.reduce(
            (acc, curr) => acc + (getProductByID(curr.id)?.price ?? 0),
            0
          ) * bunch.quantity
    })
    return sum
  })
  function getProductByID(productId: number) {
    const product = products.value.find((el) => el.id == productId)
    if (!product) {
      return productsFetched.value ? null : undefined
    }
    return {
      ...product,
      carted: carted.content.products.some((el) => el.productId == productId),
      quantity: carted.content.products.find((el) => el.productId == productId)?.quantity || 0
    }
  }
  function getProductsByID(productIds: number[]) {
    return productIds.map((el) => getProductByID(el))
  }
  async function cartProduct(productId: number) {
    if (carted.content.products.some((el) => el.productId == productId)) return
    carted.content.products.push({
      productId: productId,
      quantity: 1
    })
    carted.lastChange = Math.floor(new Date().valueOf() / 1000)
  }
  async function uncartProduct(productId: number) {
    console.log('uncartProduct', productId)
    if (!carted.content.products.some((el) => el.productId == productId)) return
    let indexOfThisProduct = carted.content.products.findIndex((el) => el.productId == productId)
    carted.content.products.splice(indexOfThisProduct, 1)
    carted.lastChange = Math.floor(new Date().valueOf() / 1000)
  }
  async function cartChangeProductQuantity(productId: number, quantity: number) {
    console.log('cartChangeProductQuantity', productId)
    let targetElement = carted.content.products.find((el) => el.productId == productId)
    if (!targetElement) return
    if (targetElement) {
      targetElement.quantity = quantity || 1
      carted.lastChange = Math.floor(new Date().valueOf() / 1000)
    }
  }

  async function createProduct(product: Product) {
    const res = await fetch(`http://localhost:8080/addProduct`, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify(product)
    })
    if (res.ok) {
      const newProduct = await res.json()
      products.value.push(newProduct)
      return newProduct
    }
  }
  async function updateProduct(product: Product) {
    const res = await fetch(`http://localhost:8080/updateProduct`, {
      method: 'PATCH',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify(product)
    })
    if (res.ok) {
      const newProduct = await res.json()
      products.value.push(newProduct)
      return newProduct
    }
  }
  async function deleteProduct(id: number) {
    const res = await fetch(`http://localhost:8080/deleteProduct`, {
      method: 'DELETE',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify({ id })
    })
    if (res.ok) {
      products.value = products.value.filter((el) => el.id !== id)
    } else {
      alert('Failed to delete product')
      return false
    }
    return true
  }

  return {
    init,
    products,
    carted,
    cartedProducts,
    cartedBunches,
    cartSum,
    getProductByID,
    getProductsByID,
    cartProduct,
    uncartProduct,
    cartChangeProductQuantity,
    createProduct,
    updateProduct,
    deleteProduct
  }
})
