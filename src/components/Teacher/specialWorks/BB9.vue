<template>
  <div class="componentSubsection category">
    <div class="categoryTitle">指导学生竞赛获奖BB9</div>
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
        <td>竞赛类别</td>
        <td>
          <input type="radio"  
          id="A" 
          value="A"
           v-model="competitionCategory"> 
          <label for="A">A</label>   

          <input type="radio" 
           id="B" 
           value="B"
            v-model="competitionCategory">  
          <label for="B">B</label>  

          <input type="radio"
            id="C" 
            value="C"  
            v-model="competitionCategory"> 
          <label for="C">C</label> 

        </td>
      </tr>

       <tr>
          <td>竞赛名称</td>
          <td>
            <input type="text" 
            placeholder="请输入竞赛名称"
            v-model="competitionName">
          </td>
        </tr>

          <tr>
        <td>竞赛级别</td>
        <td>
          <input type="radio" 
          id="international"
           value="国际"
            v-model="level">   
          <label for="international">国际</label>  

          <input type="radio" 
           id="national" 
           value="全国" 
           v-model="level"> 
          <label for="national">全国</label>  

          <input type="radio" 
           id="province" 
           value="省级" 
           v-model="level"> 
          <label for="province">省级</label> 

          <input type="radio"
            id="school"
             value="校级">
          <label for="school">校级</label>    
        </td>
      </tr>

       <tr>
          <td>竞赛等级</td>
          <td>
            <input type="radio" 
            id="first" 
            value="一等奖"
             v-model="category">   
          <label for="first">一等奖</label>  

          <input type="radio" 
           id="second"
            value="二等奖" 
            v-model="category"> 
          <label for="second">二等奖</label> 

          <input type="radio"
            id="third" 
            value="三等奖"
             v-model="category"> 
          <label for="third">三等奖</label>   

          <input type="radio"
            id="others"
             value="其他" 
             v-model="category">
          <label for="others">其他</label>   
          </td>
        </tr>

        <tr>
          <td>参赛作品名称</td>
          <td>
            <input type="text" 
            placeholder="请输入参赛作品名称"
            v-model="workName">
          </td>
        </tr>

        <tr>
          <td>参赛队伍名称</td>
          <td>
            <input type="text" 
            placeholder="请输入参赛队伍名称"
            v-model="teamName">
            </td>
        </tr>

        <tr>
          <td>参赛学生姓名</td>
          <td>
            <input type="text" 
            placeholder="请输入参赛学生姓名"
            v-model="studentName">
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

         <tr>
        <td>证明文件</td>
        <td>
          <input type="file"  placeholder="请选择相关文件" >
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
      competitionCategory:"",
      competitionName:"",
      level:"",
      category:"",
      workName:"",
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
            project_category:this.competitionCategory,
            project_naem:this.competitionName,
            level:this.level,
            category:this.category,
            work_name:this.workName,
            team_naem:this.teamName,
            student_name:this.studentName,
            teacher_name:this.teacherName,

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