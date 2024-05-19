<template>
  <div id="admin-list-layout">
    <div class="admin-list-sidebar">
      <ul>
        <li>
          <slot name="list-header"></slot>
        </li>
        <li v-for="item in items" :key="item.id" @click="emit('itemClick', item)">
          <slot name="item" v-bind="item"></slot>
        </li>
      </ul>
    </div>
    <div id="second-half">
      <slot name="content"></slot>
    </div>
  </div>
</template>
<script setup lang="ts">
import { type PropType } from 'vue'

defineProps({
  items: {
    type: Array as PropType<Record<string, any>>,
    default: () => []
  }
})
const emit = defineEmits<{
  (event: 'itemClick', item: Record<string, any>): void
}>()
</script>
<style>
#admin-list-layout {
  width: 100%;
  max-width: 1200px;
  height: calc(100vh - 80px - 180px);
  display: flex;
  flex-direction: row;
}
.admin-list-sidebar {
  height: 100%;
  width: 300px;
  display: flex;
  flex-direction: column;
  overflow: hidden auto;
}
.admin-list-sidebar ul {
  width: 100%;
  display: flex;
  flex-direction: column;
  list-style-type: none;
  padding: 0;
}
.admin-list-sidebar li {
  width: 100%;
  height: 50px;
}
.admin-list-sidebar li a,
.admin-list-sidebar li div {
  width: 100%;
  height: 100%;
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  align-items: center;
  gap: 8px;
}
.admin-list-sidebar li .center {
  flex-grow: 1;
}
#second-half {
  height: 100%;
  width: 100%;
  display: flex;
  justify-content: space-between;
  padding: 10px;
  flex-grow: 1;
  padding-left: 24px;
  overflow: auto;
}
#product {
  width: 100%;
  display: flex;
  flex-direction: column;
}
</style>
