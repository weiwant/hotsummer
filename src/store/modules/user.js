import { login } from '@/api/user'
import { getToken, setToken } from '@/utils/auth'  //cookie

const state = {
    token: getToken(),
    username: '',
    identity: '',
}

const mutations = {
    setToken(state, token) {
        state.token = token
    },
    setUsername(state, username) {
        state.username = username
    },
    setUserIdentity(state, identity) {
        state.identity = identity
    }
}

const actions = {
    login({ commit }, username, password) {
        return new Promise((resolve, reject) => {
            login({ username: username, password: password }).then(res => {
                const { data } = res;  //传过来的是最开始的res.data，实际数据还要再提取一个data
                //设置token
                commit('setToken', data.token)
                setToken(data.token)
                //设置用户信息
                commit('setUsername', username)
                commit('setUserIdentity', data.identify)
                resolve()
            }).catch(err => {
                reject(err)
            })
        })
    }
}

export default {
    namespaced: true,
    state,
    mutations,
    actions
}