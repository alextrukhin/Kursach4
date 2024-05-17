<script setup lang="ts">
import { useProductsStore } from "@/stores/products";
import { useRouter } from "vue-router";

const router = useRouter();
const productsStore = useProductsStore();

defineProps({
  id: {
    type: Number,
    required: true,
  },
  photoUrl: {
    type: String,
    required: false,
  },
  name: {
    type: String,
    required: true,
  },
  price: {
    type: Number,
    required: true,
  },
  quantity: {
    type: Number,
    required: true,
  },
});

const setQuantity = (id: number, quantity: number) => {
  productsStore.cartChangeProductQuantity(id, quantity);
};
</script>
<template>
  <div class="product-card" @click="router.push(`/flower/${id}`)">
    <div class="image-container">
      <img v-if="photoUrl" :src="photoUrl" :alt="name" class="product-image" />
      <img
        v-else
        src="https://via.placeholder.com/350x400?text=No+image"
        alt="Default"
        class="product-image"
      />
    </div>
    <div class="text-container">
      <div class="product-name">{{ name }}</div>
      <div class="product-price">
        {{ "total price - $" + price * quantity }}
      </div>
      <div class="amount">
        <button @click.stop="setQuantity(id, quantity - 1)" class="amount-btn">
          <img
            src="../../leftPink.svg"
            alt="+"
            style="height: 25px; margin-left: 20px"
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
          <h3 style="padding: 0; margin: auto; color: #370017; font-size: 20px">
            {{ quantity }}
          </h3>
        </div>
        <button @click.stop="setQuantity(id, quantity + 1)" class="amount-btn">
          <img
            src="../../rightPink.svg"
            alt="+"
            style="height: 25px; margin-right: 20px"
          />
        </button>
      </div>

      <button
        @click.stop="productsStore.uncartProduct(id)"
        class="btn product-name"
      >
        Delete
      </button>
    </div>
  </div>
</template>

<style scoped>
.product-card {
  width: 350px;
  height: 525px;
  display: flex;
  flex-direction: column;
  align-items: center;
  border-radius: 10px;
  background: #f9eff2;
}
.product-card:hover {
  box-shadow: 1px 2px 3px 0px rgba(55, 0, 23, 0.1);
}
.image-container {
  width: 330px;
  height: 300px;
  border-radius: 7px;
  background: #fff;
  margin-top: 20px;
  margin-bottom: 10px;
}
.product-image {
  width: 100%;
  height: 100%;
  object-fit: cover;
  border-radius: 7px;
}
.text-container {
  width: 330px;
  height: 60px;
}
.product-name {
  color: #370017;
  text-align: center;
  text-overflow: ellipsis;
  font-family: Montserrat;
  font-size: 20px;
  font-style: normal;
  font-weight: 500;
  line-height: normal;
  text-transform: uppercase;
  margin-bottom: 5px;
}
.amount {
  width: 250px;
  height: 50px;
  border-radius: 25px;
  background: #fff;
  margin: 20px 40px 5px 40px;
  padding: 0;
  border: none;
  outline: none;
  box-shadow: none;
  display: flex;
  justify-content: space-between;
}
.amount:hover {
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
.btn {
  width: 250px;
  height: 50px;
  border-radius: 25px;
  background: #fff;
  margin: 10px 40px 5px 40px;
  padding: 0;
  border: none;
  outline: none;
  box-shadow: none;
  display: flex;
  justify-content: center;
  align-items: center;
}
.product-name {
  color: #370017;
  text-align: center;
  text-overflow: ellipsis;
  font-family: Montserrat;
  font-size: 20px;
  font-style: normal;
  font-weight: 500;
  line-height: normal;
  text-transform: uppercase;
  margin-bottom: 5px;
}
.product-price {
  color: #370017;
  text-align: center;
  font-family: Montserrat;
  font-size: 20px;
  font-style: normal;
  font-weight: 300;
  line-height: normal;
  text-transform: uppercase;
  margin: 0 auto 5px auto;
}
</style>
