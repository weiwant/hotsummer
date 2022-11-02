import request from '@/utils/request'

export function getTeachingWorkload_paged(data) {
    return request({
        url: '/total/records/page',
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