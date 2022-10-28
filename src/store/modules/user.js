import { login, getInfo } from '@/api/user'
import { getToken, setToken } from '@/utils/auth'  //cookie



const state = {
    token: getToken(),
    username: '',
    identity: -1,

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
                commit('setToken', data.token)   //store
                setToken(data.token)   //cookie
                resolve()
            }).catch(err => {
                reject(err)
            })
        })
    },
    getInfo({ commit, state }) {
        return new Promise((resolve, reject) => {
            getInfo(state.token).then(res => {
                const { data } = res;
                commit('setIdentity', data.identity)
                resolve(data)
            }).catch(err => {
                reject(err)
            })
        })
    },

}

export default {
    namespaced: true,
    state,
    mutations,
    actions
}