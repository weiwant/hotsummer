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
      <form>
        <tr>
        <td>类别:    </td>
        <td>
          <input type="radio" name="class"  checked="checked"> 卓越班选拔面试      
          <input type="radio" name="class"  > 校园开放日咨询  
          <input type="radio" name="class"  > 本科生分专业宣讲
          <input type="radio" name="class"  > 转专业面试
          <input type="radio" name="class"  > 本科生招生咨询
          <input type="radio" name="class"  > 转专业面试
          <input type="radio" name="class"  > 其他
        </td>
        <br>
      </tr>
       <tr>
        <td>时间:   </td>
        <td>
          <input type="date" placeholder="请选择时间">
        </td>
        <br>
      </tr>
        <tr>
          <td>姓名</td>
          <td>
            <input type="textarea" placeholder="请输入用户姓名">
          </td>
          <br>
        </tr>
        <tr>
        <td>教分</td>
        <td>
          <input type="number" min="1" max="10" step="1" value="1">
        </td>
        <br>  
      </tr>
       <tr>
        <input type="reset"  value="重置">
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