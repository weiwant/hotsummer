<template>
  <!-- 不负责具体的文件导出任务，只负责监听点击事件，并将用户输入的file名传递至父组件，父组件进行具体的文件导出工作（文件导出的全局函数） -->
  <!-- 接收参数
  defaultFileName：默认文件名
  btnText：按钮显示文本
  disabled 是否禁用导出按钮（往往取决于父组件此时是否存在可供导出的数据）
  -->
  <!-- 事件
  @exportFile -> filename(用户输入的文件名)
  -->
  <div class="excelTool">
    <label class="customFileName"
      >文件名：
      <input type="text" v-model="exportFileName" />
    </label>
    <span class="defaultExportfileName"
      >（ 默认为：{{ defaultFileName }}）</span
    >
    <button
      class="button-file"
      @click="exportFile()"
      :disabled="disabled"
      :class="{ disabled: disabled }"
    >
       {{ btnText }}
    </button>
  </div>
</template>

<script>
export default {
  name: "DownloadExcelFile",
  data() {
    return {
      exportFileName: "",
    };
  },
  props: ["defaultFileName", "disabled", "btnText"],
  methods: {
    exportFile() {
      let filename;
      if (this.exportFileName == "") {
        filename = this.defaultFileName;
      } else {
        filename = this.exportFileName;
      }
      this.$emit("exportFile", filename);
    },
  },
};
</script>

<style scoped>
div.excelTool {
  width: 600px;
}
label.customFileName {
  font-size: 14px;
}
label.customFileName input {
  padding-left: 5px;
  height: 25px;
  border: 1px solid gray;
  border-radius: 5px;
}
span.defaultExportfileName {
  font-size: 10px;
  color: gray;
}
</style>
