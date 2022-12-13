<template>
  <div class="app-view-container">
    <TableFilter :filters="filters" @search="search" />
    <!-- 教学工作量详情 -->
    <div class="app-section-title">教学工作量详情</div>
    <PlainTable :headerArray="teachingWorkloadTableHeader" :dataArray="teachingWorkloadTableData"
      :showLoading="showLoading" />
    <div class="pagination">
      <el-pagination background layout="total,prev, pager, next" :page-size="20" :total="totalPage"
        @current-change="changePage">
      </el-pagination>
    </div>
    <!-- 特殊工作量详情 -->
    <!-- <div class="app-section-title">特殊工作量详情</div>
    <PlainTable :header="specialWorkloadTableHeader" :data="specialWorkloadTableData" /> -->
  </div>
</template>

<script>
import TableFilter from "../../components/table/TableFilter.vue";
import PlainTable from "@/components/table/PlainTable.vue";
export default {
  name: "TeacherWorkloadTable",
  components: {
    TableFilter,
    PlainTable
  },
  data() {
    return {
      filters: this.$store.getters.tableFilters_teaching,
      teachingWorkloadTableHeader: this.$store.state.teaching_workload.tableHeaders_teacher,
      showLoading: false,
      //查询条件
      yearChosen: this.$store.state.currentYear,
      currentPage: 1,
      // 查询结果
      totalPage: 500,
      teachingWorkloadTableData: [
        {
          workloadNature: "1111",
          academicYear: "111111",
          semester: "11111",
          courseNumber: "111211",
          courseName: "12111",
          teachingClass: "111111",
          teachingSchool: "1111111",
          planingSchool: "1111111",
          credit: "1111111",
          courseNature: "11111",
          studentGrade: "11111",
          major: "11111",
          mainTeacherName: "111111",
          mainTeacherTitle: "11111",
          studentAmount: "111111",
          theoreticalClassHours: "111111",
          computerClassHours: "11111",
          experimentalClassHours: "111111111",
          practicalClassHours: "1111111111",
          calculatingClassHours: "1111111",
          jointDepartment: "11111111111",
          remarks: "11111111",
          experimentArrangement: "111111111",
          otherTeacherName: "111111111",
          originalTeachingScores: "111111111",
          teachingCoefficient: "1111111111",
          finalTeachingScores: "111111111",
          classNatureExplanation: "111111111",
          specialClassRemarks: "1111111111",
          specialLanguageRemarks: "11111111111",
          specialClassRemarks: "11111111111",
          discount: "11111111111",
          noDiscountTeachingCoefficient: "11111111",
          laboratoryVerificationResults: "111111111",
        },
        {
          workloadNature: "1111",
          academicYear: "111111",
          semester: "11111",
          courseNumber: "111111",
          courseName: "11111",
          teachingClass: "111111",
          teachingSchool: "1111111",
          credit: "1111111",
          courseNature: "11111",
          studentGrade: "11111",
          major: "11111",
          mainTeacherName: "111111",
          mainTeacherTitle: "11111",
          studentAmount: "111111",
          theoreticalClassHours: "111111",
          computerClassHours: "11111",
          experimentalClassHours: "111111111",
          practicalClassHours: "1111111111",
          calculatingClassHours: "1111111",
          jointDepartment: "11111111111",
          remarks: "11111111",
          experimentArrangement: "111111111",
          otherTeacherName: "111111111",
          originalTeachingScores: "111111111",
          teachingCoefficient: "1111111111",
          finalTeachingScores: "111111111",
          classNatureExplanation: "111111111",
          specialClassRemarks: "1111111111",
          specialLanguageRemarks: "11111111111",
          specialClassRemarks: "11111111111",
          discount: "11111111111",
          noDiscountTeachingCoefficient: "11111111",
          laboratoryVerificationResults: "111111111",
        },
      ],

    };
  },
  methods: {
    //分页控制
    changePage() {
      this.currentPage = page;
      this.getTeachingData();
    },
    //获取当前教师用户的教学工作量
    getTeachingData() {

    },
    search(year, filters) {
      console.log(year)
      console.log(filters)
    },
  },
  created() {
    //向后台获取default学年学期数据;
    this.getTeachingData();
  },
};
</script>

<style scoped>
.pagination {
  margin-top: 10px;
  display: flex;
  justify-content: center;
}
</style>