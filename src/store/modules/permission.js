import { constantRoutes, teacherRoutes, managerRoutes } from '@/router/index'

const state = {
    routes: constantRoutes,
    addRoutes: []
}

const mutations = {
    setRoutes: (state, routes) => {
        state.addRoutes = routes;
        state.routes = constantRoutes.concat(routes)
    }
}

const actions = {
    generateRoutes({ commit }, identity) {
        //如果是老师
        if (identity === 0) {
            commit('setRoutes', teacherRoutes)
        } else if (identity === 1 || identity === 2) {  //如果是管理员
            commit('setRoutes', managerRoutes)
        }
    }
}

export default {
    namespaced: true,
    state,
    mutations,
    actions
}