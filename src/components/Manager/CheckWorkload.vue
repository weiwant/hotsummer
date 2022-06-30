<template>
  <div class="componentWrapper">
    <div class="componentSectionTitle">查看教学工作量</div>
    <FilterWithSearch
      :searchKeywords="searchKeywords"
      @yearOnly="confirmYear"
      @yearAndSearchValue="search"
    ></FilterWithSearch>
    <TableStatisticsBar
      :allPageCount="allPageCount"
      :totalItems="totalItems"
    ></TableStatisticsBar>
    <DownloadExcelFile
      :btnText="'导出excel至本地'"
      :disabled="!dataExists"
      :defaultFileName="`${yearChosen}年度工作量`"
      @exportFile="exportFile"
    ></DownloadExcelFile>
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
      <input v-model="cPage" type="text" @keyup.enter="choosePage()" />
      页
      <button @click="pageAfter()" :disabled="noMorePage">&gt;</button>
    </div>
  </div>
</template>

<script>
import axios from "axios";
import TableStatisticsBar from "../SharingComponent/TableStatisticsBar.vue";
import DownloadExcelFile from "../SharingComponent/DownloadExcelFile.vue";
import FilterWithSearch from "../SharingComponent/FilterWithSearch.vue";
export default {
  name: "CheckWorkload",
  components: {
    TableStatisticsBar,
    DownloadExcelFile,
    FilterWithSearch,
  },
  data() {
    return {
      //查询条件
      currentYear: this.$currentYear,
      yearChosen: this.$currentYear, //年份
      searchKeywords: ["上课老师", "教分"],
      searchKeyword: "",
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
    confirmYear(year) {
      this.yearChosen = year;
      this.currentPage = 1;
      this.getTableData();
    },
    //页码操作
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
    search(year, searchKeyword, searchValue) {
      this.yearChosen = year;
      this.searchKeyword = searchKeyword;
      this.searchValue = searchValue; //获取值主要是为了显示提示信息
      switch (searchKeyword) {
        case "上课老师":
          axios
            .post(`${this.$domainName}/search/searchIndeed`, {
              year: year,
              teacherName: searchValue,
            })
            .then((res) => {
              if (res.data.response.code == 200) {
                this.dataExists = true;
                this.workloadTableData = res.data.data;
                this.totalItems = this.workloadTableData.length;
                this.currentPage = 1;
              } else {
                this.dataExists = false;
                this.workloadTableData = [];
                this.currentPage = 0;
                this.noDataHint = `暂无&nbsp;&nbsp;${this.year}&nbsp;&nbsp;年度，${this.searchKeyword}&nbsp;&nbsp;为&nbsp;&nbsp;${this.searchValue}&nbsp;&nbsp;的数据！`;
              }
            });
          break;
        case "教分":
          console.log(year);
          console.log(searchKeyword);
          console.log(searchValue);
      }
    },
    //文件导出
    exportFile(filename) {
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