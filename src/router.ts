import { createWebHistory, createRouter } from "vue-router";

import MainPage from "./pages/main.vue";
import Cartpage from "./pages/cart.vue";

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
			component: Cartpage,
		},
	],
});

export default router;
