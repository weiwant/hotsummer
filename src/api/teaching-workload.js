import request from '@/utils/request'
let qs = require('qs')


// 查询教学工作量
export function getTeachingWorkload(data) {
    return request({
        url: '/total/records',
        method: "post",
        data
    })
}

// 查询教学工作量（分页）
export function getTeachingWorkload_paged(data) {
    return request({
        url: '/total/records/page',
        method: 'post',
        data
    })
}

// 修改教学工作量
export function updateTeachingWorkload(url, data) {
    return request({
        url,
        method: 'post',
        data: qs.stringify(data),
    })
}

export function uploadTeachingWorkload(data, type) {
    return request({
        url: `/file/upload/${type}`,
        method: 'post',
        data,
        headers: { "Content-Type": "multipart/form-data" }
    })
}