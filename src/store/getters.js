const getters = {
    identity: state => state.user.identity,
    token: state => state.user.token,
    addRoutes: state => state.permission.addRoutes,
    routes: state => state.permission.routes,
    currentRoute: state => state.permission.currentRoute,
    currentYear: () => `${new Date().getFullYear()}`,
    tableFilters_teaching: state => state.teaching_workload.filters,
    tableHeaderGroups_teaching: state => state.teaching_workload.headerGroups,
    workloadType_teaching: state => state.teaching_workload.type,
    tableFilters_special: state => state.special_workload.filters,
    constantHeaders_special: state => state.special_workload.constantHeaders,
    specificHeaders_special: state => state.special_workload.specificHeaders,
    formItems_special: state => state.special_workload.formItems,
    workloadTypes_special: state => state.special_workload.workloadTypes,
    teacherListHeaders_special: state => state.special_workload.teacherListHeaders
}

export default getters