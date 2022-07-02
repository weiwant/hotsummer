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
    };
  },
  methods: {
    exportFile() {},
    yearConfirmed(year) {
      this.yearChosen = year;
      console.log(this.yearChosen);
      // this.getTableData();
    },
  },
};
</script>

<style scoped>
</style>