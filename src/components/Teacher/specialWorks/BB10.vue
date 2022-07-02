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
      <form>
        <tr>
        <td>项目级别:    </td>
        <td>
          <input type="radio" name="class" id="jibie" checked="checked"> 国家级        
          <input type="radio" name="class" id="jibie" > 省级  
          <input type="radio" name="class" id="jibie" > 校级
          <input type="radio" name="class" id="jibie" > 院级  
        </td>
        <br>
      </tr>
      <tr>
          <td>项目荣誉:   </td>
          <td>
            <input type="text" placeholder="请输入项目荣誉">
          </td>
          <br>
        </tr>
        <tr>
          <td>项目名称:   </td>
          <td>
            <input type="text" placeholder="请输入项目名称">
          </td>
          <br>
        </tr>
        <tr>
          <td>学生团队名称:   </td>
          <td>
            <input type="text" placeholder="请输入学生团队名称">
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
          <td>学生学号:   </td>
          <td>
            <input type="text" placeholder="请输入学生学号">
          </td>
          <br>
        </tr>
         <tr>
          <td>指导老师姓名:   </td>
          <td>
            <input type="text" placeholder="请输入指导老师姓名">
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