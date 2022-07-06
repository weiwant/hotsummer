<template>
  <div class="componentSubsection category">
    <div class="categoryTitle saved" v-if="!committed">仅保存</div>
    <div class="categoryTitle commited" v-if="committed">已提交</div>

    <!-- 填报与添加区域 -->
    <div class="addNew">
      <tr>
        <td style="vertical-align: middle">内容</td>
        <td>
          <textarea
            cols="30"
            rows="10"
            placeholder="请输入教学兼职相关内容"
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
      content: "",
      teachername: "",
    };
  },
  props: ["data"],
  methods: {
    showHistory() {
      if (!this.historyShown) {
        this.historyDisplayBtnText = "收起 ";
        this.historyShown = true;
      } else {
        this.historyDisplayBtnText = "展开 ";
        this.historyShown = false;
      }
    },
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
    },
  },
  created() {},
};
</script>


<style scoped>
</style>