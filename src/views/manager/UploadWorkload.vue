<template>
  <div class="app-right-wrapper">
    <div class="app-right-title">上传教学工作量</div>
    <!-- part1 -->
    <div class="app-section-title" v-if="this.$currentIdentity == 1">
      课程工作量
    </div>
    <div class="app-section" v-if="this.$currentIdentity == 1">
      <!-- 年份选择 -->
      <label
        >年份:&nbsp;
        <select v-model="yearForClassWorkloadTable">
          <option v-for="index in 5" :key="index">
            {{ currentYear - index + 1 }}
          </option>
        </select>
      </label>
      <!-- 文件上传控件 -->
      <el-input
        type="file"
        ref="file_1"
        v-model="file_1"
        @change="getFileData(file_1, 1)"
        multiple="false"
        accept=".xls,.xlsx"
      ></el-input>
      <!-- 模版下载 -->
      <button class="button-file" @click="downloadTemplate(1)">
         模版下载
      </button>
    </div>
    <!-- part2 -->
    <div class="app-section-title" v-if="this.$currentIdentity != 3">
      考务工作量
    </div>
    <div class="app-section" v-if="this.$currentIdentity != 3">
      <!-- 年份选择 -->
      <label
        >年份:&nbsp;
        <select v-model="yearForExaminationWorkloadTable">
          <option v-for="index in 5" :key="index">
            {{ currentYear - index + 1 }}
          </option>
        </select>
      </label>
      <!-- 文件上传控件 -->
      <el-input
        type="file"
        ref="file_2"
        v-model="file_2"
        @change="getFileData(file_2, 2)"
        multiple="false"
        accept=".xls,.xlsx"
      ></el-input>
      <!-- 模版下载 -->
      <button class="button-file" @click="downloadTemplate(2)">
         模版下载
      </button>
    </div>
    <!-- part3 -->
    <div class="app-section-title" v-if="this.$currentIdentity != 2">
      论文工作量
    </div>
    <div class="app-section" v-if="this.$currentIdentity != 2">
      <!-- 年份选择 -->
      <label
        >年份:&nbsp;
        <select v-model="yearForPaperWorkloadTable">
          <option v-for="index in 5" :key="index">
            {{ currentYear - index + 1 }}
          </option>
        </select>
      </label>
      <!-- 文件上传控件 -->
      <el-input
        type="file"
        ref="file_3"
        v-model="file_3"
        @change="getFileData(file_3, 3)"
        multiple="false"
        accept=".xls,.xlsx"
      ></el-input>
      <!-- 模版下载 -->
      <button class="button-file" @click="downloadTemplate(3)">
         模版下载
      </button>
    </div>
  </div>
</template>

<script>
export default {
  name: "UploadWorkload",
  data() {
    return {
      currentYear: this.$currentYear,
      yearForClassWorkloadTable: this.$currentYear,
      yearForExaminationWorkloadTable: this.$currentYear,
      yearForPaperWorkloadTable: this.$currentYear,
      classWorkloadTableTemplate: [
        {
          学年: "",
          学期: "",
          辅助: "",
          计算用学时: "",
          课程性质解释: "",
          计算机用时: "",
          课程名称: "",
          课程性质: "",
          课程号: "",
          学分: "",
          折扣: "",
          实验安排: "",
          实验课时: "",
          教分: "",
          合课单位: "",
          实验室核对结果: "",
          上课教师名字: "",
          教师职称: "",
          专业: "",
          折扣前BA1系数: "",
          原始教分: "",
          其他教师名: "",
          计划学院: "",
          实践课时: "",
          备注: "",
          学期: "",
          是否为特殊班级: "",
          是否全英教学: "",
          上课人数: "",
          年级: "",
          教学班: "",
          BA1系数: "",
          开课学院: "",
          理论课时: "",
          工作性质: "",
        },
      ],
      examinationWorkloadTableTemplate: [
        {
          学年: "",
          学期: "",
          课程名称: "",
          上课老师: "",
          计算用学时: "",
          教分原始分: "",
          BA1系数: "",
          教分: "",
        },
      ],
      paperWorkloadTableTemplate: [
        {
          学年: "",
          学期: "",
          课程名称: "",
          上课老师: "",
          计算用学时: "",
          教分原始分: "",
          BA1系数: "",
          教分: "",
        },
      ],
      file_1: "",
      file_2: "",
      file_3: "",
    };
  },
  methods: {
    //点击触发上传方法
    uploadMaterial() {
      this.$refs.file_1.dispatchEvent(new MouseEvent("click"));
      this.$refs.file_2.dispatchEvent(new MouseEvent("click"));
      this.$refs.file_3.dispatchEvent(new MouseEvent("click"));
    },
    //触发选择文件，判断文件类型
    getFileData(file, a) {
      var _this = this;
      let inputFile;
      if (a == 1) {
        inputFile = this.$refs.file_1;
      } else if (a == 2) {
        inputFile = this.$refs.file_2;
      } else {
        inputFile = this.$refs.file_3;
      }
      let filename = file;
      const isExcel = filename.substring(filename.lastIndexOf(".") + 1);
      if (isExcel != "xls" && isExcel != "xlsx") {
        alert("文件格式错误，请上传xls或xlsx类型文件！");
      } else {
        this.uploadFile(inputFile.$refs.input.files[0], a);
      }
    },
    //上传文件，自然学年
    uploadFile(file, a) {
      const formData = new FormData();
      //课程工作量上传
      if (a == 1) {
        formData.append("naturalYear", this.$data.yearForClassWorkloadTable);
        formData.append("file", file);
        this.$axios
          .post(`${this.$domainName}/file/upload/academic`, formData, {
            headers: {
              "Content-Type": "multipart/form-datas",
            },
          })
          .then((res) => {
            if (res.data.response.code == 200) {
              alert("报表文件上传成功！");
            } else {
              alert("上传失败！");
            }
          })
          .catch(function (error) {
            console.log(error);
          });
      }
      //监考工作量上传
      else if (a == 2) {
        formData.append(
          "naturalYear",
          this.$data.yearForExaminationWorkloadTable
        );
        formData.append("file", file);
        this.$axios
          .post(`${this.$domainName}/file/upload/examination`, formData, {
            headers: {
              "Content-Type": "multipart/form-datas",
            },
          })
          .then((res) => {
            if (res.data.response.code == 200) {
              alert("报表文件上传成功！");
            } else {
              alert("上传失败！");
            }
          })
          .catch(function (error) {
            console.log(error);
          });
      }
      //论文工作量上传
      else if (a == 3) {
        formData.append("naturalYear", this.$data.yearForPaperWorkloadTable);
        formData.append("file", file);
        this.$axios
          .post(`${this.$domainName}/file/upload/paper`, formData, {
            headers: {
              "Content-Type": "multipart/form-datas",
            },
          })
          .then((res) => {
            if (res.data.response.code == 200) {
              alert("报表文件上传成功！");
              console.log(response);
            } else {
              alert("上传失败！");
            }
          })
          .catch(function (error) {
            console.log(error);
          });
      } else {
        alert("错误！");
      }
    },
    //下载模版
    //由于模版下载的数据格式和一般的数据不太一样，就不调用全局方法了
    downloadTemplate(type) {
      import("xlsx").then((XLSX) => {
        let data;
        let fileName;
        switch (type) {
          case 1:
            data = XLSX.utils.json_to_sheet(this.classWorkloadTableTemplate);
            fileName = "课程工作量（模版）";
            break;
          case 2:
            data = XLSX.utils.json_to_sheet(
              this.examinationWorkloadTableTemplate
            );
            fileName = "监考工作量（模版）";
            break;
          case 3:
            data = XLSX.utils.json_to_sheet(this.paperWorkloadTableTemplate);
            fileName = "论文工作量（模版）";
            break;
        }
        const wb = XLSX.utils.book_new();
        XLSX.utils.book_append_sheet(wb, data, "data");
        XLSX.writeFile(wb, fileName + ".xlsx");
      });
    },
  },
};
</script>

<style scoped>
label {
  font-size: 14px;
}
.app-section {
  height: 130px;
  max-width: 700px;
}
button.button-file {
  position: absolute;
  right: 15px;
  bottom: 10px;
}
/******管理员文件上传部分的e-input控件样式******/
.el-input >>> input.el-input__inner {
  border: 0px;
  padding: 0;
}
/******管理员文件上传部分的e-input控件样式******/
.app-section .el-input input.el-input__inner {
  border: 0px;
  padding: 0;
}
</style>
