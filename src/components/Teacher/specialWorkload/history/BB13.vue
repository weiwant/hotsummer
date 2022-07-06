<template>
  <div class="componentSubsection category">
    <div class="categoryTitle saved" v-if="!committed">仅保存</div>
    <div class="categoryTitle commited" v-if="committed">已提交</div>
    <!-- 填报与添加区域 -->
    <div class="addNew">
      <tr>
        <td>类别</td>
        <td>
          <select v-model="category">
            <option value="卓越班选拔面试">卓越班选拔面试</option>
            <option value="校园开放日咨询">校园开放日咨询</option>
            <option value="本科生分专业宣讲">本科生分专业宣讲</option>
            <option value="转专业面试">转专业面试</option>
            <option value="本科生招生咨询">本科生招生咨询</option>
            <option value="各类评审">各类评审</option>
            <option value="其他">其他</option>
          </select>
        </td>
      </tr>

      <tr>
        <td>时间</td>
        <td>
          <input type="date" placeholder="请选择时间" v-model="time" />
        </td>
      </tr>

      <tr>
        <td>姓名</td>
        <td>
          <input type="text" placeholder="请输入姓名" v-model="name" />
        </td>
      </tr>

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
export default {
  data() {
    return {
      //编辑状态
      isEditing: false,
      //提交状态
      committed: true,
      category: "",
      time: "",
      name: "",
    };
  },
  props: ["data"],
  methods: {
    // 编辑
    edit() {
      this.isEditing = true;
    },
    // 提交
    commit() {
      this.isEditing = false;
      this.$refs.dynamic.transmitData();
    },
    // 保存
    save() {
      this.isEditing = false;
      this.$refs.dynamic.transmitData();
    },
  },
  created() {},
};
</script>


<style scoped>
</style>