<template>
	<div>
		<el-menu
  :default-active= 1
  class="el-menu-demo"
  mode="horizontal"
  background-color="#00a183"
  text-color="#fff"
  active-text-color="#ffd04b">
  <el-menu-item index="1" 
  @click="gotoCheck">查看报表</el-menu-item>
</el-menu>

<!-- 后端对接时在upload后面加 :action="url接口"，询问返回值确定是否成功信息 -->
<el-input
type="file"
ref="file"
name="file"
v-model="file"
@click="e => {e.target.value = '';}"
@change="getFileData"
multiple="false"
accept=".xls,.xlsx">上传报表</el-input>
<el-input v-model="year">学年</el-input>
<el-input v-model="semester">学期</el-input>

<el-main>
  <router-view></router-view>
</el-main>

	</div>
</template>

<script>
import axios from 'axios';

export default{
	data(){
		return{
      file: '',
      year: '',
      semester: ''
    }
	},

  methods: {

    gotoCheck(){
      this.$router.push('/checktable');  //暂无正确的查看报表页面
    },

    uploadMaterial(){
      this.$refs.file.dispatchEvent(new MouseEvent('click'));
    },

    //触发选择文件，判断文件类型
    getFileData(file){
      const inputFile = this.$refs.file;
      let filename = this.$data.file;
      const isExcel = filename.substring(filename.lastIndexOf('.')+ 1);
      if(isExcel != "xls" && isExcel != "xlsx"){
        alert("文件格式不对，请选择.xls或.xlsx文件！");
      }else{
        console.log(inputFile);
        this.uploadFile(inputFile);
      }
    },

    //上传文件，学年，学期
    uploadFile(file){
      var formData = new FormData();
      console.log(file);
      formData.append("excelFile",file);
      formData.append("year", this.$data.year);
      formData.append("semester", this.$data.semester);
      console.log(formData.get("file","year","semester"));
    }
  }
}
</script>

<style>
</style>