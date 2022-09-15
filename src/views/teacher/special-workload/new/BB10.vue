<template>
  <div class="app-section category">
    <div class="categoryTitle">指导学生科研立项并顺利完成项目BB10</div>

    <!-- 填报与添加区域 -->
    <div class="addNew">
      <tr>
        <td>项目级别</td>
        <td>
          <input type="radio" id="national" value="国家级" v-model="level" />
          <label for="national">国家级</label>
          <input type="radio" id="province" value="省级" v-model="level" />
          <label for="province">省级</label>
          <input type="radio" id="school" value="校级" v-model="level" />
          <label for="school">校级</label>
          <input type="radio" id="college" value="院级" v-model="level" />
          <label for="college">院级</label>
        </td>
      </tr>

      <tr>
        <td>项目荣誉</td>
        <td>
          <input type="text" placeholder="请输入项目荣誉" v-model="honor" />
        </td>
      </tr>

      <tr>
        <td>项目名称</td>
        <td>
          <input
            type="text"
            placeholder="请输入项目名称"
            v-model="projectName"
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
          />
        </td>
      </tr>

      <tr>
        <td>学生学号</td>
        <td>
          <input type="text" placeholder="请输入学生学号" v-model="number" />
        </td>
      </tr>
      <tr>
        <td>指导老师姓名</td>
        <td>
          <input
            type="text"
            placeholder="请输入指导老师姓名"
            v-model="teacherName"
          />
        </td>
      </tr>

      <button class="button-blue complete" @click="save">保&nbsp;存</button>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      level: "",
      honor: "",
      projectName: "",
      number: "",
      teamName: "",
      studentName: "",
      teacherName: "",
      participants: [],
    };
  },
  methods: {
    save() {
      this.isEditing = false;
      const formData = new FormData();
      this.participants.push({
        teacherName: this.teacherName,
        authorOrder: 0,
      });
      var specialVo = {
        level: this.$data.level,
        receivingHonor: this.$data.honor,
        guidingStudentId: this.$data.number,
        projectName: this.$data.projectName,
        guidingStudentTeam: this.$data.teamName,
        guidingStudentName: this.$data.studentName,
        declarantName: this.$currentUser,
        type: "BB10",
      };
      for (const key in specialVo) {
        formData.append(key, specialVo[key]);
      }

      formData.append("teachers", JSON.stringify(this.$data.participants));
      formData.append("specialVo", specialVo);

      //以下需要修改接口
      this.$axios
        .post(`/special-workload/save/teacher`, formData, {
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
  created() {},
};
</script>


<style scoped>
</style>