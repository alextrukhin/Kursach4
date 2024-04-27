<template>
  <div class="buttons-container">
    <div class="container">
      <div class="color red"></div>
      <div class="color pink"></div>
      <div class="color orange"></div>
      <div class="color yellow"></div>
    </div>
    <div class="container">
      <div class="seasonal-filter">
        <h2>seasonal flowers</h2>
      </div>
      <div class="filter-btn">
        <img style="height: 40px; margin: auto" src="../../Filter.svg" />
      </div>
    </div>
    <div class="container search">
      <div style="width: 285px">
        <h2 style="margin: auto; padding-left: 25px">search</h2>
      </div>

      <img
        style="height: 40px; margin: 0 25px 0 0"
        src="../../SearchPicture.svg"
      />
    </div>
  </div>
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
.buttons-container {
  width: 1200px;
  margin: 40px auto 40px auto;
  display: flex;
  justify-content: space-between;
  padding: 0;
}
.container {
  width: 350px;
  display: flex;
  justify-content: space-between;
  padding: 0;
}
.color {
  width: 70px;
  height: 70px;
  border-radius: 35px;
}
.color:hover {
  box-shadow: 1px 2px 3px 0px rgba(55, 0, 23, 0.15);
  cursor: pointer;
}
.red {
  background-color: #ef6363;
}
.pink {
  background-color: #fd84be;
}
.orange {
  background-color: #ffad80;
}
.yellow {
  background-color: #ffe380;
}
.filter-btn {
  width: 70px;
  height: 70px;
  border-radius: 35px;
  background-color: #efc1cf;
  padding: 0;
  display: flex;
  justify-content: center;
  align-items: center;
}
.filter-btn:hover {
  box-shadow: 1px 2px 3px 0px rgba(55, 0, 23, 0.15);
  cursor: pointer;
}
.seasonal-filter {
  width: 260px;
  height: 70px;
  border-radius: 35px;
  background-color: #efc1cf;
  padding: 0;
  display: flex;
  justify-content: center;
  align-items: center;
}
.seasonal-filter:hover {
  box-shadow: 1px 2px 3px 0px rgba(55, 0, 23, 0.15);
  cursor: pointer;
}
h2 {
  color: #370017;
  text-align: center;
  font-family: Montserrat;
  font-size: 20px;
  font-style: normal;
  font-weight: 400;
  line-height: normal;
  text-transform: uppercase;
}
.search {
  width: 350px;
  height: 70px;
  border-radius: 35px;
  background-color: #efc1cf;
  padding: 0;
  display: flex;
  justify-content: right;
  align-items: center;
}
.search:hover {
  box-shadow: 1px 2px 3px 0px rgba(55, 0, 23, 0.15);
  cursor: pointer;
}
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
