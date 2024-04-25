<template>
  <h1>Кvitka</h1>
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
