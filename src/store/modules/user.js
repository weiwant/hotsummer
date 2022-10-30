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
    setIdentity(state, identity) {
        state.identity = identity
    }
}

const actions = {
    login({ commit }, username, password) {
        return new Promise((resolve, reject) => {
            console.log(`服务器接收到了登录请求，登录名：${username},密码：${password}`)
            console.log('验证通过');
            commit('setToken', 'adsicadsfw32323');
            setToken('adsicadsfw32323');
            resolve();
            // console.log('验证失败');
            // resolve('用户名或密码错误');


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
    getInfo({ commit, state }) {
        return new Promise((resolve, reject) => {
            console.log('后端接收到了获取用户信息的请求')
            console.log('请求成功')
            commit('setUsername', '张三');
            commit('setIdentity', 0);
            resolve({ identity: 0 })
            // console.log('请求失败')
            // reject('获取用户信息错误')

            // getInfo(state.token).then(res => {
            //     const { data } = res;
            //     commit('setIdentity', data.identity)
            //     resolve(data)
            // }).catch(err => {
            //     reject(err)
            // })
        })
    },

}

export default {
    namespaced: true,
    state,
    mutations,
    actions
}