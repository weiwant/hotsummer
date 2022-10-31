const getters = {
    identity: state => state.user.identity,
    token: state => state.user.token,
    addRoutes: state => state.permission.addRoutes,
    routes: state => state.permission.routes,
    currentYear: () => `${new Date().getFullYear()}`,
    tableFilters_teaching: state => state.teaching_workload.fiters,
    tableHeaderGroups_teaching: state => state.teaching_workload.headerGroups,
}

export default getters