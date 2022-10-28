<template>
  <div class="app-section category">
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
          <input type="text" placeholder="请输入姓名" v-model="teacherName" />
        </td>
      </tr>

      <button class="blue complete" @click="save">保&nbsp;存</button>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      category: "",
      time: "",
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
        awardDate: this.$data.time,
        declarantName: this.$currentUser,
        type: "BB13",
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