const getters = {
    identity: state => state.user.identity,
    token: state => state.user.token,
    accessRoutes: state => state.permission.addRoutes,
    currentYear: () => `${new Date().getFullYear()}`,
}

export default getters