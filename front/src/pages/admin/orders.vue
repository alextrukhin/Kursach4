<template>
	<div id="wrapper">
		<Layout :items="ordersFiltered">
			<template #item="{ id, client_firstname, client_lastname, status }">
				<RouterLink :to="`/admin/orders/${id}`">
					<div class="left">
						<p>#{{ id }}</p>
					</div>
					<div class="center">
						<p>
							{{ client_firstname }}
							{{ client_lastname }}
						</p>
					</div>
					<div class="right">
						<p>{{ status }}</p>
					</div>
				</RouterLink>
			</template>
			<template #content>
				<form id="product" v-if="order">
					<h2>Order #{{ order.id }}</h2>
					<div>
						<label for="client_firstname">First name</label>
						<input
							id="client_firstname"
							type="text"
							v-model="order.client_firstname"
							placeholder="First name"
						/>
					</div>
					<div>
						<label for="client_lastname">Last name</label>
						<input
							id="client_lastname"
							type="text"
							v-model="order.client_lastname"
							placeholder="Last name"
						/>
					</div>
					<div>
						<label for="client_address">Address</label>
						<input
							id="client_address"
							type="text"
							v-model="order.client_address"
							placeholder="Address"
						/>
					</div>
					<div>
						<label for="client_phone">Phone</label>
						<input
							id="client_phone"
							type="tel"
							v-model="order.client_phone"
							placeholder="Phone"
						/>
					</div>
					<div>
						<label for="client_email">Email</label>
						<input
							id="client_email"
							type="email"
							v-model="order.client_email"
							placeholder="Email"
						/>
					</div>
					<button type="submit">Update</button>
				</form>
				<div v-else>order not selected</div>
			</template>
		</Layout>
	</div>
</template>
<script setup lang="ts">
import { computed, ref, watch } from "vue";
import Layout from "./shared.vue";
import { useOrdersStore } from "../../stores/orders";
import { useRoute } from "vue-router";
import type { Order } from "../../types";

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
#product {
	width: 100%;
	display: flex;
	flex-direction: column;
}
</style>
