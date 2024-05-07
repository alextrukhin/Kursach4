import { ref, computed, watch, reactive } from "vue";
import { defineStore } from "pinia";
import type { Bunch, Product } from "../types";

export const useProductsStore = defineStore("products", () => {
	const productsFetched = ref(false);
	const products = ref<Product[]>([]);
	const carted = reactive<{
		content: {
			products: { productID: number; quantity: number }[];
			bunches: Bunch[];
		};
		lastChange: number;
	}>({
		content: {
			products: [],
			bunches: [],
		},
		lastChange: -1,
	});
	watch(carted, handleCartChange);

	async function init() {
		try {
			const stored = localStorage.getItem("carted");
			if (stored) {
				const parsed = JSON.parse(stored) as typeof carted;
				console.log("parsed", parsed);
				if (!parsed.content) throw new Error("Invalid carted content");
				if (!parsed.lastChange)
					throw new Error("Invalid carted lastChange");
				carted.content = parsed.content;
				carted.lastChange = parsed.lastChange;
			}
		} catch {
			localStorage.removeItem("carted");
		}
		// fetch products here
		const fetchedProducts = await (
			await fetch(`http://localhost:8080/flowers`)
		).json();
		products.value = fetchedProducts;
		// products.value = [
		// 	{
		// 		id: 1,
		// 		name: "Rose",
		// 		color: "Red",
		// 		description: "Flower",
		// 		image: "https://raw.githubusercontent.com/alextrukhin/Kursach4_front/master/public/redRose.jpg",
		// 		price: 5.0,
		// 		seasoning: "Growing in a greenhouse",
		// 	},
		// 	{
		// 		id: 2,
		// 		name: "Rose",
		// 		color: "Pink",
		// 		description: "Flower",
		// 		image: "https://raw.githubusercontent.com/alextrukhin/Kursach4_front/master/public/pinkRose.jpg",
		// 		price: 5.0,
		// 		seasoning: "Growing in a greenhouse",
		// 	},
		// 	{
		// 		id: 3,
		// 		name: "Rose",
		// 		color: "White",
		// 		description: "Flower",
		// 		image: "https://raw.githubusercontent.com/alextrukhin/Kursach4_front/master/public/whiteRose.jpg",
		// 		price: 5.0,
		// 		seasoning: "Growing in a greenhouse",
		// 	},
		// 	{
		// 		id: 4,
		// 		name: "Sunflower",
		// 		color: "Yellow",
		// 		description: "Flower",
		// 		image: "https://raw.githubusercontent.com/alextrukhin/Kursach4_front/master/public/sunflower.jpg",
		// 		price: 10.0,
		// 		seasoning: "Summer",
		// 	},
		// 	{
		// 		id: 5,
		// 		name: "Tulip",
		// 		color: "White",
		// 		description: "Flower",
		// 		image: "https://raw.githubusercontent.com/alextrukhin/Kursach4_front/master/public/whiteTulip.jpg",
		// 		price: 3.0,
		// 		seasoning: "Spring",
		// 	},
		// 	{
		// 		id: 6,
		// 		name: "Tulip",
		// 		color: "Pink",
		// 		description: "Flower",
		// 		image: "https://raw.githubusercontent.com/alextrukhin/Kursach4_front/master/public/pinkTulip.jpg",
		// 		price: 3.0,
		// 		seasoning: "Spring",
		// 	},
		// 	{
		// 		id: 7,
		// 		name: "Tulip",
		// 		color: "Red",
		// 		description: "Flower",
		// 		image: "https://raw.githubusercontent.com/alextrukhin/Kursach4_front/master/public/redTulip.jpg",
		// 		price: 3.0,
		// 		seasoning: "Spring",
		// 	},
		// 	{
		// 		id: 8,
		// 		name: "Gerbera",
		// 		color: "Pink",
		// 		description: "Flower",
		// 		image: "https://raw.githubusercontent.com/alextrukhin/Kursach4_front/master/public/pinkGerbera.jpg",
		// 		price: 6.0,
		// 		seasoning: "Growing in a greenhouse",
		// 	},
		// 	{
		// 		id: 9,
		// 		name: "Lily",
		// 		color: "White",
		// 		description: "Flower",
		// 		image: "https://raw.githubusercontent.com/alextrukhin/Kursach4_front/master/public/whiteLily.jpg",
		// 		price: 9.0,
		// 		seasoning: "Summer",
		// 	},
		// 	{
		// 		id: 10,
		// 		name: "Lily",
		// 		color: "Pink",
		// 		description: "Flower",
		// 		image: "https://raw.githubusercontent.com/alextrukhin/Kursach4_front/master/public/pinkLily.jpg",
		// 		price: 9.0,
		// 		seasoning: "Summer",
		// 	},
		// 	{
		// 		id: 11,
		// 		name: "100 & 1 Roses",
		// 		color: "Pink",
		// 		description: "Bouquet",
		// 		image: "https://raw.githubusercontent.com/alextrukhin/Kursach4_front/master/public/100&1roses.jpg",
		// 		price: 600.0,
		// 		seasoning: "Growing in a greenhouse",
		// 	},
		// 	{
		// 		id: 12,
		// 		name: "Spring bouquet",
		// 		color: "Pink",
		// 		description: "Bouquet",
		// 		image: "https://raw.githubusercontent.com/alextrukhin/Kursach4_front/master/public/springBouquet.jpg",
		// 		price: 300.0,
		// 		seasoning: "Spring",
		// 	},
		// 	{
		// 		id: 13,
		// 		name: "Bouquet of lilies",
		// 		color: "Pink",
		// 		description: "Bouquet",
		// 		image: "https://raw.githubusercontent.com/alextrukhin/Kursach4_front/master/public/bouquetOfLilies.jpg",
		// 		price: 400.0,
		// 		seasoning: "Summer",
		// 	},
		// 	{
		// 		id: 14,
		// 		name: "Bouquet of peonies",
		// 		color: "Pink",
		// 		description: "Bouquet",
		// 		image: "https://raw.githubusercontent.com/alextrukhin/Kursach4_front/master/public/bouquetOfPeonies.jpg",
		// 		price: 350.0,
		// 		seasoning: "Spring",
		// 	},
		// 	{
		// 		id: 15,
		// 		name: "Bouquet of gerberas",
		// 		color: "Pink",
		// 		description: "Bouquet",
		// 		image: "https://raw.githubusercontent.com/alextrukhin/Kursach4_front/master/public/bouquetOfGerberas.jpg",
		// 		price: 450.0,
		// 		seasoning: "Spring",
		// 	},
		// ];
		// console.log(JSON.stringify(products.value));
		productsFetched.value = true;
	}

	async function handleCartChange(val: typeof carted) {
		console.log("handleCartChange", val);
		localStorage.setItem("carted", JSON.stringify(val));
	}

	const cartedProducts = computed(() => {
		let toReturn: Array<Product & { quantity: number }> = [];
		carted.content.products.map((el) => ({
			...products.value.find((prod) => prod.id == el.productID)!,
			quantity: el.quantity,
		}));
		return toReturn;
	});
	function getProductByID(productID: number) {
		const product = products.value.find((el) => el.id == productID);
		if (!product) {
			return productsFetched.value ? null : undefined;
		}
		return {
			...product,
			carted: carted.content.products.some(
				(el) => el.productID == productID
			),
			quantity:
				carted.content.products.find((el) => el.productID == productID)
					?.quantity || 0,
		};
	}
	function getProductsByID(productIDs: number[]) {
		return productIDs.map((el) => getProductByID(el));
	}
	async function cartProduct(productID: number) {
		console.log("cartProduct", productID);
		if (carted.content.products.some((el) => el.productID == productID))
			return;
		carted.content.products.push({
			productID: productID,
			quantity: 1,
		});
		carted.lastChange = Math.floor(new Date().valueOf() / 1000);
	}
	async function uncartProduct(productID: number) {
		console.log("uncartProduct", productID);
		if (!carted.content.products.some((el) => el.productID == productID))
			return;
		let indexOfThisProduct = carted.content.products.findIndex(
			(el) => el.productID == productID
		);
		carted.content.products.splice(indexOfThisProduct, 1);
		carted.lastChange = Math.floor(new Date().valueOf() / 1000);
	}
	async function cartChangeProductQuantity(
		productID: number,
		quantity: number
	) {
		console.log("cartChangeProductQuantity", productID);
		let targetElement = carted.content.products.find(
			(el) => el.productID == productID
		);
		if (!targetElement) return;
		if (targetElement) {
			targetElement.quantity = quantity || 1;
			carted.lastChange = Math.floor(new Date().valueOf() / 1000);
		}
	}

	return {
		init,
		products,
		carted,
		cartedProducts,
		getProductByID,
		getProductsByID,
		cartProduct,
		uncartProduct,
		cartChangeProductQuantity,
	};
});
