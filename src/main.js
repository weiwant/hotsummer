import Vue from 'vue'


import App from './App.vue'
import axios from 'axios'
import router from './router'
import store from './store'


import './plugins'

import 'normalize.css/normalize.css'
import './style/index.css'
import './style/transition.css'

Vue.config.productionTip = false


Vue.prototype.$axios = axios;
Vue.prototype.$domainName = "http://116.205.224.180:8080";
Vue.prototype.$axios.defaults.baseURL = "http://116.205.224.180:8080"
Vue.prototype.$currentYear = (new Date()).getFullYear()  //当前的年份


//下载excel文件方法
Vue.prototype.$exportExcelFile = (dataToBeExported, header, filename) => {
  import("xlsx").then((XLSX) => {
    const data = XLSX.utils.json_to_sheet(dataToBeExported);
    const col = XLSX.utils.decode_range(data["!ref"]).e.c; //获取数据的列数（起始列序号为0）
    //替换第一行的值为中文表头
    for (let i = 0; i <= col; i++) {
      data[XLSX.utils.encode_cell({ r: 0, c: i })].v =
        header[i];
    }
    const wb = XLSX.utils.book_new();
    XLSX.utils.book_append_sheet(wb, data, "data");
    XLSX.writeFile(wb, filename + ".xlsx");
  });
}


//登陆
//当前用户的身份一定要延迟到home界面的created处获取，main.js会在login.vue之前执行
Vue.prototype.$getUser = () => {
  Vue.prototype.$currentUser = localStorage.getItem("userName"); //当前用户的姓名
  Vue.prototype.$currentIdentity = localStorage.getItem("userIdentify"); //当前用户身份
}
// localStorage.setItem("token", "");
axios.interceptors.request.use(function (config) {
  config.headers['token'] = localStorage.getItem("token") || '';
  return config;
}, error => {

})
axios.interceptors.response.use(response => {
  console.log(response);
  let msg = response.data.response.code;
  if (msg == 401) {
    alert("身份验证失败，请重新登陆！");
    router.push('/');
  } else if (msg == 403) {
    alert("无访问权限！");
    if (localStorage.getItem("userIdentify") == 0) {
      router.push('/TeacherHome');
    } else if (localStorage.getItem("userIdentify") == 1 || localStorage.getItem("userIdentify") == 2 || localStorage.getItem("userIdentify") == 3) {
      router.push('/ManagerHome');
    }
  }
  return response;
}, error => { })

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')

