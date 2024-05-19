<template>
  <div class="wrapper">
    <h2>Checkout Success</h2>
    <p>Thank you for your purchase!</p>
    <RouterLink to="/" class="btn"><ChevronLeft /> Back to main</RouterLink>
  </div>
</template>
<script setup lang="ts">
import { ChevronLeft } from 'lucide-vue-next'
</script>
<style scoped>
h2 {
  color: #370017;
  font-family: 'Yeseva One';
  font-size: 40px;
  font-style: normal;
  font-weight: 400;
  line-height: normal;
  text-transform: uppercase;
  padding: 0;
  margin: 30px 0 30px 0;
}
p {
  color: #370017;
  text-align: left;
  font-family: Montserrat;
  font-size: 20px;
  font-style: normal;
  font-weight: 300;
  line-height: normal;
  text-transform: uppercase;
  margin: 5px 0 5px 0;
}
.wrapper {
  display: flex;
  justify-content: center;
  flex-direction: column;
  width: 1200px;
  padding: 0;
  margin: auto;
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
</style>
