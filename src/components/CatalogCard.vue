<script setup lang="ts">
defineProps({
	photoUrl: String,
	name: String,
	price: String,
});
</script>
<template>
	<div class="product-card">
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
			<div class="product-price">{{ "price per 1 - $" + price }}</div>
		</div>
	</div>
</template>

<style scoped>
.product-card {
	width: 350px;
	height: 400px;
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
	font-weight: 400;
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
}
</style>
