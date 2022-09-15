<template>
  <div class="app-section category">
    <div class="categoryTitle saved" v-if="!committed">仅保存</div>
    <div class="categoryTitle commited" v-if="committed">已提交</div>
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
            :disabled="!isEditing"
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
      content: "",
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
    this.$data.content = this.data.briefIntroduction;
    this.$data.teacherName = this.data.somePeople[0].teacherName;
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
      let specialVo = {
        briefIntroduction: this.$data.content,
        declarantName: this.$currentUser,
        type: "BB15",
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
      let specialVo = {
        briefIntroduction: this.$data.content,
        declarantName: this.$currentUser,
        type: "BB15",
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