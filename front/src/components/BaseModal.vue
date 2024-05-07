<template>
	<div class="modal-container">
		<slot></slot>
	</div>
</template>
<script setup lang="ts"></script>
<style scoped>
.modal-container {
	position: fixed;
	top: 0;
	left: 0;
	width: 100%;
	height: 100%;
	background-color: rgba(0, 0, 0, 0.5);
	display: flex;
	justify-content: center;
	align-items: center;
	z-index: 100;
}
</style>
