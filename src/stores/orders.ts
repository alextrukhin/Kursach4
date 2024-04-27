import { ref } from "vue";
import { defineStore } from "pinia";
import { Order } from "../types";

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
				status: "pending",
				total: 30,
				createdAt: new Date().valueOf(),
			},
			{
				id: 2,
				products: [
					{ productID: 1, quantity: 1 },
					{ productID: 2, quantity: 2 },
					{ productID: 3, quantity: 3 },
				],
				status: "pending",
				total: 50,
				createdAt: new Date().valueOf(),
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
