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
  <el-menu-item index="2">
<!-- 后端对接时在upload后面加 :action="url接口"，询问返回值确定是否成功信息 -->
    <el-upload
    :before-upload="beforeUpload"
    :on-success="uploadExcel"
    :on-error="uploadError"
    :show-file-list="false"
    accept=".xsl,.xslx"
    name="file">上传报表</el-upload>

    </el-menu-item>
</el-menu>

<el-main>
  <router-view></router-view>
</el-main>

	</div>
</template>

<script>

export default{
	data(){
		return{
    }
	},

  methods: {

    gotoCheck(){
      this.$router.push('/checktable');  //暂无正确的查看报表页面
    },

    beforeUpload(file){
      const isExcel = file.type;
      if(isExcel != ".xsl" && isExcel != ".xslx"){
        this.$message.error('只能上传.xsl或.xslx格式的表格文件！');
      }
    },

    uploadExcel(){
      alert("点是点了，成功没不知道！");
    },

    uploadError(){
      alert("上传文件失败，请重新上传！");
    }

  }
}
</script>

<style>
</style>