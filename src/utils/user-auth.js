import Cookies from 'js-cookie'

const TokenKey = 'WorkingloadMIS-Token'
const userInfoKey = 'WorkingloadMIS-UserInfo'
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
export function getUserInfo() {
    return Cookies.get(userInfoKey)
}

export function setUserInfo(userInfo) {
    return Cookies.set(userInfoKey, userInfo)
}

export function removeUserInfo() {
    return Cookies.remove(userInfoKey)
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
