import request from '@/utils/request'

export function getTeachingWorkload_paged(data) {
    return request({
        url: '/total/records/page',
        method: "post",
        data
    })
}