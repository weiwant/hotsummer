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
      participants: [],
    };
  },
  methods: {
    save() {
      this.$refs.dynamic.changeState();
      this.isEditing = false;
      //点击保存，调用DynamicCollection组件的方法，将其中含有的数据同步至本组件内
      this.$refs.dynamic.transmitData();
      // console.log(this.participants);
      var _this = this;
      const formData = new FormData();
      // console.log("响应");

      var specialVo = {
          briefIntroduction: this.$data.content,
          declarantName: this.$currentUser,
          type: "BB12",
          id: this.data.id,
        }
      for (const key in specialVo) {
        formData.append(key,specialVo[key]);
      }

      formData.append("teachers", JSON.stringify(this.$data.participants));
      formData.append("specialVo",specialVo);

      //以下需要修改接口
      this.$axios
        .post('http://abkkds.vaiwan.com/special-workload/save/teacher', formData, {
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