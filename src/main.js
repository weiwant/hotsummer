import Vue from 'vue'


import App from './App.vue'
import router from './router'
import store from './store'

import ElementUI from 'element-ui'
import '@/style/element-variables.scss'
Vue.use(ElementUI)


// 管理员：00200302  密码：094184；教师：00006672 密码：276811

//全局样式
import './style/index.scss';

Vue.config.productionTip = false





new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')

