import { login, logout } from '@/api/user'
import { getToken, setToken, removeToken, getIdentity, setIdentity, removeIdentity, getUserInfo, setUserInfo, removeUserInfo } from '@/utils/user-auth'  //cookie




const state = {
    token: getToken(),   //初始值从cookie内获取，没有的话就是undefined
    identity: getIdentity(),
    userInfo: getUserInfo(),
}

const mutations = {
    setToken(state, token) {
        state.token = token
    },
    setUserInfo(state, userInfo) {
        state.userInfo = userInfo
    },
    setIdentity(state, identity) {
        state.identity = identity
    },

}

const actions = {
    login({ commit }, formData) {
        return new Promise((resolve, reject) => {
            login(formData).then(res => {
                //token
                commit('setToken', res.token)
                setToken(res.token)
                //身份权限
                commit('setIdentity', res.identify);
                setIdentity(res.identify);
                //个人信息
                let userInfo = {
                    name: res.username,
                    faculty: res.faculty,
                    gender: res.gender,
                    rank: res.rank,
                    title: res.title
                }
                commit('setUserInfo', userInfo);
                setUserInfo(userInfo);
                resolve();
            }).catch(err => {
                reject(err)
            })
        })
    },
    logout({ commit }) {
        return new Promise((resolve, reject) => {
            logout().then(() => {
                commit('setToken', null);
                removeToken();
                commit('setIdentity', null);
                removeIdentity();
                commit('setUserInfo', null);
                removeUserInfo();
                resolve();
            }).catch(err => {
                reject(err);
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