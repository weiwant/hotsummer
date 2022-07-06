<template>
  <div class="componentSubsection category">
    <div class="categoryTitle saved" v-if="!committed">仅保存</div>
    <div class="categoryTitle commited" v-if="committed">已提交</div>

    <!-- 填报与添加区域 -->
    <div class="addNew">
      <tr>
        <td>竞赛类别</td>
        <td>
          <input type="radio" 
          id="A" 
          value="A" 
          v-model="competitionCategory" 
          :disabled="!isEditing"/>
          <label for="A">A</label>

          <input type="radio"
           id="B" 
           value="B" 
           v-model="competitionCategory" 
           :disabled="!isEditing"/>
          <label for="B">B</label>

          <input type="radio"
           id="C" 
           value="C" 
           v-model="competitionCategory" 
           :disabled="!isEditing"/>
          <label for="C">C</label>
        </td>
      </tr>

      <tr>
        <td>竞赛名称</td>
        <td>
          <input
            type="text"
            placeholder="请输入竞赛名称"
            v-model="competitionName"
            :disabled="!isEditing"
          />
        </td>
      </tr>

      <tr>
        <td>竞赛级别</td>
        <td>
          <input type="radio" 
          id="international"
           value="国际" 
           v-model="level" 
           :disabled="!isEditing"/>
          <label for="international">国际</label>

          <input type="radio" 
          id="national"
           value="全国"
            v-model="level" 
            :disabled="!isEditing"/>
          <label for="national">全国</label>

          <input type="radio" 
          id="province"
           value="省级" 
           v-model="level"
           :disabled="!isEditing" />
          <label for="province">省级</label>

          <input type="radio"
           id="school"
            value="校级" 
            :disabled="!isEditing" />
          <label for="school">校级</label>
        </td>
      </tr>

      <tr>
        <td>竞赛等级</td>
        <td>
          <input type="radio"
           id="first" 
           value="一等奖"
            v-model="category"
            :disabled="!isEditing"  />
          <label for="first">一等奖</label>

          <input type="radio"
           id="second"
            value="二等奖" 
            v-model="category"
            :disabled="!isEditing"  />
          <label for="second">二等奖</label>

          <input type="radio" 
          id="third" 
          value="三等奖"
           v-model="category"
           :disabled="!isEditing"  />
          <label for="third">三等奖</label>

          <input type="radio"
           id="others"
            value="其他"
             v-model="category"
             :disabled="!isEditing"  />
          <label for="others">其他</label>
        </td>
      </tr>

      <tr>
        <td>参赛作品名称</td>
        <td>
          <input
            type="text"
            placeholder="请输入参赛作品名称"
            v-model="workName"
            :disabled="!isEditing" 
          />
        </td>
      </tr>

      <tr>
        <td>参赛队伍名称</td>
        <td>
          <input
            type="text"
            placeholder="请输入参赛队伍名称"
            v-model="teamName"
            :disabled="!isEditing" 
          />
        </td>
      </tr>

      <tr>
        <td>参赛学生姓名</td>
        <td>
          <input
            type="text"
            placeholder="请输入参赛学生姓名"
            v-model="studentName"
            :disabled="!isEditing" 
          />
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

      <tr>
        <td>证明文件</td>
        <td>
          <input type="file" placeholder="请选择相关文件" :disabled="!isEditing" />
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
      competitioncCategory: "",
      competitionName: "",
      level: "",
      category: "",
      workName: "",
      teamName: "",
      studentName: "",
      teacherName: "",
    };
  },
  props: ["data"],
  mounted(){
    var categoryString = this.data.awardLevel.substring(2,5);
    var levelString = this.data.awardLevel.substring(0,2);
    console.log(levelString);
    console.log(categoryString);

    this.$data.competitioncCategory = this.data.projectCategory;
    this.$data.competitionName = this.data.projectName;
    this.$data.teacherName = this.data.declarantName;
    this.$data.level = levelString;
    this.$data.category = categoryString;
    this.$data.workName = this.data.achievementName;
    this.$data.studentName = this.data.guidingStudentName;
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
     
       if(this.$data.competitioncCategory==""||this.$data.competitionName==""||this.$data.level==""||this.$data.category==""||this.$data. workName==""||this.$data.studentName==""||this.$data.teamName==""||this.$data.teacherName==""){
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