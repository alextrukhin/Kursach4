import { createApp } from "vue";
import "./assets/normalize.css";
import "./style.css";
// import "vue-select/dist/vue-select.css";
import App from "./App.vue";
import router from "./router";
import { createPinia } from "pinia";
// import vSelect from "vue-select";

const pinia = createPinia();

createApp(App)
	.use(router)
	.use(pinia)
	// .component("VSelect", vSelect)
	.mount("#app");
