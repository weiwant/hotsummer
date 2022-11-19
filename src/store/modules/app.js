const state = {
    sidebar: {
        opened: true,
        withAnimation: true,
    }
}

const mutations = {
    CLOSE_SIDEBAR: (state, withAnimation) => {
        state.sidebar.opened = false,
            state.sidebar.withAnimation = withAnimation
    },
    OPEN_SIDEBAR: (state, withAnimation) => {
        state.sidebar.opened = true,
            state.sidebar.withAnimation = withAnimation
    },
    TOGGLE_SIDEBAR: (state, withAnimation) => {
        state.sidebar.opened = !state.sidebar.opened,
            state.sidebar.withAnimation = withAnimation
    }
}

const actions = {
    closeSidebar({ commit }, withAnimation) {
        commit('CLOSE_SIDEBAR', withAnimation)
    },
    openSidebar({ commit }, withAnimation) {
        commit('OPEN_SIDEBAR', withAnimation)
    },
    toggleSidebar({ commit }, withAnimation) {
        commit('TOGGLE_SIDEBAR', withAnimation)
    },

}

export default {
    namespaced: true,
    state,
    mutations,
    actions
}