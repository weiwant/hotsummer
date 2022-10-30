import Vue from 'vue'


import App from './App.vue'
import axios from 'axios'
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
import './style/table.css'

Vue.config.productionTip = false

Vue.prototype.$axios = axios;

// debug用的
import { removeToken } from '@/utils/auth'

removeToken();

router.beforeEach((to, before, next) => {
  if (store.getters.token) {
    if (to.path === '/login') {
      next({ path: '/' });
    } else {
      //如果还没有拉取用户身份，需先拉取
      if (store.getters.identity === -1) {
        store.dispatch('user/getInfo').then((data) => {
          store.dispatch('permission/generateRoutes', data.identity).then(() => {
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
    if (to.path === '/login') next()    //在没有token的情况下进入登录页，直接调next()，结束本轮导航
    else next(`/login?redirect=${to.fullPath}`)    //在没有token的情况下进别的页面，重新导航至登录页（此时会再次引发beforeEach调用，并走上面一行的分支），并将本来想去的目的地记录在query中
  }
})



new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')

