import axios from 'axios'
import { Message, MessageBox } from 'element-ui'
import store from '@/store'
import { getToken, removeToken } from '@/utils/user-auth'
import router from '@/router'

const instance = axios.create({
    baseURL: "http://139.196.170.184:8080",
    // timeout: 5000,
});



instance.interceptors.request.use(config => {
    // 在登录的前提下，添加token
    // 没有token不代表请求一定会失败，有些接口是不需要登录也可以正常访问的，比如login
    if (store.getters.token) {
        config.headers['token'] = getToken();
    }
    return config;
}, err => {
    // 请求错误的用户提示
    Message({
        message: '请求发送失败！',
        type: 'error',
        duration: 2000
    })
    return Promise.reject(err);
});



instance.interceptors.response.use(res => {
    console.log('res', res)
    const response = res.data.response
    const code = response.code
    //HTTP正常但业务逻辑错误
    if (code !== 200) {

        //无内容
        if (code == 204) return null;

        Message({
            message: response.message || '发生未知错误！',
            type: 'error',
            duration: 2000
        })

        //token身份验证出问题（token过期、token无效）
        if (code === 401 || code === 403) {

            MessageBox({
                title: '提示',
                message: '身份验证错误，请重新登录！',
                type: 'info',
                callback: () => {
                    store.commit('user/setToken', null);
                    removeToken();
                    router.push(`/login?redirect=${router.currentRoute.fullPath}`)
                },
                showClose: false,
            })
        }

        return Promise.reject(res.data.response.message)

    } else {
        return res.data.data
    }

}, err => {
    // HTTP错误
    let message;
    switch (err.code) {
        case 'ERR_NETWORK':
            message = "网络故障！";
            break;
        default:
            message = err.message;
    }
    Message({
        message: message,
        type: 'error',
        duration: 2000
    })
    return Promise.reject(err);
});

export default instance