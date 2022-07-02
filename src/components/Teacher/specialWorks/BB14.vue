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
      <form>
        <tr>
          <td>内容:   </td>
          <td>
            <input ty="textarea" placeholder="请输入相关工作量描述">
          </td>
        </tr>
         <tr>
          <td>教师姓名:   </td>
          <td>
            <input ty="textarea" placeholder="请输入教师姓名">
          </td>
        </tr>
        <tr>
        <td>教分</td>
        <td>
          <input type="number" min="1" max="10" step="1" value="1">
        </td>
        <br> 
        </tr>
         
      </form>
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
    commit() {},
  },
  created() {},
};
</script>


<style scoped>
</style>