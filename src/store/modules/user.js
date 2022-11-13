import { login, logout } from '@/api/user'
import { getToken, setToken, removeToken, getUsername, setUsername, removeUsername, getIdentity, setIdentity, removeIdentity } from '@/utils/user-auth'  //cookie




const state = {
    token: getToken(),   //初始值从cookie内获取，没有的话就是undefined
    username: getUsername(),
    identity: getIdentity(),
}

const mutations = {
    setToken(state, token) {
        state.token = token
    },
    setUsername(state, username) {
        state.username = username
    },
    setIdentity(state, identity) {
        state.identity = identity
    },

}

const actions = {
    login({ commit }, formData) {
        return new Promise((resolve, reject) => {
            console.log(`服务器接收到了登录请求，登录名：${formData.username},密码：${formData.password}`)
            console.log('验证通过,发送token');
            //token
            commit('setToken', 'adsicadsfw32323');
            setToken('adsicadsfw32323');
            //个人信息
            commit('setUsername', '张三');
            setUsername('张三');
            //身份
            commit('setIdentity', 0);
            setIdentity(0)
            resolve();
            // console.log('验证失败');
            // reject('用户名或密码错误');


            // login({ username: username, password: password }).then(res => {
            //     const { data } = res;  //传过来的是最开始的res.data，实际数据还要再提取一个data
            //     //设置token
            //     commit('setToken', data.token)   //store
            //     setToken(data.token)   //cookie
            //     resolve()
            // }).catch(err => {
            //     reject(err)
            // })
        })
    },
    logout({ commit }) {
        return new Promise((resolve, reject) => {

            commit('setToken', '');
            removeToken();
            commit('setIdentity', undefined);
            removeIdentity();
            commit('setUsername', '');
            removeUsername();
            resolve();

            // logout().then(() => {
            //     commit('setToken', '');
            //     removeToken();
            //     commit('setIdentity', undefined);
            //     removeIdentity();
            //     commit('setUsername', '');
            //     removeUsername();
            //     resolve();
            // }).catch(err => {
            //     console.log(err);
            //     reject();
            // })
        })

    }

}

export default {
    namespaced: true,
    state,
    mutations,
    actions
}