<template>
  <div id="wrapper">
    <RouterLink to="/admin">
      <button>Dashboard</button>
    </RouterLink>
    <RouterLink to="/admin/products/add">
      <button>+New product</button>
    </RouterLink>
    <Layout :items="ordersFiltered">
      <template #list-header>
        <div>
          <div class="left">
            <p>id</p>
          </div>
          <div class="center">
            <p>name</p>
          </div>
          <div class="right">
            <p>price</p>
          </div>
        </div>
      </template>
      <template #item="{ id, name, price }">
        <RouterLink :to="`/admin/products/${id}`">
          <div class="left">
            <p>#{{ id }}</p>
          </div>
          <div class="center">
            <p>
              {{ name }}
            </p>
          </div>
          <div class="right">
            <p>${{ price }}</p>
          </div>
        </RouterLink>
      </template>
      <template #content>
        <form v-if="product" id="product" :key="product.id" @submit.prevent="submit">
          <h2 v-if="product.id">Product #{{ product.id }}</h2>
          <h2 v-else>Create product</h2>
          <button v-if="product.id" type="button" @click="deleteProduct">Delete product</button>
          <div>
            <label for="name">Name</label>
            <input id="name" type="text" v-model="product.name" placeholder="Name" />
          </div>
          <div>
            <label for="color">Color</label>
            <input id="color" type="text" v-model="product.color" placeholder="Color" />
          </div>
          <div>
            <label for="description">Description</label>
            <textarea
              id="description"
              v-model="product.description"
              placeholder="Description"
            ></textarea>
          </div>
          <div>
            <label for="type">Type</label>
            <select id="type" v-model="product.type">
              <option value="Flower">Flower</option>
              <option value="Bouquet">Bouquet</option>
            </select>
          </div>
          <div>
            <label for="price">Price</label>
            <input id="price" type="number" v-model="product.price" placeholder="Price" />
          </div>
          <div>
            <label for="seasoning">Seasoning</label>
            <select id="seasoning" v-model="product.seasoning">
              <option value="" disabled>Seasoning</option>
              <option value="summer">🌞 Summer</option>
              <option value="spring">🌸 Spring</option>
              <option value="greenhouse">🏡 Greenhouse</option>
            </select>
          </div>
          <div>
            <p>Main image</p>
            <label for="image"><img :src="product.image" /></label>
            <input id="image" type="file" @change="uploadImage($event, 'image')" />
          </div>
          <div>
            <p>Single image</p>
            <label for="image"><img :src="product.image_single" /></label>
            <input id="image_single" type="file" @change="uploadImage($event, 'image_single')" />
          </div>
          <button type="submit">{{ product.id ? 'Update' : 'Create' }}</button>
        </form>
        <div v-else>product not selected</div>
      </template>
    </Layout>
  </div>
</template>
<script setup lang="ts">
import { computed, ref, watch } from 'vue'
import Layout from './shared.vue'
import { useRoute, useRouter } from 'vue-router'
import type { Product } from '../../types'
import { useProductsStore } from '@/stores/products'

const productsStore = useProductsStore()
const route = useRoute()
const router = useRouter()

const product = ref<Product | null>(null)

const ordersFiltered = computed(() => productsStore.products)
const productId = computed(() => (route.params.product ? route.params.product.toString() : null))

const refreshProduct = () => {
  if (productId.value === null) {
    product.value = null
    return
  }
  if (productId.value === 'add') {
    product.value = {
      id: 0,
      name: '',
      color: '',
      description: '',
      type: 'Flower',
      price: 0,
      seasoning: '',
      image: null,
      image_single: null
    }
    return
  }
  const productData = productsStore.products.find(
    (product) => product.id === parseInt(productId.value)
  )
  product.value = productData ? { ...productData } : null
}

const fileToBase64 = (inputFile: Blob) => {
  const promise = new Promise<string>(function (resolve, reject) {
    const reader = new FileReader()
    reader.onload = (event) => {
      if (!event.target) {
        return reject(new Error('Failed to load file'))
      }
      resolve(event.target.result as string)
    }
    reader.readAsDataURL(inputFile)
  })
  return promise
}

const uploadImage = async (event: Event, field: 'image' | 'image_single') => {
  const target = event.target as HTMLInputElement
  if (!target.files || !target.files[0]) {
    return
  }
  const base64 = await fileToBase64(target.files[0])
  if (field === 'image') {
    product.value!.image = base64
  } else {
    product.value!.image_single = base64
  }
}

const submit = async () => {
  if (product.value!.id) {
    await productsStore.updateProduct(product.value!)
  } else {
    const newProduct = await productsStore.createProduct(product.value!)
    router.push(`/admin/products/${newProduct.id}`)
  }
}

const deleteProduct = async () => {
  if (product.value!.id) {
    if (await productsStore.deleteProduct(product.value!.id)) router.push('/admin/products')
  }
}

watch(productId, refreshProduct, { immediate: true })
watch(() => productsStore.products, refreshProduct)
</script>
<style scoped>
#wrapper {
  width: 100%;
  display: flex;
  justify-content: center;
}
#product {
  width: 100%;
  display: flex;
  flex-direction: column;
}
</style>
