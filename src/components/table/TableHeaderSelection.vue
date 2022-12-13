<template>
  <div id="table-header-selection">
    <div class="app-section">
      <div class="app-section-title">展示表头选择</div>
      <el-checkbox-group v-model="checkList" @change="choose">
        <el-checkbox v-for='(item, index) in this.headers' :label="index">{{ item.label }}</el-checkbox>
      </el-checkbox-group>
    </div>
  </div>
</template>

<script>

export default {
  props: {
    headers: {
      type: Array,
      required: true,
    },
  },
  data() {
    return {
      checkList: [],
    };
  },
  methods: {
    choose() {
      this.$emit('change', this.checkList) //改变选中状态
    },
  },
  created() {
    //将默认展示的表头index放入checkList中
    for (let i = 0; i < this.headers.length; i++) {
      if (this.headers[i].show) this.checkList.push(i)
    }
  },
};
</script>

<style scoped>
#table-header-selection {
  flex-grow: 3;
}

.app-section {
  height: 100%;
}
</style>