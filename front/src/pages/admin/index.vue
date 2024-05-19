<template>
  <div class="wrapper">
    <RouterLink to="/admin/orders">
      <button>Orders</button>
    </RouterLink>
    <RouterLink to="/admin/products">
      <button>Products</button>
    </RouterLink>
  </div>
</template>
<script setup lang="ts"></script>
<style scoped>
.wrapper {
  display: flex;
  justify-content: center;
  flex-direction: column;
  width: 1200px;
  padding: 0;
  margin: auto;
}
button {
  width: 350px;
  height: 70px;
  border-radius: 35px;
  background: #efc1cf;
  padding: 0;
  display: flex;
  justify-content: center;
  align-items: center;
  margin: 30px auto 0px auto;
  color: #370017;
  text-align: center;
  font-family: Montserrat;
  font-size: 25px;
  font-style: normal;
  font-weight: 500;
  line-height: normal;
  text-transform: uppercase;
  outline: none;
  border: none;
}
button:hover {
  box-shadow: 1px 2px 3px 0px rgba(55, 0, 23, 0.15);
  cursor: pointer;
}
</style>
