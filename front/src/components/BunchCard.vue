<template>
	<div v-if="bunch" class="bunch-card-wrapper">
		<div v-if="!bunch.products" class="product-left"></div>
		<div v-else-if="bunch.products.length >= 4" class="product-left-4">
			<img :src="bunch.products[0].product?.image" />
			<img :src="bunch.products[1].product?.image" />
			<img :src="bunch.products[2].product?.image" />
			<img :src="bunch.products[3].product?.image" />
		</div>
		<div v-else-if="bunch.products.length >= 2" class="product-left-2">
			<img :src="bunch.products[0].product?.image" />
			<img :src="bunch.products[1].product?.image" />
		</div>
		<div class="product-center">
			<h3>
				{{ bunch.products?.map((e) => e.product?.name).join(", ") }}
			</h3>
		</div>
		<div class="product-right">
			<button @click="emit('edit')">Edit</button>
			<button @click="emit('delete')">Delete</button>
		</div>
	</div>
</template>
<script setup lang="ts">
import { Bunch, Product } from "@/types";
import { type PropType } from "vue";

defineProps({
	bunch: {
		type: Object as PropType<
			Bunch & {
				products: Array<
					NonNullable<Bunch["products"]>[number] & {
						product: Product;
					}
				>;
			}
		>,
	},
	controls: { type: Boolean, default: true },
});
const emit = defineEmits<{
	(event: "edit"): void;
	(event: "delete"): void;
}>();
</script>
<style scoped>
.bunch-card-wrapper-4 {
	width: 60px;
	height: 60px;
	display: flex;
	flex-wrap: wrap;
}
.product-left-4 img {
	width: 30px;
	height: 30px;
	object-fit: cover;
}
.product-left-2 {
	width: 60px;
	height: 30px;
	display: flex;
	object-fit: cover;
}
.product-left {
	display: flex;
	flex-direction: column;
	gap: 5px;
}
</style>
