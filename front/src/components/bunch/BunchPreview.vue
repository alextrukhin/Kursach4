<template>
	<div
		class="bunch-preview"
		:style="{
			width: `${size}px`,
			height: `${size}px`,
		}"
	>
		<img
			v-for="(product, index) in products"
			:key="`${product.id}-${index}`"
			:src="product?.product?.image_single ?? product?.product?.image"
			:style="{
				left: `${product.x * (size / canvasSize)}px`,
				top: `${product.y * (size / canvasSize)}px`,
				width: `${size / 8}px`,
				height: `${size / 8}px`,
			}"
		/>
	</div>
</template>
<script setup lang="ts">
import { Bunch, Product } from "@/types";

const props = defineProps({
	products: Array<
		NonNullable<Bunch["products"]>[number] & {
			product: Product;
		}
	>,
	size: {
		type: Number,
		default: 64,
	},
	canvasSize: {
		type: Number,
		default: 400,
	},
});
</script>
<style scoped>
.bunch-preview {
	position: relative;
}
.bunch-preview img {
	position: absolute;
	object-fit: cover;
}
</style>
