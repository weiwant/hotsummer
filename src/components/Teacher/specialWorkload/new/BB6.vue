<template>
  <div class="componentSubsection category">
    <div class="categoryTitle">出版教材BB6</div>

    <!-- 填报与添加区域 -->
    <div class="addNew">
      <tr>
        <td>书名</td>
        <td>
          <input
            type="text"
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
        <td style="width: 60px; vertical-align: middle">内容简介</td>
        <td>
          <textarea
            cols="30"
            rows="10"
            placeholder="请在此输入教材的内容简介"
          ></textarea>
        </td>
      </tr>
      <tr>
        <td>出版日期</td>
        <td>
          <input type="date" v-model="time" />
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
          <select v-model="number" style="margin-left: 10px">
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
          <textarea
            cols="30"
            rows="10"
            placeholder="请在此填入你所获的荣誉"
          ></textarea>
        </td>
      </tr>
      <tr>
        <td style="vertical-align: middle">证明文件</td>
        <td>
          <input type="file" placeholder="请选择对应封面图片" />
          <input type="file" placeholder="请选择对应封底图片" />
        </td>
      </tr>
      <DynamicCollection
        ref="dynamic"
        @transmit="updateParticipants"
      ></DynamicCollection>

      <button class="universalBlueBtn complete" @click="save">
        保&nbsp;存
      </button>
    </div>
  </div>
</template>

<script>
import DynamicCollection from "../sharing/DynamicCollection.vue";
export default {
  components: { DynamicCollection },
  data() {
    return {
      title: "",
      edition: "",
      number: "",
      award: "",
      participants: [],
    };
  },
  methods: {
    updateParticipants(participants) {
      this.participants = participants;
    },

    save() {
      //点击保存，调用DynamicCollection组件的方法，将其中含有的数据同步至本组件内
      this.$refs.dynamic.transmitData();
    },
  },
  created() {},
};
</script>


<style scoped>
</style>