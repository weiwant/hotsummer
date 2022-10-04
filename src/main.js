import Vue from 'vue'


import App from './App.vue'
import axios from 'axios'
import router from './router'
import store from './store'


import './plugins'

import 'normalize.css/normalize.css'
import './style/index.css'
import './style/button.css'
import './style/transition.css'
import './style/table.css'

Vue.config.productionTip = false


Vue.prototype.$axios = axios;


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
  store,
  render: h => h(App)
}).$mount('#app')

