<script setup lang="ts">
import { RouterLink } from "vue-router";
</script>
<template>
	<header>
		<div class="header-inner">
			<div class="logo">
				<RouterLink to="/main">
					<div class="logo-container">
						<img style="height: 60px; margin: 10px 30px 10px 0;" src="../../Logo.svg">
						<h1>Flowers Market</h1>
					</div>
				</RouterLink>
			</div>

			<div class="cart">
				<RouterLink to="/cart">
					<img style="height: 60px; margin: 10px 0;" src="../../Cart.svg">
				</RouterLink>
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
	background: linear-gradient(90deg, #F9EFF2 0%, #FFF 100%);
}
.header-inner {
	width: 1200px;
	display: flex;
	flex-direction: row;
	justify-content: space-between;
	align-items: center;
	margin: auto;
}
.logo-container {
	display: flex;
	align-items: left;
}
h1
{
	color: #370017;
	font-family: "Yeseva One";
	font-size: 30px;
	font-style: normal;
	font-weight: 400;
	line-height: normal;
	text-transform: uppercase;
}
</style>
