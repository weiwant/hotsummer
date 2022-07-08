<template>
  <div class="componentSubsection category">
    <div class="categoryTitle saved" v-if="!committed">仅保存</div>
    <div class="categoryTitle commited" v-if="committed">已提交</div>

    <!-- 填报与添加区域 -->
    <div class="addNew">
      <tr>
        <td>项目级别</td>
        <td>
          <input
            type="radio"
            id="national"
            value="国家级"
            v-model="level"
            :disabled="!isEditing"
          />
          <label for="national">国家级</label>

          <input
            type="radio"
            id="province"
            value="省级"
            v-model="level"
            :disabled="!isEditing"
          />
          <label for="province">省级</label>

          <input
            type="radio"
            id="school"
            value="校级"
            v-model="level"
            :disabled="!isEditing"
          />
          <label for="school">校级</label>

          <input
            type="radio"
            id="college"
            value="院级"
            v-model="level"
            :disabled="!isEditing"
          />
          <label for="college">院级</label>
        </td>
      </tr>

      <tr>
        <td>项目荣誉</td>
        <td>
          <input
            type="text"
            placeholder="请输入项目荣誉"
            v-model="honor"
            :disabled="!isEditing"
          />
        </td>
      </tr>

      <tr>
        <td>项目名称</td>
        <td>
          <input
            type="text"
            placeholder="请输入项目名称"
            v-model="projectName"
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
            v-model="studentName"
            :disabled="!isEditing"
          />
        </td>
      </tr>

      <tr>
        <td>学生学号</td>
        <td>
          <input
            type="text"
            placeholder="请输入学生学号"
            v-model="number"
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
      number: "",
      honor: "",
      projectName: "",
      teamName: "",
      studentName: "",
      teacherName: "",
    };
  },
  props: ["data"],
  created() {
    if (this.data.status == "已提交") {
      this.committed = true;
    } else {
      this.committed = false;
    }
    this.$data.honor = this.data.receivingHonor;
    this.$data.projectName = this.data.projectName;
    this.$data.teacherName = this.data.somePeople[0].teacherName;
    this.$data.level = this.data.level;
    this.$data.teamName = this.data.guidingStudentTeam;
    this.$data.studentName = this.data.guidingStudentName;
    this.$data.number = this.data.guidingStudentId;
  },

  methods: {
    // 编辑
    edit() {
      this.isEditing = true;
    },
    // 提交
    commit() {
      this.isEditing = false;
      const formData = new FormData();
      let specialVo = {
        level: this.$data.level,
        receivingHonor: this.$data.honor,
        guidingStudentId: this.$data.number,
        projectName: this.$data.projectName,
        guidingStudentTeam: this.$data.teamName,
        guidingStudentName: this.$data.studentName,
        declarantName: this.$currentUser,
        type: "BB10",
        id: this.data.id,
        status: "已提交",
      };
      for (const key in specialVo) {
        formData.append(key, specialVo[key]);
      }

      formData.append(
        "teachers",
        JSON.stringify([
          {
            teacherName: this.teacherName,
            authorOrder: 0,
          },
        ])
      );
      formData.append("specialVo", specialVo);

      //以下需要修改接口
      this.$axios
        .post(`${this.$domainName}/special-workload/update/teacher`, formData, {
          headers: {
            "Content-Type": "multipart/form-datas",
          },
        })
        .then((res) => {
          if (res.data.response.code == 200) {
            this.committed = true; //按道理应该重新请求，但是暂时直接改状态吧
            alert("提交申报成功！");
          } else {
            alert("提交申报失败！");
          }
        })
        .catch(function (error) {
          console.log(error);
        });
    },
    // 保存
    save() {
      this.isEditing = false;
      const formData = new FormData();
      let specialVo = {
        level: this.$data.level,
        receivingHonor: this.$data.honor,
        guidingStudentId: this.$data.number,
        projectName: this.$data.projectName,
        guidingStudentTeam: this.$data.teamName,
        guidingStudentName: this.$data.studentName,
        declarantName: this.$currentUser,
        type: "BB10",
        id: this.data.id,
      };
      for (const key in specialVo) {
        formData.append(key, specialVo[key]);
      }

      formData.append(
        "teachers",
        JSON.stringify([
          {
            teacherName: this.teacherName,
            authorOrder: 0,
          },
        ])
      );
      formData.append("specialVo", specialVo);

      //以下需要修改接口
      this.$axios
        .post(`${this.$domainName}/special-workload/update/teacher`, formData, {
          headers: {
            "Content-Type": "multipart/form-datas",
          },
        })
        .then((res) => {
          if (res.data.response.code == 200) {
            alert("提交申报成功！");
          } else {
            alert("提交申报失败！");
          }
        })
        .catch(function (error) {
          console.log(error);
        });
    },
  },
};
</script>


<style scoped>
</style>