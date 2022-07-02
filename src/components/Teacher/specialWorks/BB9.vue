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
      <form>
         <tr>
        <td>竞赛类别:    </td>
        <td>
          <input type="radio" name="class" id="jibie" checked="checked"> A       
          <input type="radio" name="class" id="jibie" > B  
          <input type="radio" name="class" id="jibie" > C
        </td>
        <br>
      </tr>
       <tr>
          <td>竞赛名称:   </td>
          <td>
            <input type="text" placeholder="请输入竞赛名称">
          </td>
          <br>
        </tr>
          <tr>
        <td>竞赛级别:    </td>
        <td>
          <input type="radio" name="class" id="jibie" checked="checked"> 国际      
          <input type="radio" name="class" id="jibie" > 全国  
          <input type="radio" name="class" id="jibie" > 省级
          <input type="radio" name="class" id="jibie" > 校级
        </td>
        <br>
      </tr>
       <tr>
          <td>竞赛等级:   </td>
          <td>
            <input type="text" placeholder="请输入竞赛等级">
          </td>
          <br>
        </tr>
        <tr>
          <td>参赛作品名称:   </td>
          <td>
            <input type="text" placeholder="请输入参赛作品名称">
          </td>
          <br>
        </tr>
        <tr>
          <td>参赛队伍名称:   </td>
          <td>
            <input type="text" placeholder="请输入参赛队伍名称">
          </td>
          <br>
        </tr>
        <tr>
          <td>参赛学生姓名:   </td>
          <td>
            <input type="text" placeholder="请输入参赛学生姓名">
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
        <td>证明文件：   </td>
        <td>
          <input type="file"  placeholder="请选择相关文件" >
        
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