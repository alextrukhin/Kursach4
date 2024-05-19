<template>
  <div id="wrapper">
    <div>
      <RouterLink to="/admin">
        <button class="btn"><ChevronLeft /> Back to Dashboard</button>
      </RouterLink>
      <RouterLink to="/admin/products/add">
        <button class="btn"><Plus /> New product</button>
      </RouterLink>
    </div>
    <div>
      <Layout :items="ordersFiltered">
        <template #list-header>
          <div>
            <div class="left header">
              <p>id</p>
            </div>
            <div class="center header">
              <p>name</p>
            </div>
            <div class="right header">
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
            <button v-if="product.id" type="button" @click="deleteProduct" class="button">
              Delete product
            </button>
            <div class="form-container">
              <label for="name" class="first-colomn">Name</label>
              <input
                id="name"
                type="text"
                v-model="product.name"
                placeholder="Name"
                class="second-colomn"
              />
            </div>
            <div class="form-container">
              <label for="color" class="first-colomn">Color</label>
              <input
                id="color"
                type="text"
                v-model="product.color"
                placeholder="Color"
                class="second-colomn"
              />
            </div>
            <div class="form-container">
              <label for="description" class="first-colomn">Description</label>
              <textarea
                class="second-colomn textarea"
                id="description"
                v-model="product.description"
                placeholder="Description"
              ></textarea>
            </div>
            <div class="form-container">
              <label for="type" class="first-colomn">Type</label>
              <select id="type" v-model="product.type" class="second-colomn">
                <option value="Flower">Flower</option>
                <option value="Bouquet">Bouquet</option>
              </select>
            </div>
            <div class="form-container">
              <label for="price" class="first-colomn">Price</label>
              <input
                id="price"
                type="number"
                v-model="product.price"
                placeholder="Price"
                class="second-colomn"
              />
            </div>
            <div class="form-container">
              <label for="seasoning" class="first-colomn">Seasoning</label>
              <select id="seasoning" v-model="product.seasoning" class="second-colomn">
                <option value="" disabled>Seasoning</option>
                <option value="summer">🌞 Summer</option>
                <option value="spring">🌸 Spring</option>
                <option value="greenhouse">🏡 Greenhouse</option>
              </select>
            </div>
            <div>
              <h2>Main image</h2>
              <label for="image"><img :src="product.image" /></label>
              <input id="image" type="file" @change="uploadImage($event, 'image')" class="text" />
            </div>
            <div>
              <h2>Single image</h2>
              <label for="image"><img :src="product.image_single" /></label>
              <input
                id="image_single"
                type="file"
                @change="uploadImage($event, 'image_single')"
                class="text"
              />
            </div>
            <button type="submit" class="button">{{ product.id ? 'Update' : 'Create' }}</button>
          </form>
          <div v-else class="text">product not selected</div>
        </template>
      </Layout>
    </div>
  </div>
</template>
<script setup lang="ts">
import { computed, ref, watch } from 'vue'
import Layout from './shared.vue'
import { useRoute, useRouter } from 'vue-router'
import type { Product } from '../../types'
import { useProductsStore } from '@/stores/products'
import { ChevronLeft, Plus } from 'lucide-vue-next'

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
  width: 1200px;
  display: flex;
  justify-content: center;
  flex-direction: column;
  margin: auto;
}
#product {
  width: 100%;
  display: flex;
  flex-direction: column;
}
.btn {
  padding: 0;
  display: inline-flex;
  justify-content: left;
  align-items: center;
  margin: 10px auto 10px 0;
  color: #370017;
  text-align: center;
  font-family: Montserrat;
  font-size: 20px;
  font-style: normal;
  font-weight: 500;
  line-height: normal;
  text-transform: uppercase;
  outline: none;
  border: none;
  background-color: transparent;
  height: 26px;
  padding-right: 35px;
}
.btn:hover {
  cursor: pointer;
}
p {
  color: #370017;
  text-align: left;
  font-family: Montserrat;
  font-size: 15px;
  font-style: normal;
  font-weight: 300;
  line-height: normal;
  text-transform: uppercase;
  margin: 5px 0 5px 0;
}
.left {
  min-width: 40px;
}
.left p {
  font-weight: 600;
}
.center p {
  text-transform: none;
}
.header p {
  font-weight: 600;
  text-transform: uppercase;
  font-size: 15px;
}
h2 {
  color: #370017;
  text-align: left;
  text-overflow: ellipsis;
  font-family: Montserrat;
  font-size: 20px;
  font-style: normal;
  font-weight: 500;
  line-height: normal;
  text-transform: uppercase;
  margin-bottom: 5px;
}
.button {
  width: 440px;
  height: 50px;
  border-radius: 35px;
  background: #efc1cf;
  padding: 0;
  display: flex;
  justify-content: center;
  align-items: center;
  margin: 10px 0 5px 0;
  color: #370017;
  text-align: center;
  text-overflow: ellipsis;
  font-family: Montserrat;
  font-size: 15px;
  font-style: normal;
  font-weight: 500;
  line-height: normal;
  text-transform: uppercase;
  border: none;
  outline: none;
  flex-shrink: 0;
}
.button:hover {
  box-shadow: 1px 2px 3px 0px rgba(55, 0, 23, 0.15);
  cursor: pointer;
}
.first-colomn {
  width: 150px;
  display: inline-block;
  color: #370017;
  text-align: left;
  font-family: Montserrat;
  font-size: 15px;
  font-style: normal;
  font-weight: 500;
  line-height: normal;
  padding-top: 15px;
}
.second-colomn {
  width: 290px;
  height: 50px;
  display: inline-block;
  border-radius: 20px;
  background: #f9eff2;
  padding: 0;
  border-style: none;
  padding-left: 15px;
  color: #370017;
  text-align: left;
  font-family: Montserrat;
  font-size: 15px;
  font-style: normal;
  font-weight: 300;
  line-height: normal;
  outline: none;
}
.form-container {
  margin: 5px 0;
  display: flex;
  justify-content: left;
  align-items: start;
}
.textarea {
  resize: vertical;
  min-height: 100px;
  padding-top: 15px;
}
.text {
  padding-left: 15px;
  color: #370017;
  text-align: left;
  font-family: Montserrat;
  font-size: 15px;
  font-style: normal;
  font-weight: 300;
  line-height: normal;
  padding: 0;
}
</style>
