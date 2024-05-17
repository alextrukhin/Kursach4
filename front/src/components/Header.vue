<script setup lang="ts">
import { RouterLink } from "vue-router";
</script>
<template>
  <header>
    <div class="header-inner">
      <div class="logo">
        <RouterLink to="/">
          <div class="logo-container">
            <img class="logo-img" src="../../Logo.svg" />
            <h1>Flowers Market</h1>
          </div>
        </RouterLink>
      </div>
      <div class="container">
        <div class="cart">
          <RouterLink to="/bunch">
            <img style="height: 60px" src="../../bunch.svg" />
          </RouterLink>
        </div>
        <div class="cart">
          <RouterLink to="/catalog">
            <img style="height: 60px" src="../../flower.svg" />
          </RouterLink>
        </div>
        <div class="cart">
          <RouterLink to="/cart">
            <img style="height: 60px" src="../../Cart.svg" />
          </RouterLink>
        </div>
      </div>
    </div>
  </header>
</template>
<style scoped>
header {
  width: 100%;
  height: 80px;
  display: flex;
  flex-direction: row;
  position: sticky;
  background: linear-gradient(90deg, #f9eff2 0%, #fff 100%);
}
.header-inner {
  width: 1200px;
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  align-items: center;
  margin: auto;
}
.container {
  display: flex;
  flex-direction: row;
  justify-content: right;
  align-items: center;
}
.logo-container {
  display: flex;
  align-items: left;
}
.logo-img {
  height: 60px;
  margin: 10px 30px 10px 0;
  border-radius: 30px;
}
.logo-img:hover {
  box-shadow: 1px 1px 2px 0px rgba(55, 0, 23, 0.1);
  cursor: pointer;
}
h1 {
  color: #370017;
  font-family: "Yeseva One";
  font-size: 30px;
  font-style: normal;
  font-weight: 400;
  line-height: normal;
  text-transform: uppercase;
}
.cart {
  height: 70px;
  width: 70px;
  margin: 5px 0 5px 25px;
  border-radius: 35px;
  padding: 0;
  display: flex;
  justify-content: center;
  align-items: center;
}
.cart:hover {
  background-color: #f9eff2;
  box-shadow: 1px 1px 2px 0px rgba(55, 0, 23, 0.1);
  cursor: pointer;
}
</style>
