<template>
  <div class="app-right-wrapper">
    <TableFilter :filters="filters" @search="search" />
    <TableHeaderSelection :headerGroups="headerNameGroups" :headerChosen="headerChosen" @change="adjustHeader" />
    <PlainTable :header="tableHeaderDisplayed" :data="tableData" />
    <div class="pagination">
      <el-pagination background layout="total,prev, pager, next" page-size="20" :total="totalPage"
        @current-change="changePage" :hide-on-single-page="true">
      </el-pagination>
    </div>
  </div>
</template>

<script>
import TableFilter from "@/components/table/TableFilter.vue";
import TableHeaderSelection from "@/components/table/TableHeaderSelection.vue";
import PlainTable from "@/components/table/PlainTable.vue";
import { getTeachingWorkload_paged } from "@/api/teaching-workload";
export default {
  name: "CheckWorkload",
  components: {
    TableFilter,
    TableHeaderSelection,
    PlainTable,
  },
  data() {
    return {
      //初始化TableFilter
      filters: this.$store.getters.tableFilters_teaching,
      //查询条件
      yearChosen: `${this.$store.getters.currentYear}`,
      filterAdded: [],
      //查询结果
      headerChosen: [], //选择要展示的表头组
      tableHeaderGroups: this.$store.getters.tableHeaderGroups_teaching, //表头组集合
    };
  },
  computed: {
    //根据表头组集合，计算传递给TableHeaderSelection的用于展示的表头组字符串数组
    headerNameGroups() {
      const result = [];
      for (let group of this.tableHeaderGroups) {
        let s = "";
        for (let i = 0; i < group.length; i++) {
          s += group[i].key;
          if (i === group.length - 1) break;
          s += "，";
        }
        result.push(s);
      }
      return result;
    },
    //根据chosen数组值，计算传递给table组件的表头
    tableHeaderDisplayed() {
      const result = [];
      for (let i = 0; i < this.headerChosen.length; i++) {
        if (this.headerChosen[i]) {
          for (let item of this.tableHeaderGroups[i]) result.push(item);
        }
      }
      return result;
    },
  },
  methods: {
    //查询列表数据
    search(yearChosen, filterAdded) {
      this.yearChosen = yearChosen;
      this.filterAdded = JSON.parse(JSON.stringify(filterAdded)); //深复制，当前组件保存的added永远是上一次点击查询时的
      this.getTableData();
    },
    //更改展示表头
    adjustHeader(chosen) {
      this.headerChosen = [...chosen];
    },
    //分页
    changePage(currentPage) {
      this.currentPage = currentPage;
      this.getTableData();
    },
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
  },
  created() {
    //设置headerChosen初始值
    for (let i = 0; i < this.tableHeaderGroups.length; i++) {
      if (i === 0) {
        this.headerChosen.push(true);
      } else {
        this.headerChosen.push(false);
      }
    }
    //默认先获取当年数据以展示
    this.getTableData();
  },
};
</script>

<style scoped>
#table-wrapper {
  margin-top: 10px;
}

.pagination {
  margin: 10px 0;
  margin-left: 50%;
  transform: translateX(-50%);
}
</style>