<template>
	<div class="buy-container">
		<h1>Total price of cart - ${{ productsStore.cartSum }}</h1>
		<RouterLink to="/checkout">
			<div class="buy-button"><h2>Checkout</h2></div>
		</RouterLink>
	</div>
	<h2>Flowers</h2>
	<div class="container">
		<div v-for="elem in products" :key="elem.productId">
			<CartCard
				:id="elem.productId"
				:photoUrl="elem.product?.image || ''"
				:name="elem.product?.name"
				:price="elem.product?.price"
				:quantity="elem.quantity"
			/>
		</div>
	</div>
	<h2>Bunches</h2>
	<div class="container">
		<div v-for="(elem, index) in bunches" :key="index">
			<BunchCartCard
				:bunch="elem"
				@delete="deleteBunch(index)"
				@edit="editBunch(index)"
			/>
		</div>
	</div>
	<button @click="createBunch">New bunch</button>
	<BunchEditModal
		v-if="!!bunchToEdit"
		:bunch="bunchToEdit"
		@update="updateBunch"
		@close="bunchToEdit = null"
	/>
</template>
<script setup lang="ts">
import { useProductsStore } from "../stores/products";
import { computed, ref } from "vue";
import CartCard from "../components/CartCard.vue";
import BunchCartCard from "@/components/BunchCartCard.vue";
import BunchEditModal from "@/components/bunch/BunchEditModal.vue";
import { Bunch } from "@/types";

const productsStore = useProductsStore();

const bunchToEditIndex = ref<number | null>(null);
const bunchToEdit = ref<Bunch | null>(null);

const products = computed(() =>
	productsStore.carted.content.products.map((cartElem) => {
		return {
			...cartElem,
			product: productsStore.products.find(
				(product) => product.id === cartElem.productId
			),
		};
	})
);
const bunches = computed(() =>
	productsStore.carted.content.bunches.map((bunch) => {
		return {
			...bunch,
			products:
				bunch.bunch.products?.map((product) => {
					return {
						...product,
						product: productsStore.products.find(
							(p) => p.id === product.id
						),
					};
				}) ?? null,
		};
	})
);

const createBunch = () => {
	productsStore.carted.content.bunches.push({
		bunch: { id: null, products: [] },
		quantity: 1,
	});
};
const deleteBunch = (index: number) => {
	productsStore.carted.content.bunches.splice(index, 1);
};
const editBunch = (index: number) => {
	bunchToEditIndex.value = index;
	bunchToEdit.value = productsStore.carted.content.bunches[index].bunch;
};
const updateBunch = (bunch: Bunch) => {
	productsStore.carted.content.bunches[bunchToEditIndex.value!].bunch = bunch;
	bunchToEdit.value = null;
};
</script>
<style scoped>
.buy-container {
	width: 1200px;
	margin: 40px auto 40px auto;
	display: flex;
	justify-content: space-between;
	padding: 0;
}
.container {
	display: flex;
	flex-direction: row;
	flex-wrap: wrap;
	width: 1200px;
	margin: 0 auto;
	row-gap: 40px;
	column-gap: 75px;
	margin-bottom: 50px;
}
h1 {
	color: #370017;
	font-family: "Yeseva One";
	font-size: 40px;
	font-style: normal;
	font-weight: 400;
	line-height: normal;
	text-transform: uppercase;
	padding: 0;
	margin: 15px 0 0 0;
}
.buy-button {
	width: 350px;
	height: 70px;
	border-radius: 35px;
	background: #efc1cf;
	padding: 0;
	display: flex;
	justify-content: center;
	align-items: center;
}
.buy-button:hover {
	box-shadow: 1px 2px 3px 0px rgba(55, 0, 23, 0.15);
	cursor: pointer;
}
h2 {
	color: #370017;
	text-align: center;
	font-family: Montserrat;
	font-size: 25px;
	font-style: normal;
	font-weight: 500;
	line-height: normal;
	text-transform: uppercase;
}
</style>
