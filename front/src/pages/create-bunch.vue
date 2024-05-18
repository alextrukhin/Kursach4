<template>
  <div class="wrapper">
    <div class="bunch-edit-modal__left">
      <BunchContainer :products="bunch.products" @update="updateProducts" />
      <span>Drag in corner to delete</span>
      <div><button @click="save">Save</button></div>
    </div>
    <div class="bunch-edit-modal__right">
      <h3>Used flowers - ${{ total }}</h3>
      <div class="products-list">
        <div v-for="product in usedProducts" class="product-row">
          <img :src="product.image" />
          <div style="flex-grow: 1">{{ product.name }}</div>
          <div>
            {{ product.quantity }} x ${{ product.price }} = ${{ product.quantity * product.price }}
          </div>
        </div>
      </div>
      <h3>Flowers</h3>
      <div class="products-list">
        <div v-for="product in flowers" @click="addProduct(product)" class="product-row">
          <img :src="product.image" />
          <div style="flex-grow: 1">{{ product.name }}</div>
          <div>${{ product.price }}</div>
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
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
}
.bunch-edit-modal__right {
  width: 800px;
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
</style>
