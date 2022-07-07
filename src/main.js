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
Vue.prototype.$currentUser = localStorage.getItem("userName"); //当前用户的姓名
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
// 特殊工作量类型(BB12文件里没给)
const SpecialWorkloadType = {
  BB1: "课程建设BB1",
  BB2: "教研项目BB2",
  BB3: "教学成果奖BB3",
  BB4: "教学竞赛奖励BB4",
  BB5: "发表教学研究论文BB5",
  BB6: "出版教材BB6",
  BB7: "教学名师BB7",
  BB8: "指导优秀毕业论文BB8",
  BB9: "指导学生竞赛获奖BB9",
  BB10: "指导学生科研立项并顺利完成项目BB10",
  BB11: "教学兼职BB11",
  BB13: "本科生培养活动BB13",
  BB14: "本科生主考之外的监考计BB14",
  BB15: "其他BB15"
}
Vue.prototype.$SpecialWorkloadType = SpecialWorkloadType

new Vue({
  router,
  el: '#app',
  render: h => h(App)
}).$mount('#app')

