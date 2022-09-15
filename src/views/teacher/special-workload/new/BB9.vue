<template>
  <div class="app-section category">
    <div class="categoryTitle">指导学生竞赛获奖BB9</div>

    <!-- 填报与添加区域 -->
    <div class="addNew">
      <tr>
        <td>竞赛类别</td>
        <td>
          <input type="radio" id="A" value="A" v-model="competitionCategory" />
          <label for="A">A</label>

          <input type="radio" id="B" value="B" v-model="competitionCategory" />
          <label for="B">B</label>

          <input type="radio" id="C" value="C" v-model="competitionCategory" />
          <label for="C">C</label>
        </td>
      </tr>

      <tr>
        <td>竞赛名称</td>
        <td>
          <input
            type="text"
            placeholder="请输入竞赛名称"
            v-model="competitionName"
          />
        </td>
      </tr>

      <tr>
        <td>竞赛级别</td>
        <td>
          <input type="radio" id="international" value="国际" v-model="level" />
          <label for="international">国际</label>

          <input type="radio" id="national" value="全国" v-model="level" />
          <label for="national">全国</label>

          <input type="radio" id="province" value="省级" v-model="level" />
          <label for="province">省级</label>

          <input type="radio" id="school" value="校级" />
          <label for="school">校级</label>
        </td>
      </tr>

      <tr>
        <td>竞赛等级</td>
        <td>
          <input type="radio" id="first" value="一等奖" v-model="category" />
          <label for="first">一等奖</label>

          <input type="radio" id="second" value="二等奖" v-model="category" />
          <label for="second">二等奖</label>

          <input type="radio" id="third" value="三等奖" v-model="category" />
          <label for="third">三等奖</label>

          <input type="radio" id="others" value="其他" v-model="category" />
          <label for="others">其他</label>
        </td>
      </tr>

      <tr>
        <td>参赛作品名称</td>
        <td>
          <input
            type="text"
            placeholder="请输入参赛作品名称"
            v-model="workName"
          />
        </td>
      </tr>

      <tr>
        <td>参赛队伍名称</td>
        <td>
          <input
            type="text"
            placeholder="请输入参赛队伍名称"
            v-model="teamName"
          />
        </td>
      </tr>

      <tr>
        <td>参赛学生姓名</td>
        <td>
          <input
            type="text"
            placeholder="请输入参赛学生姓名"
            v-model="studentName"
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
          />
        </td>
      </tr>

      <tr>
        <td>证明文件</td>
        <td>
          <input
            type="file"
            ref="file"
            name="file"
            @change="getFileData()"
            multiple="true"
          />
        </td>
      </tr>
      <label v-show="isVisible">已上传文件:</label>
      <div v-show="isVisible" v-for="(fileName, item) in fileNames" :key="item">
        <label>{{ fileName }}</label>
        <button @click="deleteFile(item)">删除</button>
      </div>

      <button class="blue complete" @click="save">保&nbsp;存</button>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      competitionCategory: "",
      competitionName: "",
      level: "",
      category: "",
      workName: "",
      teamName: "",
      studentName: "",
      teacherName: "",
      //文件列表
      uploadFile: [],
      //文件名
      fileNames: [],
      isVisible: false,
      participants: [],
    };
  },
  methods: {
    //点击触发上传方法
    uploadMaterial() {
      this.$refs.file.dispatchEvent(new MouseEvent("click"));
    },
    //添加文件数据
    getFileData(file) {
      this.isVisible = true;
      const inputFile = this.$refs.file.files[0];
      this.$data.uploadFile.push(inputFile);
      this.$data.fileNames.push(inputFile.name);
    },

    save() {
      this.isEditing = false;
      const formData = new FormData();
      this.participants.push({
        teacherName: this.teacherName,
        authorOrder: 0,
      });
      const awardLevel = this.level + this.category;
      var specialVo = {
        projectCategory: this.$data.competitionCategory,
        projectName: this.$data.competitionName,
        awardLevel: awardLevel,
        achievementName: this.$data.workName,
        guidingStudentTeam: this.$data.teamName,
        guidingStudentName: this.$data.studentName,
        declarantName: this.$currentUser,
        type: "BB9",
      };
      for (const key in specialVo) {
        formData.append(key, specialVo[key]);
      }

      formData.append("teachers", JSON.stringify(this.$data.participants));
      formData.append("specialVo", specialVo);

      for (let i = 0; i < this.$data.uploadFile.length; i++) {
        formData.append("files", this.$data.uploadFile[i]);
      }
      //以下需要修改接口
      this.$axios
        .post(`/special-workload/save/teacher`, formData, {
          headers: {
            "Content-Type": "multipart/form-datas",
          },
        })
        .then((res) => {
          if (res.data.response.code == 200) {
            alert("保存申报成功！");
          } else {
            alert("保存申报失败！");
          }
        })
        .catch(function (error) {
          console.log(error);
        });
    },
    deleteFile(item) {
      var _this = this;
      // console.log(item);
      this.$data.uploadFile.splice(item, 1);
      // console.log(this.$data.uploadFile);
      this.$data.fileNames.splice(item, 1);
      // console.log(this.$data.fileNames);
      if (this.$data.uploadFile == "") {
        this.$data.isVisible = false;
      } else {
        this.$data.isVisible = true;
      }
    },
  },
  created() {},
};
</script>


<style scoped>
</style>