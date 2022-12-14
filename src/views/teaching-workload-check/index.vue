<template>
  <div class="app-view-container">
    <TableFilter :filters="filters" @search="search" />
    <TableHeaderSelection :headers="headers" @change="changeHeaderShow" />
    <EditableTable :headerArray="headers" :dataArray="tableData" :showLoading="showLoading" @update="update"
      ref="table" />
    <div class="pagination">
      <el-pagination background layout="total,prev, pager, next" :page-size="20" :total="totalItems"
        @current-change="changePage">
      </el-pagination>
    </div>
  </div>
</template>

<script>
import TableFilter from "@/components/table/TableFilter.vue";
import TableHeaderSelection from "@/components/table/TableHeaderSelection.vue";
import EditableTable from './components/EditableTable.vue';
import { getTeachingWorkload_paged, updateTeachingWorkload } from "@/api/teaching-workload";
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
      filters: this.$store.state.teaching_workload.filters_master,
      headers: JSON.parse(JSON.stringify(this.$store.state.teaching_workload.tableHeaders)),
      //查询条件
      yearChosen: `${this.$store.getters.currentYear}`,
      filterAdded: [],
      currentPage: 1,
      //假数据
      totalItems: 0,
      tableData: [],
      //是否显示table内的加载动画
      showLoading: false,
    };
  },
  methods: {
    getTableData() {
      this.showLoading = true;
      const formData = new FormData();
      formData.append("naturalYear", this.yearChosen);
      formData.append("page", this.currentPage);
      this.filterAdded.forEach(el => {
        formData.append(el.key, el.value);
      });
      getTeachingWorkload_paged(formData)
        .then((res) => {
          this.totalItems = res.itemNum;
          this.tableData = res.records;
          this.showLoading = false;
        })
        .catch((err) => {
          this.totalItems = 0;
          this.tableData = [];
          this.showLoading = false;
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
      let apiData = { id: this.tableData[index].id }
      apiData[key] = value;
      let workloadNature = this.tableData[index].workloadNature
      let url;
      switch (workloadNature) {
        case '教学':
          url = '/teaching-workload/update';
          break;
        case '论文':
          url = '/paper/update';
          break;
        case '监考':
          url = '/examination/update';
          break;
      }
      console.log(url)
      updateTeachingWorkload(url, apiData).then(() => {
        this.showLoading = false;
        this.tableData[index][key] = value;  //如果修改成功就直接在当前array上修改，不再向后段请求数据了
        this.$refs['table'].finishUpdating();
        Message({
          message: "修改成功！",
          type: "success",
          duration: 1000,
        });
      }).catch(() => {
        Message({
          message: "修改失败！",
          type: "error",
          duration: 2000,
        });
        this.showLoading = false;
        this.$refs['table'].finishUpdating();
      })
    }
  },
  created() {
    // 默认先获取当年数据以展示
    this.getTableData();
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
  margin-bottom: 30px;
}

.el-pagination {
  transform: translateX(15px);
}
</style>