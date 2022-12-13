import Vue from 'vue'


import App from './App.vue'
import router from './router'
import store from './store'

import ElementUI from 'element-ui'
import '@/style/element-variables.scss'
Vue.use(ElementUI)


//全局样式
import './style/index.scss';

Vue.config.productionTip = false





// // debug用的
// import { removeToken, removeIdentity, removeUsername } from '@/utils/user-auth'
// removeToken(); removeIdentity(); removeUsername()


new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')

