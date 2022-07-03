<template>
  <div class="componentSubsection category">
    <div class="categoryTitle">指导学生科研立项并顺利完成项目BB10</div>
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
        <td>项目级别</td>
        <td>
          <input type="radio"  id="national" value="国家级" v-model="level"> 
         <label for="national">国家级</label>
          <input type="radio"  id="province" value="省级" v-model="level"> 
          <label for="province">省级</label>    
          <input type="radio"  id="school" value="校级" v-model="level">
          <label for="school">校级</label>    
           <input type="radio" id="college" value="院级" v-model="level">   
          <label for="college">院级</label>    
        </td>
      </tr>

      <tr>
          <td>项目荣誉</td>
          <td>
            <input type="text"
             placeholder="请输入项目荣誉"
             v-model="honor">
             </td>
        </tr>

        <tr>
          <td>项目名称</td>
          <td>
            <input type="text" 
            placeholder="请输入项目名称"
            v-model="projectName">
          </td>
        </tr>

        <tr>
          <td>学生团队名称</td>
          <td>
            <input type="text"
             placeholder="请输入学生团队名称"
             v-model="teamName">
          </td>
        </tr>

         <tr>
          <td>学生姓名</td>
          <td>
            <input type="text" 
            placeholder="请输入学生姓名"
            v-model="studentName">
          </td>
        </tr>

        <tr>
          <td>学生学号</td>
          <td>
            <input type="text" 
            placeholder="请输入学生学号"
            v-model="number">
            
          </td>
        </tr>
         <tr>
          <td>指导老师姓名</td>
          <td>
            <input type="text" 
            placeholder="请输入指导老师姓名"
            v-model="teacherName">
            </td>
        </tr>
        
      <button class="universalBlueBtn complete" @click="commit">
        提&nbsp;交
      </button>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      historyDisplayBtnText: "展开 ",
      historyShown: false,
      level:"",
      honor:"",
      projectName:"",
      teamName:"",
      studentName:"",
      teacherName:"",
      
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
      this.$axios.post('url:',{
        data:[
          {
            level:this.level,
            honor:this.honor,
            project_name:this.projectName,
            team_name:this.teamName,
            studnet_name:this.studentName,
            teacher_naem:this.teacherName,

          }
        ]
      }).then(res=>{
        console.log(res.data)
      })
    },
  },
  created() {},
};
</script>


<style scoped>
</style>