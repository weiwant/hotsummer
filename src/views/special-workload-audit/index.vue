<template>
  <div class="app-view-container">
    <EditDDL />
    <div class="app-section-title">已提交上报</div>
    <div class="app-section filter">
      <TableFilter :filters="filters" @search="search" />
    </div>
    <AuditTable />
  </div>
</template>

<script>
import EditDDL from './components/EditDDL.vue'
import TableFilter from "@/components/table/TableFilter.vue";
import AuditTable from "./components/AuditTable.vue"
import { getAuditingTableData } from "@/api/special-workload/audit"
export default {
  name: "AuditWorkload",
  components: {
    TableFilter,
    EditDDL,
    AuditTable
  },
  data() {
    return {
      //初始化TableFilter
      filters: this.$store.getters.tableFilters_special,
      //查询条件
      yearChosen: `${this.$store.getters.currentYear}`,
      filterAdded: [],
      //查询结果
      tableData: [],
      //分页
      totalItems: 0,
      totalPage: 0,
      currentPage: 1,
    };
  },
  provide() {
    //Vue2让inject/provide具有reactivity,https://stackoverflow.com/questions/65718651/how-do-i-make-vue-2-provide-inject-api-reactive
    const tableInfo = {}
    Object.defineProperty(tableInfo, 'yearChosen', {
      get: () => this.yearChosen
    })
    return {
      tableInfo
    }
  },

  methods: {
    /******下载某个项目的特殊工作量附件******/
    downloadSpecificFile(index) {
      var _this = this;
      const formData = new FormData();
      var id = this.$data.tableData[index].id;

      formData.append("id", id);

      this.$axios
        .post(`${this.$domainName}/file/download-package`, formData, {
          responseType: "blob",
        })
        .then((file) => {
          console.log(file);
          let content = file.data;
          // 组装a标签
          let elink = document.createElement("a");
          // 设置下载文件名
          elink.download =
            this.$data.tableData[index].declarantName +
            this.$data.yearChosen +
            "年度特殊工作量上报附件.zip";
          elink.style.display = "none";
          let blob = new Blob([content], { type: "application/zip" });
          elink.href = URL.createObjectURL(blob);
          document.body.appendChild(elink);
          elink.click();
          document.body.removeChild(elink);
        })
        .catch(function (error) {
          console.log(error);
        });
    },
    //编辑
    edit(index) {
      // 调整选中的行的样式与状态
      document
        .querySelectorAll(`table tr:nth-child(${index + 1}) .editable`)
        .forEach((item) => {
          item.classList.add("editing");
          item.disabled = false;
        });
      document.querySelector(
        `table tr:nth-child(${index + 1}) #edit`
      ).style.display = "none";
      document.querySelector(
        `table tr:nth-child(${index + 1}) #save`
      ).style.display = "block";
    },
    //保存编辑
    save(index) {
      // 调整选中的行的样式与状态
      document
        .querySelectorAll(`table tr:nth-child(${index + 1}) .editable`)
        .forEach((item) => {
          item.classList.remove("editing");
          item.disabled = true;
        });
      document.querySelector(
        `table tr:nth-child(${index + 1}) #edit`
      ).style.display = "block";
      document.querySelector(
        `table tr:nth-child(${index + 1}) #save`
      ).style.display = "none";
      this.tableData[index].status = "已审核"; //修改审核状态
      //向后端同步数据
      this.$axios
        .post(
          `${this.$domainName}/special-workload/mark`,
          this.tableData[index]
        )
        .then((res) => {
          console.log(res);
        });
    },
    //分页查询
    getTableData() {
      const formData = new FormData();
      formData.append("year", this.yearChosen);
      formData.append("pageNumber", this.currentPage);
      for (let item of this.filterAdded) {
        if (item.type === "statusList" && item.value === "全部") {
          formData.append("statusList", JSON.stringify(["已提交", "已审核"]));
          continue;
        }
        formData.append(item.type, item.value)
      }
      getAuditingTableData(formData).then(data => {
        this.tableData = data.records;
        this.totalPage = data.pages;
        this.totalItems = data.total;
      }).catch(() => {
        this.tableData = [];
        this.allPageCount = 1;
        this.totalItems = 0;
      })

    },
    //选择了年份和搜索条件，并给出了搜索值后确认
    search(yearChosen, filterAdded) {
      this.yearChosen = yearChosen;
      this.filterAdded = JSON.parse(JSON.stringify(filterAdded)); //深复制，当前组件保存的added永远是上一次点击查询时的
      this.getTableData();
    },
    created() {
      // this.getTableData();
    }
  },

};
</script>

<style scoped>
.app-section.filter {
  padding: 0px 15px;
}

.tableWrapper {
  position: relative;
  overflow: scroll;
  height: 86vh;
  padding-bottom: 15px;
  transition: all 0.5s;
}


table.specialWorkloadTable {
  width: 4000px;
  border-spacing: 0;
}

table.specialWorkloadTable thead {
  position: sticky;
  top: 0;
  font-size: 10px;
  font-weight: 500;
  background-color: rgb(239, 241, 247);
}

table.specialWorkloadTable tbody {
  font-size: 13px;
  background-color: transparent;
  padding: 3px 5px;
}

table.specialWorkloadTable tbody td {
  vertical-align: top;
  padding: 0;
  padding-bottom: 10px;
}

table.specialWorkloadTable tbody td input {
  width: 100%;
  height: 30px;
  border: 1px solid rgba(128, 128, 128, 0.212);
  border-right: 0;
  border-radius: 0;
  text-align: center;
}

table.specialWorkloadTable tbody td label {
  font-weight: 500;
  font-size: 13px;
  margin-left: 5px;
}

table.specialWorkloadTable tbody td ul li {
  margin-bottom: 5px;
}

textarea {
  width: 100%;
  overflow: scroll;
  border-radius: 0;
  border: 1px solid rgba(128, 128, 128, 0.212);
}

table.specialWorkloadTable tbody td input.editing {
  border: 2px solid rgba(95, 120, 130, 0.32);
  border-radius: 5px;
}

textarea.editing {
  border: 2px solid rgba(95, 120, 130, 0.32);
  border-radius: 5px;
}

button.file {
  border: 0;
  text-decoration: underline;
  cursor: pointer;
  color: rgb(96, 157, 135);
}

button.file:hover {
  color: rgb(33, 164, 116);
}

button.edit {
  width: 50px;
  height: 25px;
  border: 1px solid rgba(128, 128, 128, 0.267);
  /* border-radius: 5px; */
}

.auditDownloadTool {
  margin-top: 20px;
  padding-top: 15px;
  max-width: 800px;
  border-top: 1px solid rgba(128, 128, 128, 0.185);
}
</style>