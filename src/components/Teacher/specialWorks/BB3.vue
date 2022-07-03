<template>
  <div class="componentSubsection category">
    <div class="categoryTitle">教学成果奖BB3</div>
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
>
        <tr>
        <td>级别:    </td>
        <td>
          <input type="radio" id="nation" value="国家级" v-model="awardLevel" />
          <label for="nation">国家级</label>
          <input type="radio" id="province" value="省级" v-model="awardLevel" />
          <label for="province">省级</label>
          <input type="radio" id="school" value="校级" v-model="awardLevel" />
          <label for="school">校级</label>
        </td>
        
      </tr>
       <tr>
        <td>奖项名称:   </td>
        <td>
          <input type="text" placeholder="请输入所获奖项目的名称" v-model="awardname">
        </td>
        
      </tr>
      <tr>
        <td>奖项类别:   </td>
        <td>
          <input type="text" placeholder="请输入所获奖项目的类别" v-model="awardCategory">
        </td>
       
      </tr>
       <tr>
        <td>获奖等级:   </td>
        <td>
          <input type="text" placeholder="请输入获奖等级" v-model="level">
        </td>
      
      </tr>
       <tr>
        <td>授奖单位:   </td>
        <td>
          <input type="text" placeholder="请输入授奖单位" v-model="Awardingunit"> 
        </td>
   
      </tr>
      <tr>
        <td>获奖时间:   </td>
        <td>
          <input type="date" placeholder="请输入获奖时间" v-model="time">
        </td>
       
      </tr>
       <tr>
        <td>证明文件：   </td>
        <td>
          <input type="file">
        </td>
      </tr>
   
      <!-- 动态增删填报项组件 -->
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
      awardLevel:"",
      awardname:"",
      level:"",
      Awardingunit:"",
      time:"",
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
    // 动态增删participants
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