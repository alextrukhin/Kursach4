<template>
  <div class="buttons-container">
    <div class="container" style="gap: 8px; padding: 8px">
      <div
        v-for="colorsOption in colorsOptions"
        :key="colorsOption.value"
        class="color"
        :style="{ 'background-color': colorsOption.label }"
        @click="color = color === colorsOption.value ? null : colorsOption.value"
      >
        <svg
          v-if="colorsOption.value === color"
          xmlns="http://www.w3.org/2000/svg"
          width="24"
          height="24"
          viewBox="0 0 24 24"
          fill="none"
          stroke="currentColor"
          stroke-width="2"
          stroke-linecap="round"
          stroke-linejoin="round"
        >
          <path d="M20 6 9 17l-5-5" />
        </svg>
      </div>
    </div>
    <div class="container-menu">
      <select v-model="season" class="seasonal-filter">
        <option value="">All seasons</option>
        <option v-for="option in seasonOptions" :value="option.value">
          {{ option.label }}
        </option>
      </select>
      <div class="arrow"></div>
    </div>
    <div class="container search">
      <input v-model="search" placeholder="Search" type="text" />
      <img class="search-icon" src="../../SearchPicture.svg" />
    </div>
  </div>
  <div v-if="filteredProducts.length" class="products-container">
    <div v-for="product in filteredProducts" :key="product.id" class="product-card">
      <RouterLink :to="`/flower/${product.id}`">
        <CatalogCard
          :photoUrl="product.image || ''"
          :name="product.name"
          :price="product.price.toString()"
        />
      </RouterLink>
    </div>
  </div>
  <div v-else>
    <h2>No products found</h2>
  </div>
</template>
<script setup lang="ts">
import { useProductsStore } from '../stores/products'
import CatalogCard from '../components/CatalogCard.vue'
import { computed, ref } from 'vue'
const productsStore = useProductsStore()

const labels: Record<string, string> = {
  summer: '🌞 Summer',
  spring: '🌸 Spring',
  greenhouse: '🏡 Greenhouse'
}
const colors: Record<string, string> = {
  Red: '#ef6363',
  Pink: '#fd84be',
  Orange: '#ffad80',
  Yellow: '#ffe380',
  White: '#FFFFFF',
  Green: '#89E09C'
}

const season = ref<string | null>('')
const color = ref<string | null>('')
const search = ref('')

const seasonOptions = computed(() =>
  Array.from(new Set(productsStore.products.map((product) => product.seasoning))).map((season) => ({
    value: season,
    label: labels[season] ?? null
  }))
)
const colorsOptions = computed(() =>
  Array.from(new Set(productsStore.products.map((product) => product.color))).map((color) => ({
    value: color,
    label: colors[color]
  }))
)
const filteredProducts = computed(() => {
  return productsStore.products.filter((product) => {
    const isSeason = !season.value || product.seasoning === season.value
    const isSearch =
      !search.value || product.name.toLowerCase().includes(search.value.toLowerCase())
    const isColor = !color.value || product.color === color.value
    return isSeason && isSearch && isColor
  })
})
</script>
<style scoped>
.buttons-container {
  width: 1200px;
  margin: 40px auto 40px auto;
  display: flex;
  justify-content: space-between;
  padding: 0;
}
.buttons-container .container {
  height: 70px;
  display: flex;
  flex-direction: row;
  justify-content: space-between;
}
.buttons-container .container .color {
  display: flex;
  width: min(60px, 100%);
  height: auto;
  flex-shrink: 1;
  aspect-ratio: 1 / 1;
}
.container {
  width: 350px;
  display: flex;
  justify-content: space-around;
  align-items: center;
  padding: 0;
  border-radius: 35px;
  background-color: #f9eff2;
}
.container-menu {
  position: relative;
  width: 350px;
  height: 70px;
  border-radius: 35px;
  background-color: #efc1cf;
  overflow: hidden;
}
.container-menu:hover {
  box-shadow: 1px 2px 3px 0px rgba(55, 0, 23, 0.15);
  cursor: pointer;
}
.color {
  width: 60px;
  height: 60px;
  display: flex;
  justify-content: center;
  align-items: center;
  border-radius: 50%;
}
.color:hover {
  box-shadow: 1px 2px 3px 0px rgba(55, 0, 23, 0.15);
  cursor: pointer;
}
.seasonal-filter {
  width: 100%;
  height: 100%;
  border: none;
  background-color: transparent;
  padding: 0;
  appearance: none;
  font-size: 20px;
  color: #370017;
  font-family: Montserrat;
  outline: none;
  padding-left: 20px;
  text-transform: uppercase;
}
.seasonal-filter:hover {
  box-shadow: 1px 2px 3px 0px rgba(55, 0, 23, 0.15);
  cursor: pointer;
}
.seasonal-filter option {
  background-color: #f9eff2;
}
.seasonal-filter option:checked {
  background-color: #f2d6de;
}
.seasonal-filter option:hover {
  background-color: #efc1cf;
}
.arrow {
  position: absolute;
  top: 20px;
  right: 20px;
  width: 30px;
  height: 100%;
  background-image: url('../../arrow.svg');
  background-size: contain;
  background-repeat: no-repeat;
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
  position: relative;
  width: 350px;
  height: 70px;
  border-radius: 35px;
  background-color: #efc1cf;
  padding: 0;
  display: flex;
  justify-content: left;
  align-items: center;
}
.search:hover {
  box-shadow: 1px 2px 3px 0px rgba(55, 0, 23, 0.15);
  cursor: pointer;
}
input {
  background: rgba(249, 239, 242, 0.5);
  border: none;
  height: 70px;
  width: 275px;
  margin: 0px;
  border-radius: 35px;
  padding: 0 60px 0 30px;
  font-size: 20px;
  color: #370017;
  font-family: Montserrat;
  outline: none;
}
input:focus {
  border: 1px solid rgba(249, 239, 242, 1);
}
input::placeholder {
  font-size: 20px;
  color: #370017;
  font-family: Montserrat;
  text-transform: uppercase;
}
.search img.search-icon {
  position: absolute;
  right: 25px;
  height: 35px;
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
