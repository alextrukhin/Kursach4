<template>
	<div id="wrapper">
		<div id="layout">
			<div class="sidebar">
				<h2>Orders</h2>
				<ul>
					<li
						v-for="order in ordersFiltered"
						:key="order.id"
						:class="{ active: order.id === orderId }"
					>
						<RouterLink :to="`/admin/${order.id}`">
							<div class="left">
								<p>#{{ order.id }}</p>
							</div>
							<div class="center">
								<p>
									{{ order.client_firstname }}
									{{ order.client_lastname }}
								</p>
							</div>
							<div class="right">
								<p>{{ order.status }}</p>
							</div>
						</RouterLink>
					</li>
				</ul>
			</div>
			<div id="second-half">
				<div id="product" v-if="order">
					<h2>Order #{{ order.id }}</h2>
					<p>{{ order.status }}</p>
					<p>{{ order.total }}</p>
				</div>
				<div v-else>order not selected</div>
			</div>
		</div>
	</div>
</template>
<script setup lang="ts">
import { computed, ref, watch } from "vue";
import { useOrdersStore } from "../stores/orders";
import { useRoute } from "vue-router";
import type { Order } from "../types";

const ordersStore = useOrdersStore();
const route = useRoute();

ordersStore.init();

const order = ref<Order | null>(null);

const ordersFiltered = computed(() => ordersStore.orders);
const orderId = computed(() =>
	route.params.order ? parseInt(route.params.order.toString()) : null
);

watch(
	orderId,
	(newOrderId) => {
		const orderData = ordersStore.orders.find(
			(order) => order.id === newOrderId
		);
		order.value = orderData ? { ...orderData } : null;
	},
	{ immediate: true }
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
.sidebar {
	width: 300px;
	display: flex;
	flex-direction: column;
}
ul {
	width: 100%;
	display: flex;
	flex-direction: column;
	list-style-type: none;
	padding: 0;
}
li {
	width: 100%;
	height: 50px;
}
li a {
	width: 100%;
	height: 100%;
	display: flex;
	flex-direction: row;
	justify-content: space-between;
	align-items: center;
	gap: 8px;
}
li .center {
	flex-grow: 1;
}
#second-half {
	width: 100%;
	display: flex;
	justify-content: space-between;
	padding: 10px;
	flex-grow: 1;
	padding-left: 24px;
}
</style>
