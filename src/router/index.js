import Vue from 'vue'
import VueRouter from 'vue-router'
import { constantRoutes } from './routes'     //routes文件内存储了具体的路由数组
import store from '@/store'


Vue.use(VueRouter)



/* 路由首先装载的是教师和管理员共有的路由 */
const createRouter = () => new VueRouter({
  mode: 'history',
  routes: constantRoutes
})

const router = createRouter();


router.beforeEach((to, before, next) => {
  /***如果用户已经登录***/
  if (store.getters.token) {
    if (to.path === '/login') {
      next({ path: '/userinfo' });
    } else {
      //如果是刚进app，用户路由还没生成
      if (store.getters.addRoutes.length === 0) {
        //路由数据是让vuex管理的，所以最后生成完整路由表的事情要让vuex来做，router应该作为一个数据请求者，从store.getters那拿过来以后，在填入自身
        store.dispatch('permission/generateRoutes', store.getters.identity).then(() => {
          router.addRoutes(store.getters.addRoutes)
          next({ ...to, replace: true }) //再调一次next而非直接导航到目的地，确保addRoutes已经完成
        }).catch(err => {
          console.log(err)
        })
      } else {
        next();
      }
    }
    /***如果用户还没登录***/
  } else {
    if (to.path === '/login') next()    //在没有token的情况下进入登录页，直接调next()，结束本轮导航
    else next(`/login?redirect=${to.fullPath}`)    //在没有token的情况下进别的页面，重新导航至登录页（此时会再次引发beforeEach调用，并走上面一行的分支），并将本来想去的目的地记录在query中
  }
})

router.afterEach((to, from) => {
  store.commit('permission/updateCurrentRoute');
})

export default router;


//用户退出登录以后，app的router需要更换为初始状态（否则再次登录后addroutes会在之前登出的基础上做，必然是有问题的）
export function resetRouter() {
  const newRouter = createRouter();
  router.matcher = newRouter.matcher
}







