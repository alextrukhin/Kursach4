import { createWebHistory, createRouter } from "vue-router";

import MainPage from "./pages/main.vue";
import CartPage from "./pages/cart.vue";
import FlowerPage from "./pages/flower.vue";
import CatalogPage from "./pages/catalog.vue";
import BunchPage from "./pages/bunch.vue";
import AdminOrdersPage from "./pages/admin/orders.vue";
import CheckoutPage from "./pages/checkout.vue";
import OrderPage from "./pages/order.vue";

const router = createRouter({
	history: createWebHistory(import.meta.env.BASE_URL),
	routes: [
		{
			path: "/",
			name: "home",
			component: MainPage,
		},
		{
			path: "/cart",
			name: "cart",
			component: CartPage,
		},
		{
			path: "/flower/:id",
			name: "flower",
			component: FlowerPage,
		},
		{
			path: "/bunch/:id",
			name: "bunch",
			component: BunchPage,
		},
		{
			path: "/catalog",
			name: "catalog",
			component: CatalogPage,
		},
		{
			path: "/admin/orders/:order?",
			name: "orders",
			component: AdminOrdersPage,
		},
		{
			path: "/checkout",
			name: "checkout",
			component: CheckoutPage,
		},
		{
			path: "/order/:id",
			name: "order",
			component: OrderPage,
		},
	],
});

export default router;
