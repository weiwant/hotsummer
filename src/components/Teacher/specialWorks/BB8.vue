<template>
  <div class="componentSubsection category">
    <div class="categoryTitle">指导优秀毕业论文BB8</div>
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
          <td>指导类型:   </td>
          <td>
          <input type="radio" name="class" id="level" checked="checked"> 独立指导        
          <input type="radio" name="class" id="level" > 与院外合作指导  
         
          </td>
          <br>
        </tr>
        <tr>
          <td>论文名称:   </td>学生姓名
          <td>
            <input type="text" placeholder="请输入论文名称">
          </td>
          <br>
        </tr>
        <tr>
          <td>学生姓名:   </td>
          <td>
            <input type="text" placeholder="请输入学生姓名">
          </td>
          <br>
        </tr>
        <tr>
          <td>学生姓名:   </td>
          <td>
            <input type="text" placeholder="请输入学生姓名">
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