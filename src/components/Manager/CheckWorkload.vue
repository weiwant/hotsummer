<template>
  <div class="componentWrapper">
    <div class="componentSectionTitle">查看教学工作量</div>
    <FilterWithSearch
      :searchKeywords="searchKeywords"
      @yearOnly="confirmYear"
      @yearAndSearchValue="confirmSearchValue"
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
    <PlainTable
      :dataExists="dataExists"
      :noDataHint="noDataHint"
      :tableData="workloadTableData"
      :tableHeader="workloadTableHeader"
    ></PlainTable>
    <Pagination
      :currentPage="currentPage"
      :allPageCount="allPageCount"
      @pageAfter="pageAfter"
      @pageBefore="pageBefore"
    ></Pagination>
  </div>
</template>

<script>
import TableStatisticsBar from "../SharingComponent/TableStatisticsBar.vue";
import DownloadExcelFile from "../SharingComponent/DownloadExcelFile.vue";
import FilterWithSearch from "../SharingComponent/FilterWithSearch.vue";
import Pagination from "../SharingComponent/Pagination.vue";
import PlainTable from "../SharingComponent/PlainTable.vue";
export default {
  name: "CheckWorkload",
  components: {
    TableStatisticsBar,
    DownloadExcelFile,
    FilterWithSearch,
    PlainTable,
    Pagination,
  },
  data() {
    return {
      searchKeywords: ["上课老师", "教分"],
      //当前的状态
      useSearch: false,
      yearChosen: this.$currentYear,
      currentSearchKeyword: "",
      currentSearchValue: "",
      //pagination
      currentPage: 1,
      allPageCount: 1,
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
        "工作性质",
      ],
      workloadTableData: [],
      totalItems: 0,
      dataExists: false,
      noDataHint: "", //“暂无数据”提示
    };
  },
  methods: {
    /*
     *getTableData()与search()永远不会是事件的第一handler
     *每个事件都有其对应的第一handler，负责将请求参数都配置好后
     *再调用getTableData()或search()
     */
    //获取某学年全部数据
    getTableData() {
      const formData = new FormData();
      formData.append("naturalYear", this.yearChosen);
      formData.append("page", this.currentPage);
      // console.log(formData.get("page"));
      this.$axios
        .post(`${this.$domainName}/total/records/page`, formData)
        .then((res) => {
          //如果有数据
          if (res.data.response.code == 200) {
            this.dataExists = true;
            this.workloadTableData = res.data.data.records;
            this.allPageCount = res.data.data.pageNum;
            this.totalItems = res.data.data.itemNum;
          }
          //如果没有数据
          else {
            this.dataExists = false;
            this.workloadTableData = [];
            this.allPageCount = 1;
            this.totalItems = 0;
            this.noDataHint = `暂无&nbsp;&nbsp;${this.yearChosen}&nbsp;&nbsp;年度的数据！`;
          }
        })
        .catch((err) => {
          console.log(err);
          this.noDataHint = "获取数据出错！";
        });
    },
    //条件搜索
    search() {
      switch (this.searchKeyword) {
        case "上课老师":
          const formData = new FormData();
          formData.append("naturalYear", this.yearChosen);
          formData.append("page", this.currentPage);
          formData.append("mainTeacherName", this.searchValue);
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
                this.noDataHint = `暂无&nbsp;&nbsp;${this.year}&nbsp;&nbsp;年度，${this.searchKeyword}&nbsp;&nbsp;为&nbsp;&nbsp;${this.searchValue}&nbsp;&nbsp;的数据！`;
              }
            })
            .catch((err) => {
              console.log(err);
              this.noDataHint = "获取数据出错！";
            });
          break;
        case "教分":
          console.log(year);
          console.log(searchKeyword);
          console.log(searchValue);
      }
    },

    //选择了年份并确认
    confirmYear(year) {
      //请求数据前设置好参数
      this.useSearch = false;
      this.yearChosen = year;
      this.currentPage = 1;
      this.getTableData();
    },
    //选择了年份和搜索条件，并给出了搜索值后确认
    confirmSearchValue(year, searchKeyword, searchValue) {
      //在请求数据前设置好参数
      this.useSearch = true;
      this.yearChosen = year;
      this.searchKeyword = searchKeyword;
      this.searchValue = searchValue;
      this.currentPage = 1;
      this.search();
    },
    //页面切换
    //进行页面切换时，基本的搜索参数没变，只需要调整页数
    pageBefore() {
      this.currentPage = this.currentPage - 1; //由于组件内存在计算属性把关是否前面还存在页面，所以可以直接-1
      if (this.useSearch) {
        //在基本表格是通过关键词搜索的情况下得到时
        this.search();
      } else {
        //在基本表格是在单纯年份的条件下得到时
        this.getTableData();
      }
    },
    pageAfter() {
      this.currentPage = this.currentPage + 1;
      if (this.useSearch) {
        this.search();
      } else {
        this.getTableData();
      }
    },
    //文件导出(暂时还不考虑在search状态下的导出，只导出全年的)
    exportFile(filename) {
      const formData = new FormData();
      formData.append("naturalYear", this.yearChosen);
      this.$axios
        .post(`http://abcd.vaiwan.com/total/records`, formData)
        .then((res) => {
          console.log(res);
          this.$exportExcelFile(
            res.data.data,
            this.workloadTableHeader,
            filename
          );
        });
    },
  },
  created() {
    //获取default年度数据;
    this.useSearch = false;
    this.yearChosen = this.$currentYear;
    this.currentPage = 1;
    this.getTableData();
  },
};
</script>

<style scoped>
/* 数据列表 */
</style>