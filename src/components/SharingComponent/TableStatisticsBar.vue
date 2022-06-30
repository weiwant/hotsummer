<template>
  <!-- 展示查询结果（表格）的一些统计特性 -->
  <!-- 接收参数
      totalItems：查询结果总项目数
      allPageCount：查询结果总页数
  -->
  <div class="componentSubsection">
    <ul class="resultStatistics">
      <li>
        <div class="name">查询结果数</div>
        <div class="value">{{ totalItems }}</div>
      </li>
      <li>
        <div class="name">总页数</div>
        <div class="value">{{ allPageCount }}</div>
      </li>
    </ul>
  </div>
</template>

<script>
export default {
  name: "TableStatisticsBar",
  props: ["totalItems", "allPageCount"],
};
</script>

<style scoped>
ul.resultStatistics {
  text-align: left;
  padding-left: 15px;
}
ul.resultStatistics li {
  display: inline-block;
  padding-right: 30px;
  margin-right: 30px;
  text-align: center;
  border-right: 1px solid rgba(128, 128, 128, 0.212);
}
ul.resultStatistics li .name {
  font-size: 10px;
}
ul.resultStatistics li .value {
  font-size: 20px;
  font-weight: 500;
}
</style>
