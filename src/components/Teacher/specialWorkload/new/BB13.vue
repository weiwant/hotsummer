<template>
  <div class="componentSubsection category">
    <div class="categoryTitle">本科生培养活动BB13</div>
    <!-- 填报与添加区域 -->
    <div class="addNew">
      <tr>
        <td>类别</td>
        <td>
          <select v-model="category">
            <option value="卓越班选拔面试">卓越班选拔面试</option>
            <option value="校园开放日咨询">校园开放日咨询</option>
            <option value="本科生分专业宣讲">本科生分专业宣讲</option>
            <option value="转专业面试">转专业面试</option>
            <option value="本科生招生咨询">本科生招生咨询</option>
            <option value="各类评审">各类评审</option>
            <option value="其他">其他</option>
          </select>
        </td>
      </tr>

      <tr>
        <td>时间</td>
        <td>
          <input type="date" placeholder="请选择时间" v-model="time" />
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
      category: "",
      time: "",
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
          projectCategory: this.$data.category,
          awardDate: this.$data.time,
          declarantName: this.$currentUser,
          type: "BB13",
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