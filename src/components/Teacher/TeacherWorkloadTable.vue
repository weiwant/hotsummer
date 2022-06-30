<template>
  <div class="componentWrapper">
    <!--component标题-->
    <div class="componentSectionTitle">教学工作量</div>
    <YearFilter @yearConfirmed="yearConfirmed"></YearFilter>
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
      <div class="noDataHint" v-if="!dataExists" v-text="noDataHint"></div>
      <table class="workloadDataTable">
        <thead>
          <tr>
            <th v-for="(item, index) in workloadTableHeader" :key="index">
              {{ item }}
            </th>
          </tr>
        </thead>
        <tbody>
          <tr
            class="tableDataLine"
            v-for="arrayItem in workloadTableData"
            :key="arrayItem.id"
          >
            <td v-for="(value, index) in arrayItem" :key="index">
              {{ value }}
            </td>
          </tr>
        </tbody>
      </table>
    </div>
  </div>
</template>

<script>
import axios from "axios";
import TableStatisticsBar from "../SharingComponent/TableStatisticsBar.vue";
import DownloadExcelFile from "../SharingComponent/DownloadExcelFile.vue";
import YearFilter from "../SharingComponent/YearFilter.vue";
export default {
  name: "TeacherWorkloadTable",
  components: {
    TableStatisticsBar,
    DownloadExcelFile,
    YearFilter,
  },
  data() {
    return {
      //当前教师姓名
      currentTeacherName: "",
      //统计数据
      totalItems: 0,
      allPageCount: 0,
      currentPage: 0,
      //数据
      yearChosen: this.$currentYear,
      workloadTableHeader: [],
      workloadTableData: [],
      dataExists: false, //“暂无数据”提示的显示
      noDataHint: "",
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
    //根据当前学年和学期获取对应工作量的数据
    getTableData() {
      axios
        .post(`${this.$domainName}/resource/customIndeed`, {
          year: this.yearChosen,
          teacherName: this.currentTeacherName,
        })
        .then((res) => {
          console.log(res);
          if (res.data.response.code != 204) {
            this.dataExists = true;
            this.workloadTableHeader = [
              "课程号",
              "课程名称",
              "教学班",
              "开课学院",
              "学分",
              "课程性质",
              "年级",
              "专业",
              "上课老师",
              "职称",
              "上课人数",
              "计算用学时",
              "合课单位",
              "备注",
              "实验安排",
              "其他教师",
              "辅助",
              "课程性质说明",
              "是否卓工或弘毅",
              "是否全英文",
            ];
            this.workloadTableData = res.data.data;
          }
          //如果没有数据
          else {
            this.dataExists = false;
            this.workloadTableHeader = [];
            this.workloadTableData = [];
            this.noDataHint = `暂无${this.currentAcademicYear}学年，第${this.currentSemester}学期的数据！`;
          }
        });
    },
    yearConfirmed(year) {
      this.yearChosen = year;
      console.log(this.yearChosen);
      // this.getTableData();
    },
    exportFile(filename) {
      import("xlsx").then((XLSX) => {
        const data = XLSX.utils.json_to_sheet(this.workloadTableData);
        const col = XLSX.utils.decode_range(data["!ref"]).e.c; //获取数据的列数（起始列序号为0）
        //替换第一行的值为中文表头
        for (let i = 0; i <= col; i++) {
          data[XLSX.utils.encode_cell({ r: 0, c: i })].v =
            this.workloadTableHeader[i];
        }
        const wb = XLSX.utils.book_new();
        XLSX.utils.book_append_sheet(wb, data, "data");
        XLSX.writeFile(wb, filename + ".xlsx");
      });
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
/* 数据列表 */
.workloadTableWrapper {
  position: relative;
  overflow: scroll;
  height: 60vh;
}
table.workloadDataTable {
  width: 2000px;
}
.workloadDataTable thead {
  position: sticky;
  top: 0;
  font-size: 10px;
  font-weight: 500;
  background-color: rgb(239, 241, 247);
}
.workloadDataTable tbody {
  background-color: #fff;
  padding: 3px 5px;
}
</style>