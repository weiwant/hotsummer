<template>
  <div class="componentSubsection category">
    <div class="categoryTitle">未知BB12</div>

    <!-- 填报与添加区域 -->
    <div class="addNew">
      <tr>
        <td>内容</td>
        <td>
          <input
            type="textarea"
            placeholder="请输入相关工作量描述"
            v-model="content"
          />
        </td>
      </tr>

      <tr>
        <td>教师姓名</td>
        <td>
          <input
            type="textarea"
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
      content: "",
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
          briefIntroduction: this.$data.content,
          declarantName: this.$data.teachername,
          type: "BB12",
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