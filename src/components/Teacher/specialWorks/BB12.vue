<template>
  <div class="componentSubsection category">
    <div class="categoryTitle">本科生培养活动BB13</div>
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
        <td>类别</td>
        <td>
          <input type="radio" 
          id="excellentclass" 
          value="卓越班选拔面试"
           v-model="category" >
          <label for="excellentclass">卓越班选拔面试</label>

          <input type="radio"
           id="campusopenday" 
           value="校园开放日咨询 " 
           v-model="category" >
          <label for="campusopenday">校园开放日咨询</label>

          <input type="radio" 
           id="submajor"
            value="本科生分专业宣讲 " 
            v-model="category" >
          <label for="submajor">本科生分专业宣讲</label>

          <input type="radio" 
           id="transfermajor" 
           value="转专业面试"
            v-model="category" >
           <label for="transfermajorr"> 转专业面试 </label>

          <input type="radio"  
          id="undergraduateenrollmentconsultation" 
          value="本科生招生咨询" 
          v-model="category" >
           <label for="undergraduateenrollmentconsultation">本科生招生咨询  </label>

          <input type="radio" 
           id="variousreviews" 
           value="各类评审" 
           v-model="category" > 
           <label for="variousreviewsr">各类评审 </label>

          <input type="radio" 
           id="others" 
           value="其他" 
           v-model="category" > 
           <label for="others">其他</label>
        </td>
      </tr>

       <tr>
        <td>时间</td>
        <td>
          <input type="date" 
          placeholder="请选择时间"
          v-model="time">
        </td>
      </tr>

        <tr>
          <td>姓名</td>
          <td>
            <input type="textarea" 
            placeholder="请输入用户姓名"
            v-model="name">
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
      category:"",
      time:"",
      name:"",
     

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
      this.$axios.post('',{
        data:[
          {
            category:this.category,
            time:this.time,
            name:this.name,

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