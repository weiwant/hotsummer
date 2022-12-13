const getters = {
    // 用户身份
    identity: state => state.user.identity,
    token: state => state.user.token,
    // 路由
    addRoutes: state => state.permission.addRoutes,
    routes: state => state.permission.routes,
    //教学工作量
    currentYear: () => `${new Date().getFullYear()}`,
    tableHeaderGroups_teaching: state => state.teaching_workload.headerGroups,
    workloadType_teaching: state => state.teaching_workload.type,
    // 特殊工作量
    tableFilters_special: state => state.special_workload.filters,
    constantHeaders_special: state => state.special_workload.constantHeaders,
    specificHeaders_special: state => state.special_workload.specificHeaders,
    formItems_special: state => state.special_workload.formItems,
    workloadTypes_special: state => state.special_workload.workloadTypes,
    teacherListHeaders_special: state => state.special_workload.teacherListHeaders,
    workloadStatus_special: state => state.special_workload.workloadStatus,
    previewHeaders_special: state => state.special_workload.previewHeaders
}

export default getters