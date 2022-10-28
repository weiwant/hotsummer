<template>
  <div class="menuBar">
    <ul>
      <li
        v-for="(item, index) in menuItems"
        :Key="index"
        @click="choose(index)"
      >
        {{ item }}
      </li>
    </ul>
  </div>
</template>

<script>
export default {
  name: "MenuBar",
  data() {
    return {
      chosen: [],
    };
  },
  props: ["menuItems"],
  methods: {
    choose(index) {
      //如果被点击的菜单项之前未被选择
      if (!this.chosen[index]) {
        document
          .querySelector(`.menuBar ul li:nth-child(${index + 1})`)
          .classList.add("chosen");
        this.chosen[index] = true;
        this.$emit("show", index);
      } else {
        document
          .querySelector(`.menuBar ul li:nth-child(${index + 1})`)
          .classList.remove("chosen");
        this.chosen[index] = false;
        this.$emit("hide", index);
      }
    },
  },
  created() {
    //起初都未被选择
    for (let i = 0; i < this.menuItems.length; i++) {
      this.chosen.push(false);
    }
  },
};
</script>

<style scoped>
.menuBar ul {
  padding: 5px 15px;
}
.menuBar li {
  display: inline-block;
  margin-right: 20px;
  margin-bottom: 10px;
  padding: 5px;
  background-color: white;
  border: 2px solid rgba(128, 128, 128, 0.301);
  border-radius: 10px;
  cursor: pointer;
  transition: all 0.2s;
}

.menuBar li:hover {
  box-shadow: 1px 1px 3px rgba(0, 0, 0, 0.103);
  transform: scale(1.1);
}

.menuBar li.chosen {
  border: 2px solid rgb(41, 34, 106);
}
</style>
