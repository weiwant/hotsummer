<template>
  <div class="app-section category">
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
            v-model="studentName"
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
        class="button-blue complete commit"
        @click="commit"
        v-if="!committed"
      >
        提&nbsp;交
      </button>
      <button
        class="button-blue complete notCommit"
        @click="save"
        v-if="isEditing && !committed"
      >
        保&nbsp;存
      </button>
      <button
        class="button-blue complete notCommit"
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
    this.$data.category = this.data.projectCategory;
    this.$data.title = this.data.achievementName;
    this.$data.teacherName = this.data.somePeople[0].teacherName;
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
      const formData = new FormData();
      var specialVo = {
        projectCategory: this.$data.category,
        achievementName: this.$data.title,
        guidingStudentName: this.$data.studentName,
        declarantName: this.$currentUser,
        type: "BB8",
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

      var specialVo = {
        projectCategory: this.$data.category,
        achievementName: this.$data.title,
        guidingStudentName: this.$data.studentName,
        declarantName: this.$currentUser,
        type: "BB8",
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