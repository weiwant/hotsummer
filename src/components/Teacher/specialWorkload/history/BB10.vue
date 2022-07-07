<template>
  <div class="componentSubsection category">
    <div class="categoryTitle saved" v-if="!committed">仅保存</div>
    <div class="categoryTitle commited" v-if="committed">已提交</div>

    <!-- 填报与添加区域 -->
    <div class="addNew">
      <tr>
        <td>项目级别</td>
        <td>
          <input
            type="radio"
            id="national"
            value="国家级"
            v-model="level"
            :disabled="!isEditing"
          />
          <label for="national">国家级</label>

          <input
            type="radio"
            id="province"
            value="省级"
            v-model="level"
            :disabled="!isEditing"
          />
          <label for="province">省级</label>

          <input
            type="radio"
            id="school"
            value="校级"
            v-model="level"
            :disabled="!isEditing"
          />
          <label for="school">校级</label>

          <input
            type="radio"
            id="college"
            value="院级"
            v-model="level"
            :disabled="!isEditing"
          />
          <label for="college">院级</label>
        </td>
      </tr>

      <tr>
        <td>项目荣誉</td>
        <td>
          <input
            type="text"
            placeholder="请输入项目荣誉"
            v-model="honor"
            :disabled="!isEditing"
          />
        </td>
      </tr>

      <tr>
        <td>项目名称</td>
        <td>
          <input
            type="text"
            placeholder="请输入项目名称"
            v-model="projectNname"
            :disabled="!isEditing"
          />
        </td>
      </tr>

      <tr>
        <td>学生团队名称</td>
        <td>
          <input
            type="text"
            placeholder="请输入学生团队名称"
            v-model="teamName"
            :disabled="!isEditing"
          />
        </td>
      </tr>

      <tr>
        <td>学生姓名</td>
        <td>
          <input
            type="text"
            placeholder="请输入学生姓名"
            v-model="studentname"
            :disabled="!isEditing"
          />
        </td>
      </tr>

      <tr>
        <td>学生学号</td>
        <td>
          <input
            type="text"
            placeholder="请输入学生学号"
            v-model="number"
            :disabled="!isEditing"
          />
        </td>
      </tr>
      <tr>
        <td>指导老师姓名</td>
        <td>
          <input
            type="text"
            placeholder="请输入指导老师姓名"
            v-model="teacherName"
            :disabled="!isEditing"
          />
        </td>
      </tr>

      <button
        class="universalBlueBtn complete commit"
        @click="commit"
        v-if="!committed"
      >
        提&nbsp;交
      </button>
      <button
        class="universalBlueBtn complete notCommit"
        @click="save"
        v-if="isEditing && !committed"
      >
        保&nbsp;存
      </button>
      <button
        class="universalBlueBtn complete notCommit"
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
      level: "",
      honor: "",
      projectName: "",
      teamName: "",
      studentName: "",
      teacherName: "",
      participants: [],
    };
  },
  props: ["data"],
  created() {
    if ((this.data.status == "已提交")) {
      this.committed = true;
    } else {
      this.committed = false;
    }
    this.$data.honor = this.data.receivingHonor;
    this.$data.projectName = this.data.projectName;
    this.$data.teacherName = this.data.declarantName;
    this.$data.level = this.data.level;
    this.$data.teamName = this.data.guidingStudentTeam;
    this.$data.studentName = this.data.guidingStudentName;
  },
  mounted() {
    this.$refs.dynamic.changeState(); //默认没有disable，需要调整,而调整需要在dynamic组件的created之后，故放置在本组件的mounted中
  },
  methods: {
    updateParticipants(participants) {
      this.participants = participants;
    },
    // 编辑
    edit() {
      this.$refs.dynamic.changeState();
      this.isEditing = true;
    },
    // 提交
    commit() {
      this.$refs.dynamic.changeState();
      this.isEditing = false;
      //点击保存，调用DynamicCollection组件的方法，将其中含有的数据同步至本组件内
      this.$refs.dynamic.transmitData();
      // console.log(this.participants);
      var _this = this;
      const formData = new FormData();
      // console.log("响应");

      var specialVo = {
          level: this.$data.level,
          receivingHonor: this.$data.honor,
          guidingStudentId: this.$data.number,
          projectName: this.$data.projectName,
          guidingStudentTeam: this.$data.teamName,
          guidingStudentName: this.$data.studentname,
          declarantName: this.$currentUser,
          type: "BB10",
          id: this.data.id,
          status: "已提交"
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
    // 保存
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
          level: this.$data.level,
          receivingHonor: this.$data.honor,
          guidingStudentId: this.$data.number,
          projectName: this.$data.projectName,
          guidingStudentTeam: this.$data.teamName,
          guidingStudentName: this.$data.studentname,
          declarantName: this.$currentUser,
          type: "BB210",
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
};
</script>


<style scoped>
</style>