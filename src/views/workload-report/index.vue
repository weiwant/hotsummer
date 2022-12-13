<template>
  <div class="app-view-container">
    <TableFilter :filters="filters" @search="search" />
    <!-- 教学工作量详情 -->
    <div class="app-section-title">教学工作量详情</div>
    <PlainTable :headerArray="teachingWorkloadTableHeader" :dataArray="teachingWorkloadTableData"
      :showLoading="showLoading" />
    <!-- 特殊工作量详情 -->
    <!-- <div class="app-section-title">特殊工作量详情</div>
    <PlainTable :header="specialWorkloadTableHeader" :data="specialWorkloadTableData" /> -->
  </div>
</template>

<script>
import { getTeachingWorkload } from "@/api/teaching-workload";
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
      filterAdded: [],
      yearChosen: this.$store.state.currentYear,
      // 查询结果
      teachingWorkloadTableData: [],

    };
  },
  methods: {
    //获取当前教师用户的教学工作量
    getTeachingData() {
      this.showLoading = true;
      const formData = new FormData();
      formData.append('naturalYear', this.yearChosen);
      formData.append('mainTeacherName', this.$store.state.user.userInfo.name);
      this.filterAdded.forEach(el => {
        formData.append(el.key, el.value);
      });
      getTeachingWorkload(formData).then(res => {
        this.teachingWorkloadTableData = res;
        this.showLoading = false;
      }).catch(err => {
        this.teachingWorkloadTableData = [];
        this.showLoading = false;
      })

    },
    search(year, filters) {
      this.yearChosen = year;
      this.filterAdded = filters;
      this.getTeachingData();
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