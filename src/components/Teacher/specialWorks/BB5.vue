<template>
  <div class="componentSubsection category">
    <div class="categoryTitle">发表教学研究论文BB5</div>
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
        <td>级别:    </td>
        <td>
          <input type="radio"  id="authority" value="权威" v-model="level"> 
          <label for="authority" >权威</label>    
          <input type="radio"  id="core" value="核心" v-model="level"> 
          <label for="core" > 核心</label>
          <input type="radio"  id="noncore" value="非核心" v-model="level"> 
          <label for="noncore" >非核心</label>
        </td>
      </tr>
       <tr>
        <td>文章名称:   </td>
        <td>
          <input type="text" 
          placeholder="请输入文章名称"
          v-model="articlename"
          >
        </td>
      </tr>
       <tr>
        <td>刊物名称:   </td>
        <td>
          <input type="text" 
          placeholder="请输入刊物名称"
          v-model="publicationname">
        </td>
      </tr>
       <tr>
        <td>刊物期数:   </td>
        <td>
          <input type="month" placeholder="请选择出版月份" v-model="month">
          <input type="number" 
           min="1" 
            max="100"  
             step="1" 
             value="1" 
             v-model="stage">期
        </td>
      </tr>
       <tr>
        <td>证明文件：   </td>
        <td>
         
          <input type="file" value="论文封面"  >
          <input type="file"  value="论文目录" >
          <input type="file"  value="论文正文" >
        </td>
      </tr>


    <DynamicCollection @update="changeParticipant"></DynamicCollection>
      <button class="universalBlueBtn complete" @click="commit">
        提&nbsp;交
      </button>
    </div>
  </div>
</template>

<script>
import DynamicCollection from "./DynamicCollection.vue";
export default {
   components: { DynamicCollection },
  data() {
    return {
      historyDisplayBtnText: "展开 ",
      historyShown: false,
      level:"",
      articlename:"",
      publicationname:"",
      month:"",
      stage:"",
      participants: [],

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
    changeParticipant(participants) {
      this.participants = participants;
      console.log(this.participants);
    },
    /*提交上报数据*/
    commit() {},
  },
  created() {},
};
</script>


<style scoped>
</style>