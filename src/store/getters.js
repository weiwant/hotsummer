const getters = {
    identity: state => state.user.identity,
    token: state => state.user.token,
    addRoutes: state => state.permission.addRoutes,
    routes: state => state.permission.routes,
    currentYear: () => `${new Date().getFullYear()}`,
}

export default getters