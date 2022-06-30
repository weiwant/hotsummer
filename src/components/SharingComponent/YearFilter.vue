<template>
  <!-- 年份筛选，范围为近5年，默认值为今年 -->
  <!-- 接收参数：无 -->
  <!-- 事件
  @yearConfirmed -> yearChosen(用户所选年份) 
  @yearSelected -> yearChosen(用户选择了年份，但没点)
  -->
  <div class="tableFilter">
    <label
      >年份
      <select v-model="yearChosen" @change="selectYear">
        <option
          v-for="index in 5"
          :key="index"
          :value="currentYear - index + 1"
        >
          {{ currentYear - index + 1 }}
        </option>
      </select>
    </label>
    <button class="universalBlueBtn confirmYearBtn" @click="confirmYear()">
      确&nbsp;认
    </button>
  </div>
</template>

<script>
export default {
  name: "YearFilter",
  data() {
    return {
      currentYear: this.$currentYear,
      yearChosen: this.$currentYear,
    };
  },
  methods: {
    selectYear() {
      this.$emit("yearSelected", this.yearChosen);
    },
    confirmYear() {
      this.$emit("yearConfirmed", this.yearChosen);
    },
  },
};
</script>


<style scoped>
.tableFilter {
  font-size: 14px;
  margin-bottom: 5px;
  text-align: left;
}

.tableFilter select {
  height: 20px;
}

.tableFilter .confirmYearBtn {
  margin-left: 25px;
  width: 50px;
  height: 25px;
  border: 1px solid rgba(128, 128, 128, 0.555);
}
</style>

