import request from '@/utils/request'

export function setDDL(data) {
    return request({
        url: '/deadline/set',
        method: "post",
        data
    })
}

export function getDDL(data) {
    return request({
        url: '/deadline/get',
        method: "post",
        data
    })
}