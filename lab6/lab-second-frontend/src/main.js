import Vue from "vue";
import App from "./App.vue";
import router from "./router";
import "@/assets/css/normalize.css";
import "@/assets/css/base.css";
import "@/plugins/elementui";
// import FileSaver from 'file-saver';
// import * as XLSX from 'xlsx';


Vue.config.productionTip = false;
Vue.prototype.$bus = new Vue(); //用于取代Vuex实现组件之间的消息传递
// Vue.prototype.$FileSaver = FileSaver;
// Vue.prototype.$XLSX = XLSX;

new Vue({
  router,
  data:function (){
    return{
      UN:'孙宇鹏好帅'
    }
  },
  render: h => h(App)
}).$mount("#app");
