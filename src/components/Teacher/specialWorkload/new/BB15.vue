<template>
  <div class="componentSubsection category">
    <div class="categoryTitle">其他BB15</div>
    <!-- 填报与添加区域 -->
    <div class="addNew">
      <tr>
        <td style="vertical-align: middle">内容</td>
        <td>
          <textarea
            cols="30"
            rows="10"
            placeholder="请输入工作量相关内容"
          ></textarea>
        </td>
      </tr>

      <tr>
        <td>教师姓名</td>
        <td>
          <input
            type="text"
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