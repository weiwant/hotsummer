import request from '@/utils/request'


//提交申报截止日期设置
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

//批量下载年度审核附件
export function downloadFiles_yearly(data) {
    return request({
        url: '/file/download-by-year',
        method: 'post',
        data,
        responseType: "blob",
    })
}


//分页条件查询，获取审核列表
export function getAuditingTableData(data) {
    return request({
        url: '/special-join/select',
        method: 'post',
        data
    })
}

//导出审核结果
export function exportAuditingResult(data) {
    return request({
        url: '/scores/calculate',
        method: 'post',
        data
    })
}

