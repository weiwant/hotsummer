<template>
  <div class="componentWrapper">
    <div class="componentSectionTitle">查看教学工作量</div>
    <!-- 查询条件 -->
    <div class="tableFilter">
      <!--年份选择-->
      <label
        >年份
        <select v-model="yearChosen">
          <option
            v-for="index in 5"
            :key="index"
            :value="currentYear - index + 1"
          >
            {{ currentYear - index + 1 }}
          </option>
        </select>
      </label>
      <button class="universalBlueBtn confirmYearBtn" @click="confirmYear()">
        确&nbsp;认
      </button>
      <!-- 条件搜索 -->
      <select class="search" v-model="searchKeyword" id="">
        <option>上课老师</option>
      </select>
      <input
        class="search"
        type="search"
        v-model="searchValue"
        placeholder="查询内容"
        @keyup.enter="search()"
      />
      <button class="searchBtn universalBlueBtn" @click="search()"></button>
    </div>
    <!--查询结果的统计数据栏-->
    <div class="componentSubsection">
      <ul class="resultStatistics">
        <li>
          <div class="name">查询结果数</div>
          <div class="value">{{ totalItems }}</div>
        </li>
        <li>
          <div class="name">总页数</div>
          <div class="value">{{ allPageCount }}</div>
        </li>
      </ul>
    </div>
    <!--下载文件工具栏-->
    <div class="componentSubsection toolbar">
      <label class="customFileName"
        >文件名：
        <input type="text" v-model="exportFileName" />
      </label>
      <span class="defaultExportfileName"
        >（默认：{{ this.yearChosen }}年度工作量）</span
      >
      <button
        class="workloadFileProcessingBtn"
        @click="exportFile()"
        :disabled="!dataExists"
        :class="{ disabled: !dataExists }"
      >
         导出Excel至本地
      </button>
    </div>
    <!--数据列表-->
    <div class="workloadTableWrapper">
      <div class="noDataHint" v-if="!dataExists" v-html="noDataHint"></div>
      <table class="workloadDataTable">
        <thead v-if="dataExists">
          <tr>
            <th v-for="(item, index) in workloadTableHeader" :key="index">
              {{ item }}
            </th>
          </tr>
        </thead>
        <tbody>
          <tr
            class="tableDataLine"
            v-for="item in workloadTableData"
            :key="item.id"
          >
            <td v-for="(value, index) in item" :key="index">{{ value }}</td>
          </tr>
        </tbody>
      </table>
    </div>
    <div class="pagination">
      <button @click="pageBefore()" :disabled="noLessPage">&lt;</button>
      第
      <input v-model="currentPage" type="text" @keyup.enter="choosePage()" />
      页
      <button @click="pageAfter()" :disabled="noMorePage">&gt;</button>
    </div>
  </div>
</template>

<script>
import axios from "axios";
export default {
  name: "CheckWorkload",
  data() {
    return {
      //查询条件
      currentYear: this.$currentYear,
      yearChosen: this.$currentYear, //年份
      searchFilterRequired: false,
      searchKeyword: "上课老师",
      searchValue: "",
      //pagination
      currentPage: 0,
      allPageCount: 0,
      //查询结果
      workloadTableHeader: [
        "学年",
        "辅助",
        "计算用学时",
        "课程性质解释",
        "计算机用时",
        "课程名称",
        "课程性质",
        "课程号",
        "学分",
        "折扣",
        "实验安排",
        "实验课时",
        "教分",
        "合课单位",
        "实验室核对结果",
        "上课教师名字",
        "教师职称",
        "专业",
        "折扣前BA1系数",
        "原始教分",
        "其他教师名",
        "计划学院",
        "实践课时",
        "备注",
        "学期",
        "是否为特殊班级",
        "是否全英教学",
        "上课人数",
        "年级",
        "教学班",
        "BA1系数",
        "开课学院",
        "理论课时",
      ],
      workloadTableData: [],
      totalItems: 0,
      dataExists: false,
      noDataHint: "", //“暂无数据”提示
      //文件导出
      dataTobeExported: [],
      exportFileName: "",
    };
  },
  computed: {
    noLessPage() {
      return this.currentPage == 0 || this.currentPage == 1;
    },
    noMorePage() {
      return this.currentPage == this.allPageCount;
    },
  },
  methods: {
    //获取某学年学期数据
    getTableData() {
      axios
        .post(`${this.$domainName}/resource/tableinsemester`, {
          year: this.currentAcademicYear,
          semester: this.currentSemester,
          pageNumber: this.currentPage,
        })
        .then((res) => {
          console.log(res);
          //如果有数据
          if (res.data.response.code != 204) {
            this.dataExists = true;
            this.workloadTableData = res.data.data.records;
            this.allPageCount = res.data.data.pages;
            this.currentPage = 1;
            this.totalItems = res.data.data.total;
          }
          //如果没有数据
          else {
            this.dataExists = false;
            this.allPageCount = 0;
            this.currentPage = 0;
            this.workloadTableData = [];
            this.noDataHint = `暂无&nbsp;&nbsp;${this.yearChosen}&nbsp;&nbsp;年度的数据！`;
          }
        });
    },
    //页码操作
    confirmYearAndSemester() {
      this.currentPage = 1;
      this.getTableData();
    },
    pageBefore() {
      if (this.currentPage == 1) return;
      this.currentPage = this.currentPage - 1;
      this.noMorePage = false;
      if (this.currentPage == 1) this.noLessPage = true;
      this.getTableData();
    },
    pageAfter() {
      if (this.currentPage == this.allPageCount) return;
      this.currentPage = this.currentPage + 1;
      this.noLessPage = false;
      if (this.currentPage == this.allPageCount) this.noMorePage = true;
      this.getTableData();
    },
    choosePage() {
      if (this.currentPage < 1 || this.currentPage > this.allPageCount) return;
      this.getTableData();
    },
    //条件搜索
    search() {
      if (this.searchValue == "") return;
      switch (this.searchKeyword) {
        case "上课老师":
          axios
            .post(`${this.$domainName}/search/searchIndeed`, {
              year: this.currentAcademicYear,
              semester: this.currentSemester,
              teacherName: this.searchValue,
            })
            .then((res) => {
              console.log(res);
              if (res.data.response.code == 200) {
                console.log("Y");
                this.dataExists = true;
                this.workloadTableData = res.data.data;
                this.totalItems = this.workloadTableData.length;
                this.allPageCount = 1;
              } else {
                console.log("N");
                this.dataExists = false;
                this.workloadTableData = [];
                this.noDataHint = `暂无&nbsp;&nbsp;${this.year}&nbsp;&nbsp;年度，${this.searchKeyword}&nbsp;&nbsp;为&nbsp;&nbsp;${this.searchValue}&nbsp;&nbsp;的数据！`;
              }
            });
      }
    },
    //文件导出
    exportFile() {
      axios
        .post(`${this.$domainName}/resource/tabledownload`, {
          year: this.currentAcademicYear,
          semester: this.currentSemester,
        })
        .then((res) => {
          this.dataTobeExported = res.data.data;
          import("xlsx").then((XLSX) => {
            const data = XLSX.utils.json_to_sheet(this.dataTobeExported);
            const col = XLSX.utils.decode_range(data["!ref"]).e.c; //获取数据的列数（起始列序号为0）
            for (let i = 0; i <= col; i++) {
              data[XLSX.utils.encode_cell({ r: 0, c: i })].v =
                this.workloadTableHeader[i];
            }
            //获取文件名
            let filename = `${this.yearChosen}年度工作量`; //default
            if (this.exportFileName != "") {
              //custom
              filename = this.exportFileName;
            }
            const wb = XLSX.utils.book_new();
            XLSX.utils.book_append_sheet(wb, data, "data");
            XLSX.writeFile(wb, filename + ".xlsx");
          });
        });
      this.dataTobeExported = [];
    },
  },
  created() {
    //获取default年度数据;
    this.getTableData();
  },
};
</script>

<style scoped>
/* 数据查询筛选栏 */
.tableFilter {
  font-size: 14px;
  margin-bottom: 5px;
  text-align: left;
  padding: 5px 15px;
}
.tableFilter select {
  height: 20px;
}

.tableFilter .confirmYearBtn {
  margin-left: 5%;
  margin-right: 15%;
  width: 50px;
  height: 25px;
  border: 1px solid rgba(128, 128, 128, 0.555);
}
.tableFilter .search {
  height: 30px;
  border: 0px;
  background-color: #fff;
}
.tableFilter select.search {
  vertical-align: bottom;
  border-top-left-radius: 5px;
  border-bottom-left-radius: 5px;
}
.tableFilter input.search {
  padding-left: 10px;
}
.tableFilter button.searchBtn {
  width: 35px;
  height: 30px;
  vertical-align: bottom;
  border-top-left-radius: 0;
  border-bottom-left-radius: 0;
}

/* 查询结果的统计数据栏 */
ul.resultStatistics {
  text-align: left;
  padding-left: 15px;
}
ul.resultStatistics li {
  display: inline-block;
  padding-right: 30px;
  margin-right: 30px;
  text-align: center;
  border-right: 1px solid rgba(128, 128, 128, 0.212);
}
ul.resultStatistics li .name {
  font-size: 10px;
}
ul.resultStatistics li .value {
  font-size: 20px;
  font-weight: 500;
}
/*文件下载工具栏*/
div.componentSubsection.toolbar {
  text-align: right;
}
label.customFileName {
  font-size: 14px;
}
label.customFileName input {
  padding-left: 5px;
  height: 25px;
  border: 1px solid gray;
  border-radius: 5px;
}
span.defaultExportfileName {
  font-size: 10px;
  color: gray;
}

/* 数据列表 */
.workloadTableWrapper {
  position: relative;
  overflow: scroll;
  height: 60vh;
  padding-bottom: 15px;
}
table.workloadDataTable {
  width: 2500px;
}
.workloadDataTable thead {
  position: sticky;
  top: 0;
  font-size: 10px;
  font-weight: 500;
  background-color: rgb(239, 241, 247);
}
.workloadDataTable tbody {
  font-size: 13px;
  background-color: #fff;
  padding: 3px 5px;
}

/* 分页 */
.pagination button {
  border: 0;
  font-size: 20px;
  padding: 10px 20px;
  position: relative;
  top: 1px;
  background-color: rgb(239, 241, 246);
}
.pagination input {
  border: 1px solid gray;
  border-radius: 5px;
  width: 30px;
  text-align: center;
  padding: 3px 0;
}
</style>