import Cookies from 'js-cookie'

const TokenKey = 'WorkingloadMIS-Token'
const usernameKey = 'WorkingloadMIS-Username'
const identityKey = 'WorkingloadMIS-Identity'


//token
export function getToken() {
    return Cookies.get(TokenKey)
}

export function setToken(token) {
    return Cookies.set(TokenKey, token)
}

export function removeToken() {
    return Cookies.remove(TokenKey)
}



//用户姓名
export function getUsername() {
    return Cookies.get(usernameKey)
}

export function setUsername(username) {
    return Cookies.set(usernameKey, username)
}

export function removeUsername() {
    return Cookies.remove(usernameKey)
}


//用户身份
export function getIdentity() {
    return parseInt(Cookies.get(identityKey))
}

export function setIdentity(identity) {
    return Cookies.set(identityKey, identity)
}

export function removeIdentity() {
    return Cookies.remove(identityKey)
}
