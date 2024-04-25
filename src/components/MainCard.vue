<script setup lang="ts">
import { defineProps } from "vue";
defineProps({
  photoUrl: String,
  title: String,
});
</script>
<template>
  <div class="card">
    <div class="card-container">
      <div class="photo-container">
        <img :src="photoUrl" alt="*Flowers image*" class="card-img" />
      </div>
      <div class="title-container">
        <h5 class="card-title">{{ title }}</h5>
      </div>
    </div>
  </div>
</template>
<style scoped>
.card {
  height: 280px;
  width: 210px;
  display: flex;
  justify-content: center;
  align-items: center;
}
.card-container {
  height: 270px;
  width: 200px;
}
.card:hover {
  border-radius: 10px;
  background: #f9eff2;
}
.photo-container {
  width: 200px;
  height: 200px;
  border-radius: 10px;
  background: #f9eff2;
  margin-bottom: 20px;
}
.card-img {
  width: 100%;
  height: 100%;
  object-fit: cover;
  object-position: center;
  border-radius: 10px;
}

.card-title {
  color: #370017;
  text-align: center;
  font-family: Montserrat;
  font-size: 20px;
  font-style: normal;
  font-weight: 400;
  line-height: normal;
  text-transform: uppercase;
  margin: 0 auto 0 auto;
}
</style>
