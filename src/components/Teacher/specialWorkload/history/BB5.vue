<template>
  <div class="componentSubsection category">
    <div class="categoryTitle saved" v-if="!committed">仅保存</div>
    <div class="categoryTitle commited" v-if="committed">已提交</div>

    <!-- 填报与添加区域 -->
    <div class="addNew">
      <tr>
        <td>级别</td>
        <td>
          <input
            type="radio"
            id="authority"
            value="权威"
            v-model="level"
            :disabled="!isEditing"
          />
          <label for="authority">权威</label>

          <input
            type="radio"
            id="core"
            value="核心"
            v-model="level"
            :disabled="!isEditing"
          />
          <label for="core">核心</label>

          <input
            type="radio"
            id="noncore"
            value="非核心"
            v-model="level"
            :disabled="!isEditing"
          />
          <label for="noncore">非核心</label>
        </td>
      </tr>

      <tr>
        <td style="width: 80px">文章名称</td>
        <td>
          <input
            type="text"
            placeholder="请输入文章名称"
            v-model="articleName"
            :disabled="!isEditing"
          />
        </td>
      </tr>

      <tr>
        <td>刊物名称</td>
        <td>
          <input
            type="text"
            placeholder="请输入刊物名称"
            v-model="publicationName"
            :disabled="!isEditing"
          />
        </td>
      </tr>
      <tr>
        <td>刊物期数</td>
        <td>
          <input
            type="month"
            placeholder="请选择出版月份"
            v-model="month"
            :disabled="!isEditing"
          />

          <input
            type="number"
            min="1"
            v-model="stage"
            style="margin-left: 10px"
            :disabled="!isEditing"
          />&nbsp;期
        </td>
      </tr>

      <tr>
        <td style="vertical-align: middle">证明文件</td>
        <td>
          <input type="file" value="论文封面" :disabled="!isEditing" />
          <input type="file" value="论文目录" :disabled="!isEditing" />
          <input type="file" value="论文正文" :disabled="!isEditing" />
        </td>
      </tr>

      <DynamicCollection
        ref="dynamic"
        @transmit="updateParticipants"
        :data="participants"
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
      isEditting: false,
      //提交状态
      commited: true,
      level: "",
      articleName: "",
      publicationName: "",
      month: "",
      stage: "",
      participants: [],
    };
  },
  props: ["data"],
  created() {
    if ((this.data.status = "已提交")) {
      this.committed = true;
    } else {
      this.committed = false;
    }
    this.$data.level = this.data.level;
    this.$data.articleName = this.data.achievementName;
    this.$data.publicationName = this.data.publicationName;
    this.$data.month = this.data.awardDate;
    this.$data.stage = this.data.publicationNumber;
    this.$data.participants = this.data.somePeople;
  },
  mounted() {
    this.$refs.dynamic.changeState(); //默认没有disable，需要调整
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
      if (
        this.$data.level == "" ||
        this.$data.competitionname == "" ||
        this.$data.articleName == "" ||
        this.$data.publicationName == "" ||
        this.$data.month == "" ||
        this.$data.stage == "" ||
        this.$data.participants == ""
      ) {
        alert("数据填报不可为空！！！");
        return;
      }
    },
  },
};
</script>


<style scoped>
</style>