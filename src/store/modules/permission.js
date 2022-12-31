import { constantRoutes, teacherRoutes, managerRoutes } from '@/router/routes'
import router, { resetRouter } from '@/router/index'

const state = {
    routes: constantRoutes,
    addRoutes: [],
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

}

const actions = {
    generateRoutes({ commit }, identity) {
        if (identity === 0) {
            commit('setRoutes', teacherRoutes);
        } else if (identity === 1) {
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