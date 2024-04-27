<template>
	<div id="wrapper">
		<div id="layout">
			<ul>
				<li
					v-for="order in ordersFiltered"
					:key="order.id"
					:class="{ active: order.id === orderId }"
				>
					<RouterLink :to="`/admin/${order.id}`">
						<p>{{ order.id }}</p>
						<p>{{ order.status }}</p>
						<p>{{ order.total }}</p>
					</RouterLink>
				</li>
			</ul>
			<div id="second-half">
				<div id="product" v-if="order">
					<h2>{{ order.id }}</h2>
					<p>{{ order.status }}</p>
					<p>{{ order.total }}</p>
				</div>
				<div v-else></div>
			</div>
		</div>
	</div>
</template>
<script setup lang="ts">
import { computed, ref } from "vue";
import { useOrdersStore } from "../stores/orders";
import { useRoute } from "vue-router";

const ordersStore = useOrdersStore();
const route = useRoute();

ordersStore.init();

const status = ref("new");

const ordersFiltered = computed(() => ordersStore.orders);
const orderId = computed(() =>
	route.params.order ? parseInt(route.params.order.toString()) : null
);
const order = computed(() =>
	ordersStore.orders.find((order) => order.id === orderId.value)
);
</script>
<style scoped>
#wrapper {
	width: 100%;
	display: flex;
	justify-content: center;
}
#layout {
	width: 100%;
	max-width: 1200px;
	display: flex;
	flex-direction: row;
}
ul {
	width: 200px;
	display: flex;
	flex-direction: column;
	list-style-type: none;
	padding: 0;
}
li {
	width: 100%;
	height: 50px;
}
#second-half {
	width: 100%;
	display: flex;
	justify-content: space-between;
	padding: 10px;
	flex-grow: 1;
}
</style>
