import { constantRoutes, teacherRoutes, managerRoutes } from '@/router/index'
import router, { resetRouter } from '@/router/index'

const state = {
    routes: constantRoutes,
    addRoutes: [],
    currentRoute: undefined,  //当前路由，主要用于topbar的title更改，更改操作无法直接放在router-link的点击事件处理函数内，会有延迟
}

const mutations = {
    setRoutes: (state, routes) => {
        state.addRoutes = routes;
        state.routes = constantRoutes.concat(routes)
    },
    resetRouter: (state) => {
        state.addRoutes = [];
        resetRouter();
    },
    updateCurrentRoute: (state) => {
        state.currentRoute = router.currentRoute;
    }
}

const actions = {
    generateRoutes({ commit }, identity) {
        if (identity === 0) {
            commit('setRoutes', teacherRoutes);
        } else if (identity === 1 || identity === 2) {
            commit('setRoutes', managerRoutes);
        }
    }
}

export default {
    namespaced: true,
    state,
    mutations,
    actions
}