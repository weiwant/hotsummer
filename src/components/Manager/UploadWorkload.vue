<template>
  <div class="componentWrapper">
    <div class="componentSectionTitle">上传教学工作量</div>
    <!-- part1 -->
    <div class="componentSubtitle" v-if="managerType == 1">课程工作量</div>
    <div class="componentSubsection" v-if="managerType == 1">
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
        ref="file"
        name="file"
        v-model="file"
        @change="getFileData"
        multiple="false"
        accept=".xls,.xlsx"
      ></el-input>
      <!-- 模版下载 -->
      <button class="workloadFileProcessingBtn" @click="downloadTemplate(1)">
         模版下载
      </button>
    </div>
    <!-- part2 -->
    <div class="componentSubtitle" v-if="managerType != 3">监考工作量</div>
    <div class="componentSubsection" v-if="managerType != 3">
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
        ref="file"
        name="file"
        v-model="file"
        @change="getFileData"
        multiple="false"
        accept=".xls,.xlsx"
      ></el-input>
      <!-- 模版下载 -->
      <button class="workloadFileProcessingBtn" @click="downloadTemplate(2)">
         模版下载
      </button>
    </div>
    <!-- part3 -->
    <div class="componentSubtitle" v-if="managerType != 2">**工作量</div>
    <div class="componentSubsection" v-if="managerType != 2">
      <!-- 年份选择 -->
      <label
        >年份:&nbsp;
        <select v-model="yearForXXWorkloadTable">
          <option v-for="index in 5" :key="index">
            {{ currentYear - index + 1 }}
          </option>
        </select>
      </label>
      <!-- 文件上传控件 -->
      <el-input
        type="file"
        ref="file"
        name="file"
        v-model="file"
        @change="getFileData"
        multiple="false"
        accept=".xls,.xlsx"
      ></el-input>
      <!-- 模版下载 -->
      <button class="workloadFileProcessingBtn" @click="downloadTemplate(3)">
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
      managerType: 1, //管理员身份
      currentYear: this.$currentYear,
      yearForClassWorkloadTable: this.$currentYear,
      yearForExaminationWorkloadTable: this.$currentYear,
      yearForXXWorkloadTable: this.$currentYear,
      classWorkloadTableTemplate: [{ 年份: "", 上课老师: "" }],
      examinationWorkloadTableTemplate: [],
      xxWorkloadTableTemplate: [],
    };
  },
  methods: {
    //点击触发上传方法
    uploadMaterial() {
      this.$refs.file.dispatchEvent(new MouseEvent("click"));
    },
    //触发选择文件，判断文件类型
    getFileData(file) {
      const inputFile = this.$refs.file;
      let filename = file;
      const isExcel = filename.substring(filename.lastIndexOf(".") + 1);
      this.uploadFile(inputFile.$refs.input.files[0]);
    },
    //上传文件，学年，学期
    uploadFile(file) {
      const formData = new FormData();
      var _this = this;
      var firstYear = this.$data.uploadFileYearInfo1;
      var secondYear = firstYear + 1;
      _this.year = firstYear + "-" + secondYear;
      _this.semester = this.$data.uploadFileSemesterInfo;
      formData.append("year", _this.year);
      formData.append("semester", _this.semester);
      formData.append("file", file);
      axios
        .post(`${this.$domainName}/file/upload`, formData, {
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
    },
    //下载模版
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
            data = XLSX.utils.json_to_sheet(this.xxWorkloadTableTemplate);
            fileName = "XX工作量（模版）";
            break;
        }
        const wb = XLSX.utils.book_new();
        XLSX.utils.book_append_sheet(wb, data, "data");
        XLSX.writeFile(wb, fileName + ".xlsx");
      });
    },
  },
  created() {},
};
</script>

<style scoped>
label {
  font-size: 14px;
}
.componentSubsection {
  height: 130px;
}
button.workloadFileProcessingBtn {
  position: absolute;
  right: 15px;
  bottom: 10px;
}
/******管理员文件上传部分的e-input控件样式******/
.el-input >>> input.el-input__inner {
  border: 0px;
  padding: 0;
}
</style>
