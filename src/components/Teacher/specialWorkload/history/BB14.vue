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
            placeholder="请输入工作量相关内容"
            v-model="content"
          ></textarea>
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
      content: "",
      teacherName: "",
    };
  },
  props: ["data"],
  mounted(){
    this.$data.content = this.data.briefIntroduction;
    this.$data.teacherName = this.data.declarantName;
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
      if(this.$data.content==""||this.$data.teacherName==""){
        alert("数据填报不可为空！！！")
        return;
      }
    },
  },
  created() {},
};
</script>


<style scoped>
</style>