import { ref } from "vue";
import { defineStore } from "pinia";
import type { Order } from "../types";

export const useOrdersStore = defineStore("orders", () => {
	const ordersFetched = ref(false);
	const orders = ref<Order[]>([]);

	async function init() {
		// fetch orders here
		orders.value = [
			{
				id: 1,
				products: [
					{ productID: 1, quantity: 1 },
					{ productID: 2, quantity: 2 },
					{ productID: 3, quantity: 3 },
				],
				bunches: [],
				status: "pending",
				total: 30,
				createdAt: new Date().valueOf(),
				lastStatusChange: new Date().valueOf(),

				client_firstname: "John",
				client_lastname: "Doe",
				client_address: "1234 Elm Street",
				client_phone: "555-555-5555",
				client_email: "sth@example.com",
			},
			{
				id: 2,
				products: [
					{ productID: 1, quantity: 1 },
					{ productID: 2, quantity: 2 },
					{ productID: 3, quantity: 3 },
				],
				bunches: [],
				status: "pending",
				total: 50,
				createdAt: new Date().valueOf(),
				lastStatusChange: new Date().valueOf(),

				client_firstname: "John",
				client_lastname: "Doe",
				client_address: "1234 Elm Street",
				client_phone: "555-555-5555",
				client_email: "sth@example.com",
			},
		];
		ordersFetched.value = true;
	}

	async function updateOrder(order: Order) {
		// update order here
		orders.value = orders.value.map((o) => (o.id === order.id ? order : o));
	}

	return {
		init,
		orders,
		ordersFetched,
		updateOrder,
	};
});
