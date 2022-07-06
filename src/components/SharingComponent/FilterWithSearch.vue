<template>
  <div class="searchFilter">
    <YearFilter :yearChosen="year" @yearConfirmed="search"></YearFilter>
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
      <button class="searchBtn universalBlueBtn" @click="search"></button>
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
      year: this.$currentYear,
      searchKeyword: this.searchKeywords[0],
      searchValue: "",
    };
  },
  methods: {
    //点击了搜索btn或确定btn
    search() {
      this.$emit(
        "getData",
        this.year,
        this.searchKeyword,
        this.searchValue.trim()
      );
    },
  },
};
</script>

<style scoped>
.searchFilter {
  position: relative;
  font-size: 14px;
  text-align: left;
}
.wrapper {
  position: absolute;
  top: -3px;
  left: 200px;
  padding: 5px 0;
  width: 300px;
}
.wrapper .search {
  height: 30px;
  border: 0px;
  background-color: #fff;
}
.wrapper select.search {
  vertical-align: bottom;
  border-top-left-radius: 5px;
  border-bottom-left-radius: 5px;
}
.wrapper input.search {
  padding-left: 10px;
}
.searchFilter button.searchBtn {
  vertical-align: bottom;
  width: 35px;
  height: 30px;
  border-top-left-radius: 0;
  border-bottom-left-radius: 0;
}
</style>
