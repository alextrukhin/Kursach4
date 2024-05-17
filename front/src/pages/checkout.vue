<template>
  <form class="wrapper" @submit.prevent="submit">
    <div>
      <div class="buy-container">
        <h1>Total price of cart - ${{ productsStore.cartSum }}</h1>
      </div>
      <h2>Bunches</h2>
      <div class="container">
        <div v-for="(bunch, index) in bunches" :key="index" class="product-card">
          <BunchCartCard :bunch="bunch" :controls="false" />
        </div>
      </div>
      <h2>Products in cart</h2>
      <div class="container">
        <div v-for="elem in products" :key="elem.productId">
          <FlowerCheckout
            :photoUrl="elem.product?.image || ''"
            :name="elem.product?.name"
            :price="elem.product?.price"
            :quantity="elem.quantity"
          />
        </div>
      </div>
    </div>
    <div>
      <h2>Your details</h2>
      <div>
        <input
          v-model="formState.client_firstname"
          placeholder="First name"
          autocomplete="given-name"
        />
        <div class="error" v-if="triedToSubmit && errors.client_firstname">
          {{ errors.client_firstname[0] }}
        </div>
      </div>
      <div>
        <input
          v-model="formState.client_lastname"
          placeholder="Last name"
          autocomplete="family-name"
        />
        <div class="error" v-if="triedToSubmit && errors.client_lastname">
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
        <div class="error" v-if="triedToSubmit && errors.client_address">
          {{ errors.client_address[0] }}
        </div>
      </div>
      <div>
        <input v-model="formState.client_phone" type="tel" placeholder="Phone" autocomplete="tel" />
        <div class="error" v-if="triedToSubmit && errors.client_phone">
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
        <div class="error" v-if="triedToSubmit && errors.client_email">
          {{ errors.client_email[0] }}
        </div>
      </div>
      <div>
        <select v-model="formState.payment_type">
          <option value="cash">Cash</option>
          <option value="card">Card</option>
        </select>
        <div class="error" v-if="triedToSubmit && errors.payment_type">
          {{ errors.payment_type[0] }}
        </div>
      </div>
      <div>
        <select v-model="formState.delivery_type">
          <option value="postman">Postman</option>
          <option value="pickup">Pickup</option>
          <option value="delivery">Delivery</option>
        </select>
        <div class="error" v-if="triedToSubmit && errors.delivery_type">
          {{ errors.delivery_type[0] }}
        </div>
      </div>
      <div>
        <textarea v-model="formState.client_comments" placeholder="Comments"></textarea>
        <div class="error" v-if="triedToSubmit && errors.client_comments">
          {{ errors.client_comments[0] }}
        </div>
      </div>
    </div>
    <button type="submit" class="btn"><h2>Place order</h2></button>
  </form>
</template>
<script setup lang="ts">
import { computed, reactive, ref } from 'vue'
import { useProductsStore } from '../stores/products'
import { Order } from '@/types'
import { z } from 'zod'
import { useOrdersStore } from '@/stores/orders'
import BunchCartCard from '@/components/BunchCartCard.vue'
import router from '@/router'
import FlowerCheckout from '../components/FlowerCheckout.vue'
const productsStore = useProductsStore()
const ordersStore = useOrdersStore()

const formSchema = z.object({
  client_firstname: z.string().min(1),
  client_lastname: z.string().min(1),
  client_address: z.string().min(1),
  client_phone: z.string().min(1),
  client_email: z.string().min(1),
  client_comments: z.string().min(1),
  payment_type: z.enum(['cash', 'card']),
  delivery_type: z.enum(['postman', 'pickup', 'delivery'])
})
type ValidForm = ReturnType<(typeof formSchema)['parse']>

const triedToSubmit = ref(false)
const formState = reactive<ValidForm>({
  client_firstname: '',
  client_lastname: '',
  client_address: '',
  client_phone: '',
  client_email: '',
  client_comments: '',
  payment_type: 'cash',
  delivery_type: 'pickup'
})

const errors = computed(() => formSchema.safeParse(formState).error?.flatten().fieldErrors ?? {})
const products = computed(() =>
  productsStore.carted.content.products.map((cartElem) => ({
    ...cartElem,
    product: productsStore.products.find((product) => product.id === cartElem.productId)
  }))
)
const bunches = computed(() =>
  productsStore.carted.content.bunches.map((bunch) => {
    return {
      ...bunch,
      bunch: {
        ...bunch.bunch,
        products:
          bunch.bunch.products?.map((product) => {
            return {
              ...product,
              product: productsStore.products.find((p) => p.id === product.id)
            }
          }) ?? null
      }
    }
  })
)
productsStore.carted.content.products.map((cartElem) => ({
  ...cartElem,
  product: productsStore.products.find((product) => product.id === cartElem.productId)!
}))

const submit = async () => {
  triedToSubmit.value = true
  if (Object.keys(errors.value).length) return
  const order: Order = {
    id: 0,
    products: products.value.map((product) => ({
      productId: product.product!.id,
      quantity: product.quantity
    })),
    bunches: productsStore.carted.content.bunches.map((b) => ({
      ...b,
      bunch: {
        id: null,
        products:
          b.bunch.products?.map((p) => ({
            id: p.id,
            x: p.x,
            y: p.y
          })) ?? null
      }
    })),
    status: 'created',
    createdAt: new Date().valueOf(),
    lastStatusChange: new Date().valueOf(),
    ...formState
  }
  console.log('submit', order)
  await ordersStore.placeOrder(order)
  productsStore.carted.content.products = []
  productsStore.carted.content.bunches = []
  router.push('/checkout-success')
}
</script>
<style scoped>
.wrapper {
  display: flex;
  justify-content: center;
  flex-direction: column;
  width: 1200px;
  padding: 0;
  margin: auto;
}

.container {
  display: flex;
  flex-direction: row;
  flex-wrap: wrap;
  width: 1200px;
  margin: 0 auto;
  row-gap: 40px;
  column-gap: 75px;
  margin-bottom: 50px;
}
h1 {
  color: #370017;
  font-family: 'Yeseva One';
  font-size: 40px;
  font-style: normal;
  font-weight: 400;
  line-height: normal;
  text-transform: uppercase;
  padding: 0;
  margin: 35px 0 0 0;
}
h2 {
  color: #370017;
  text-align: center;
  font-family: Montserrat;
  font-size: 25px;
  font-style: normal;
  font-weight: 500;
  line-height: normal;
  text-transform: uppercase;
}
.btn {
  width: 350px;
  height: 70px;
  border-radius: 35px;
  background: #efc1cf;
  padding: 0;
  display: flex;
  justify-content: center;
  align-items: center;
  max-width: auto;
  outline: none;
  border: none;
  margin: 10px auto;
}
.btn:hover {
  box-shadow: 1px 2px 3px 0px rgba(55, 0, 23, 0.15);
  cursor: pointer;
  outline: none;
  border: none;
}
</style>
