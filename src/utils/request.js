import axios from 'axios'
import { Message } from 'element-ui'
import store from '@/store'
import { getToken, removeToken } from '@/utils/user-auth'
import router from '@/router'

const instance = axios.create({
    baseURL: "http://139.196.170.184:8080",
    timeout: 5000,
});


// 请求拦截器
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



//响应拦截器
//所有错误的用户Message提示都在这里处理，各个组件在错误处理上只需要关注自己要做什么
instance.interceptors.response.use(response => {
    console.log(response)
    const res = response.data
    const code = res.response.code
    //HTTP正常但业务逻辑错误
    if (code !== 200) {

        //无内容感觉不算错误，不需要给用户message错误提示
        if (code == 204 && res.response.message == '无内容') return Promise.reject(new Error(res.response.message));

        Message({
            message: res.response.message,
            type: 'error',
            duration: 2000
        })

        //token身份验证出问题（token过期、token无效）
        if (code === 401 || code === 403) {
            store.commit('user/setToken', null);
            removeToken();
            router.push('/login')
        }
        return Promise.reject(new Error(res.response.message))
    } else {
        //一切正常则返回
        return res.data
    }

}, err => {
    // HTTP错误
    Message({
        message: err.message,
        type: 'error',
        duration: 2000
    })
    return Promise.reject(err);
});

export default instance