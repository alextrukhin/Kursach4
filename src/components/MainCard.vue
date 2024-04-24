<script setup lang="ts">
import { defineProps } from "vue";
defineProps({
  photoUrl: String,
  title: String,
});
</script>
<template>
  <MainCard
    ><div class="card">
      <img :src="photoUrl" alt="Card image" class="card-img" />
      <div class="card-body">
        <h5 class="card-title">{{ title }}</h5>
      </div>
    </div>
  </MainCard>
</template>
<style scoped>
.card-img {
  height: 50px;
  width: auto;
}
</style>
