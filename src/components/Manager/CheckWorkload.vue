<template>
  <div class="componentWrapper">
    <div class="componentSectionTitle">查看教学工作量</div>
    <FilterWithSearch
      :searchKeywords="searchKeywords"
      @getData="confirmSearchValue"
    ></FilterWithSearch>
    <TableStatisticsBar :keyValuePairs="keyValuePairs"></TableStatisticsBar>
    <div class="componentSubsection toolBar">
      <DownloadExcelFile
        :btnText="'导出excel至本地'"
        :disabled="!dataExists"
        :defaultFileName="`${yearChosen}年度教学工作量`"
        @exportFile="exportFile"
      ></DownloadExcelFile>
    </div>

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
      //当前查询的状态
      searchKeywords: ["上课老师"],
      yearChosen: this.$currentYear,
      searchKeywordChosen: "",
      searchValueChosen: "",
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
    /*
     *getTableData()永远不会是事件的第一handler
     *每个事件都有其对应的第一handler，负责将请求参数都配置好后
     *再调用getTableData()
     */
    //获取某学年全部数据
    getTableData() {
      const formData = new FormData();
      formData.append("naturalYear", this.yearChosen);
      formData.append("page", this.currentPage);
      // 如果搜索值不为空
      if (this.searchValueChosen != "") {
        switch (this.searchKeywordChosen) {
          case "上课老师":
            formData.append("mainTeacherName", this.searchValue);
            break;
          case "教分":
            formData.append("mainTeacherName", this.searchValue);
            break;
        }
      }
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
            if (this.searchValueChosen == "") {
              this.noDataHint = `暂无&nbsp;&nbsp;${this.yearChosen}&nbsp;&nbsp;年度的数据！`;
            } else {
              this.noDataHint = `暂无&nbsp;&nbsp;${this.yearChosen}&nbsp;&nbsp;年度，${this.searchKeywordChosen}&nbsp;为&nbsp;${this.searchValueChosen}&nbsp;的数据！`;
            }
          }
        })
        .catch((err) => {
          console.log(err);
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
      this.getTableData;
    },
    //页面切换
    //进行页面切换时，基本的搜索参数没变，只需要调整页数
    pageBefore() {
      this.currentPage = this.currentPage - 1; //由于组件内存在计算属性把关是否前面还存在页面，所以可以直接-1
      this.getTableData();
    },
    pageAfter() {
      this.currentPage = this.currentPage + 1;
      this.getTableData();
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
    this.getTableData();
  },
};
</script>

<style scoped>
</style>