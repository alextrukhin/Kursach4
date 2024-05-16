<template>
	<BaseModal>
		<div class="bunch-edit-modal">
			<div class="bunch-edit-modal__left">
				<BunchContainer
					:products="bunchLocal.products"
					@update="updateProductsDebounced"
				/>
				<span>Drag in corner to delete</span>
			</div>
			<div class="bunch-edit-modal__right">
				<div><button @click="emit('close')">Close</button></div>
				<div>
					<h3>Products</h3>
					<div
						v-for="product in flowers"
						@click="addProduct(product)"
						class="product-row"
					>
						<img :src="product.image" />
						<div style="flex-grow: 1">{{ product.name }}</div>
						<div>${{ product.price }}</div>
					</div>
				</div>
			</div>
		</div>
	</BaseModal>
</template>
<script setup lang="ts">
import { type PropType, ref, watch, computed } from "vue";
import BaseModal from "../BaseModal.vue";
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
}>();

const bunchLocal = ref(props.bunch);

const flowers = computed(() =>
	productsStore.products.filter((product) => product.type === "Flower")
);

const addProduct = (product: Product) => {
	console.log(product);
	bunchLocal.value.products?.push({ id: product.id, x: 0, y: 0 });
};

const updateProducts = (products: Bunch["products"]) => {
	bunchLocal.value.products = products;
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
	width: 800px;
	height: 800px;
	display: flex;
	flex-direction: row;
	gap: 12px;
	background-color: white;
	padding: 20px;
	border-radius: 20px;
	color: black;
	overflow: hidden;
}
.bunch-edit-modal__left {
	display: flex;
	flex-direction: column;
	align-items: center;
	justify-content: center;
}
.bunch-edit-modal__right {
	flex-grow: 1;
}
.product-row {
	display: flex;
	flex-direction: row;
	align-items: center;
	justify-content: space-between;
	gap: 8px;
	margin-top: 8px;
}
.product-row img {
	width: 50px;
	height: 50px;
	object-fit: cover;
}
</style>
