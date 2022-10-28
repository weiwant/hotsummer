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

router.beforeEach((to, before, next) => {
  if (store.getters.token) {
    if (to.path === '/login') {
      next({ path: '/' });
    } else {
      //如果还没有拉取用户身份，需先拉取
      if (store.getters.identity === -1) {
        store.dispatch('user/getInfo').then((data) => {
          store.dispatch('user/generateRoutes', data.identity).then(() => {
            router.addRoutes(store.getters.addRoutes)
            next({ ...to, replace: true })   //教程说直接使用next()可能会由于暂时还没add好而失效，所以使用next({})来引发一次新的路由，再过一遍beforeEach()就可以正常去想去的地方了
          })
        }).catch(err => {
          console.log(err)
        })
      } else {
        next();
      }
    }
  } else {
    next({
      path: '/login',
      query: {
        redirect: to.fullPath
      }
    });
  }
})


new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')

