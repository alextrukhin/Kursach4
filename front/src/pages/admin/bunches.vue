<template>
	<div id="wrapper">
		<Layout :items="bunchesFiltered">
			<template #item="{ id, client_firstname, client_lastname, status }">
				<RouterLink :to="`/admin/bunches/${id}`">
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
				<form id="bunch" v-if="bunch">
					<h2>Bunch #{{ bunch.id }}</h2>
					<div>
						<label for="client_firstname">Name</label>
						<input
							id="client_firstname"
							type="text"
							v-model="bunch.name"
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
import type { Bunch } from "../../types";
import { useProductsStore } from "@/stores/products";

const productsStore = useProductsStore();
const route = useRoute();

const bunch = ref<Bunch | null>(null);

const bunchesFiltered = computed(() => productsStore.bunches);
const bunchId = computed(() =>
	route.params.bunch ? parseInt(route.params.bunch.toString()) : null
);

watch(
	bunchId,
	(newProductId) => {
		const productData = productsStore.bunches.find(
			(product) => product.id === newProductId
		);
		bunch.value = productData ? { ...productData } : null;
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
#bunches {
	width: 100%;
	display: flex;
	flex-direction: column;
}
</style>
