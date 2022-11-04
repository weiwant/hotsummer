<template>
  <div class="app-right-wrapper">
    <EditDDL />
    <div class="app-section-title">已提交上报</div>
    <div class="app-section filter">
      <TableFilter :filters="filters" @search="search" />
    </div>

  </div>
</template>

<script>
import EditDDL from './components/EditDDL.vue'
import TableFilter from "../../components/table/TableFilter.vue";
export default {
  name: "AuditWorkload",
  components: {
    TableFilter,
    EditDDL,
  },
  data() {
    return {
      //初始化TableFilter
      filters: this.$store.getters.tableFilters_special,
      //查询条件
      yearChosen: `${this.$store.getters.currentYear}`,
      filterAdded: [],
      //分页
      totalPage: 10,
      currentPage: 1,
      //查询结果
      headerChosen: [], //选择要展示的表头组
      tableHeaderGroups: this.$store.getters.tableHeaderGroups_special, //表头组集合
    };
  },
  computed: {
    //根据表头组集合，计算传递给TableHeaderSelection的用于展示的表头组字符串数组
    headerNameGroups() {
      const result = [];
      for (let group of this.tableHeaderGroups) {
        let s = "";
        for (let i = 0; i < group.length; i++) {
          s += group[i].key;
          if (i === group.length - 1) break;
          s += "，";
        }
        result.push(s);
      }
      return result;
    },
    //根据chosen数组值，计算传递给table组件的表头
    tableHeaderDisplayed() {
      const result = [];
      for (let i = 0; i < this.headerChosen.length; i++) {
        if (this.headerChosen[i]) {
          for (let item of this.tableHeaderGroups[i]) result.push(item);
        }
      }
      return result;
    },
  },
  methods: {
    /******下载某年的特殊工作量附件******/
    downloadSpecialWorkloadFiles() {
      const formData = new FormData();
      var year = this.$data.yearChosen;

      formData.append("year", year);

      this.$axios
        .post(`${this.$domainName}/file/download-by-year`, formData, {
          responseType: "blob",
        })
        .then((file) => {
          let content = file.data;
          // 组装a标签
          let elink = document.createElement("a");
          // 设置下载文件名
          elink.download = this.$data.yearChosen + "年度特殊工作量审批记录.zip";
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
      formData.append("statusList", JSON.stringify(["已提交", "已审核"]));
      // 如果搜索值不为空
      if (this.searchValueChosen != "") {
        switch (this.searchKeywordChosen) {
          case "教学业绩类型":
            formData.append("type", this.searchValueChosen);
            break;
          case "申报人":
            formData.append("declarantName", this.searchValueChosen);
            break;
        }
      }
      this.$axios
        .post(`/special-join/select`, formData)
        .then((res) => {
          console.log(res);
          if (res.data.response.code == 200) {
            this.dataExists = true;
            this.tableData = res.data.data.records;
            this.allPageCount = res.data.data.pages;
            this.totalItems = res.data.data.total;
          } else {
            this.dataExists = false;
            this.tableData = [];
            this.allPageCount = 1;
            this.totalItems = 0;
            if (this.searchValueChosen == "") {
              this.noDataHint = `暂无${this.yearChosen}年度的数据！`;
            } else {
              this.noDataHint = `暂无${this.yearChosen}年度，${this.searchKeywordChosen}为${this.searchValueChosen}的数据！`;
            }
          }
        })
        .catch((err) => {
          this.dataExists = false;

          this.noDataHint = "获取数据出错！";
        });
    },
    //选择了年份和搜索条件，并给出了搜索值后确认
    confirmSearchValue(year, searchKeyword, searchValue) {
      //在请求数据前设置好参数
      this.yearChosen = year;
      this.searchKeywordChosen = searchKeyword;
      this.searchValueChosen = searchValue;
      this.currentPage = 1;
      this.getTableData();
    },
  },
  created() {
    //获取当年数据
    this.yearChosen = this.$store.getters.currentYear;
    this.currentPage = 1;
    this.getTableData();
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