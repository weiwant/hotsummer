import Vue from 'vue'


import App from './App.vue'
import router from './router'
import store from './store'

import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import '@/assets/theme/index.css'
Vue.use(ElementUI)


import 'normalize.css/normalize.css'
import './style/index.css'
import './style/button.css'
import './style/transition.css'


Vue.config.productionTip = false





// debug用的
import { removeToken, removeIdentity, removeUsername } from '@/utils/user-auth'
removeToken(); removeIdentity(); removeUsername()


new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')

