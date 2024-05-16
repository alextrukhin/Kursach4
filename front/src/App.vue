<script setup lang="ts">
import { RouterView } from "vue-router";
import Header from "./components/Header.vue";
import Footer from "./components/Footer.vue";

import { useProductsStore } from "./stores/products";

const productsStore = useProductsStore();

productsStore.init();
</script>

<template>
	<Header></Header>
	<main>
		<RouterView />
	</main>
	<Footer></Footer>
</template>
<style scoped>
main {
	flex-grow: 1;
}
</style>
