<script setup lang="ts">
import MainCard from "../components/MainCard.vue";
</script>
<template>
  <div class="banner">
    <h1>FLOWERS MARKET</h1>
    <RouterLink to="/catalog">
      <div class="btn">
        <p>BUY NOW!</p>
      </div>
    </RouterLink>
  </div>
  <div class="main-cards-container">
    <div>
      <MainCard photoUrl="../../100&1roses.jpg" title="100 & 1 Roses" />
    </div>
    <div>
      <MainCard photoUrl="../../springBouquet.jpg" title="spring bouquet" />
    </div>
    <RouterLink to="/bunch/1">
			<MainCard
				photoUrl="../../bouquetOfLilies.jpg"
				title="bouquet of lilies"
			/>
		</RouterLink>
		<RouterLink to="/flower/1">
			<MainCard
				photoUrl="../../bouquetOfPeonies.jpg"
				title="bouquet of peonies"
			/>
		</RouterLink>
    <div>
      <MainCard
        photoUrl="../../bouquetOfGerberas.jpg"
        title="bouquet of gerberas"
      />
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

.btn {
  cursor: pointer;
  border: 0px solid transparent;
  border-radius: 50px;
  background: #fff;
  width: 500px;
  height: 100px;
  margin-top: 80px;
  padding: 0;
  display: flex;
  justify-content: center;
  align-items: center;
}

.btn:hover {
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
  display: flex;
  justify-content: space-between;
  width: 1200px;
  padding: 0;
  margin: auto;
  margin-bottom: 50px;
}
</style>
