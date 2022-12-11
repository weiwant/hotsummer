import Cookies from 'js-cookie'

const state = {
    sidebar: {
        opened: Cookies.get('sidebarStatus') ? !!+Cookies.get('sidebarStatus') : true,
        withoutAnimation: true,
    },
    device: 'desktop'
}

const mutations = {
    CLOSE_SIDEBAR: (state, withoutAnimation) => {
        state.sidebar.opened = false,
            state.sidebar.withoutAnimation = withoutAnimation
    },
    TOGGLE_SIDEBAR: (state, withoutAnimation) => {
        state.sidebar.opened = !state.sidebar.opened,
            state.sidebar.withoutAnimation = withoutAnimation

        Cookies.set('sidebarStatus', state.sidebar.opened ? '1' : '0')
    },
    TOGGLE_DEVICE: (state, device) => {
        state.device = device;
    }

}

const actions = {
    closeSidebar({ commit }, { withoutAnimation }) {
        commit('CLOSE_SIDEBAR', withoutAnimation)
    },
    toggleSidebar({ commit }, { withoutAnimation }) {
        commit('TOGGLE_SIDEBAR', withoutAnimation)
    },
    toggleDevice({ commit }, device) {
        commit('TOGGLE_DEVICE', device)
    }
}

export default {
    namespaced: true,
    state,
    mutations,
    actions
}