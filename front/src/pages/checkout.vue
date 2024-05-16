<template>
	<form class="wrapper" @submit.prevent="submit">
		<div class="two-sides">
			<div>
				<h2>Your details</h2>
				<div>
					<input
						v-model="formState.client_firstname"
						placeholder="First name"
						autocomplete="given-name"
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
						autocomplete="family-name"
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
						autocomplete="shipping street-address"
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
						autocomplete="tel"
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
						autocomplete="email"
					/>
					<div
						class="error"
						v-if="triedToSubmit && errors.client_email"
					>
						{{ errors.client_email[0] }}
					</div>
				</div>
				<div>
					<select v-model="formState.payment_type">
						<option value="cash">Cash</option>
						<option value="card">Card</option>
					</select>
					<div
						class="error"
						v-if="triedToSubmit && errors.payment_type"
					>
						{{ errors.payment_type[0] }}
					</div>
				</div>
				<div>
					<select v-model="formState.delivery_type">
						<option value="postman">Postman</option>
						<option value="pickup">Pickup</option>
						<option value="delivery">Delivery</option>
					</select>
					<div
						class="error"
						v-if="triedToSubmit && errors.delivery_type"
					>
						{{ errors.delivery_type[0] }}
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
					<h2>Bunches</h2>
					<div
						v-for="(bunch, index) in productsStore.cartedBunches"
						:key="index"
						class="product-card"
					>
						<BunchCard :bunch="bunch" :controls="false" />
					</div>
					<h2>Products in cart</h2>
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
import { useOrdersStore } from "@/stores/orders";
import BunchCard from "@/components/BunchCard.vue";

const productsStore = useProductsStore();
const ordersStore = useOrdersStore();

const formSchema = z.object({
	client_firstname: z.string().min(1),
	client_lastname: z.string().min(1),
	client_address: z.string().min(1),
	client_phone: z.string().min(1),
	client_email: z.string().min(1),
	client_comments: z.string().min(1),
	payment_type: z.enum(["cash", "card"]),
	delivery_type: z.enum(["postman", "pickup", "delivery"]),
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
	payment_type: "cash",
	delivery_type: "pickup",
});

const errors = computed(
	() => formSchema.safeParse(formState).error?.flatten().fieldErrors ?? {}
);
const products = computed(() =>
	productsStore.carted.content.products.map((cartElem) => ({
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
productsStore.carted.content.products.map((cartElem) => ({
	...cartElem,
	product: productsStore.products.find(
		(product) => product.id === cartElem.productID
	)!,
}));

const submit = async () => {
	triedToSubmit.value = true;
	if (Object.keys(errors.value).length) return;
	const order: Order = {
		id: 0,
		products: products.value.map((product) => ({
			productID: product.product!.id,
			quantity: product.quantity,
		})),
		bunches: productsStore.carted.content.bunches,
		status: "created",
		createdAt: new Date().valueOf(),
		lastStatusChange: new Date().valueOf(),
		...formState,
	};
	console.log("submit", order);
	await ordersStore.placeOrder(order);
	productsStore.carted.content.products = [];
	productsStore.carted.content.bunches = [];
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
