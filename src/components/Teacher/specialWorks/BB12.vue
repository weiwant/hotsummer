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
      <tr>
        <td>类别</td>
        <td>
          <input
            type="radio"
            id="excellentclass"
            value="卓越班选拔面试 "
            v-model="category"
          />
          <label for="excellentclass">卓越班选拔面试 </label>

          <input
            type="radio"
            id="Campus open day"
            value="校园开放日咨询 "
            v-model="category"
          />
          <label for="Campus open day">校园开放日咨询</label>

          <input
            type="radio"
            id="Sub major"
            value="
            本科生分专业宣讲 "
            v-model="category"
          />
          <label for="Sub major">本科生分专业宣讲 </label>

          <input
            type="radio"
            id="Transfer major"
            value=" 转专业面试 "
            v-model="category"
          />
          <label for="Transfer majorr"> 转专业面试 </label>

          <input
            type="radio"
            id="Undergraduate enrollment consultation"
            value=" 本科生招生咨询 "
            v-model="category"
          />
          <label for="Undergraduate enrollment consultation"
            >本科生招生咨询
          </label>

          <input
            type="radio"
            id="Various reviews"
            value="各类评审 "
            v-model="category"
          />
          <label for="Various reviewsr">各类评审 </label>

          <input
            type="radio"
            id="excellentclass"
            value="其他 "
            v-model="category"
          />
          <label for="others">本科生分专业宣讲 </label>
        </td>
      </tr>
      <tr>
        <td>时间:</td>
        <td>
          <input type="date" placeholder="请选择时间" v-model="time" />
        </td>
      </tr>

      <tr>
        <td>姓名</td>
        <td>
          <input type="textarea" placeholder="请输入用户姓名" v-model="name" />
        </td>
      </tr>

      <tr>
        <td>教分</td>
        <td>
          <input
            type="number"
            min="1"
            max="10"
            step="1"
            value="1"
            v-model="score"
          />
        </td>
      </tr>

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
      category: "",
      time: "",
      name: "",
      score: "",
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