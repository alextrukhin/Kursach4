<script setup lang="ts">
import { useProductsStore } from "@/stores/products";
import { useRouter } from "vue-router";

const router = useRouter();
const productsStore = useProductsStore();

defineProps({
	id: {
		type: Number,
		required: true,
	},
	photoUrl: {
		type: String,
		required: false,
	},
	name: {
		type: String,
		required: true,
	},
	price: {
		type: Number,
		required: true,
	},
	quantity: {
		type: Number,
		required: true,
	},
});

const setQuantity = (id: number, quantity: number) => {
	productsStore.cartChangeProductQuantity(id, quantity);
};
</script>
<template>
	<div class="product-card" @click="router.push(`/flower/${id}`)">
		<div class="image-container">
			<img
				v-if="photoUrl"
				:src="photoUrl"
				:alt="name"
				class="product-image"
			/>
			<img
				v-else
				src="https://via.placeholder.com/350x400?text=No+image"
				alt="Default"
				class="product-image"
			/>
		</div>
		<div class="text-container">
			<div class="product-name">{{ name }}</div>
			<div class="product-price">
				{{ "total price - $" + price * quantity }}
			</div>
			<div class="product-price">
				<button @click.stop="setQuantity(id, quantity - 1)">-</button>
				{{ "quantity - " + quantity }}
				<button @click.stop="setQuantity(id, quantity + 1)">+</button>
			</div>
			<button @click.stop="productsStore.uncartProduct(id)">
				Remove
			</button>
		</div>
	</div>
</template>

<style scoped>
.product-card {
	width: 350px;
	height: 435px;
	display: flex;
	flex-direction: column;
	align-items: center;
	border-radius: 10px;
	background: #f9eff2;
}
.product-card:hover {
	box-shadow: 1px 2px 3px 0px rgba(55, 0, 23, 0.1);
}
.image-container {
	width: 330px;
	height: 300px;
	border-radius: 7px;
	background: #fff;
	margin-top: 20px;
	margin-bottom: 10px;
}
.product-image {
	width: 100%;
	height: 100%;
	object-fit: cover;
	border-radius: 7px;
}
.text-container {
	width: 330px;
	height: 60px;
}
.product-name {
	color: #370017;
	text-align: center;
	text-overflow: ellipsis;
	font-family: Montserrat;
	font-size: 20px;
	font-style: normal;
	font-weight: 500;
	line-height: normal;
	text-transform: uppercase;
	margin-bottom: 5px;
}
.product-price {
	color: #370017;
	text-align: center;
	font-family: Montserrat;
	font-size: 20px;
	font-style: normal;
	font-weight: 300;
	line-height: normal;
	text-transform: uppercase;
	margin: 0 auto 5px auto;
}
</style>
