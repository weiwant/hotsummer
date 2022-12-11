<template>
  <!-- 展示查询结果（表格）的一些统计特性 -->
  <div class="app-section">
    <ul class="resultStatistics">
      <li v-for="(item, index) in keyValuePairs" :key="index">
        <div class="name">{{ item.key }}</div>
        <div class="value">{{ item.value }}</div>
      </li>
    </ul>
  </div>
</template>

<script>
export default {
  name: "TableStatisticsBar",
  props: ["keyValuePairs"],
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
