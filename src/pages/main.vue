<template>
	<div class="banner">
  		<h1>FLOWERS MARKET</h1>
  		<button><p>BUY NOW!</p></button>
	</div>
</template>
<style scoped>
.banner {
  height: 600px;
  width: 100%;
  background-image: url('../../MainPhoto.png');
  background-size: cover;
  background-position: center;
  display: flex;
  flex-direction: column;
  align-items: center;
  position: relative;
  margin-bottom: 50px;
}

.banner h1 
{
  	margin-top: 160px;
 	text-align: center;
 	color: #FFF;
	font-family: "Yeseva One";
	font-size: 100px;
	font-style: normal;
	font-weight: 400;
	line-height: normal;
}

.banner button 
{
    cursor: pointer;
    border: 0px solid rgba(255, 255, 255, 0.5);
    border-radius: 50px;
  background: #FFF;
    width: 500px;
  height: 100px;
}
.banner button:hover 
{
    background-color: #F9EFF2;
  border: 5px solid rgba(255, 255, 255, 0.5);
}
p
{
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
</style>