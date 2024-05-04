<template>
	<div class="buttons-container">
		<div class="container">
			<div class="color red"></div>
			<div class="color pink"></div>
			<div class="color orange"></div>
			<div class="color yellow"></div>
		</div>
		<div class="">
			<!-- <div class="seasonal-filter">
        <h2>seasonal flowers</h2>
      </div>
      <div class="filter-btn">
        <img style="height: 40px; margin: auto" src="../../Filter.svg" />
      </div> -->
			<!-- <VSelect
        :options="seasonOptions"
        :reduce="(option: (typeof seasonOptions)[number]) => option.title"
        label="title"
        placeholder="Season"
      >
        <template v-slot:option="option">
          <span>{{ option.icon }}</span>
          {{ option.title }}
        </template>
      </VSelect> -->
			<select>
				<option value="" disabled>Season</option>
				<option v-for="option in seasonOptions" :value="option.title">
					{{ option.icon }} {{ option.title }}
				</option>
			</select>
		</div>
		<div class="container search">
			<input v-model="search" placeholder="Search" type="text" />
			<img class="search-icon" src="../../SearchPicture.svg" />
		</div>
	</div>
	<div class="products-container">
		<div
			v-for="product in filteredProducts"
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
import { computed, ref } from "vue";
const productsStore = useProductsStore();

const icons: Record<string, string> = {
	Summer: "🌞",
	Spring: "🌸",
	"Growing in a greenhouse": "🏡",
};

const season = ref("");
const color = ref("");
const search = ref("");

const seasonOptions = computed(() =>
	Array.from(
		new Set(productsStore.products.map((product) => product.seasoning))
	).map((season) => ({
		title: season,
		icon: icons[season] ?? null,
	}))
);
const filteredProducts = computed(() => {
	return productsStore.products.filter((product) => {
		const isSeason = !season.value || product.seasoning === season.value;
		const isSearch =
			!search.value ||
			product.name.toLowerCase().includes(search.value.toLowerCase());
		const isColor = !color.value || product.color === color.value;
		return isSeason && isSearch && isColor;
	});
});
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
	position: relative;
	width: 350px;
	height: 70px;
	border-radius: 35px;
	background-color: #efc1cf;
	padding: 0;
	display: flex;
	justify-content: center;
	align-items: center;
}
.search:hover {
	box-shadow: 1px 2px 3px 0px rgba(55, 0, 23, 0.15);
	cursor: pointer;
}
input {
	background: rgba(249, 239, 242, 0.5);
	border: none;
	height: 60px;
	width: 320px;
	margin: 10px 25px 10px 25px;
	border-radius: 30px;
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
	right: 35px;
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
