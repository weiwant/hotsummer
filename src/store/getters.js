const getters = {
    identity: state => state.user.identity,
    token: state => state.user.token,
    addRoutes: state => state.permission.addRoutes,
    routes: state => state.permission.routes,
    currentRoute: state => state.permission.currentRoute,
    currentYear: () => `${new Date().getFullYear()}`,
    tableFilters_teaching: state => state.teaching_workload.filters,
    tableHeaderGroups_teaching: state => state.teaching_workload.headerGroups,
}

export default getters