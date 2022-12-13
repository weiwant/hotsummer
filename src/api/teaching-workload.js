import request from '@/utils/request'

export function getTeachingWorkload(data) {
    return request({
        url: '/total/records',
        method: "post",
        data
    })
}

export function uploadTeachingWorkload(data, type) {
    return request({
        url: `/file/upload/${type}`,
        method: 'post',
        data,
        headers: { "Content-Type": "multipart/form-datas" }
    })
}