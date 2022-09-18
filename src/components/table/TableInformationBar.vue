<template>
  <div id="table-info-bar">
    <div class="app-section">
      <div class="app-section-title">当前列表属性</div>
      <div class="filter-info-wrapper">
        <div class="filter-info-item">
          <span class="app-section-subtitle">所属自然年份</span>
          <span class="value-year">{{ currentYear }}</span>
        </div>
        <div class="filter-info-item">
          <span class="app-section-subtitle">筛选条件</span>
          <span class="value-none" v-if="filterValues.length <= 0">无</span>
          <div
            class="value-filter-wrapper"
            v-for="(item, index) in filterValues"
            :key="item.value"
          >
            <span
              class="value-filter"
              :style="{ backgroundColor: colorList[index % 4] }"
            >
              {{ item.value }}
            </span>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  props: {
    filterValues: {
      type: Array,
      required: true,
    },
    currentYear: {
      type: String,
      required: true,
    },
  },
  data() {
    return {
      colorList: [" #b9ca6e", "#87a863", "#5a813a", "#46693a"],
    };
  },
};
</script>

<style scoped>
#table-info-bar {
  flex-grow: 1;
  min-width: 200px;
}
.app-section {
  height: 100%;
  overflow: auto;
  max-width: 300px;
  word-break: break-all;
}
.app-section-subtitle {
  margin-right: 10px;
}
.filter-info-item {
  margin-bottom: 10px;
}
.value-year {
  font-size: 13px;
  color: #997776;
  font-weight: 500;
}
.value-none {
  font-size: 13px;
  color: #666;
  font-weight: 500;
}
.value-filter-wrapper {
  margin: 5px 0;
}
.value-filter {
  padding: 0 10px;
  font-size: 13px;
  font-weight: 500;
  color: whitesmoke;
  border-radius: 50px;
}
</style>