<template>
  <div class="componentWrapper">
    <!--component标题-->
    <div class="componentSectionTitle">教学工作量</div>
    <YearFilter
      :yearChosen="yearChosen"
      @yearConfirmed="yearConfirmed"
    ></YearFilter>
    <TableStatisticsBar :keyValuePairs="keyValuePairs"></TableStatisticsBar>
    <div class="componentSubsection toolBar">
      <DownloadExcelFile
        :btnText="'导出excel至本地'"
        :disabled="!dataExists"
        :defaultFileName="`${yearChosen}年度工作量`"
        @exportFile="exportFile"
      ></DownloadExcelFile>
    </div>

    <!--数据列表-->
    <PlainTable
      :dataExists="dataExists"
      :tableData="workloadTableData"
      :tableHeader="workloadTableHeader"
      :noDataHint="noDataHint"
    ></PlainTable>
    <Pagination
      :currentPage="currentPage"
      :allPageCount="allPageCount"
      @pageBefore="pageBefore"
      @pageAfter="pageAfter"
    ></Pagination>
  </div>
</template>

<script>
import TableStatisticsBar from "../SharingComponent/TableStatisticsBar.vue";
import DownloadExcelFile from "../SharingComponent/DownloadExcelFile.vue";
import YearFilter from "../SharingComponent/YearFilter.vue";
import Pagination from "../SharingComponent/Pagination.vue";
import PlainTable from "../SharingComponent/PlainTable.vue";
export default {
  name: "TeacherWorkloadTable",
  components: {
    TableStatisticsBar,
    DownloadExcelFile,
    YearFilter,
    PlainTable,
    Pagination,
  },
  data() {
    return {
      //当前教师姓名
      currentTeacherName: "",
      //统计数据
      totalItems: 0,
      //分页
      allPageCount: 1,
      currentPage: 1,
      //表格数据
      yearChosen: this.$currentYear,
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
        "工作性质",
      ],
      workloadTableData: [],
      dataExists: false, //“暂无数据”提示的显示
      noDataHint: "",
    };
  },
  computed: {
    //展示在统计栏的数据
    keyValuePairs() {
      return [
        {
          key: "查询结果数",
          value: this.totalItems,
        },
        {
          key: "总页数",
          value: this.allPageCount,
        },
      ];
    },
  },
  methods: {
    //根据当前学年和学期获取对应工作量的数据
    getTableData() {
      const formData = new FormData();
      formData.append("naturalYear", this.yearChosen);
      formData.append("page", this.currentPage);
      formData.append("mainTeacherName", this.currentTeacherName);
      this.$axios
        .post(`${this.$domainName}/total/records/page`, formData)
        .then((res) => {
          if (res.data.response.code == 200) {
            this.dataExists = true;
            this.workloadTableData = res.data.data.records;
            this.allPageCount = res.data.data.pageNum;
            this.totalItems = res.data.data.itemNum;
          } else {
            this.dataExists = false;
            this.workloadTableData = [];
            this.allPageCount = 1;
            this.totalItems = 0;
            this.noDataHint = `暂无${this.yearChosen}年度的数据！`;
          }
        })
        .catch((err) => {
          console.log(err);
          this.noDataHint = "获取数据出错！";
        });
    },
    yearConfirmed(year) {
      this.yearChosen = year;
      this.getTableData();
    },
    pageBefore() {
      this.currentPage = this.currentPage + 1;
      this.getTableData();
    },
    pageAfter() {
      this.currentPage = this.currentPage + 1;
      this.getTableData();
    },
    pageBefore() {
      this.currentPage = this.currentPage - 1;
      this.getTableData();
    },
    exportFile(filename) {
      //老师的数据一般都不多，由于是按照每40项1页来做的
      //所以如果总长不超过40，就证明前端已经有所有要导出excel的数据了，可以直接导出
      //反之就需要再向后端请求所有数据
      if (this.workloadTableData.length <= 40) {
        this.$exportExcelFile(
          this.workloadTableData,
          this.workloadTableHeader,
          filename
        );
      } else {
        const formData = new FormData();
        formData.append("naturalYear", this.yearChosen);
        formData.append("mainTeacherName", this.currentTeacherName);
        this.$axios
          .post(`${this.$domainName}/total/records`, formData)
          .then((res) => {
            this.$exportExcelFile(
              res.data.data,
              this.workloadTableHeader,
              filename
            );
          });
      }
    },
  },
  created() {
    //获取当前老师用户的姓名
    this.currentTeacherName = localStorage.getItem("teacherName");
    //向后台获取default学年学期数据;
    this.getTableData();
  },
};
</script>

<style scoped>
</style>