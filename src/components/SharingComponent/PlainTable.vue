<template>
  <!-- 只展示数据，不存在交互功能的表格 -->
  <div class="tableWrapper">
    <div class="noDataHint" v-if="!dataExists" v-html="noDataHint"></div>
    <table class="plainDataTable">
      <thead v-if="dataExists">
        <tr>
          <th v-for="(item, index) in tableHeader" :key="index">
            {{ item }}
          </th>
        </tr>
      </thead>
      <tbody>
        <tr class="tableDataLine" v-for="item in tableData" :key="item.id">
          <td v-for="(value, index) in item" :key="index">{{ value }}</td>
        </tr>
      </tbody>
    </table>
  </div>
</template>

<script>
export default {
  name: "PlainTable",
  props: ["dataExists", "tableData", "tableHeader", "noDataHint"],
};
</script>

<style scoped>
.tableWrapper {
  position: relative;
  overflow: scroll;
  height: 60vh;
  padding-bottom: 15px;
}
table.plainDataTable {
  width: 2500px;
}
.plainDataTable thead {
  position: sticky;
  top: 0;
  font-size: 10px;
  font-weight: 500;
  background-color: rgb(239, 241, 247);
}
.plainDataTable tbody {
  font-size: 13px;
  background-color: #fff;
  padding: 3px 5px;
}
</style>
