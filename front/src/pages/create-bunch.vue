<template>
  <div class="wrapper">
    <div class="bunch-edit-modal__left">
      <BunchContainer :products="bunch.products" @update="updateProducts" />
      <h2 class="text" style="margin: 15px auto 15px auto; text-align: center">
        Drag in corner to delete
      </h2>
      <div style="justify-content: center; margin: auto; display: flex">
        <button @click="save" class="btn">Save</button>
      </div>
    </div>
    <div class="bunch-edit-modal__right">
      <div class="used-container">
        <h3>Used flowers - ${{ total }}</h3>
        <div class="products-list">
          <div v-for="product in usedProducts" class="product-row">
            <img :src="product.image_single ?? product.image" />
            <div style="flex-grow: 1; text-align: left; padding-left: 15px" class="text">
              {{ product.name }}
            </div>
            <div class="text" style="padding-right: 30px">
              {{ product.quantity }} x ${{ product.price }} = ${{
                product.quantity * product.price
              }}
            </div>
          </div>
        </div>
      </div>
      <div class="flowers-container">
        <h3>Flowers</h3>
        <div class="products-list">
          <div v-for="product in flowers" @click="addProduct(product)" class="product-row">
            <img :src="product.image_single ?? product.image" />
            <div style="flex-grow: 1; text-align: left; padding-left: 15px" class="text">
              {{ product.name }}
            </div>
            <div class="text" style="padding-right: 15px">${{ product.price }}</div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
<script setup lang="ts">
import { ref, computed } from 'vue'
import BunchContainer from '@/components/bunch/BunchContainer.vue'
import { useProductsStore } from '@/stores/products'
import { Bunch, Product } from '@/types'
import { useRouter } from 'vue-router'

const productsStore = useProductsStore()
const router = useRouter()

const bunch = ref<Bunch>({
  id: null,
  products: []
})
const emit = defineEmits<{
  (event: 'close'): void
}>()

const flowers = computed(() =>
  productsStore.products.filter((product) => product.type === 'Flower')
)
const usedProducts = computed(() =>
  Array.from(new Set(bunch.value.products?.map((product) => product.id))).map((id) => ({
    ...productsStore.getProductByID(id)!,
    quantity: bunch.value.products?.filter((product) => product.id === id).length ?? 1
  }))
)
const total = computed(() =>
  usedProducts.value.reduce((acc, product) => acc + product.price * product.quantity, 0)
)

const addProduct = (product: Product) => {
  console.log(product)
  bunch.value.products?.push({ id: product.id, x: 0, y: 0 })
}

const updateProducts = (products: Bunch['products']) => {
  bunch.value.products = products
}
const save = () => {
  productsStore.carted.content.bunches.push({
    bunch: bunch.value,
    quantity: 1
  })
  router.push('/cart')
}
</script>
<style scoped>
.wrapper {
  display: flex;
  justify-content: center;
  flex-direction: row;
  width: 1200px;
  padding: 0;
  margin: auto;
}
.bunch-edit-modal__left {
  align-items: baseline;
  justify-content: center;
  margin-top: 30px;
}
.bunch-edit-modal__right {
  width: 800px;
  padding-left: 30px;
}
.used-container {
  background-color: #f9eff2;
  border-radius: 10px;
  padding: 10px;
  margin-top: 30px;
  cursor: pointer;
}
.flowers-container {
  border: 3px solid #f9eff2;
  border-radius: 10px;
  padding: 10px;
  margin-top: 30px;
  cursor: pointer;
}
.products-list {
  overflow-y: auto;
  max-height: 400px;
}
.product-row {
  display: flex;
  flex-direction: row;
  align-items: center;
  justify-content: space-between;
  margin-top: 8px;
}
.product-row img {
  width: 50px;
  height: 50px;
  object-fit: cover;
}
.text {
  color: #370017;
  text-align: center;
  font-family: Montserrat;
  font-size: 20px;
  font-style: normal;
  font-weight: 300;
  line-height: normal;
  text-transform: uppercase;
  margin: 5px auto 5px auto;
}
h3 {
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
  margin-top: 5px;
}
.btn {
  width: 200px;
  height: 50px;
  border-radius: 25px;
  background-color: #efc1cf;
  margin: auto;
  color: #370017;
  text-align: center;
  text-overflow: ellipsis;
  font-family: Montserrat;
  font-size: 20px;
  font-style: normal;
  font-weight: 500;
  line-height: normal;
  text-transform: uppercase;
  border: none;
  outline: none;
}
.btn:hover {
  box-shadow: 1px 2px 3px 0px rgba(55, 0, 23, 0.15);
  cursor: pointer;
}
</style>
