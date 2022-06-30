<template>
  <div class="tableFilter">
    <YearFilter
      @yearSelected="selectYear"
      @yearConfirmed="confirmYearOnly"
    ></YearFilter>
    <!-- 条件搜索 -->
    <div class="wrapper">
      <select class="search" v-model="searchKeyword" id="">
        <option
          v-for="(item, index) in searchKeywords"
          :key="index"
          :value="item"
        >
          {{ item }}
        </option>
      </select>
      <input
        class="search"
        type="search"
        v-model="searchValue"
        placeholder="查询内容"
        @keyup.enter="search()"
      />
      <button class="searchBtn universalBlueBtn" @click="search()"></button>
    </div>
  </div>
</template>

<script>
import YearFilter from "./YearFilter.vue";
export default {
  name: "FilterWithSearch",
  components: {
    YearFilter,
  },
  props: ["searchKeywords"],
  data() {
    return {
      year: "",
      searchKeyword: this.searchKeywords[0],
      searchValue: "",
    };
  },
  methods: {
    //选择了年份且点击了确认
    confirmYearOnly(year) {
      this.year = year;
      this.$emit("yearOnly", this.year);
    },
    //选择了年份但没有点击确认，可能会点击searchBtn，应将year数据暂存
    selectYear(year) {
      this.year = year;
    },
    //点击了搜索btn后
    search() {
      if (this.searchValue == "") return;
      this.$emit(
        "yearAndSearchValue",
        year,
        this.searchKeyword,
        this.searchValue.trim()
      );
    },
  },
};
</script>

<style scoped>
.tableFilter {
  position: relative;
  font-size: 14px;
  margin-bottom: 5px;
  text-align: left;
  padding: 5px 5px;
  clear: both;
}
.wrapper {
  position: absolute;
  top: 8px;
  left: 200px;
  overflow: hidden;
}
.tableFilter .search {
  position: relative;
  height: 30px;
  border: 0px;
  background-color: #fff;
}
.tableFilter select.search {
  vertical-align: bottom;
  border-top-left-radius: 5px;
  border-bottom-left-radius: 5px;
}
.tableFilter input.search {
  padding-left: 10px;
}
.tableFilter button.searchBtn {
  position: relative;
  width: 35px;
  height: 30px;
  vertical-align: bottom;
  border-top-left-radius: 0;
  border-bottom-left-radius: 0;
}
</style>
