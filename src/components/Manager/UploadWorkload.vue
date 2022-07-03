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
        @change="getFileData(file, 1)"
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
        @change="getFileData(file, 2)"
        multiple="false"
        accept=".xls,.xlsx"
      ></el-input>
      <!-- 模版下载 -->
      <button class="workloadFileProcessingBtn" @click="downloadTemplate(2)">
         模版下载
      </button>
    </div>
    <!-- part3 -->
    <div class="componentSubtitle" v-if="managerType != 2">论文工作量</div>
    <div class="componentSubsection" v-if="managerType != 2">
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
        ref="file"
        name="file"
        v-model="file"
        @change="getFileData(file, 3)"
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
      yearForPaperWorkloadTable: this.$currentYear,
      classWorkloadTableTemplate: [{ 年份: "", 上课老师: "" }],
      examinationWorkloadTableTemplate: [],
      paperWorkloadTableTemplate: [],
    };
  },
  methods: {
    //点击触发上传方法
    uploadMaterial() {
      this.$refs.file.dispatchEvent(new MouseEvent("click"));
    },
    //触发选择文件，判断文件类型
    getFileData(file, a) {
      const inputFile = this.$refs.file;
      let filename = file;
      const isExcel = filename.substring(filename.lastIndexOf(".") + 1);
      if(isExcel != "xls" && isExcel != "xlsx"){
        alert("文件格式错误，请上传xls或xlsx类型文件！");
      }else{
        this.uploadFile(inputFile.$refs.input.files[0], a);
      }
    },
    //上传文件，自然学年
    uploadFile(file, a) {
      const formData = new FormData();
      var _this = this;
      //课程工作量上传
      if(a == 1){
        formData.append("year", this.$data.yearForClassWorkloadTable);
        formData.append("file", file);
        this.$axios
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
      }
      //监考工作量上传
      else if(a == 2){
        formData.append("year", this.$data.yearForExaminationWorkloadTable);
        formData.append("file", file);
        this.$axios
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
      }
      //论文工作量上传
      else if(a == 3){
        formData.append("year", this.$data.yearForPaperWorkloadTable);
        formData.append("file", file);
        this.$axios
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
      }else{
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
            data = XLSX.utils.json_to_sheet(this.xxWorkloadTableTemplate);
            fileName = "论文工作量（模版）";
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
