<template>
	<div id="layout">
		<div class="sidebar">
			<ul>
				<li
					v-for="item in items"
					:key="item.id"
					@click="emit('itemClick', item)"
				>
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
import { type PropType } from "vue";

defineProps({
	items: {
		type: Array as PropType<Record<string, any>>,
		default: () => [],
	},
});
const emit = defineEmits<{
	(event: "itemClick", item: Record<string, any>): void;
}>();
</script>
<style scoped>
#wrapper {
	width: 100%;
	display: flex;
	justify-content: center;
}
#layout {
	width: 100%;
	max-width: 1200px;
	display: flex;
	flex-direction: row;
}
.sidebar {
	width: 300px;
	display: flex;
	flex-direction: column;
}
ul {
	width: 100%;
	display: flex;
	flex-direction: column;
	list-style-type: none;
	padding: 0;
}
li {
	width: 100%;
	height: 50px;
}
li a {
	width: 100%;
	height: 100%;
	display: flex;
	flex-direction: row;
	justify-content: space-between;
	align-items: center;
	gap: 8px;
}
li .center {
	flex-grow: 1;
}
#second-half {
	width: 100%;
	display: flex;
	justify-content: space-between;
	padding: 10px;
	flex-grow: 1;
	padding-left: 24px;
}
#product {
	width: 100%;
	display: flex;
	flex-direction: column;
}
</style>
