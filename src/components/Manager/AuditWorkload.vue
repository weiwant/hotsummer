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
    <div class="tableWrapper">
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
            <td v-for="index in 25" :key="index">
              {{ object[index] }}
            </td>
          </tr>
        </tbody>
      </table>
    </div>
  </div>
</template>

<script>
import YearFilter from "../SharingComponent/YearFilter.vue";
import TableStatisticsBar from "../SharingComponent/TableStatisticsBar.vue";
import DownloadExcelFile from "../SharingComponent/DownloadExcelFile.vue";
export default {
  name: "AuditWorkload",
  components: {
    YearFilter,
    TableStatisticsBar,
    DownloadExcelFile,
  },
  data() {
    return {
      yearChosen: this.$currentYear,
      dataExists: false,
      allPageCount: 0,
      totalItems: 0,
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
        "评论",
        "相关文件",
      ],
      tableData: [
        {
          id: "1",
          report_time: "2022.7.3",
          declarant_name: "hhhhhh",
          teacher_name: "hhhhhh",
          author_order: "",
          type: "BB1",
          teaching_scores: "*",
          achievement_name: "",
          level: "",
          project_category: "eeeee",
          project_name: "",
          award_level: "",
          award_date: "",
          project_status: "aaaaaaaa",
          award_apartment: "",
          publication_name: "",
          publication_number: "fffffffff",
          isbn: "",
          brief_introduction:
            "eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee",
          receiving_honor: "",
          guiding_student_team: "",
          guiding_student_name: "",
          guiding_student_id: "",
          status: "",
          remarks: "eee",
          file_path: "",
        },
      ],
    };
  },
  methods: {
    exportFile() {},
    yearConfirmed(year) {
      this.yearChosen = year;
      this.getTableData();
    },
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