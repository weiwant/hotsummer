<template>
  <div class="app-view-container">
    <TableFilter :filters="filters" @search="search" />
    <TableHeaderSelection :headers="headers" @change="changeHeaderShow" />
    <EditableTable :headerArray="headers" :dataArray="tableData" :showLoading="showLoading" @update="update"
      ref="table" />
    <div class="pagination">
      <el-pagination background layout="total,prev, pager, next" :page-size="20" :total="totalPage"
        @current-change="changePage">
      </el-pagination>
    </div>
  </div>
</template>

<script>
import TableFilter from "@/components/table/TableFilter.vue";
import TableHeaderSelection from "@/components/table/TableHeaderSelection.vue";
import EditableTable from './components/EditableTable.vue';
import { getTeachingWorkload_paged } from "@/api/teaching-workload";
import { Message } from "element-ui";
export default {
  name: "CheckWorkload",
  components: {
    TableFilter,
    EditableTable,
    TableHeaderSelection
  },
  data() {
    return {
      filters: this.$store.getters.tableFilters_teaching,
      headers: JSON.parse(JSON.stringify(this.$store.state.teaching_workload.tableHeaders)),
      //查询条件
      yearChosen: `${this.$store.getters.currentYear}`,
      filterAdded: [],
      currentPage: 1,
      //假数据
      totalPage: 500,
      tableData: [
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
      //是否显示table内的加载动画
      showLoading: false,
    };
  },
  methods: {
    getTableData() {
      const formData = new FormData();
      formData.append("naturalYear", this.yearChosen);
      formData.append("page", this.currentPage);
      for (let item in this.filterAdded) {
        formData.append(item.type, item.value);
      }
      getTeachingWorkload_paged(formData)
        .then((res) => {
          this.tableData = res.data;
        })
        .catch((err) => {
          console.log(err);
        });
    },
    //改变表头展示状态
    changeHeaderShow(checkList) {
      for (let i = 0; i < this.headers.length; i++) {
        //如果表头的index在checkList里找不到（即没选中）
        if (checkList.findIndex(el => el == i) < 0) this.headers[i].show = false;
        //反之
        else this.headers[i].show = true;
      }
    },
    //查询列表数据
    search(yearChosen, filterAdded) {
      this.yearChosen = yearChosen;
      this.filterAdded = JSON.parse(JSON.stringify(filterAdded)); //深复制，当前组件保存的added永远是上一次点击查询时的
      this.getTableData();
    },
    //分页
    changePage(currentPage) {
      this.currentPage = currentPage;
      this.getTableData();
    },
    //更新编辑的数据
    update(index, key, value) {
      this.showLoading = true;
      let id = this.tableData[index].id
      //成功回调
      setTimeout(() => {
        this.showLoading = false;
        this.tableData[index][key] = value;
        this.$refs['table'].finishUpdating();
        Message({
          message: "修改成功！",
          type: "success",
          duration: 1000,
        });
      }, 500)
      //失败回调
      // setTimeout(()=>{
      // this.showLoading = false;
      // ;     //如果请求失败，就变回旧值
      // },500)
    }
  },
  created() {
    //默认先获取当年数据以展示
    // this.getTableData();
  },
};
</script>

<style scoped>
#editable-table {
  margin-top: 20px;
}

.pagination {
  margin-top: 10px;
  display: flex;
  justify-content: center;
}

.el-checkbox {
  margin-bottom: 8px;
}
</style>