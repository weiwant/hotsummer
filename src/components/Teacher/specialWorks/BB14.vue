<template>
  <div class="componentSubsection category">
    <div class="categoryTitle">其他BB15</div>
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
          <td>内容</td>
          <td>
            <input type="textarea" 
            placeholder="请输入相关工作量描述"
            v-model="content">
          </td>
        </tr>

         <tr>
          <td>教师姓名</td>
          <td>
            <input type="textarea" 
            placeholder="请输入教师姓名"
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
      content:"",
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
          content:this.content,
          teacher_name:this.teacherName,
        }
        ]
      }).then(res=>{
        console.log(res.data)
      })

      }
   
  },
  created() {},
};
</script>


<style scoped>
</style>