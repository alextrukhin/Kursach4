<template>
  <BaseModal>
    <div class="bunch-edit-modal">
      <div class="bunch-edit-modal__left">
        <BunchContainer :products="bunchLocal.products" @update="updateProductsDebounced" />
        <h2 class="text">Drag in corner to delete</h2>
      </div>
      <div class="bunch-edit-modal__right">
        <div class="btn-container">
          <button @click="emit('close')" class="btn text">
            <img
              src="../../../close.svg"
              alt=""
              style="width: 30px; height: 30px; margin: 0; padding: 0"
            />
          </button>
        </div>
        <div class="used-container">
          <h3>Used products: - ${{ total }}</h3>
          <div>
            <div v-for="product in usedProducts" class="product-row">
              <img :src="product.image" style="border-radius: 3px; margin-right: 10px" />
              <div style="flex-grow: 1; text-align: left" class="text">{{ product.name }}</div>
              <div class="text" style="padding-right: 15px">
                {{ product.quantity }} x ${{ product.price }} =
                {{ product.quantity * product.price }}
              </div>
            </div>
          </div>
        </div>
        <div class="flowers-container">
          <h3>Products</h3>
          <div>
            <div v-for="product in flowers" @click="addProduct(product)" class="product-row">
              <img :src="product.image" style="border-radius: 3px; margin-right: 10px" />
              <div style="flex-grow: 1; text-align: left" class="text">{{ product.name }}</div>
              <div class="text" style="padding-right: 15px">${{ product.price }}</div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </BaseModal>
</template>
<script setup lang="ts">
import { type PropType, ref, watch, computed } from 'vue'
import BaseModal from '../BaseModal.vue'
import BunchContainer from './BunchContainer.vue'
import { debounce } from 'perfect-debounce'
import { useProductsStore } from '@/stores/products'
import { Bunch, Product } from '@/types'
const productsStore = useProductsStore()

const props = defineProps({
  bunch: {
    type: Object as PropType<Bunch>,
    required: true
  }
})
const emit = defineEmits<{
  (event: 'close'): void
}>()

const bunchLocal = ref(props.bunch)

const flowers = computed(() =>
  productsStore.products.filter((product) => product.type === 'Flower')
)
const usedProducts = computed(() =>
  Array.from(new Set(bunchLocal.value.products?.map((product) => product.id))).map((id) => ({
    ...productsStore.getProductByID(id)!,
    quantity: bunchLocal.value.products?.filter((product) => product.id === id).length ?? 1
  }))
)
const total = computed(() =>
  usedProducts.value.reduce((acc, product) => acc + product.price * product.quantity, 0)
)

const addProduct = (product: Product) => {
  console.log(product)
  bunchLocal.value.products?.push({ id: product.id, x: 0, y: 0 })
}

const updateProducts = (products: Bunch['products']) => {
  bunchLocal.value.products = products
}
const updateProductsDebounced = debounce(updateProducts, 200)

watch(
  () => props.bunch,
  (newVal) => {
    bunchLocal.value = newVal
  }
)
</script>
<style scoped>
.bunch-edit-modal {
  width: 900px;
  height: 800px;
  display: flex;
  flex-direction: row;
  gap: 12px;
  background-color: white;
  padding: 20px;
  border-radius: 20px;
  color: black;
  overflow: hidden;
}
.bunch-edit-modal__left {
  margin-top: 60px;
  justify-content: center;
}
.bunch-edit-modal__right {
  height: 100%;
  flex-grow: 1;
  overflow: hidden auto;
  position: relative;
}
.used-container {
  background-color: #f9eff2;
  border-radius: 10px;
  padding: 10px;
  margin-top: 20px;
  cursor: pointer;
}
.flowers-container {
  border: 3px solid #f9eff2;
  border-radius: 10px;
  padding: 10px;
  margin-top: 20px;
  cursor: pointer;
}
.product-row {
  display: flex;
  flex-direction: row;
  align-items: center;
  justify-content: space-between;
  gap: 8px;
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
}
.btn-container {
  display: flex;
  flex-direction: row;
  align-items: center;
  justify-content: right;
  position: sticky;
  top: 0;
  right: 0;
}
.btn {
  background-color: #efc1cf;
  margin: 0;
  padding: 0;
  width: 40px;
  height: 40px;
  display: flex;
  align-items: center;
  justify-content: center;
  outline: none;
  border: none;
}
.btn:hover {
  box-shadow: 1px 1px 2px 0px rgba(55, 0, 23, 0.1);
  cursor: pointer;
}
</style>
