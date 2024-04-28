import { ref, computed, watch, reactive } from "vue";
import { defineStore } from "pinia";
import type { Product } from "../types";

export const useProductsStore = defineStore("products", () => {
	const productsFetched = ref(false);
	const products = ref<Product[]>([]);
	const carted = reactive<{
		content: { productID: number; quantity: number }[];
		lastChange: number;
	}>({
		content: [],
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
		products.value = [
			{
				id: 1,
				name: "Rose",
				color: "Red",
				description: "Flower",
				image: "../redRose.jpg",
				price: 5.0,
				seasoning: "Growing in a greenhouse",
			},
			{
				id: 2,
				name: "Rose",
				color: "Pink",
				description: "Flower",
				image: "../pinkRose.jpg",
				price: 5.0,
				seasoning: "Growing in a greenhouse",
			},
			{
				id: 3,
				name: "Rose",
				color: "White",
				description: "Flower",
				image: "../whiteRose.jpg",
				price: 5.0,
				seasoning: "Growing in a greenhouse",
			},
			{
				id: 4,
				name: "Sunflower",
				color: "Yellow",
				description: "Flower",
				image: "../sunflower.jpg",
				price: 10.0,
				seasoning: "Summer",
			},
			{
				id: 5,
				name: "Tulip",
				color: "White",
				description: "Flower",
				image: "../whiteTulip.jpg",
				price: 3.0,
				seasoning: "Spring",
			},
			{
				id: 6,
				name: "Tulip",
				color: "Pink",
				description: "Flower",
				image: "../pinkTulip.jpg",
				price: 3.0,
				seasoning: "Spring",
			},
			{
				id: 7,
				name: "Tulip",
				color: "Red",
				description: "Flower",
				image: "../redTulip.jpg",
				price: 3.0,
				seasoning: "Spring",
			},
			{
				id: 8,
				name: "Gerbera",
				color: "Pink",
				description: "Flower",
				image: "../pinkGerbera.jpg",
				price: 6.0,
				seasoning: "Growing in a greenhouse",
			},
			{
				id: 9,
				name: "Lily",
				color: "White",
				description: "Flower",
				image: "../whiteLily.jpg",
				price: 9.0,
				seasoning: "Summer",
			},
			{
				id: 10,
				name: "Lily",
				color: "Pink",
				description: "Flower",
				image: "../pinkLily.jpg",
				price: 9.0,
				seasoning: "Summer",
			},
			{
				id: 11,
				name: "100 & 1 Roses",
				color: "Pink",
				description: "Bouquet",
				image: "../100&1roses.jpg",
				price: 600.0,
				seasoning: "Growing in a greenhouse",
			},
			{
				id: 12,
				name: "Spring bouquet",
				color: "Pink",
				description: "Bouquet",
				image: "../springBouquet.jpg",
				price: 300.0,
				seasoning: "Spring",
			},
			{
				id: 13,
				name: "Bouquet of lilies",
				color: "Pink",
				description: "Bouquet",
				image: "../bouquetOfLilies.jpg",
				price: 400.0,
				seasoning: "Summer",
			},
			{
				id: 14,
				name: "Bouquet of peonies",
				color: "Pink",
				description: "Bouquet",
				image: "../bouquetOfPeonies.jpg",
				price: 350.0,
				seasoning: "Spring",
			},
			{
				id: 15,
				name: "Bouquet of gerberas",
				color: "Pink",
				description: "Bouquet",
				image: "../bouquetOfGerberas.jpg",
				price: 450.0,
				seasoning: "Spring",
			},
		];
		productsFetched.value = true;
	}

	async function handleCartChange(val: typeof carted) {
		console.log("handleCartChange", val);
		localStorage.setItem("carted", JSON.stringify(val));
	}

	const cartedProducts = computed(() => {
		let toReturn: Array<Product & { quantity: number }> = [];
		carted.content.map((el) => ({
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
			carted: carted.content.some((el) => el.productID == productID),
			quantity:
				carted.content.find((el) => el.productID == productID)
					?.quantity || 0,
		};
	}
	function getProductsByID(productIDs: number[]) {
		return productIDs.map((el) => getProductByID(el));
	}
	async function cartProduct(productID: number) {
		console.log("cartProduct", productID);
		if (carted.content.some((el) => el.productID == productID)) return;
		carted.content.push({
			productID: productID,
			quantity: 1,
		});
		carted.lastChange = Math.floor(new Date().valueOf() / 1000);
	}
	async function uncartProduct(productID: number) {
		console.log("uncartProduct", productID);
		if (!carted.content.some((el) => el.productID == productID)) return;
		let indexOfThisProduct = carted.content.findIndex(
			(el) => el.productID == productID
		);
		carted.content.splice(indexOfThisProduct, 1);
		carted.lastChange = Math.floor(new Date().valueOf() / 1000);
	}
	async function cartChangeProductQuantity(
		productID: number,
		quantity: number
	) {
		console.log("cartChangeProductQuantity", productID);
		let targetElement = carted.content.find(
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
