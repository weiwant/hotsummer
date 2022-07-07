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
            v-model="studnetname"
          />
        </td>
      </tr>

      <tr>
        <td>教师姓名</td>
        <td>
          <input
            type="text"
            placeholder="请输入教师姓名"
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
      category: "",
      title: "",
      studnetname: "",
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
          projectCategory: this.$data.category,
          achievementName: this.$data.title,
          guidingStudentName: this.$data.studnetname,
          declarantName: this.$data.teachername,
          type: "BB8",
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