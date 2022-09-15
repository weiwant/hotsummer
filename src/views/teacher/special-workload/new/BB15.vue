<template>
  <div class="app-section category">
    <div class="categoryTitle">其他BB15</div>
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
      content: "",
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
        briefIntroduction: this.$data.content,
        declarantName: this.$currentUser,
        type: "BB15",
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