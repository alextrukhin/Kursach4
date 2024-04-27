<template>
  <div v-if="product" class="container">
    <div class="photo-container">
      <div class="photo">
        <img
          v-if="product.image"
          :src="product.image"
          :alt="product.name"
          class="product-image"
        />
        <img
          v-else
          src="https://via.placeholder.com/350x400?text=No+image"
          alt="Default"
          class="product-image"
        />
      </div>
    </div>
    <div class="info-container"></div>
  </div>
  <div>
    <div v-if="product">
      {{ product.name }}
      <button
        v-if="!product.carted"
        @click="productsStore.cartProduct(product!.id)"
      >
        Add to cart
      </button>
      <button v-else @click="productsStore.uncartProduct(product!.id)">
        Remove from cart
      </button>
      <div v-if="product.carted">
        Quantity:
        {{ product!.quantity }}
        <button
          @click="
            productsStore.cartChangeProductQuantity(
              product!.id,
              product!.quantity + 1
            )
          "
        >
          +
        </button>
        <button
          @click="
            productsStore.cartChangeProductQuantity(
              product!.id,
              product!.quantity - 1
            )
          "
        >
          -
        </button>
      </div>
    </div>
    <div v-else-if="product === null">not found</div>
    <div v-else>loading products...</div>
  </div>
</template>
<script setup lang="ts">
import { useRoute } from "vue-router";
import { useProductsStore } from "../stores/products";
import { computed } from "vue";

const productsStore = useProductsStore();
const route = useRoute();

const product = computed(() => {
  return productsStore.getProductByID(parseInt(route.params.id.toString()));
});
</script>
<style scoped>
.container {
  width: 1200px;
  margin: 150px auto 75px auto;
  display: flex;
  flex-direction: row;
  justify-content: start;
}
.photo-container {
  width: 600px;
  height: 500px;
  border-radius: 10px;
  background: #f9eff2;
}
</style>
