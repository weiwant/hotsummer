<template>
  <div class="componentSubsection category">
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
            v-model="projectname"
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
            v-model="studentname"
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
      level: "",
      honor: "",
      projectname: "",
      number: "",
      teamName: "",
      studentname: "",
      teachername: "",
    };
  },
  methods: {
    save() {
      //点击保存，调用DynamicCollection组件的方法，将其中含有的数据同步至本组件内
      this.$refs.dynamic.transmitData();
      var _this = this;
      const formData = new FormData();

      var data = JSON.stringify([
        {
          level: this.$data.level,
          receivingHonor: this.$data.honor,
          guidingStudentId: this.$data.number,
          projectName: this.$data.projectName,
          guidingStudentTeam: this.$data.teamName,
          guidingStudentName: this.$data.studentname,
          declarantName: this.$data.teachername,
          type: "BB10"
        },
      ]);

      formData.append("data", data);

      console.log(formData.get("data"));

      //以下需要修改接口
      this.$axios
        .post(`${this.$domainName}/special-workload/upload`, formData, {
          headers: {
            "Content-Type": "multipart/form-datas",
          },
        })
        .then((res) => {
          if (res.data.response.code == 200) {
            alert("报表文件上传成功！");
          } else {
            alert("上传失败！");
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