import { createApp } from "vue";
import Index from "@/Index.vue";
import ElementPlus from "element-plus";
import "element-plus/dist/index.css"
import "font-awesome/css/font-awesome.min.css"
import router from "@/config/router.config.js";
import axios from "@/config/axios.config.js";
import store from "@/config/store.config.js";
import "@/assets/global.css";
import "@/assets/index.css";

const index = createApp(Index);

index.use(ElementPlus);
index.use(router);
index.config.globalProperties.$http = axios;
index.use(store);

index.mount("#index");
