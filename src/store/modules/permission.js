import { constantRoutes, teacherRoutes, managerRoutes } from '@/router/index'

const state = {
    routes: constantRoutes,
    addRoutes: []
}

const mutations = {
    setRoutes: (state, routes) => {
        state.addRoutes = routes;
        state.routes = constantRoutes.concat(routes)
    },
}

const actions = {
    generateRoutes({ commit }, identity) {
        console.log('generating')
        console.log(typeof identity)
        if (identity === 0) {
            commit('setRoutes', teacherRoutes);
        } else if (identity === 1 || identity === 2) {
            console.log(managerRoutes);
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