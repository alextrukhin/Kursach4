<script setup lang="ts">
import MainCard from "../components/MainCard.vue";
</script>
<template>
  <div class="banner">
    <h1>FLOWERS MARKET</h1>
    <button>
      <p>BUY NOW!</p>
    </button>
  </div>
  <div class="main-cards-container">
    <div>
      <MainCard photoUrl="../../Logo.svg" title="Заголовок картки" />
    </div>
    <div>
      <MainCard photoUrl="../../Logo.svg" title="Заголовок картки" />
    </div>
  </div>
</template>

<style scoped>
.banner {
  height: 600px;
  width: 100%;
  background-image: url("../../MainPhoto.png");
  background-size: cover;
  background-position: center;
  display: flex;
  flex-direction: column;
  align-items: center;
  position: relative;
  margin-bottom: 50px;
}

.banner h1 {
  margin-top: 160px;
  margin-bottom: 0;
  text-align: center;
  color: #fff;
  font-family: "Yeseva One";
  font-size: 100px;
  font-style: normal;
  font-weight: 400;
  line-height: normal;
}

.banner button {
  cursor: pointer;
  border: 0px solid transparent;
  border-radius: 50px;
  background: #fff;
  width: 500px;
  height: 100px;
  margin-top: 80px;
  padding: 0;
}
.banner button:hover {
  width: 510px;
  height: 110px;
  border-radius: 55px;
  background-color: rgba(249, 239, 242, 0.7);
  border: 3px solid #efc1cf;
  margin-top: 75px;
}
p {
  color: #370017;
  text-align: center;
  font-family: Montserrat;
  font-size: 45px;
  font-style: normal;
  font-weight: 600;
  line-height: normal;
  text-transform: uppercase;
  margin: 0;
  padding: 0;
}
.main-cards-container {
  justify-content: space-between;
  width: 1200px;
  padding: 0;
  margin: auto;
  margin-bottom: 50px;
}
</style>
