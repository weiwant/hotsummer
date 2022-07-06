<template>
  <div class="componentSubsection category">
    <div class="categoryTitle">未知BB12</div>

    <!-- 填报与添加区域 -->
    <div class="addNew">
      <tr>
        <td>内容</td>
        <td>
          <input
            type="textarea"
            placeholder="请输入相关工作量描述"
            v-model="content"
          />
        </td>
      </tr>

      <tr>
        <td>教师姓名</td>
        <td>
          <input
            type="textarea"
            placeholder="请输入教师姓名"
            v-model="teachername"
          />
        </td>
      </tr>

      <button class="universalBlueBtn complete" @click="save">
        保&nbsp;存
      </button>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      content: "",
      teachername: "",
    };
  },
  methods: {
    save() {
      this.$refs.dynamic.transmitData();
    },
  },
  created() {},
};
</script>


<style scoped>
</style>