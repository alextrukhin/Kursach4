import { ref } from 'vue'
import { defineStore } from 'pinia'
import type { Order } from '../types'

export const useOrdersStore = defineStore('orders', () => {
  const ordersFetched = ref(false)
  const orders = ref<Order[]>([])

  async function fetchOrders() {
    const fetchedOrders = await (await fetch(`http://localhost:8080/orders`)).json()
    orders.value = fetchedOrders
  }

  async function init() {
    await fetchOrders()
    ordersFetched.value = true
  }

  async function placeOrder(order: Order) {
    await fetch(`http://localhost:8080/addOrder`, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify(order)
    })
    await fetchOrders()
  }

  async function updateOrder(order: Order) {
    await fetch(`http://localhost:8080/updateOrder`, {
      method: 'PATCH',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify(order)
    })
    orders.value = orders.value.map((o) => (o.id === order.id ? order : o))
  }

  async function sendEmailWithCurrentStatus(order: Order) {
    await fetch(`http://localhost:8080/updateOrderStatus`, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify({
        id: order.id,
        status: order.status,
        lastStatusChange: new Date().valueOf()
      })
    })
    await fetchOrders()
  }

  async function deleteOrder(order: Order) {
    const res = await fetch(`http://localhost:8080/deleteOrder`, {
      method: 'DELETE',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify({ id: order.id })
    })
    if (res.ok) {
      orders.value = orders.value.filter((o) => o.id !== order.id)
    } else {
      alert('Failed to delete order')
      return false
    }
    return true
  }

  return {
    init,
    orders,
    ordersFetched,
    placeOrder,
    updateOrder,
    sendEmailWithCurrentStatus,
    deleteOrder
  }
})
