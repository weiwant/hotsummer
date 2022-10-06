<template>
  <div id="table-header-selection">
    <div class="app-section">
      <div class="app-section-title">展示表头选择</div>
      <div class="header-selection-wrapper">
        <div
          class="header-selection-item"
          v-for="(group, index) in headerGroups"
        >
          <div
            class="toggle"
            :style="{
              backgroundColor: chosen[index] ? colorList[index % 5] : '#999',
            }"
            :class="{ chosen: chosen[index] }"
            @click="choose(index)"
          ></div>
          <span class="value">{{ group }}</span>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  props: {
    headerGroups: {
      type: Array,
      required: true,
    },
    headerChosen: {
      type: Array,
      required: true,
    },
  },
  data() {
    return {
      colorList: ["#306f8b", "#8baeb2", "#d9e7d6", "#eeeedb", "#99a67c"],
      chosen: [],
    };
  },
  methods: {
    choose(index) {
      const newValue = !this.chosen[index];
      this.chosen.splice(index, 1, newValue); //让数组元素值的更改可响应
      this.$emit("change", this.chosen);
    },
  },
  created() {
    this.chosen = [...this.headerChosen];
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
.header-selection-item {
  margin-bottom: 10px;
}
.toggle {
  display: inline-block;
  position: relative;
  width: 50px;
  height: 22px;
  border: 1px solid #eee;
  border-radius: 100px;
  transition: all 0.2s;
  cursor: pointer;
}
.toggle::before {
  display: block;
  position: absolute;
  left: 0;
  top: 0;
  content: "";
  width: 18px;
  height: 18px;
  border: 1px solid #eee;
  border-radius: 100px;
  background-color: #fff;
  transition: all 0.2s;
}
.toggle:hover {
  opacity: 0.5;
}
.chosen::before {
  left: 30px;
}
.header-selection-item .value {
  font-size: 13px;
  vertical-align: super;
  margin-left: 10px;
}
</style>