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
import { removeToken, removeIdentity, removeUsername } from '@/utils/user-auth'
removeToken(); removeIdentity(); removeUsername()
console.log('执行main.js')

router.beforeEach((to, before, next) => {
  console.log(`执行路由守卫，token:${store.getters.token}`)
  if (store.getters.token) {   //如果用户已经登录
    console.log('有token')
    if (to.path === '/login') {
      next({ path: '/' });
    } else {
      console.log('检查路由')
      //如果是刚进app，用户路由还没生成
      if (store.getters.addRoutes.length === 0) {
        console.log('没生成')
        console.log(`identity:${store.getters.identity}`)
        store.dispatch('permission/generateRoutes', store.getters.identity).then(() => {
          console.log('addRoutes:')
          console.log(store.getters.addRoutes)
          router.addRoutes(store.getters.addRoutes)
          next({ ...to, replace: true })
        }).catch(err => {
          console.log(err)
        })
      } else {
        console.log('生成了')
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

