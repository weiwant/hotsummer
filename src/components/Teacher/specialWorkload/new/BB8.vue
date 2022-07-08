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
            v-model="studnetName"
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
      studnetName: "",
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
        projectCategory: this.$data.category,
        achievementName: this.$data.title,
        guidingStudentName: this.$data.studnetName,
        declarantName: this.$currentUser,
        type: "BB8",
      };
      for (const key in specialVo) {
        formData.append(key, specialVo[key]);
      }

      formData.append("teachers", JSON.stringify(this.$data.participants));
      formData.append("specialVo", specialVo);

      //以下需要修改接口
      this.$axios
        .post(`${this.$domainName}/special-workload/save/teacher`, formData, {
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