<template>
  <div class="componentWrapper">
    <div class="componentSectionTitle">特殊工作量上报</div>
    <div class="componentSubtitle">类型选择</div>
    <MenuBar :menuItems="menuItems" @show="show" @hide="hide"></MenuBar>
    <div class="componentSubtitle" id="contentSectionTitle">内容填报</div>
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
#contentSectionTitle {
  margin-bottom: 45px;
}
/* 最外层盒子 */
.category {
  position: relative;
  min-height: 200px;
  max-width: 1000px;
  border: 1px solid rgba(128, 128, 128, 0.301);
  padding-bottom: 35px;
  transition: all 0.2s;
}
/* 标题 */
.componentSubsection.category >>> .categoryTitle {
  display: inline-block;
  position: absolute;
  top: -35px;
  padding: 5px;
  font-weight: 400;
  font-size: 16px;
  text-align: center;
  background-color: white;
  border: 1px solid rgba(128, 128, 128, 0.301);
  border-bottom: 0;
  border-top-left-radius: 5px;
  border-top-right-radius: 5px;
}
/* 历史上报记录 */
.componentSubsection.category >>> .historyTitle {
  font-weight: 500;
}
.componentSubsection.category >>> .historyTitle span.historyDisplayBtn {
  margin-left: 10px;
  font-family: "icomoon";
  font-size: 14px;
  color: gray;
  cursor: pointer;
}
.componentSubsection.category >>> .historyTitle span.historyDisplayBtn:hover {
  color: rgb(48, 60, 78);
}
.componentSubsection.category >>> .historyTableWrapper {
  position: relative;
  border-bottom: 1px dashed gray;
  height: 100px;
  background-color: rgba(91, 104, 129, 0.185);
}

/* 填报与添加区域 */
.componentSubsection.category >>> .addNew {
  height: 100px;
  padding-top: 5px;
}
/* 添加新上报按钮 */
.componentSubsection.category >>> button.complete {
  position: absolute;
  bottom: 5px;
  right: 15px;
  padding: 5px;
  border-radius: 8px;
  border: 1px solid rgba(128, 128, 128, 0.555);
}

/*收集框显隐动画 */
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
/*  历史记录显影动画 */
.history-enter {
  top: -10px;
  opacity: 0;
}
.history-enter-to {
  top: 0;
  opacity: 1;
}
.history-enter-active {
  transition: all 0.2s;
}

.history-leave {
  opacity: 1;
}
.history-leave-to {
  opacity: 0;
}
.history-leave-active {
  transition: all 0.2s;
}
</style>


