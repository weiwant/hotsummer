<template>
  <div class="componentWrapper">
    <div class="componentSectionTitle">特殊工作量上报</div>
    <div class="componentSubtitle">类型选择</div>
    <MenuBar :menuItems="menuItems" @show="show" @hide="hide"></MenuBar>
    <div class="componentSubtitle">内容填报</div>
    <transition>
      <BB1 v-if="render0"></BB1>
    </transition>
    <transition>
      <BB2 v-if="render1"></BB2>
    </transition>
    <transition>
      <BB3 v-if="render2"></BB3>
    </transition>
  </div>
</template>

<script>
import MenuBar from "./specialWorks/MenuBar.vue";
import BB1 from "./specialWorks/BB1.vue";
import BB2 from "./specialWorks/BB2.vue";
import BB3 from "./specialWorks/BB3.vue";
export default {
  name: "SpecialWorkload",
  components: { MenuBar, BB1, BB2, BB3 },
  data() {
    return {
      render0: false,
      render1: false,
      render2: false,
      menuItems: [
        "课程建设BB1",
        "教研项目BB2",
        "教学成果奖BB3",
        "教学竞赛奖励BB4",
        "发表教学研究论文BB5",
        "出版教材BB6",
        "教学名师BB7",
        "指导优秀毕业论文BB8",
        "指导学生竞赛获奖BB9",
        "指导学生科研立项并顺利完成项目BB10",
        "教学兼职BB11",
        "本科生培养活动BB13",
        "本科生主考之外的监考计BB14",
      ],
    };
  },
  methods: {
    show(index) {
      console.log("接收");
      console.log(index);
      switch (index) {
        case 0:
          this.render0 = true;
          break;
        case 1:
          this.render1 = true;
          break;
        case 2:
          this.render2 = true;
          break;
      }
    },
    hide(index) {
      switch (index) {
        case 0:
          this.render0 = false;
          break;
        case 1:
          this.render1 = false;
          break;
        case 2:
          this.render2 = false;
          break;
      }
    },
  },
};
</script>

<style scoped>
.category {
  position: relative;
  height: 200px;
  max-width: 1000px;
  border: 1px solid rgba(128, 128, 128, 0.301);
}
.v-enter {
  right: -1000px;
}
.v-enter-to {
  right: 0;
}
.v-enter-active {
  transition: all 0.2s;
}

.v-leave {
  opacity: 1;
  top: 0;
}
.v-leave-to {
  opacity: 0;
  top: 10px;
}
.v-leave-active {
  transition: all 0.2s;
}
</style>


