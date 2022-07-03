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
      <tr>
        <td>书名</td>
        <td>
          <input
            type="textarea"
            placeholder="请输入出版书籍的书名"
            v-model="title"
          />
        </td>
      </tr>
      <tr>
        <td>ISBN</td>
        <td>
          <input type="text" placeholder="请输入教材的国际标准书号" />
        </td>
      </tr>
      <tr>
        <td>内容简介</td>
        <td>
          <input type="textarea" placeholder="请在此输入教材的内容简介" />
        </td>
      </tr>
      <tr>
        <td>出版日期</td>
        <td>
          <input type="”date”" v-model="time" />
        </td>
      </tr>
      <tr>
        <td>教材版次</td>
        <td>
          <select v-model="edition">
            <option value="第一版">第一版</option>
            <option value="第二版">第二版</option>
            <option value="第三版">第三版</option>
            <option value="第四版">第四版</option>
            <option value="第五版">第五版</option>
            <option value="第六版">第六版</option>
            <option value="第七版">第七版</option>
            <option value="第八版">第八版</option>
            <option value="第九版">第九版</option>
            <option value="第十版">第十版</option>
          </select>
          <select v-model="number">
            <option value="第一次印刷">第一次印刷</option>
            <option value="第二次印刷">第二次印刷</option>
            <option value="第三次印刷">第三次印刷</option>
            <option value="第四次印刷">第四次印刷</option>
            <option value="第五次印刷">第五次印刷</option>
            <option value="第六次印刷">第六次印刷</option>
            <option value="第七次印刷">第七次印刷</option>
            <option value="第八次印刷">第八次印刷</option>
            <option value="第九次印刷">第九次印刷</option>
            <option value="第十次印刷">第十次印刷</option>
          </select>
        </td>
      </tr>
      <tr>
        <td>所获荣誉</td>
        <td>
          <input
            type="textarea"
            placeholder="请在此填入你所获的荣誉"
            v-model="award"
          />
        </td>
      </tr>
      <tr>
        <td>证明文件</td>
        <td>
          <input type="file" placeholder="请选择对应封面图片" />
          <input type="file" placeholder="请选择对应封底图片" />
        </td>
      </tr>
      <DynamicCollection @update="changeParticipant"></DynamicCollection>

      <button class="universalBlueBtn complete" @click="commit">
        提&nbsp;交
      </button>
    </div>
  </div>
</template>

<script>
import DynamicCollection from "./DynamicCollection.vue";
export default {
  components: { DynamicCollection },
  data() {
    return {
      historyDisplayBtnText: "展开 ",
      historyShown: false,
      title: "",
      edition: "",
      number: "",
      award: "",
      participants: [],
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
    changeParticipant(participants) {
      this.participants = participants;
      console.log(this.participants);
    },
    /*提交上报数据*/
    commit() {},
  },
  created() {},
};
</script>


<style scoped>
</style>