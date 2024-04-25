<template>
  <h1>Каталог</h1>
  <div class="products-container">
    <div
      v-for="product in productsStore.products"
      :key="product.id"
      class="product-card"
    >
      <RouterLink :to="`/flower/${product.id}`">
        <CatalogCard
          :photoUrl="product.image || ''"
          :name="product.name"
          :price="product.price.toString()"
        />
      </RouterLink>
    </div>
  </div>
</template>
<script setup lang="ts">
import { useProductsStore } from "../stores/products";
import CatalogCard from "../components/CatalogCard.vue";
const productsStore = useProductsStore();
</script>
<style scoped>
.products-container {
  display: flex;
  flex-wrap: wrap;
  justify-content: flex-start;
  width: 1200px;
  margin: 0 auto;
  row-gap: 40px;
  column-gap: 75px;
  margin-bottom: 50px;
}
</style>
