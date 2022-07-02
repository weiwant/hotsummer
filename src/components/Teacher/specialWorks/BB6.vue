<template>
  <div class="componentSubsection category">
    <div class="categoryTitle">出版教材BB6</div>
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
          <td>书名:   </td>
          <td>
            <input type="textarea" placeholder="请输入出版书籍的书名">
          </td>
          <br>
        </tr>
        <tr>
          <td>ISBN:   </td>
          <td>
            <input type="text" placeholder="请输入教材的国际标准书号">
          </td>
          <br>
        </tr>
        <tr>
          <td>内容简介:   </td>
          <td>
            <input type="textarea"  placeholder="请在此输入教材的内容简介">
          </td>
          <br>
        </tr>
        <tr>
          <td>出版日期：   </td>
          <td>
            <input type=”date”>
          </td>
          <br>
        </tr>
        <tr>
          <td>教材版次：   </td>
          <td>
            <select>
              <option value="edition" selected="selected" >第一版</option>
              <option value="edition" selected="selected" >第二版</option>
              <option value="edition" selected="selected" >第三版</option>
              <option value="edition" selected="selected" >第四版</option>
              <option value="edition" selected="selected" >第五版</option>
              <option value="edition" selected="selected" >第六版</option>
              <option value="edition" selected="selected" >第七版</option>
              <option value="edition" selected="selected" >第八版</option>
              <option value="edition" selected="selected" >第九版</option>
              <option value="edition" selected="selected" >第十版</option>
            </select>
            <select>
              <option value="edition" selected="selected" >第一次印刷</option>
              <option value="edition" selected="selected" >第二次印刷</option>
              <option value="edition" selected="selected" >第三次印刷</option>
              <option value="edition" selected="selected" >第四次印刷</option>
              <option value="edition" selected="selected" >第五次印刷</option>
              <option value="edition" selected="selected" >第六次印刷</option>
              <option value="edition" selected="selected" >第七次印刷</option>
              <option value="edition" selected="selected" >第八次印刷</option>
              <option value="edition" selected="selected" >第九次印刷</option>
              <option value="edition" selected="selected" >第十次印刷</option>
            </select>
            <br>
          </td>
        </tr>
        <tr>
          <td>所获荣誉</td>
          <td>
            <input type="textarea"  placeholder="请在此填入你所获的荣誉">
          </td>
          <br>
        </tr>
        <tr>
        <td>证明文件：   </td>
        <td>
          <input type="file"  placeholder="请选择对应封面图片" >
          <input type="file"  placeholder="请选择对应封底图片" >
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