<template>
  <div class="app-section category">
    <div class="categoryTitle saved" v-if="!committed">仅保存</div>
    <div class="categoryTitle commited" v-if="committed">已提交</div>
    <!-- 填报与添加区域 -->
    <div class="addNew">
      <tr>
        <td>级别</td>
        <td>
          <input
            type="radio"
            id="nation"
            value="国家级"
            v-model="awardLevel"
            :disabled="!isEditing"
          />
          <label for="nation">国家级</label>

          <input
            type="radio"
            id="province"
            value="省级"
            v-model="awardLevel"
            :disabled="!isEditing"
          />
          <label for="province">省级</label>

          <input
            type="radio"
            id="school"
            value="校级"
            v-model="awardLevel"
            :disabled="!isEditing"
          />
          <label for="school">校级</label>
        </td>
      </tr>

      <tr>
        <td>评奖时间</td>
        <td>
          <input
            type="date"
            placeholder="请选择评奖时间"
            v-model="date"
            :disabled="!isEditing"
          />
        </td>
      </tr>

      <tr>
        <td>姓名</td>
        <td>
          <input
            type="text"
            placeholder="请输入姓名"
            v-model="teacherName"
            :disabled="!isEditing"
          />
        </td>
      </tr>

      <button class="blue complete commit" @click="commit" v-if="!committed">
        提&nbsp;交
      </button>
      <button
        class="blue complete notCommit"
        @click="save"
        v-if="isEditing && !committed"
      >
        保&nbsp;存
      </button>
      <button
        class="blue complete notCommit"
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
      //填报数据
      awardLevel: "",
      date: "",
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
    this.$data.awardLevel = this.data.awardLevel;
    this.$data.date = this.data.awardDate;
    this.$data.teacherName = this.data.somePeople[0].teacherName;
    this.$data.participants = this.data.somePeople;
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
        awardLevel: this.$data.awardLevel,
        awardDate: this.$data.date,
        declarantName: this.$currentUser,
        type: "BB7",
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
        awardLevel: this.$data.awardLevel,
        awardDate: this.$data.date,
        declarantName: this.$currentUser,
        type: "BB7",
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