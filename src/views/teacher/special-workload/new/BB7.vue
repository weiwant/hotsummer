<template>
  <div class="app-section category">
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
          <input type="text" placeholder="请输入姓名" v-model="teacherName" />
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
      //填报数据
      awardLevel: "",
      date: "",
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
        awardLevel: this.$data.awardLevel,
        awardDate: this.$data.date,
        declarantName: this.$currentUser,
        type: "BB7",
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