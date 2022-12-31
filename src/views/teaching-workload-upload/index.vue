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
        <!-- input在未设置multiple时，默认只能放一个文件 -->
        <div class="choose-section">
          <label :for="item.apiName">
            <div class="mask" v-if="showLoading[index]">
              <Loading />
            </div>
            <span class="icon"></span>
            <span class="text">点击选择要上传的文件</span>
            <div class="notice">(仅.xlsx/.xls文件，一次最多选择1个)</div>
          </label>
          <input type="file" accept=".xlsx,.xls" :id="item.apiName" @change="chooseFile(index, item.apiName)">
          <!-- 已上传文件 -->
          <div class="file-list">
            <div class="title">已选择文件：</div>
            <div class="fileName" v-if="fileName[index]">{{ fileName[index] }}</div>
            <div class="nofile" v-else><span class="icon"></span>暂未选择文件</div>
          </div>
        </div>
        <div class="button">
          <button class="withBorder white" @click="upload(index, item.apiName)">确认上传</button>
        </div>
      </div>
      <!-- 模版下载控件 -->
      <div class="download">
        <button class="withBorder green" @click="downloadTemp(item)">
          下载模版
        </button>
      </div>
    </div>
  </div>
</template>

<script>
import { uploadTeachingWorkload } from '@/api/teaching-workload'
import { Message } from 'element-ui'
import { downloadTemplate } from '@/utils/excel';
import Loading from '@/components/Loading.vue';
export default {
  name: "UploadWorkload",
  components: {
    Loading
  },
  data() {
    return {
      workloadType: this.$store.getters.workloadType_teaching,
      currentYear: this.$store.getters.currentYear,
      yearForWorkloadTable: [],
      fileName: [],
      showLoading: []
    }
  },
  methods: {
    chooseFile(index, apiName) {
      let files = document.getElementById(apiName).files;
      this.fileName.splice(index, 1, files[0].name)
    },
    upload(index, apiName) {
      //如果没选择年份
      let naturalYear = this.yearForWorkloadTable[index];
      if (!naturalYear) {
        Message({
          message: '请选择年份！',
          type: 'warning',
          duration: 2000
        })
        return;
      }
      let files = document.getElementById(apiName).files
      if (files.length == 0) {
        Message({
          message: '请选择文件！',
          type: 'warning',
          duration: 2000
        })
        return;
      }
      this.showLoading.splice(index, 1, true);
      let formData = new FormData();
      formData.append('naturalYear', naturalYear);
      formData.append('file', files[0]);
      uploadTeachingWorkload(formData, apiName).then(() => {
        Message({
          message: '上传成功！',
          type: 'success',
          duration: 2000
        });
        this.showLoading.splice(index, 1, false);
      }).catch(() => {
        Message({
          message: '上传失败！',
          type: 'error',
          duration: 2000
        });
        this.showLoading.splice(index, 1, false);
      })
    },
    downloadTemp(type) {
      let columns = this.$store.state.teaching_workload[`${type.apiName}WorkloadTableTemplate`];
      let fileName = `${type.label}模版.xlsx`;
      downloadTemplate(columns, fileName)
    }
  },
  created() {
    for (let i = 0; i < this.workloadType.length; i++) {
      this.yearForWorkloadTable.push(null);
      this.fileName.push(null);
      this.showLoading.push(false);
    }
  }
};
</script>

<style scoped lang="scss">
@import "@/style/variables.scss";

.icon {
  font-family: 'icomoon';
}

.app-section {
  position: relative;
  margin-top: 10px;
  max-width: 700px;
}

.upload {
  margin-top: 20px;

  .button {
    margin-top: 20px;
  }

  label {
    position: relative;
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    gap: 10px;
    width: 50%;
    height: 200px;
    border-radius: 5px;
    border: 1px dashed #aaa;
    background-color: #f4f4f5;
    color: #43566f;
    cursor: pointer;

    &:hover {
      border: 1px dashed $subThemeColor;
      color: #2f3c4d;

    }

    .mask {
      position: absolute;
      display: flex;
      align-items: center;
      justify-content: center;
      width: 100%;
      height: 200px;
      background-color: rgba(255, 255, 255, 0.419);
      z-index: 1000;
    }

    .icon {
      font-size: 40px;
    }

    .text {
      font-size: 14px;
    }

    .notice {
      font-size: 12px;
      color: #aaa;
    }
  }

  input[type="file"] {
    display: none;
  }

  .file-list {
    margin-top: 10px;
    font-size: 14px;
    font-weight: 500;

    .title {
      margin-bottom: 5px;
    }

    .fileName {
      color: #666;
    }

    .nofile {
      color: $yellow;
      font-weight: 400;

      .icon {
        vertical-align: bottom;
        margin-right: 5px;
      }
    }
  }


}

.download {
  position: absolute;
  bottom: 15px;
  right: 20px;
}
</style>
