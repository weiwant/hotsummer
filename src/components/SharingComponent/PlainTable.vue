<template>
  <!-- 只展示数据，不存在交互功能的表格 -->
  <div class="tableWrapper">
    <div class="noDataHint" v-if="!dataExists" v-html="noDataHint"></div>
    <table class="plainDataTable">
      <thead v-if="dataExists">
        <tr>
          <th v-for="(item, index) in tableHeader" :key="index">
            {{ item.key }}
          </th>
        </tr>
      </thead>
      <tbody>
        <tr
          class="tableDataLine"
          v-for="(object, index) in tableData"
          :key="index"
        >
          <!-- 按照tableHeader的顺序遍历对象 -->
          <td v-for="(item, index) in tableHeader" :key="index">
            {{ object[item.value] }}
          </td>
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
  max-height: 86vh;
  padding-bottom: 15px;
}
table.plainDataTable {
  width: 3000px;
}
.plainDataTable thead {
  position: sticky;
  top: 0;
  font-size: 10px;
  font-weight: 500;
  background-color: rgb(211, 210, 210);
  background-color: rgb(239, 241, 247);
}
.plainDataTable tbody {
  font-size: 13px;
  background-color: #fff;
  padding: 3px 5px;
}

.plainDataTable tr:nth-child(2n) {
  background-color: rgba(201, 198, 198, 0.308);
}
</style>
