<template>
	<footer>
		<div class="footer-inner">
			<div class="left-container">
				<p class="course-work">Course Work</p>
				<p class="nau">National Aviation University</p>
			</div>
			<div class="right-container">
				<p class="right-text">Created by<br>Alexander Trukhin<br>and Anna Kuts’</p>
			</div>
		</div>
	</footer>
</template>
<style scoped>
footer {
	width: 100%;
  	height: 180px;
  	display: flex;
  	flex-direction: row;
  	position: sticky;
  	bottom: 0;
  	background: linear-gradient(0deg, #F9EFF2 0%, #FFF 100%);
}
.footer-inner {
	width: 1200px;
	display: flex;
	flex-direction: row;
	justify-content: space-between;
	align-items: center;
	margin: auto;
}
.left-container {
	display: flex;
	flex-direction: column;
	align-items: left;
}
.right-container {
	display: flex;
	flex-direction: column;
	align-items: right;
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
.course-work
{
	color: #370017;
	font-family: Montserrat;
	font-size: 30px;
	font-style: normal;
	font-weight: 400;
	line-height: normal;
	text-transform: uppercase;
	margin: 0 0 10px;
}
.nau
{
	color: #370017;
	font-family: Montserrat;
	font-size: 15px;
	font-style: normal;
	font-weight: 300;
	line-height: normal;
	text-transform: uppercase;
	margin: 0;
}
.right-text{
	color: #370017;
	text-align: right;
	font-family: Montserrat;
	font-size: 15px;
	font-style: normal;
	font-weight: 300;
	line-height: normal;
	text-transform: uppercase;
	margin: 0;
}
</style>