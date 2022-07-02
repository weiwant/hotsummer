<template>
  <div class="pagination">
    <button @click="pageBefore()" :disabled="noLessPage">&lt;</button>
    第
    <input v-model="currentPage" type="text" disabled />
    页
    <button @click="pageAfter()" :disabled="noMorePage">&gt;</button>
  </div>
</template>

<script>
export default {
  name: "Pagination",
  props: ["currentPage", "allPageCount"],
  methods: {
    pageBefore() {
      this.$emit("pageBefore");
    },
    pageAfter() {
      this.$emit("pageAfter");
    },
  },
  computed: {
    noLessPage() {
      return this.currentPage == 0 || this.currentPage == 1;
    },
    noMorePage() {
      return this.currentPage == this.allPageCount;
    },
  },
};
</script>

<style scoped>
.pagination button {
  border: 0;
  font-size: 20px;
  padding: 10px 20px;
  position: relative;
  top: 1px;
  background-color: rgb(239, 241, 246);
}
.pagination input {
  border: 1px solid gray;
  border-radius: 5px;
  width: 30px;
  text-align: center;
  padding: 3px 0;
}
</style>
