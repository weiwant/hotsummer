<template>
  <div class="componentSubsection category">
    <div class="categoryTitle">指导优秀毕业论文BB8</div>

    <!-- 填报与添加区域 -->
    <div class="addNew">
      <tr>
        <td>指导类型</td>
        <td>
          <input
            type="radio"
            id="independent"
            value="独立指导"
            v-model="category"
          />
          <label for="independent">独立指导</label>

          <input
            type="radio"
            id="cooperation"
            value="与院外合作指导"
            v-model="category"
          />
          <label for="cooperation">与院外合作指导</label>
        </td>
      </tr>

      <tr>
        <td>论文名称</td>
        <td>
          <input type="text" placeholder="请输入论文名称" v-model="title" />
        </td>
      </tr>

      <tr>
        <td>学生姓名</td>
        <td>
          <input
            type="text"
            placeholder="请输入学生姓名"
            v-model="studnetname"
          />
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
      category: "",
      title: "",
      studnetname: "",
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