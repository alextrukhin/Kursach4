<script setup lang="ts">
import { PropType, computed } from 'vue'
import { Bunch, Product } from '@/types'
import BunchPreview from '@/components/bunch/BunchPreview.vue'
import { useProductsStore } from '@/stores/products'

const productsStore = useProductsStore()

const props = defineProps({
  bunch: {
    type: Object as PropType<{
      bunch: Bunch & {
        products: Array<
          NonNullable<Bunch['products']>[number] & {
            product: Product
          }
        >
      }
      quantity: number
    }>,
    required: true
  },
  controls: { type: Boolean, default: true }
})
const emit = defineEmits<{
  (event: 'edit'): void
  (event: 'delete'): void
}>()

const bunchPrice = computed(() => {
  return props.bunch.bunch.products.reduce(
    (acc, curr) => acc + (productsStore.getProductByID(curr.id)?.price ?? 0),
    0
  )
})
</script>
<template>
  <div class="product-card">
    <div class="image-container" @click="controls ? emit('edit') : () => {}">
      <BunchPreview :products="bunch.bunch.products" :size="330" />
    </div>
    <div class="text-container">
      <div class="product-name">
        {{
          Array.from(new Set(bunch.bunch.products?.map((e) => e.product?.name)))
            .slice(0, 3)
            .join(', ') || 'Bunch'
        }}
      </div>
      <div class="product-price">
        {{ 'total price - $' + bunchPrice }}
      </div>
      <button v-if="controls" @click="emit('delete')" class="btn product-name">Delete</button>
    </div>
  </div>
</template>

<style scoped>
.product-card {
  width: 350px;
  height: 490px;
  display: flex;
  flex-direction: column;
  align-items: center;
  border-radius: 10px;
  background: #f9eff2;
}
.product-card:hover {
  box-shadow: 1px 2px 3px 0px rgba(55, 0, 23, 0.1);
}
.image-container {
  width: 330px;
  height: 330px;
  border-radius: 7px;
  background: #fff;
  margin-top: 20px;
  margin-bottom: 10px;
}
.product-image {
  width: 100%;
  height: 100%;
  object-fit: cover;
  border-radius: 7px;
}
.text-container {
  width: 330px;
  height: 60px;
}
.product-name {
  color: #370017;
  text-align: center;
  text-overflow: ellipsis;
  font-family: Montserrat;
  font-size: 20px;
  font-style: normal;
  font-weight: 500;
  line-height: normal;
  text-transform: uppercase;
  margin-bottom: 5px;
}
.product-price {
  color: #370017;
  text-align: center;
  font-family: Montserrat;
  font-size: 20px;
  font-style: normal;
  font-weight: 300;
  line-height: normal;
  text-transform: uppercase;
  margin: 0 auto 5px auto;
}
.btn {
  width: 250px;
  height: 50px;
  border-radius: 25px;
  background: #fff;
  margin: 10px 40px 5px 40px;
  padding: 0;
  border: none;
  outline: none;
  box-shadow: none;
}
</style>
