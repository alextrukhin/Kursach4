<template>
	<form class="wrapper" @submit.prevent="submit">
		<div class="two-sides">
			<div>
				<h2>Your details</h2>
				<div>
					<input
						v-model="formState.client_firstname"
						placeholder="First name"
					/>
					<div
						class="error"
						v-if="triedToSubmit && errors.client_firstname"
					>
						{{ errors.client_firstname[0] }}
					</div>
				</div>
				<div>
					<input
						v-model="formState.client_lastname"
						placeholder="Last name"
					/>
					<div
						class="error"
						v-if="triedToSubmit && errors.client_lastname"
					>
						{{ errors.client_lastname[0] }}
					</div>
				</div>
				<div>
					<input
						v-model="formState.client_address"
						type="text"
						placeholder="Address"
					/>
					<div
						class="error"
						v-if="triedToSubmit && errors.client_address"
					>
						{{ errors.client_address[0] }}
					</div>
				</div>
				<div>
					<input
						v-model="formState.client_phone"
						type="tel"
						placeholder="Phone"
					/>
					<div
						class="error"
						v-if="triedToSubmit && errors.client_phone"
					>
						{{ errors.client_phone[0] }}
					</div>
				</div>
				<div>
					<input
						v-model="formState.client_email"
						type="email"
						placeholder="Email"
					/>
					<div
						class="error"
						v-if="triedToSubmit && errors.client_email"
					>
						{{ errors.client_email[0] }}
					</div>
				</div>
				<div>
					<textarea
						v-model="formState.client_comments"
						placeholder="Comments"
					></textarea>
					<div
						class="error"
						v-if="triedToSubmit && errors.client_comments"
					>
						{{ errors.client_comments[0] }}
					</div>
				</div>
			</div>
			<div>
				<div class="buy-container">
					<h1>Total price of cart - ${{ totalPrice }}</h1>
				</div>
				<div class="container">
					<div
						v-for="product in products"
						:key="product.productID"
						class="product-card"
					>
						<div class="product-left">
							<img :src="product.product?.image" />
						</div>
						<div class="product-center">
							<h3>{{ product.product?.name }}</h3>
							<p>Quantity: {{ product.quantity }}</p>
						</div>
						<div class="product-right"></div>
					</div>
				</div>
				<button type="submit">Place order</button>
			</div>
		</div>
	</form>
</template>
<script setup lang="ts">
import { computed, reactive, ref } from "vue";
import { useProductsStore } from "../stores/products";
import { Order } from "@/types";
import { z } from "zod";

const productsStore = useProductsStore();

const formSchema = z.object({
	client_firstname: z.string().min(1),
	client_lastname: z.string().min(1),
	client_address: z.string().min(1),
	client_phone: z.string().min(1),
	client_email: z.string().min(1),
	client_comments: z.string().min(1),
});
type ValidForm = ReturnType<(typeof formSchema)["parse"]>;

const triedToSubmit = ref(false);
const formState = reactive<ValidForm>({
	client_firstname: "",
	client_lastname: "",
	client_address: "",
	client_phone: "",
	client_email: "",
	client_comments: "",
});

const errors = computed(
	() => formSchema.safeParse(formState).error?.flatten().fieldErrors ?? {}
);
const products = computed(() =>
	productsStore.carted.content.map((cartElem) => ({
		...cartElem,
		product: productsStore.products.find(
			(product) => product.id === cartElem.productID
		),
	}))
);
const totalPrice = computed(() => {
	return products.value.reduce(
		(sum, item) => sum + (item.product?.price ?? 0) * item.quantity,
		0
	);
});
productsStore.carted.content.map((cartElem) => {
	return {
		...cartElem,
		product: productsStore.products.find(
			(product) => product.id === cartElem.productID
		)!,
	};
});

const submit = () => {
	triedToSubmit.value = true;
	if (Object.keys(errors.value).length) return;
	const order: Order = {
		products: products.value.map((product) => ({
			productID: product.product!.id,
			quantity: product.quantity,
		})),
		bunches: [],
		...formState,
	};
	console.log("submit", order);
};
</script>
<style scoped>
.wrapper {
	display: flex;
	justify-content: center;
}
.two-sides {
	width: 100%;
	max-width: 1600px;
	display: flex;
	flex-direction: row;
}
.two-sides > div {
	width: 50%;
	padding: 24px;
}
.product-card {
	display: flex;
	flex-direction: row;
}
.product-card div {
	display: flex;
	flex-direction: column;
}
.product-left img {
	width: 100px;
}
</style>
