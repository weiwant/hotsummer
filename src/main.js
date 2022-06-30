import Vue from 'vue'
import App from './App.vue'
import router from './router'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import '@/utils/axios'
import axios from 'axios'

Vue.config.productionTip = false
Vue.use(ElementUI);
Vue.prototype.$axios = axios;
Vue.prototype.$domainName = "http://awjkds.vaiwan.com"  //axios请求域名
Vue.prototype.$currentYear = (new Date()).getFullYear()  //当前的年份



new Vue({
  router,
  el: '#app',
  render: h => h(App)
}).$mount('#app')
