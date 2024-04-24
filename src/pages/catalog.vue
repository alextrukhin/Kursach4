<template>
	<h1>Каталог</h1>
	<div>
		<div v-for="product in productsStore.products" :key="product.id">
			{{ product.name }}
			<img v-if="product.image" :src="product.image" alt="Red dot" />
			<img v-else src="https://" alt="Default" />
		</div>
	</div>
</template>
<script setup lang="ts">
import { useProductsStore } from "../stores/products";

const productsStore = useProductsStore();
</script>
