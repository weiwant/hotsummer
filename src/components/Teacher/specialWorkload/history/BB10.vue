<template>
  <div class="componentSubsection category">
    <div class="categoryTitle saved" v-if="!committed">仅保存</div>
    <div class="categoryTitle commited" v-if="committed">已提交</div>

    <!-- 填报与添加区域 -->
    <div class="addNew">
      <tr>
        <td>项目级别</td>
        <td>
          <input type="radio" 
          id="national" 
          value="国家级" 
          v-model="level"
          :disabled="!isEditing"  />
          <label for="national">国家级</label>

          <input type="radio" 
          id="province" 
          value="省级" 
          v-model="level" 
          :disabled="!isEditing" />
          <label for="province">省级</label>

          <input type="radio"
           id="school" 
           value="校级"
            v-model="level"
            :disabled="!isEditing"  />
          <label for="school">校级</label>

          <input type="radio"
           id="college" 
           value="院级" 
           v-model="level"
           :disabled="!isEditing"  />
          <label for="college">院级</label>
        </td>
      </tr>

      <tr>
        <td>项目荣誉</td>
        <td>
          <input type="text"
           placeholder="请输入项目荣誉"
            v-model="honor" 
            :disabled="!isEditing" />
        </td>
      </tr>

      <tr>
        <td>项目名称</td>
        <td>
          <input
            type="text"
            placeholder="请输入项目名称"
            v-model="projectNname"
            :disabled="!isEditing" 
          />
        </td>
      </tr>

      <tr>
        <td>学生团队名称</td>
        <td>
          <input
            type="text"
            placeholder="请输入学生团队名称"
            v-model="teamName"
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
            v-model="studentname"
            :disabled="!isEditing" 
          />
        </td>
      </tr>

      <tr>
        <td>学生学号</td>
        <td>
          <input type="text" 
          placeholder="请输入学生学号"
           v-model="number"
           :disabled="!isEditing"  />
        </td>
      </tr>
      <tr>
        <td>指导老师姓名</td>
        <td>
          <input
            type="text"
            placeholder="请输入指导老师姓名"
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
      level: "",
      honor: "",
      projectName: "",
      teamName: "",
      studentName: "",
      teacherName: "",
    };
  },
  props: ["data"],
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
      if(this.$data. level==""||this.$data. honor==""||this.$data.projectName==""||this.$data.teamName==""||this.$data. studentName==""||this.$data.teacherName==""){
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