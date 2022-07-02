<template>
  <div class="componentSubsection category">
    <div class="categoryTitle">教学竞赛奖励BB4</div>
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
        <td>级别:    </td>
        <td>
          <input type="radio" name="class" id="jibie" checked="checked"> 国家级        
          <input type="radio" name="class" id="jibie" > 省级  
          <input type="radio" name="class" id="jibie" > 校级
        </td>
        <br>
      </tr>
       <tr>
        <td>竞赛名称:   </td>
        <td>
          <input type="text" placeholder="请输入比赛的名称">
        </td>
        <br>
      </tr>
      <tr>
        <td>奖项类别:   </td>
        <td>
          <input type="text" placeholder="请输入所获奖项目的类别">
        </td>
        <br>
      </tr>
      <tr>
        <td>获奖等级:   </td>
        <td>
          <input type="text" placeholder="请输入获奖等级">
        </td>
        <br>
      </tr>
      <tr>
        <td>授奖单位:   </td>
        <td>
          <input type="text" placeholder="请输入授奖单位">
        </td>
        <br>
      </tr>
       <tr>
        <td>获奖时间:   </td>
        <td>
          <input type="date" placeholder="请选择获奖时间">
        </td>
        <br>
      </tr>
      <tr>
        <td>证明文件：   </td>
        <td>
          <input type="file">
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