<template>
	<div ref="listEl" class="bunch-container">
		<VueDragResize
			v-for="(product, index) in products"
			:key="index"
			:w="50"
			:h="50"
			:x="product.x"
			:y="product.y"
			:parentW="listWidth"
			:parentH="listHeight"
			:minw="50"
			:minh="50"
			:isDraggable="true"
			:isResizable="false"
			:parentLimitation="true"
			:snapToGrid="false"
			:aspectRatio="true"
			:z="10"
			contentClass="bunch-container__flower"
			v-on:dragging="changePosition($event, index)"
		>
			<div class="product-card">
				<img :src="product.product?.image" />
			</div>
		</VueDragResize>
	</div>
</template>
<script setup lang="ts">
import { Bunch } from "@/types";
import { computed, onMounted, onUnmounted, ref, type PropType } from "vue";
import { useProductsStore } from "@/stores/products";

const productsStore = useProductsStore();

const props = defineProps({
	products: Array as PropType<Bunch["products"]>,
});
const emit = defineEmits<{
	(event: "update", products: Bunch["products"] | null): void;
}>();

const listEl = ref();
const listWidth = ref(0);
const listHeight = ref(0);

const products = computed(() => {
	return props.products?.map((product) => ({
		...product,
		product: productsStore.products.find((p) => p.id === product.id),
	}));
});

const changePosition = (
	e: { left: number; top: number; width: number; height: number },
	index: number
) => {
	emit(
		"update",
		products.value?.map((product, i) => {
			if (i === index) {
				return {
					...product,
					x: e.left,
					y: e.top,
				};
			}
			return product;
		}) ?? null
	);
};
const resizeListener = () => {
	listWidth.value = listEl.value.clientWidth;
	listHeight.value = listEl.value.clientHeight;
};
onMounted(() => {
	resizeListener();
	window.addEventListener("resize", resizeListener);
});
onUnmounted(() => {
	window.removeEventListener("resize", resizeListener);
});
</script>
<style scoped>
.bunch-container {
	width: 400px;
	height: 400px;
	display: flex;
	flex-wrap: wrap;
	justify-content: center;
	position: relative;
}
.product-card {
	width: 50px;
	height: 50px;
	background-color: white;
}
.bunch-container img {
	width: 100%;
	height: 100%;
	object-fit: cover;
}
</style>
