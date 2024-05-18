import { createWebHistory, createRouter } from 'vue-router'

import MainPage from './pages/main.vue'
import CartPage from './pages/cart.vue'
import FlowerPage from './pages/flower.vue'
import CatalogPage from './pages/catalog.vue'
import AdminPage from './pages/admin/index.vue'
import AdminProductsPage from './pages/admin/products.vue'
import AdminOrdersPage from './pages/admin/orders.vue'
import CheckoutPage from './pages/checkout.vue'
import CheckoutSuccessPage from './pages/checkout-success.vue'
import CreateBunchPage from './pages/create-bunch.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'home',
      component: MainPage
    },
    {
      path: '/cart',
      name: 'cart',
      component: CartPage
    },
    {
      path: '/flower/:id',
      name: 'flower',
      component: FlowerPage
    },
    {
      path: '/catalog',
      name: 'catalog',
      component: CatalogPage
    },
    {
      path: '/admin',
      name: 'admin',
      component: AdminPage
    },
    {
      path: '/admin/products/:product?',
      name: 'admin-products',
      component: AdminProductsPage
    },
    {
      path: '/admin/orders/:order?',
      name: 'admin-orders',
      component: AdminOrdersPage
    },
    {
      path: '/checkout',
      name: 'checkout',
      component: CheckoutPage
    },
    {
      path: '/checkout-success',
      name: 'checkout-success',
      component: CheckoutSuccessPage
    },
    {
      path: '/create-bunch',
      name: 'create-bunch',
      component: CreateBunchPage
    }
  ]
})

export default router
