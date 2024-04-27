<template>
  <div v-if="product" class="container">
    <div class="photo-container">
      <div class="photo">
        <img
          v-if="product.image"
          :src="product.image"
          :alt="product.name"
          class="product-image"
        />
        <img
          v-else
          src="https://via.placeholder.com/350x400?text=No+image"
          alt="Default"
          class="product-image"
        />
      </div>
    </div>
    <div class="info-container">
      <h2>{{ product.name }}</h2>
      <div style="margin-bottom: 35px">
        <h3><b>Color</b> - {{ product.color }}</h3>
        <h3><b>Type</b> - {{ product.description }}</h3>
        <h3><b>Season</b> - {{ product.seasoning }}</h3>
        <h3 v-if="product.description === 'Flower'">
          <b>Price per 1</b> - ${{ product.price }}
        </h3>
        <h3 v-if="product.description === 'Bouquet'">
          <b>Price</b> - ${{ product.price }}
        </h3>
      </div>
      <div class="amount">
        <button
          @click="
            productsStore.cartChangeProductQuantity(
              product!.id,
              product!.quantity - 1
            )
          "
          class="amount-btn"
        >
          <img
            src="../../left.svg"
            alt="+"
            style="height: 40px; margin-left: 20px"
          />
        </button>
        <div
          style="
            margin: 0 auto 0 auto;
            padding: 0;
            width: 140px;
            display: flex;
            justify-content: center;
            align-items: center;
          "
        >
          <h3 style="padding: 0; margin: auto">{{ product!.quantity }}</h3>
        </div>
        <button
          @click="
            productsStore.cartChangeProductQuantity(
              product!.id,
              product!.quantity + 1
            )
          "
          class="amount-btn"
        >
          <img
            src="../../right.svg"
            alt="+"
            style="height: 40px; margin-right: 20px"
          />
        </button>
      </div>
      <div class="add-to-cart">
        <button
          v-if="!product.carted"
          @click="productsStore.cartProduct(product!.id)"
          class="add-to-cart-btn"
        >
          <h3 style="font-size: 15px; padding: 0; margin: auto">Add to cart</h3>
        </button>
        <button
          v-else
          @click="productsStore.uncartProduct(product!.id)"
          class="add-to-cart-btn"
        >
          <h3 style="font-size: 15px; padding: 0; margin: 0">
            Remove from cart
          </h3>
        </button>
      </div>
      <div v-if="product.carted">
        <h3>Quantity: {{ product!.quantity }}</h3>
      </div>
    </div>
  </div>
  <div v-else-if="product === null" class="container"><h2>not found</h2></div>
  <div v-else class="container"><h2>loading products...</h2></div>
</template>
<script setup lang="ts">
import { useRoute } from "vue-router";
import { useProductsStore } from "../stores/products";
import { computed } from "vue";

const productsStore = useProductsStore();
const route = useRoute();

const product = computed(() => {
  return productsStore.getProductByID(parseInt(route.params.id.toString()));
});
</script>
<style scoped>
.container {
  width: 1200px;
  margin: 120px auto 75px auto;
  display: flex;
  flex-direction: row;
  justify-content: start;
}
.photo-container {
  width: 600px;
  height: 500px;
  border-radius: 10px;
  background: #f9eff2;
  padding: 0;
}
.photo {
  width: 580px;
  height: 460px;
  border-radius: 7px;
  background: #fff;
  margin: 20px auto 20px auto;
  padding: 0;
}
.product-image {
  width: 100%;
  height: 100%;
  object-fit: cover;
  object-position: center;
  border-radius: 7px;
  padding: 0;
}
.info-container {
  margin-left: 115px;
  max-width: calc(100% - 600px - 115px);
}
h2 {
  color: #370017;
  font-family: "Yeseva One";
  font-size: 40px;
  font-style: normal;
  font-weight: 400;
  line-height: normal;
  text-transform: uppercase;
  margin: 0 0 30px 0;
}
h3 {
  color: #370017;
  font-family: Montserrat;
  font-size: 25px;
  font-style: normal;
  font-weight: 400;
  line-height: normal;
  text-transform: uppercase;
  margin: 0 0 10px 0;
}
.amount {
  width: 260px;
  height: 70px;
  border-radius: 35px;
  background: #efc1cf;
  display: flex;
  justify-content: space-between;
  margin-bottom: 25px;
}
.amount:hover {
  box-shadow: 1px 2px 3px 0px rgba(55, 0, 23, 0.15);
  cursor: pointer;
}
.amount-btn {
  padding: 0;
  border: none;
  background: transparent;
  outline: none;
  box-shadow: none;
}
.amount-btn:hover,
.amount-btn:active,
.amount-btn:focus {
  background: transparent;
  outline: none;
  box-shadow: none;
}
.add-to-cart {
  width: 260px;
  height: 70px;
  border-radius: 35px;
  background: #efc1cf;
  display: flex;
  justify-content: space-between;
  margin-bottom: 25px;
}
.add-to-cart-btn {
  margin: 0 auto 0;
  padding: 0;
  width: 260px;
  height: 70px;
  border-radius: 35px;
  background: transparent;
  display: flex;
  justify-content: center;
  align-items: center;
}
</style>
