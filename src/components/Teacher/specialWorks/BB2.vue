<template>
  <div class="componentSubsection category">
    <div class="categoryTitle">教研项目BB2</div>
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
        <td>项目名称:   </td>
        <td>
           <input type="text" placeholder="请输入所建设项目的名称">
        </td>
        <br>
      </tr>
      <tr>
        <td>项目类别:   </td>
        <td>
           <select>
            <option value="leibie" selected="selected">课程思政示范专业建设项目</option>
            <option value="leibie">自由选题建设项目</option>
            <option value="leibie">社会实践课程建设项目</option>
            <option value="leibie">教师教学发展研究项目</option>
            <option value="leibie">MOOC课程建设项目</option>
            <option value="leibie">课程思政课程建设项目</option>
          </select>
        </td>
        <br>
      </tr>
      <tr>
        <td>负责人姓名:   </td>
        <td>
           <input type="text" placeholder="请输入项目负责人姓名">
        </td>
        <br>
      </tr>
      <tr>
        <td>进展情况:   </td>
        <td>
          <input type="radio" name="priod" id="jibie" checked="checked">立项         
          <input type="radio" name="priod" id="jibie" >结题  
          <input type="radio" name="priod" id="jibie" >建设中
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