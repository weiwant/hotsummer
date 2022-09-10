import Vue from 'vue'
import App from './App.vue'
import router from './router'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import '@/utils/axios'
import axios from 'axios'

Vue.config.productionTip = false
Vue.use(ElementUI);
Vue.prototype.$axios = axios;
Vue.prototype.$domainName = "http://abcd.vaiwan.com"  //axios请求域名
Vue.prototype.$currentYear = (new Date()).getFullYear()  //当前的年份
//当前用户的身份一定要延迟到home界面的created处获取，main.js会在login.vue之前执行
Vue.prototype.$getUser = () => {
  Vue.prototype.$currentUser = localStorage.getItem("userName"); //当前用户的姓名
  Vue.prototype.$currentIdentity = localStorage.getItem("userIdentify"); //当前用户身份
}

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

new Vue({
  router,
  render: h => h(App)
}).$mount('#app')

