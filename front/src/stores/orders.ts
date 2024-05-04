import { ref } from "vue";
import { defineStore } from "pinia";
import type { Order } from "../types";

export const useOrdersStore = defineStore("orders", () => {
	const ordersFetched = ref(false);
	const orders = ref<Order[]>([]);

	async function init() {
		// fetch orders here
		const fetchedOrders = await (
			await fetch(`http://localhost:8080/bunches`)
		).json();
		orders.value = fetchedOrders;
		// orders.value = [
		// 	{
		// 		id: 1,
		// 		products: [
		// 			{ productID: 1, quantity: 1 },
		// 			{ productID: 2, quantity: 2 },
		// 			{ productID: 3, quantity: 3 },
		// 		],
		// 		bunches: [],
		// 		status: "pending",
		// 		total: 30,
		// 		createdAt: new Date().valueOf(),
		// 		lastStatusChange: new Date().valueOf(),

		// 		client_firstname: "John",
		// 		client_lastname: "Doe",
		// 		client_address: "1234 Elm Street",
		// 		client_phone: "555-555-5555",
		// 		client_email: "sth@example.com",
		// 		client_comments: "Please deliver before 5pm",
		// 	},
		// 	{
		// 		id: 2,
		// 		products: [
		// 			{ productID: 1, quantity: 1 },
		// 			{ productID: 2, quantity: 2 },
		// 			{ productID: 3, quantity: 3 },
		// 		],
		// 		bunches: [],
		// 		status: "pending",
		// 		total: 50,
		// 		createdAt: new Date().valueOf(),
		// 		lastStatusChange: new Date().valueOf(),

		// 		client_firstname: "John",
		// 		client_lastname: "Doe",
		// 		client_address: "1234 Elm Street",
		// 		client_phone: "555-555-5555",
		// 		client_email: "sth@example.com",
		// 		client_comments: "Please deliver before 5pm",
		// 	},
		// ];
		ordersFetched.value = true;
	}

	async function updateOrder(order: Order) {
		// // update order here
		// orders.value = orders.value.map((o) => (o.id === order.id ? order : o));
		// await fetch(`http://localhost:8080/bunches`, {});
	}

	return {
		init,
		orders,
		ordersFetched,
		updateOrder,
	};
});
