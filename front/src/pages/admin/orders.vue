<template>
  <div id="wrapper">
    <RouterLink to="/admin">
      <button>Dashboard</button>
    </RouterLink>
    <Layout :items="ordersFiltered">
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
              <RouterLink :to="`/flower/${elem.productId}`">
                <CartCard
                  :photoUrl="elem.product?.image || ''"
                  :name="elem.product?.name"
                  :price="elem.product?.price"
                  :quantity="elem.quantity"
                />
              </RouterLink>
            </div>
          </div>
          <h2>Bunches</h2>
          <div class="container">
            <div v-for="(elem, index) in orderBunches" :key="index">
              <BunchCard :bunch="elem.bunch" />
            </div>
          </div>
          <form id="order" @submit.prevent="submit">
            <h2>Order #{{ order.id }}</h2>
            <button v-if="order.id" type="button" @click="deleteOrder">Delete order</button>
            <div>
              <label for="client_firstname">First name</label>
              <input
                id="client_firstname"
                type="text"
                v-model="order.client_firstname"
                placeholder="First name"
              />
            </div>
            <div>
              <label for="client_lastname">Last name</label>
              <input
                id="client_lastname"
                type="text"
                v-model="order.client_lastname"
                placeholder="Last name"
              />
            </div>
            <div>
              <label for="client_address">Address</label>
              <input
                id="client_address"
                type="text"
                v-model="order.client_address"
                placeholder="Address"
              />
            </div>
            <div>
              <label for="client_phone">Phone</label>
              <input
                id="client_phone"
                type="tel"
                v-model="order.client_phone"
                placeholder="Phone"
              />
            </div>
            <div>
              <label for="client_email">Email</label>
              <input
                id="client_email"
                type="email"
                v-model="order.client_email"
                placeholder="Email"
              />
            </div>
            <div>
              <label for="payment_type">Payment method</label>
              <select v-model="order.payment_type">
                <option value="cash">Cash</option>
                <option value="card">Card</option>
              </select>
            </div>
            <div>
              <label for="delivery_type">Delivery method</label>
              <select v-model="order.delivery_type">
                <option value="postman">Postman</option>
                <option value="pickup">Pickup</option>
                <option value="delivery">Delivery</option>
              </select>
            </div>
            <div>
              <label for="delivery_type">Status</label>
              <select v-model="order.status">
                <option value="created">Created</option>
                <option value="delivering">Delivering</option>
                <option value="delivered">Delivered</option>
              </select>
            </div>
            <div>
              <label for="client_comments">Comments</label>
              <textarea
                id="client_comments"
                v-model="order.client_comments"
                placeholder="Comments"
              ></textarea>
            </div>
            <button type="submit">Update</button>
            <button type="button" @click="sendEmailWithCurrentStatus">
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
import BunchCard from '@/components/bunch/BunchCard.vue'

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
const orderProducts = computed(
  () =>
    order.value?.products.map((product) => ({
      ...product,
      product: productsStore.getProductByID(product.productId)
    })) ?? []
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
  width: 100%;
  display: flex;
  justify-content: center;
}
#order {
  width: 100%;
  display: flex;
  flex-direction: column;
}
</style>
