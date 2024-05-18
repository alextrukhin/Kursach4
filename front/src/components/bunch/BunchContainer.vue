<template>
  <div
    ref="listEl"
    :key="products?.length"
    class="bunch-container"
    :style="{ width: `${size}px`, height: `${size}px` }"
  >
    <VueDragResize
      v-for="(product, index) in products"
      :key="`${product}_${index}`"
      :w="50"
      :h="50"
      :x="product.x"
      :y="product.y"
      :parentW="listWidth"
      :parentH="listHeight"
      :minw="50"
      :minh="50"
      :isDraggable="true"
      :isResizable="false"
      :parentLimitation="true"
      :snapToGrid="false"
      :aspectRatio="true"
      :z="10"
      contentClass="bunch-container__flower"
      @dragstop="applyPosition($event, index)"
    >
      <div class="product-card">
        <img :src="product.product?.image_single ?? product.product?.image" />
      </div>
    </VueDragResize>
  </div>
</template>
<script setup lang="ts">
import { Bunch } from '@/types'
import { computed, onMounted, onUnmounted, ref, type PropType } from 'vue'
import { useProductsStore } from '@/stores/products'

const productsStore = useProductsStore()

const props = defineProps({
  products: Array as PropType<Bunch['products']>,
  size: {
    type: Number,
    default: 400
  }
})
const emit = defineEmits<{
  (event: 'update', products: Bunch['products'] | null): void
}>()

const listEl = ref()
const listWidth = ref(0)
const listHeight = ref(0)

const products = computed(() =>
  props.products?.map((product) => ({
    ...product,
    product: productsStore.products.find((p) => p.id === product.id)
  }))
)

const applyPosition = (
  e: { left: number; top: number; width: number; height: number },
  index: number
) => {
  if (
    (e.left === 0 && e.top === 0) ||
    (e.left === 400 - e.width - 2 && e.top === 400 - e.height - 2) ||
    (e.left === 0 && e.top === 400 - e.height - 2) ||
    (e.left === 400 - e.width - 2 && e.top === 0)
  ) {
    // Remove product if it's in the corners

    emit('update', products.value?.filter((_, i) => i !== index) ?? null)
    return
  }

  emit(
    'update',
    products.value?.map((product, i) => {
      if (i === index) {
        return {
          ...product,
          x: e.left,
          y: e.top
        }
      }
      return product
    }) ?? null
  )
}

const resizeListener = () => {
  listWidth.value = listEl.value.clientWidth
  listHeight.value = listEl.value.clientHeight
}

onMounted(() => {
  resizeListener()
  window.addEventListener('resize', resizeListener)
})
onUnmounted(() => {
  window.removeEventListener('resize', resizeListener)
})
</script>
<style scoped>
.bunch-container {
  display: flex;
  flex-wrap: wrap;
  justify-content: center;
  position: relative;
  border-radius: 10px;
  border: 1px solid #efc1cf;
}
.product-card {
  width: 50px;
  height: 50px;
}
.bunch-container img {
  width: 100%;
  height: 100%;
  object-fit: cover;
}
</style>
