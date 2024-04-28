<template>
  <h1>Корзина</h1>
  <div>
    <div v-for="elem in cart" :key="elem.productID">
      <RouterLink :to="`/flower/${elem.productID}`">
        <img v-if="elem.product.image" :src="elem.product.image" />
        <img
          v-else
          src="https://via.placeholder.com/350x400?text=No+image"
          alt="Default"
        />
      </RouterLink>
      <div>
        <h2>{{ elem.product.name }}</h2>
        <p>{{ elem.product.price }}</p>
        <p>{{ elem.quantity }}</p>
      </div>
    </div>
  </div>
</template>
<script setup lang="ts">
import { useProductsStore } from "../stores/products";
import { computed } from "vue";
const productsStore = useProductsStore();

const cart = computed(() =>
  productsStore.carted.content.map((cartElem) => {
    return {
      ...cartElem,
      product: productsStore.products.find(
        (product) => product.id === cartElem.productID
      )!,
    };
  })
);
</script>
