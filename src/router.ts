import { createWebHistory, createRouter } from "vue-router";

import MainPage from "./pages/main.vue";
import CartPage from "./pages/cart.vue";
import FlowerPage from "./pages/flower.vue";
import CatalogPage from "./pages/catalog.vue";

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
			name: "flowerPage",
			component: FlowerPage,
		},
		{
			path: "/catalog",
			name: "catalog",
			component: CatalogPage,
		},
	],
});

export default router;
