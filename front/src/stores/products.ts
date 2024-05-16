import { ref, computed, watch, reactive } from "vue";
import { defineStore } from "pinia";
import type { Bunch, Product } from "../types";

export const useProductsStore = defineStore("products", () => {
	const productsFetched = ref(false);
	const products = ref<Product[]>([]);
	const bunches = ref<Bunch[]>([]);
	const carted = reactive<{
		content: {
			products: { productID: number; quantity: number }[];
			bunches: { bunch: Bunch; quantity: number }[];
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
				if (!parsed.content) throw new Error("Invalid carted content");
				if (!parsed.lastChange)
					throw new Error("Invalid carted lastChange");
				carted.content = parsed.content;
				carted.lastChange = parsed.lastChange;
			}
		} catch {
			localStorage.removeItem("carted");
		}
		const fetchedProducts = await (
			await fetch(`http://localhost:8080/flowers`)
		).json();
		products.value = fetchedProducts;
		const fetchedBunches = await (
			await fetch(`http://localhost:8080/bunches`)
		).json();
		bunches.value = fetchedBunches;
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
	const cartedBunches = computed(() => {
		let toReturn: Array<
			Bunch & {
				products: Array<
					NonNullable<Bunch["products"]>[number] & {
						product: Product;
					}
				>;
				quantity: number;
			}
		> = [];
		carted.content.bunches
			.map((el) => ({
				...bunches.value.find(
					(bunch) => bunch.id && bunch.id === el.bunch.id
				),
				quantity: el.quantity,
			}))
			.map((el) => ({
				...el,
				products: el.products?.map((p) => ({
					product: products.value.find((prod) => prod.id == p.id),
					...p,
				})),
			}));
		return toReturn;
	});
	const cartSum = computed(() => {
		let sum = 0;
		carted.content.products.forEach((el) => {
			let product = products.value.find(
				(prod) => prod.id == el.productID
			);
			if (product) sum += product.price * el.quantity;
		});
		carted.content.bunches.forEach((el) => {
			let bunch = bunches.value.find((b) => b.id === el.bunch.id);
			if (bunch && bunch.products?.length)
				sum +=
					bunch.products.reduce(
						(acc, curr) =>
							acc + (getProductByID(curr.id)?.price ?? 0),
						0
					) * el.quantity;
		});
		return sum;
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
		bunches,
		carted,
		cartedProducts,
		cartedBunches,
		cartSum,
		getProductByID,
		getProductsByID,
		cartProduct,
		uncartProduct,
		cartChangeProductQuantity,
	};
});
