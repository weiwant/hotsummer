<template>
  <div class="componentSubsection category">
    <div class="categoryTitle saved" v-if="!committed">仅保存</div>
    <div class="categoryTitle commited" v-if="committed">已提交</div>

    <!-- 填报与添加区域 -->
    <div class="addNew">
      <tr>
        <td>书名</td>
        <td>
          <input
            type="text"
            placeholder="请输入出版书籍的书名"
            v-model="title"
            :disabled="!isEditing"
          />
        </td>
      </tr>

      <tr>
        <td>ISBN</td>
        <td>
          <input type="text" placeholder="请输入教材的国际标准书号" :disabled="!isEditing" v-model="isbn"/>
        </td>
      </tr>
      <tr>
        <td style="width: 60px; vertical-align: middle">内容简介</td>
        <td>
          <textarea
            cols="30"
            rows="10"
            placeholder="请在此输入教材的内容简介"
            v-model="briefIntroduction"
          ></textarea>
        </td>
      </tr>

      <tr>
        <td>出版日期</td>
        <td>
          <input type="date" v-model="time" :disabled="!isEditing"/>
        </td>
      </tr>

      <tr>
        <td>教材版次</td>
        <td>
          <select v-model="edition" :disabled="!isEditing">
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
          <select v-model="number" :disabled="isEditing" style="margin-left: 10px">
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
            v-model="receivingHonor"
          ></textarea>
        </td>
      </tr>

      <tr>
        <td style="vertical-align: middle">证明文件</td>
        <td>
          <input type="file" placeholder="请选择对应封面图片" :disabled="!isEditing"/>
          <input type="file" placeholder="请选择对应封底图片" :disabled="!isEditing"/>
        </td>
      </tr>
      
      <DynamicCollection
        ref="dynamic"
        @transmit="updateParticipants"
      ></DynamicCollection>

      <button
        class="universalBlueBtn complete commit"
        @click="commit"
        v-if="!committed"
      >
        提&nbsp;交
      </button>
      <button
        class="universalBlueBtn complete notCommit"
        @click="save"
        v-if="isEditing && !committed"
      >
        保&nbsp;存
      </button>
      <button
        class="universalBlueBtn complete notCommit"
        @click="edit"
        v-if="!isEditing && !committed"
      >
        编&nbsp;辑
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
      //编辑状态
      isEditing: false,
      //提交状态
      committed: true,
      title: "",
      isbn: "",
      edition: "",
      number: "",
      briefIntroduction: "",
      receivingHonor: "",
      participants: [],
    };
  },
  props: ["data"],
  mounted() {
  this.$refs.dynamic.changeState(); //默认没有disable，需要调整

  var numberString = this.data.publicationsNumber.substring(3,8);
  var editionString = this.data.publicationsNumber.substring(0,3);

  this.$data.title = this.data.achievementName;
  this.$data.isbn = this.data.isbn;
  this.$data.edition = editionString;
  this.$data.number = numberString;
  this.$data.receivingHonor = this.data.receivingHonor;
  this.$data.participants = this.data.somePeople;
  },
  methods: {
    updateParticipants(participants) {
      this.participants = participants;
    },
    // 编辑
    edit() {
      this.$refs.dynamic.changeState();
      this.isEditing = true;
    },
    // 提交
    commit() {
      this.$refs.dynamic.changeState();
      this.isEditing = false;
      this.$refs.dynamic.transmitData();
    },
    // 保存
    save() {
      this.$refs.dynamic.changeState();
      this.isEditing = false;
      //点击保存，调用DynamicCollection组件的方法，将其中含有的数据同步至本组件内
      this.$refs.dynamic.transmitData();
       if(this.$data.title==""||this.$data.edition==""||this.$data.number==""||this.$data.award==""||this.$data.participants==""){
        alert("数据填报不可为空！！！")
        return;
      }
    },
  },
};
</script>


<style scoped>
</style>