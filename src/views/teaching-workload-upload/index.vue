<template>
  <div class="app-view-container">
    <div class="app-section" v-for="(item, index) in workloadType" :key="item.apiName">
      <div class="app-section-title">
        {{ item.label }}
      </div>
      <!-- 年份选择 -->
      <el-date-picker v-model="yearForWorkloadTable[index]" type="year" placeholder="选择自然年份" value-format="yyyy"
        :editable="false">
      </el-date-picker>
      <!-- 文件上传控件 -->
      <div class="upload">
        <el-upload class="upload-demo" drag action="https://jsonplaceholder.typicode.com/posts/" multiple>
          <i class="el-icon-upload"></i>
          <div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
          <div class="el-upload__tip" slot="tip">只能上传jpg/png文件，且不超过500kb</div>
        </el-upload>
      </div>
      <!-- <el-input type="file" :ref="item.apiName" v-model="fileName[index]" @change="getFileData(index)" multiple="false"
        accept=".xls,.xlsx"></el-input> -->
    </div>
  </div>
</template>

<script>
import { uploadTeachingWorkload } from '@/api/teaching-workload'
import { Message } from 'element-ui'
export default {
  name: "UploadWorkload",
  data() {
    return {
      workloadType: this.$store.getters.workloadType_teaching,
      currentYear: this.$store.getters.currentYear,
      yearForWorkloadTable: [undefined, undefined, undefined],
      fileName: [undefined, undefined, undefined],
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
.app-section {
  margin-top: 10px;
  max-width: 700px;
}

.upload {
  margin-top: 20px;
}
</style>
