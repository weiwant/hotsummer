<template>
  <div class="componentSubsection category">
    <div class="categoryTitle saved" v-if="!committed">仅保存</div>
    <div class="categoryTitle commited" v-if="committed">已提交</div>

    <!-- 填报与添加区域 -->
    <div class="addNew">
      <tr>
        <td>竞赛类别</td>
        <td>
          <input
            type="radio"
            id="A"
            value="A"
            v-model="competitionCategory"
            :disabled="!isEditing"
          />
          <label for="A">A</label>

          <input
            type="radio"
            id="B"
            value="B"
            v-model="competitionCategory"
            :disabled="!isEditing"
          />
          <label for="B">B</label>

          <input
            type="radio"
            id="C"
            value="C"
            v-model="competitionCategory"
            :disabled="!isEditing"
          />
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
            :disabled="!isEditing"
          />
        </td>
      </tr>

      <tr>
        <td>竞赛级别</td>
        <td>
          <input
            type="radio"
            id="international"
            value="国际"
            v-model="level"
            :disabled="!isEditing"
          />
          <label for="international">国际</label>

          <input
            type="radio"
            id="national"
            value="全国"
            v-model="level"
            :disabled="!isEditing"
          />
          <label for="national">全国</label>

          <input
            type="radio"
            id="province"
            value="省级"
            v-model="level"
            :disabled="!isEditing"
          />
          <label for="province">省级</label>

          <input type="radio" id="school" value="校级" :disabled="!isEditing" />
          <label for="school">校级</label>
        </td>
      </tr>

      <tr>
        <td>竞赛等级</td>
        <td>
          <input
            type="radio"
            id="first"
            value="一等奖"
            v-model="category"
            :disabled="!isEditing"
          />
          <label for="first">一等奖</label>

          <input
            type="radio"
            id="second"
            value="二等奖"
            v-model="category"
            :disabled="!isEditing"
          />
          <label for="second">二等奖</label>

          <input
            type="radio"
            id="third"
            value="三等奖"
            v-model="category"
            :disabled="!isEditing"
          />
          <label for="third">三等奖</label>

          <input
            type="radio"
            id="others"
            value="其他"
            v-model="category"
            :disabled="!isEditing"
          />
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
            :disabled="!isEditing"
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
            :disabled="!isEditing"
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

      <tr>
        <td>证明文件</td>
        <td>
          <input
            type="file"
            ref="file"
            name="file"
            @change="getFileData()"
            multiple="true"
            :disabled="!isEditing"
          />
        </td>
      </tr>

      <label v-show="isVisible">已上传文件:</label>
      <div v-show="isVisible" v-for="(fileName, item) in fileNames" :key="item">
        <label>{{ fileName }}</label>
        <button @click="howDeleteFile(item)" disabled="canDelete">删除</button>
      </div>

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
      competitionCategory: "",
      competitionName: "",
      level: "",
      category: "",
      workName: "",
      teamName: "",
      studentName: "",
      teacherName: "",
      //封装文件信息
      uploadFile: [],
      isVisible: false,
      //后端返回文件列表
      fileNames: [],
      //前端更新本地文件列表
      thisFiles: [],
      //能否删除
      canDelete: false,
    };
  },
  props: ["data"],
  created() {
    //本组件的mounted会在dynamic组件的created之后执行，穿不了数据，尝试提前至created处执行
    if (this.data.status == "已提交") {
      this.$data.canDelete = true;
      this.committed = true;
    } else {
      this.committed = false;
    }
    let categoryString = this.data.awardLevel.substring(2, 5);
    let levelString = this.data.awardLevel.substring(0, 2);

    this.$data.competitionCategory = this.data.projectCategory;
    this.$data.competitionName = this.data.projectName;
    this.$data.teacherName = this.data.somePeople[0].teacherName;
    this.$data.level = levelString;
    this.$data.category = categoryString;
    this.$data.workName = this.data.achievementName;
    this.$data.studentName = this.data.guidingStudentName;
    this.$data.teamName = this.data.teamName;
  },
  mounted() {
    //文件列表是否可见
    if (this.$data.fileNames != "") {
      this.$data.isVisible = true;
    } else {
      this.$data.isVisible = false;
    }
  },
  methods: {
    //点击触发上传方法
    uploadMaterial() {
      this.$refs.file.dispatchEvent(new MouseEvent("click"));
    },
    //添加文件数据
    getFileData(file) {
      this.$data.isVisible = true;
      const inputFile = this.$refs.file.files[0];
      for (let i = 0; i++; i < this.$data.fileNames.length) {
        if (this.$data.fileNames[i] == inputFile.name) {
          alert("请勿上传同名文件！");
        } else {
          this.$data.uploadFile.push(inputFile);
          this.$data.fileNames.push(inputFile.name);
          this.$data.thisFiles.push(inputFile.name);
        }
      }
    },
    // 编辑
    edit() {
      this.isEditing = true;
    },
    // 提交
    commit() {
      this.isEditing = false;
      const formData = new FormData();
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

      for (let i = 0; i < this.$data.uploadFile.length; i++) {
        formData.append("files", this.$data.uploadFile[i]);
      }
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
    // 保存
    save() {
      this.isEditing = false;
      const formData = new FormData();

      var specialVo = {
        projectCategory: this.$data.competitionCategory,
        projectName: this.$data.competitionName,
        awardLevel: awardLevel,
        achievementName: this.$data.workName,
        guidingStudentTeam: this.$data.teamName,
        guidingStudentName: this.$data.studentName,
        declarantName: this.$currentUser,
        type: "BB9",
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

      for (let i = 0; i < this.$data.uploadFile.length; i++) {
        formData.append("files", this.$data.uploadFile[i]);
      }
      //以下需要修改接口
      this.$axios
        .post(`${this.$domainName}/special-workload/update/teacher`, formData, {
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
    howDeleteFile(item) {
      var hasUpload = true;
      for (let i = 0; i++; i <= this.$data.thisFiles.length) {
        if (this.$data.thisFiles[i] == this.$data.fileNames[item]) {
          //前端列表中存在该文件，说明文件没有上传过，本地删除
          hasUpload = false;
          this.$data.uploadFile.splice(item, 1);
          this.$data.fileNames.splice(item, 1);
          this.$data.thisFiles.splice(item, 1);
        }
      }
      if (hasUpload) {
        //循环后发现文件不存在前端列表，说明文件在后端上传过，传入delete方法请求删除
        this.deleteFile(item);
      }
    },
    deleteFile(item) {
      const formData = new FormData();

      var fileName = this.$data.fileNames[item];
      var id = this.data.id;

      formData.append("fileName", fileName);
      formData.append("id", id);

      this.$axios
        .post(`${this.$domainName}/file/delete-file`, formData)
        .then((res) => {
          if (res.data.response.code == 200) {
            this.$data.fileNames.splice(item, 1);
            alert("文件删除成功！");
          } else {
            alert("文件删除失败！");
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