<template>
  <div class="componentSubsection category">
    <div class="categoryTitle">发表教学研究论文BB</div>

    <!-- 填报与添加区域 -->
    <div class="addNew">
      <tr>
        <td>级别</td>
        <td>
          <input type="radio" id="authority" value="权威" v-model="level" />
          <label for="authority">权威</label>
          <input type="radio" id="core" value="核心" v-model="level" />
          <label for="core">核心</label>
          <input type="radio" id="noncore" value="非核心" v-model="level" />
          <label for="noncore">非核心</label>
        </td>
      </tr>
      <tr>
        <td style="width: 80px">文章名称</td>
        <td>
          <input
            type="text"
            placeholder="请输入文章名称"
            v-model="articlename"
          />
        </td>
      </tr>
      <tr>
        <td>刊物名称</td>
        <td>
          <input
            type="text"
            placeholder="请输入刊物名称"
            v-model="publicationname"
          />
        </td>
      </tr>
      <tr>
        <td>刊物期数</td>
        <td>
          <input type="month" placeholder="请选择出版月份" v-model="month" />
          <input
            type="number"
            min="1"
            v-model="stage"
            style="margin-left: 10px"
          />&nbsp;期
        </td>
      </tr>
      <tr>
        <td style="vertical-align: middle">证明文件</td>
        <td>
          <input type="file" value="论文封面" />
          <input type="file" value="论文目录" />
          <input type="file" value="论文正文" />
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
      level: "",
      articlename: "",
      publicationname: "",
      month: "",
      stage: "",
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