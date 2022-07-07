<template>
  <div class="componentSubsection category">
    <div class="categoryTitle">教学名师BB7</div>
    <!-- 填报与添加区域 -->
    <div class="addNew">
      <tr>
        <td>级别</td>
        <td>
          <input type="radio" id="nation" value="国家级" v-model="awardLevel" />
          <label for="nation">国家级</label>

          <input type="radio" id="province" value="省级" v-model="awardLevel" />
          <label for="province">省级</label>

          <input type="radio" id="school" value="校级" v-model="awardLevel" />
          <label for="school">校级</label>
        </td>
      </tr>

      <tr>
        <td>评奖时间</td>
        <td>
          <input type="date" placeholder="请选择评奖时间" v-model="date" />
        </td>
      </tr>

      <tr>
        <td>姓名</td>
        <td>
          <input type="text" placeholder="请输入姓名" v-model="name" />
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
      //填报数据
      awardLevel: "",
      date: "",
      name: "",
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
          awardLevel: this.$data.awardLevel,
          awardDate: this.$data.date,
          declarantName: this.$currentUser,
          type: "BB7",
          id: this.data.id,
        }
      for (const key in specialVo) {
        formData.append(key,specialVo[key]);
      }

      formData.append("teachers", JSON.stringify(this.$data.participants));
      formData.append("specialVo",specialVo);

      //以下需要修改接口
      this.$axios
        .post('http://abkkds.vaiwan.com/special-workload/update/teacher', formData, {
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