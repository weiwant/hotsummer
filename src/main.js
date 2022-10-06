import Vue from 'vue'


import App from './App.vue'
import axios from 'axios'
import router from './router'
import store from './store'


import './plugins'

import 'normalize.css/normalize.css'
import './style/index.css'
import './style/button.css'
import './style/transition.css'
import './style/table.css'

Vue.config.productionTip = false


Vue.prototype.$axios = axios;




new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')

