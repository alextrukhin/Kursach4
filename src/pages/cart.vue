<template>
	<div class="buy-container">
		<h1>Total price of cart - ${{ totalPrice }}</h1>
		<RouterLink to="/checkout">
			<div class="buy-button"><h2>Checkout</h2></div>
		</RouterLink>
	</div>
	<div class="container">
		<div v-for="elem in cart" :key="elem.productID">
			<RouterLink :to="`/flower/${elem.productID}`">
				<CartCard
					:photoUrl="elem.product.image || ''"
					:name="elem.product.name"
					:price="elem.product.price"
					:quantity="elem.quantity"
				/>
			</RouterLink>
		</div>
	</div>
</template>
<script setup lang="ts">
import { useProductsStore } from "../stores/products";
import { computed } from "vue";
import CartCard from "../components/CartCard.vue";
const productsStore = useProductsStore();

const cart = computed(() =>
	productsStore.carted.content.map((cartElem) => {
		return {
			...cartElem,
			product: productsStore.products.find(
				(product) => product.id === cartElem.productID
			)!,
		};
	})
);
const totalPrice = computed(() => {
	return cart.value.reduce(
		(sum, item) => sum + item.product.price * item.quantity,
		0
	);
});
productsStore.carted.content.map((cartElem) => {
	return {
		...cartElem,
		product: productsStore.products.find(
			(product) => product.id === cartElem.productID
		)!,
	};
});
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
	flex-wrap: wrap;
	justify-content: flex-start;
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
