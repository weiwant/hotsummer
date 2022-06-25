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
        this.uploadFile(inputFile);
      }
    },

    //上传文件，学年，学期
    uploadFile(file){
      const formData = new FormData();
      var _this = this;
      _this.year = this.$data.year;
      _this.semester = this.$data.semester;
      formData.append("year", _this.year);
      formData.append("semester", _this.semester);
      formData.append("excelFile", file);
      // console.log(formData.get("year"));
      // console.log(formData.get("semester"));
      // console.log(formData.get("excelFile"));

      //输入后端url
      axios.post('', {
        formData
      })
      .then(function(response){
        if(response.data.data){
          alert("报表文件上传成功！");
        }else{
          alert("上传失败！");
          console.log(response);
        }
      })
      .catch(function(error){
        console.log(error);
      })
    }
  }
}
</script>

<style>
</style>