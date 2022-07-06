<template>
  <div class="componentSubsection category">
    <div class="categoryTitle">教研项目BB2</div>
    <!-- 历史上报记录 -->
    <div class="history">
      <div class="historyTitle">
        历史
        <span class="historyDisplayBtn" @click="showHistory">{{
          historyDisplayBtnText
        }}</span>
      </div>
      <transition name="history">
        <div class="historyTableWrapper" v-if="historyShown"></div>
      </transition>
    </div>
    <!-- 填报与添加区域 -->
    <div class="addNew">

        <tr>
        <td>级别</td>
        <td>
          <input type="radio" 
           id="national"  
           value="国家级" v-model="level">  
           <label for="national">国家级</label>

          <input type="radio" 
           id="provinciallevel" 
           value="省级 "  
           v-model="level"> 
          <label for="provinciallevel">省级</label>

          <input type="radio" 
           id="schoollevel"
            value=" 校级 " v-model="level">
          <label for="schoollevel">校级</label>
        </td>
      </tr>

      <tr>
        <td>项目名称</td>
        <td>
           <input type="text" 
           placeholder="请输入所建设项目的名称"
           v-model="projectName">
        </td>
      </tr>
      <tr>
        <td>项目类别</td>
        <td>
           <select v-model="projectCategory">
            <option value="课程思政示范专业建设项目" selected="selected">
              课程思政示范专业建设项目</option>
            <option value="自由选题建设项目">自由选题建设项目</option>
            <option >社会实践课程建设项目</option>
            <option value="教师教学发展研究项目">教师教学发展研究项目</option>
            <option value="MOOC课程建设项目">MOOC课程建设项目</option>
            <option value="课程思政课程建设项目">课程思政课程建设项目</option>
          </select>
        </td>
      </tr>
      <tr>
        <td>负责人姓名</td>
        <td>
           <input type="text"
            placeholder="请输入项目负责人姓名"
            v-model="teacherName">
        </td>
      </tr>

      <tr>
        <td>进展情况</td>
        <td>
          <input
            type="radio"
            id="established"
            value="立项"
            v-model="projectStatus"
          />
          <label for="established">立项</label>

          <input type="radio"
           id="done" 
           value="结题"
            v-model="projectStatus" />
          <label for="done">结题</label>

          <input
            type="radio"
            id="doing"
            value="建设中"
            v-model="projectStatus"
          />
          <label for="doing">建设中</label>
        </td>
      </tr>

      <button class="universalBlueBtn complete" @click="commit">
        提&nbsp;交
      </button>
    </div>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  
  data() {
    return {
      historyDisplayBtnText: "展开 ",
      historyShown: false,
      //填报数据
      level:"",
      projectName:"",
      projectCategory:"",
      teacherName:"",
      projectStatus:"",
      
    };
  },
  methods: {
    showHistory() {
      if (!this.historyShown) {
        this.historyDisplayBtnText = "收起 ";
        this.historyShown = true;
      } else {
        this.historyDisplayBtnText = "展开 ";
        this.historyShown = false;
      }
    },
   
    /*提交上报数据*/
    commit() {
      var _this = this;
      const formData = new FormData();

      var data = JSON.stringify([{
        awardLevel: this.$data.awardLevel,
        projectStatus: this.$data.projectStatus,
        projectCategory: this.$data.projectCategory,
        projectName: this.$data.projectName
      }]);

      formData.append("data", data);

      console.log(formData.get("data"));
      console.log(this.$domainName)

      //以下需要修改接口
      this.$axios
        .post(`${this.$domainName}/special-workload/upload`, formData, {
          
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

  },
  created() {},
};
</script>


<style scoped>
</style>