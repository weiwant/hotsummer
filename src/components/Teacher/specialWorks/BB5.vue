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
      <form>
        <tr>
        <td>级别:    </td>
        <td>
          <input type="radio" name="level" id="jibie" checked="checked"> 权威       
          <input type="radio" name="level" id="jibie" > 核心 
          <input type="radio" name="level" id="jibie" > 非核心
        </td>
        <br>
      </tr>
       <tr>
        <td>文章名称:   </td>
        <td>
          <input type="text" placeholder="请输入文章名称">
        </td>
        <br>
      </tr>
       <tr>
        <td>刊物名称:   </td>
        <td>
          <input type="text" placeholder="请输入刊物名称">
        </td>
        <br>
      </tr>
       <tr>
        <td>刊物期数:   </td>
        <td>
          <input type=”month”>
          <input type="number"  min="0"  max="100"   step="1" value="0">期
        </td>
        <br>
      </tr>
       <tr>
        <td>证明文件：   </td>
        <td>
          <input type="file"  placeholder="请选择论文对应封面" >
          <input type="file"  placeholder="请选择论文对应目录" >
          <input type="file"  placeholder="请选择论文对应正文" >
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