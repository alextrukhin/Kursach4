<script setup lang="ts">
import { RouterLink } from "vue-router";
</script>
<template>
	<header>
		<div class="header-inner">
			<div class="header-inner-block">
				<RouterLink to="/"> <h2>Квіти</h2></RouterLink>
			</div>
			<div class="header-inner-block">
				<!-- sth -->
			</div>
			<div class="header-inner-block">
				<RouterLink to="/cart">Cart</RouterLink>
			</div>
		</div>
	</header>
</template>
<style scoped>
header {
	width: 100%;
	height: 64px;
	display: flex;
	flex-direction: row;
	position: sticky;
	padding: 0 16px;
}
.header-inner {
	width: 100%;
	display: flex;
	flex-direction: row;
	justify-content: space-between;
	align-items: center;
}
</style>
