<template>
  <div class="componentSubsection category">
    <div class="categoryTitle saved" v-if="!committed">仅保存</div>
    <div class="categoryTitle commited" v-if="committed">已提交</div>

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
            :disabled="!isEditing"
          />
          <label for="independent">独立指导</label>

          <input
            type="radio"
            id="cooperation"
            value="与院外合作指导"
            v-model="category"
            :disabled="!isEditing"
          />
          <label for="cooperation">与院外合作指导</label>
        </td>
      </tr>

      <tr>
        <td>论文名称</td>
        <td>
          <input
            type="text"
            placeholder="请输入论文名称"
            v-model="title"
            :disabled="!isEditing"
          />
        </td>
      </tr>

      <tr>
        <td>学生姓名</td>
        <td>
          <input
            type="text"
            placeholder="请输入学生姓名"
            v-model="studnetName"
            :disabled="!isEditing"
          />
        </td>
      </tr>

      <tr>
        <td>教师姓名</td>
        <td>
          <input
            type="text"
            placeholder="请输入教师姓名"
            v-model="teacherName"
            :disabled="!isEditing"
          />
        </td>
      </tr>

      <button
        class="universalBlueBtn complete commit"
        @click="commit"
        v-if="!committed"
      >
        提&nbsp;交
      </button>
      <button
        class="universalBlueBtn complete notCommit"
        @click="save"
        v-if="isEditing && !committed"
      >
        保&nbsp;存
      </button>
      <button
        class="universalBlueBtn complete notCommit"
        @click="edit"
        v-if="!isEditing && !committed"
      >
        编&nbsp;辑
      </button>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      //编辑状态
      isEditing: false,
      //提交状态
      committed: true,
      category: "",
      title: "",
      studnetName: "",
      teacherName: "",
    };
  },
  props: ["data"],
  mounted() {
    this.$data.category = this.data.projectCategory;
    this.$data.title = this.data.achievementName;
    this.$data.teacherName = this.data.declarantName;
    this.$data.studnetName = this.data.guidingStudentName;
  },
  methods: {
    // 编辑
    edit() {
      this.isEditing = true;
    },
    // 提交
    commit() {
      this.isEditing = false;
      this.$refs.dynamic.transmitData();
    },
    // 保存
    save() {
      this.isEditing = false;
      this.$refs.dynamic.transmitData();
      if (
        this.$data.category == "" ||
        this.$data.title == "" ||
        this.$data.studnetName == "" ||
        this.$data.teacherName == ""
      ) {
        alert("数据填报不可为空！！！");
        return;
      }
    },
  },
  created() {},
};
</script>


<style scoped>
</style>