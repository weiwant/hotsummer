<template>
  <div class="componentWrapper">
    <div class="componentSectionTitle">审批特殊工作量</div>
    <YearFilter @yearConfirmed="yearConfirmed"></YearFilter>
    <TableStatisticsBar
      :allPageCount="allPageCount"
      :totalItems="totalItems"
    ></TableStatisticsBar>
    <DownloadExcelFile
      :btnText="'导出excel至本地'"
      :disabled="!dataExists"
      :defaultFileName="`${yearChosen}年度特殊工作量审批记录`"
      @exportFile="exportFile"
    ></DownloadExcelFile>
    <PlainTable
      :dataExists="dataExists"
      :noDataHint="noDataHint"
      :tableData="tableData"
      :tableHeader="tableHeader"
    ></PlainTable>
    <Pagination
      :currentPage="currentPage"
      :allPageCount="allPageCount"
      @pageAfter="pageAfter"
      @pageBefore="pageBefore"
    ></Pagination>
    <!-- <div class="tableWrapper">
      <table class="specialWorkloadTable">
        <thead>
          <tr>
            <th v-for="(item, index) in tableHeader" :key="index">
              {{ item }}
            </th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="(object, index) in tableData" :key="index">
            <td v-for="(value,key) in object" :key="key">
              {{ value }}
            </td>
          </tr>
        </tbody>
      </table>
    </div> -->
  </div>
</template>

<script>
import YearFilter from "../SharingComponent/YearFilter.vue";
import TableStatisticsBar from "../SharingComponent/TableStatisticsBar.vue";
import DownloadExcelFile from "../SharingComponent/DownloadExcelFile.vue";
import PlainTable from "../SharingComponent/PlainTable.vue";
import Pagination from "../SharingComponent/Pagination.vue";
export default {
  name: "AuditWorkload",
  components: {
    YearFilter,
    TableStatisticsBar,
    DownloadExcelFile,
    PlainTable,
    Pagination,
  },
  data() {
    return {
      yearChosen: "",
      currentPage: 1,
      dataExists: false,
      allPageCount: 1,
      totalItems: 0,
      noDataHint: "",
      tableHeader: [
        "申报时间",
        "申报人",
        "姓名",
        "排序/负责情况",
        "教学业绩类型",
        "教分",
        "成果/作品/参赛项目/参评项目/论文/专著名称",
        "级别",
        "课程/项目/奖项/竞赛/论文指导类别",
        "课程/项目/奖项/竞赛详细名称",
        "获奖等级",
        "获奖/获评/出版日期",
        "项目进展",
        "授奖单位",
        "刊物/出版社名称",
        "刊物期数/出版版次",
        "ISBN号",
        "内容简介",
        "所获荣誉",
        "指导学生团队名",
        "指导学生姓名",
        "指导学生学号",
        "审核状态",
        "备注",
        "相关文件",
      ],
      tableData: [],
    };
  },
  methods: {
    exportFile() {},
    getTableData() {
      this.$axios
        .post(`${this.$domainName}/special/year`, {
          year: this.yearChosen,
          page: this.currentPage,
        })
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
            this.noDataHint = `暂无${this.yearChosen}年度的特殊工作量数据！`;
          }
        })
        .catch((err) => {
          console.log("出错了");
          this.dataExists = false;
          this.noDataHint = "获取数据出错！";
        });
    },
    yearConfirmed(year) {
      this.yearChosen = year;
      this.currentPage = 1;
      this.getTableData();
    },
    pageBefore() {
      this.currentPage = this.currentPage - 1;
      this.getTableData();
    },
    pageAfter() {
      this.currentPage = this.currentPage + 1;
      this.getTableData();
    },
  },
  created() {
    this.yearChosen = this.$currentYear;
    this.currentPage = 1;
    this.getTableData();
  },
};
</script>

<style scoped>
.tableWrapper {
  position: relative;
  overflow: scroll;
  height: 60vh;
  padding-bottom: 15px;
}
table.specialWorkloadTable {
  width: 3000px;
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
  background-color: #fff;
  padding: 3px 5px;
}
</style>