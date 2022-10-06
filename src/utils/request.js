import axios from 'axios'
import { Message } from 'element-ui'
import store from '@/store'
import { getToken } from '@/utils/auth'

const instance = axios.create({
    baseURL: "http://116.205.224.180:8080",
    timeout: 5000,
});


instance.interceptors.request.use(config => {
    if (store.state.token) {
        config.headers['token'] = getToken();
    }
    return config;
}, err => {
    console.log(err);
    Message({
        message: '请求发送失败！',
        type: 'error',
        duration: 2000
    })
    return Promise.reject(err);
});


instance.interceptors.response.use(response => {
    console.log(response)
    const res = response.data
    const code = res.response.code
    if (code !== 200) {
        Message({
            message: res.response.message,
            type: 'error',
            duration: 2000
        })
        //token身份验证出问题（token过期、token无效）
        if (code === 401 || code === 403) {

        }
        return Promise.reject(new Error(res.response.message))
    } else {
        return res
    }

}, err => {
    console.log(err)
    Message({
        message: err.message,
        type: 'error',
        duration: 2000
    })
    return Promise.reject(err);
});

export default instance