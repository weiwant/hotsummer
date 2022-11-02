<template>
  <div class="app-right-wrapper">
    <div class="app-section" v-for="(item, index) in workloadType" :key="item.apiName">
      <div class="app-section-title">
        {{ item.label }}
      </div>
      <!-- 年份选择 -->
      <label>年份:&nbsp;
        <select v-model="yearForWorkloadTable[index]">
          <option v-for="index in 5" :key="index">
            {{ currentYear - index + 1 }}
          </option>
        </select>
      </label>
      <!-- 文件上传控件 -->
      <el-input type="file" :ref="item.apiName" v-model="fileName[index]" @change="getFileData(index)" multiple="false"
        accept=".xls,.xlsx"></el-input>
    </div>
  </div>
</template>

<script>
import uploadTeachingWorkload from '@/api/teaching-workload'
import { Message } from 'element-ui'
export default {
  name: "UploadWorkload",
  data() {
    return {
      workloadType: this.$store.getters.workloadType_teaching,
      currentYear: this.$store.getters.currentYear,
      yearForWorkloadTable: [undefined, undefined, undefined],
      fileName: [undefined, undefined, undefined],
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
    };
  },
  methods: {
    //点击触发上传方法
    uploadMaterial() {
      this.workloadType.forEach(element => {
        this.$refs[item.apiName].dispatchEvent(new MouseEvent("click"));
      });
    },
    //触发选择文件，判断文件类型
    getFileData(index) {
      let apiName = this.workloadType[index].apiName;
      let inputFile = this.$refs[apiName]
      let filename = fileName[index];
      const isExcel = filename.substring(filename.lastIndexOf(".") + 1);
      if (isExcel != "xls" && isExcel != "xlsx") {
        Message({
          message: "文件格式错误，请上传xls或xlsx类型文件！",
          type: 'error',
          duration: 2000
        })
      } else {
        this.uploadFile(inputFile.$refs.input.files[0], apiName, index);
      }
    },
    //上传文件，自然学年
    uploadFile(file, apiName, index) {
      const formData = new FormData();
      formData.append("file", file);
      formData.append("naturalYear", this.$data.yearForWorkloadTable[index]);
      uploadTeachingWorkload(formData, apiType).then(() => {
        Message({
          message: "文件上传成功！",
          type: 'success',
          duration: 2000
        })
      }).catch(err => {
        Message({
          message: `文件上传失败，${err}`,
          type: 'error',
          duration: 2000
        })
      })
    },
  },
};
</script>

<style scoped>
label {
  font-size: 14px;
}

.app-section {
  margin-top: 10px;
  height: 130px;
  max-width: 700px;
}

button.file {
  position: absolute;
  right: 15px;
  bottom: 10px;
}

/******管理员文件上传部分的e-input控件样式******/
.el-input>>>input.el-input__inner {
  border: 0px;
  padding: 0;
}

/******管理员文件上传部分的e-input控件样式******/
.app-section .el-input input.el-input__inner {
  border: 0px;
  padding: 0;
}
</style>
