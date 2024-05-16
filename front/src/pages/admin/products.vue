<template>
	<div id="wrapper">
		<RouterLink to="/admin">
			<button>Dashboard</button>
		</RouterLink>
		<Layout :items="ordersFiltered">
			<template #item="{ id, client_firstname, client_lastname, status }">
				<RouterLink :to="`/admin/products/${id}`">
					<div class="left">
						<p>#{{ id }}</p>
					</div>
					<div class="center">
						<p>
							{{ client_firstname }}
							{{ client_lastname }}
						</p>
					</div>
					<div class="right">
						<p>{{ status }}</p>
					</div>
				</RouterLink>
			</template>
			<template #content>
				<form id="product" v-if="product">
					<h2>Product #{{ product.id }}</h2>
					<div>
						<label for="client_firstname">Name</label>
						<input
							id="client_firstname"
							type="text"
							v-model="product.name"
							placeholder="First name"
						/>
					</div>
					<button type="submit">Update</button>
				</form>
				<div v-else>product not selected</div>
			</template>
		</Layout>
	</div>
</template>
<script setup lang="ts">
import { computed, ref, watch } from "vue";
import Layout from "./shared.vue";
import { useRoute } from "vue-router";
import type { Product } from "../../types";
import { useProductsStore } from "@/stores/products";

const productsStore = useProductsStore();
const route = useRoute();

const product = ref<Product | null>(null);

const ordersFiltered = computed(() => productsStore.products);
const productId = computed(() =>
	route.params.product ? parseInt(route.params.product.toString()) : null
);

watch(
	productId,
	(newProductId) => {
		const productData = productsStore.products.find(
			(product) => product.id === newProductId
		);
		product.value = productData ? { ...productData } : null;
	},
	{ immediate: true }
);
</script>
<style scoped>
#wrapper {
	width: 100%;
	display: flex;
	justify-content: center;
}
#product {
	width: 100%;
	display: flex;
	flex-direction: column;
}
</style>
