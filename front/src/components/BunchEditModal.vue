<template>
	<BaseModal>
		<div class="bunch-edit-modal">
			<div class="bunch-edit-modal__left">
				<BunchContainer
					:products="bunchLocal.products"
					@update="updateProductsDebounced"
				/>
			</div>
			<div class="bunch-edit-modal__right">
				<div
					v-for="product in productsStore.products"
					@click="addProduct(product)"
				>
					<div>{{ product.name }}</div>
					<div>{{ product.price }}</div>
				</div>
			</div>
		</div>
	</BaseModal>
</template>
<script setup lang="ts">
import { type PropType, ref, watch } from "vue";
import BaseModal from "./BaseModal.vue";
import BunchContainer from "./BunchContainer.vue";
import { debounce } from "perfect-debounce";

import { useProductsStore } from "@/stores/products";
import { Bunch, Product } from "@/types";

const productsStore = useProductsStore();

const props = defineProps({
	bunch: {
		type: Object as PropType<Bunch>,
		required: true,
	},
});
const emit = defineEmits<{
	(event: "close"): void;
	(event: "update", bunch: Bunch): void;
}>();

const bunchLocal = ref(props.bunch);

const addProduct = (product: Product) => {
	bunchLocal.value.products?.push({ id: product.id, x: 0, y: 0 });
};

const updateProducts = (products: Bunch["products"]) => {
	bunchLocal.value.products = products;
	// emit("update", bunchLocal.value);
};
const updateProductsDebounced = debounce(updateProducts, 200);

watch(
	() => props.bunch,
	(newVal) => {
		bunchLocal.value = newVal;
	}
);
</script>
<style scoped>
.bunch-edit-modal {
	display: flex;
	flex-direction: row;
	gap: 12px;
	background-color: white;
	padding: 20px;
	border-radius: 20px;
	color: black;
}
.bunch-edit-modal__left {
	display: flex;
	align-items: center;
	justify-content: center;
}
</style>
