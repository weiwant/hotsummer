import Vue from 'vue'
import Vuex from 'vuex'
import { login } from '@/api/user'
import { getToken, setToken } from '@/utils/auth'  //cookie


Vue.use(Vuex)

const store = new Vuex.Store({
    state: {
        token: getToken(),
        username: '',
        identity: '',
    },
    mutations: {
        setToken(state, token) {
            state.token = token
        },
        setUsername(state, username) {
            state.username = username
        },
        setUserIdentity(state, identity) {
            state.identity = identity
        }
    },
    actions: {
        login({ commit }, userInput) {
            const { username, password } = userInput;
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
})

export default store