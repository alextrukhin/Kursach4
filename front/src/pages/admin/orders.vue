<template>
  <div id="wrapper">
    <RouterLink to="/admin">
      <button class="btn"><ChevronLeft /> Back to Dashboard</button>
    </RouterLink>
    <Layout :items="ordersFiltered">
      <template #list-header>
        <div>
          <div class="left header">
            <p>id</p>
          </div>
          <div class="center header">
            <p>client</p>
          </div>
          <div class="right header">
            <p>status</p>
          </div>
        </div>
      </template>
      <template #item="{ id, client_firstname, client_lastname, status }">
        <RouterLink :to="`/admin/orders/${id}`">
          <div class="left">
            <p>#{{ id }}</p>
          </div>
          <div class="center">
            <p>
              {{ client_firstname }}
              {{ client_lastname }}
            </p>
          </div>
          <div class="right">
            <p>{{ status }}</p>
          </div>
        </RouterLink>
      </template>
      <template #content>
        <div v-if="order">
          <h2>Flowers</h2>
          <div class="container">
            <div v-for="elem in orderProducts" :key="elem.productId">
              <CartCard
                :id="elem.productId"
                :photoUrl="elem.product?.image || ''"
                :name="elem.product?.name"
                :price="elem.product?.price"
                :quantity="elem.quantity"
                @setQuantity="setQuantity(elem.productId, $event)"
                @delete="deleteProduct(elem.productId)"
              />
            </div>
          </div>
          <h2>Bunches</h2>
          <div class="container">
            <div v-for="(elem, index) in orderBunches" :key="index">
              <BunchCartCard :bunch="elem" @delete="order?.bunches.splice(index, 1)" />
            </div>
          </div>
          <form id="order" @submit.prevent="submit">
            <h2>Order #{{ order.id }}</h2>
            <button v-if="order.id" type="button" @click="deleteOrder" class="button">
              Delete order
            </button>
            <div class="form-container">
              <label for="client_firstname" class="first-colomn">First name</label>
              <input
                class="second-colomn"
                id="client_firstname"
                type="text"
                v-model="order.client_firstname"
                placeholder="First name"
              />
            </div>
            <div class="form-container">
              <label for="client_lastname" class="first-colomn">Last name</label>
              <input
                class="second-colomn"
                id="client_lastname"
                type="text"
                v-model="order.client_lastname"
                placeholder="Last name"
              />
            </div>
            <div class="form-container">
              <label for="client_address" class="first-colomn">Address</label>
              <input
                class="second-colomn"
                id="client_address"
                type="text"
                v-model="order.client_address"
                placeholder="Address"
              />
            </div>
            <div class="form-container">
              <label for="client_phone" class="first-colomn">Phone</label>
              <input
                class="second-colomn"
                id="client_phone"
                type="tel"
                v-model="order.client_phone"
                placeholder="Phone"
              />
            </div>
            <div class="form-container">
              <label for="client_email" class="first-colomn">Email</label>
              <input
                class="second-colomn"
                id="client_email"
                type="email"
                v-model="order.client_email"
                placeholder="Email"
              />
            </div>
            <div class="form-container">
              <label for="payment_type" class="first-colomn">Payment method</label>
              <select v-model="order.payment_type" class="second-colomn select">
                <option value="cash">Cash</option>
                <option value="card">Card</option>
              </select>
            </div>
            <div class="form-container">
              <label for="delivery_type" class="first-colomn">Delivery method</label>
              <select v-model="order.delivery_type" class="second-colomn select">
                <option value="postman">Postman</option>
                <option value="pickup">Pickup</option>
                <option value="delivery">Delivery</option>
              </select>
            </div>
            <div class="form-container">
              <label for="delivery_type" class="first-colomn">Status</label>
              <select v-model="order.status" class="second-colomn select">
                <option value="created">Created</option>
                <option value="delivering">Delivering</option>
                <option value="delivered">Delivered</option>
              </select>
            </div>
            <div class="form-container">
              <label for="client_comments" class="first-colomn">Comments</label>
              <textarea
                class="second-colomn textarea"
                id="client_comments"
                v-model="order.client_comments"
                placeholder="Comments"
              ></textarea>
            </div>
            <button type="submit" class="button">Update</button>
            <button type="button" @click="sendEmailWithCurrentStatus" class="button">
              Send email with current status
            </button>
          </form>
        </div>
        <div v-else>order not selected</div>
      </template>
    </Layout>
  </div>
</template>
<script setup lang="ts">
import { computed, ref, watch } from 'vue'
import Layout from './shared.vue'
import { useOrdersStore } from '../../stores/orders'
import { useProductsStore } from '../../stores/products'
import { useRoute, useRouter } from 'vue-router'
import type { Order } from '../../types'
import CartCard from '@/components/CartCard.vue'
import BunchCartCard from '@/components/BunchCartCard.vue'
import { ChevronLeft } from 'lucide-vue-next'

const ordersStore = useOrdersStore()
const productsStore = useProductsStore()
const route = useRoute()
const router = useRouter()

ordersStore.init()

const order = ref<Order | null>(null)

const ordersFiltered = computed(() => ordersStore.orders)
const orderId = computed(() =>
  route.params.order ? parseInt(route.params.order.toString()) : null
)
const orderProducts = computed(() =>
  order.value?.products.length
    ? order.value?.products.map((product) => ({
        ...product,
        product: productsStore.getProductByID(product.productId)
      })) ?? []
    : []
)
const orderBunches = computed(
  () =>
    order.value?.bunches.map((bunch) => ({
      ...bunch,
      bunch: {
        ...bunch.bunch,
        products:
          bunch.bunch.products?.map((product) => ({
            ...product,
            product: productsStore.getProductByID(product.id)
          })) ?? null
      }
    })) ?? []
)

const submit = () => {
  ordersStore.updateOrder(order.value!)
}

const sendEmailWithCurrentStatus = () => {
  ordersStore.sendEmailWithCurrentStatus(order.value!)
}

const refreshOrder = (newOrderId: number | null) => {
  const orderData = ordersStore.orders.find((order) => order.id === newOrderId)
  order.value = orderData ? { ...orderData } : null
}

const deleteOrder = async () => {
  if (await ordersStore.deleteOrder(order.value!)) router.push('/admin/orders')
}

const setQuantity = (productId: number, quantity: number) => {
  const productIndex = order.value?.products.findIndex((product) => product.productId === productId)
  if (productIndex === undefined || productIndex === -1) return
  order.value!.products[productIndex].quantity = quantity
}
const deleteProduct = (productId: number) => {
  const productIndex = order.value?.products.findIndex((product) => product.productId === productId)
  if (productIndex === undefined || productIndex === -1) return
  order.value!.products.splice(productIndex, 1)
}

watch(orderId, refreshOrder, { immediate: true })
watch(
  () => ordersStore.orders,
  () => {
    refreshOrder(orderId.value)
  }
)
</script>
<style scoped>
#wrapper {
  width: 1200px;
  padding: 0;
  margin: auto;
  display: flex;
  flex-direction: column;
  justify-content: center;
}
#order {
  width: 100%;
  display: flex;
  flex-direction: column;
}
.btn {
  padding: 0;
  display: flex;
  justify-content: left;
  align-items: center;
  margin: 10px auto 10px 0;
  color: #370017;
  text-align: center;
  font-family: Montserrat;
  font-size: 20px;
  font-style: normal;
  font-weight: 500;
  line-height: normal;
  text-transform: uppercase;
  outline: none;
  border: none;
  background-color: transparent;
  height: 26px;
}
.btn:hover {
  cursor: pointer;
}
p {
  color: #370017;
  text-align: left;
  font-family: Montserrat;
  font-size: 15px;
  font-style: normal;
  font-weight: 300;
  line-height: normal;
  text-transform: uppercase;
  margin: 5px 0 5px 0;
}
.left {
  min-width: 40px;
}
.left p {
  font-weight: 600;
}
.center p {
  text-transform: none;
}
.header p {
  font-weight: 600;
  text-transform: uppercase;
  font-size: 15px;
}
h2 {
  color: #370017;
  text-align: left;
  text-overflow: ellipsis;
  font-family: Montserrat;
  font-size: 20px;
  font-style: normal;
  font-weight: 500;
  line-height: normal;
  text-transform: uppercase;
  margin-bottom: 5px;
}
.container {
  display: flex;
  flex-wrap: wrap;
  justify-content: flex-start;
  width: 800px;
  margin: 0 auto;
  row-gap: 40px;
  column-gap: 40px;
  margin-bottom: 50px;
}
.button {
  width: 440px;
  height: 50px;
  border-radius: 35px;
  background: #efc1cf;
  padding: 0;
  display: flex;
  justify-content: center;
  align-items: center;
  margin: 10px 0 5px 0;
  color: #370017;
  text-align: center;
  text-overflow: ellipsis;
  font-family: Montserrat;
  font-size: 15px;
  font-style: normal;
  font-weight: 500;
  line-height: normal;
  text-transform: uppercase;
  border: none;
  outline: none;
}
.button:hover {
  box-shadow: 1px 2px 3px 0px rgba(55, 0, 23, 0.15);
  cursor: pointer;
}
.first-colomn {
  width: 150px;
  display: inline-block;
  color: #370017;
  text-align: left;
  font-family: Montserrat;
  font-size: 15px;
  font-style: normal;
  font-weight: 500;
  line-height: normal;
  padding-top: 15px;
}
.second-colomn {
  width: 290px;
  height: 50px;
  display: inline-block;
  border-radius: 20px;
  background: #f9eff2;
  padding: 0;
  border-style: none;
  padding-left: 15px;
  color: #370017;
  text-align: left;
  font-family: Montserrat;
  font-size: 15px;
  font-style: normal;
  font-weight: 300;
  line-height: normal;
  outline: none;
}
.form-container {
  margin: 5px 0;
  display: flex;
  justify-content: left;
  align-items: start;
}
.textarea {
  resize: vertical;
  min-height: 100px;
  padding-top: 15px;
}
</style>
