<template>
  <h1>Букет</h1>
  <div>
    <h2>To be added...</h2>
  </div>
</template>
<script setup lang="ts">
import { useRoute } from "vue-router";
import { useProductsStore } from "../stores/products";
import { computed } from "vue";

const productsStore = useProductsStore();
const route = useRoute();

const bunch = computed(() => {
  return null;
});
</script>
