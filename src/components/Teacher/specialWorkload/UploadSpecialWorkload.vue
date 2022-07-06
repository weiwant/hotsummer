<template>
  <div class="componentWrapper">
    <div class="componentSectionTitle">特殊工作量上报</div>
    <div class="componentSubtitle">类型选择</div>
    <MenuBar :menuItems="menuItems" @show="show" @hide="hide"></MenuBar>
    <div class="componentSubtitle" id="contentSectionTitle">内容填报</div>
    <div class="flexBox">
      <transition>
        <BB1 v-if="render0"></BB1>
      </transition>
      <transition>
        <BB2 v-if="render1"></BB2>
      </transition>
      <transition>
        <BB3 v-if="render2"></BB3>
      </transition>
      <transition>
        <BB4 v-if="render3"></BB4>
      </transition>
      <transition>
        <BB5 v-if="render4"></BB5>
      </transition>

      <transition>
        <BB6 v-if="render5"></BB6>
      </transition>
      <transition>
        <BB7 v-if="render6"></BB7>
      </transition>

      <transition>
        <BB8 v-if="render7"></BB8>
      </transition>

      <transition>
        <BB9 v-if="render8"></BB9>
      </transition>
      <transition>
        <BB10 v-if="render9"></BB10>
      </transition>
      <transition>
        <BB11 v-if="render10"></BB11>
      </transition>
      <transition>
        <BB12 v-if="render11"></BB12>
      </transition>
      <transition>
        <BB13 v-if="render12"></BB13>
      </transition>
      <transition>
        <BB14 v-if="render13"></BB14>
      </transition>
      <transition>
        <BB15 v-if="render14"></BB15>
      </transition>
    </div>
  </div>
</template>

<script>
import MenuBar from "./MenuBar.vue";
import BB1 from "./new/BB1.vue";
import BB2 from "./new/BB2.vue";
import BB3 from "./new/BB3.vue";
import BB4 from "./new/BB4.vue";
import BB5 from "./new/BB5.vue";
import BB6 from "./new/BB6.vue";
import BB7 from "./new/BB7.vue";
import BB8 from "./new/BB8.vue";
import BB9 from "./new/BB9.vue";
import BB10 from "./new/BB10.vue";
import BB11 from "./new/BB11.vue";
import BB12 from "./new/BB12.vue";
import BB13 from "./new/BB13.vue";
import BB14 from "./new/BB14.vue";
import BB15 from "./new/BB15.vue";
export default {
  name: "SpecialWorkload",
  components: {
    MenuBar,
    BB1,
    BB2,
    BB3,
    BB4,
    BB5,
    BB6,
    BB7,
    BB8,
    BB9,
    BB10,
    BB11,
    BB12,
    BB13,
    BB14,
    BB15,
  },
  data() {
    return {
      render0: false,
      render1: false,
      render2: false,
      render3: false,
      render4: false,
      render5: false,
      render6: false,
      render7: false,
      render8: false,
      render9: false,
      render10: false,
      render11: false,
      render12: false,
      render13: false,
      render14: false,
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
        "BB12",
        "本科生培养活动BB13",
        "本科生主考之外的监考计BB14",
        "其他BB15",
      ],
    };
  },
  methods: {
    show(index) {
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
        case 3:
          this.render3 = true;
          break;
        case 4:
          this.render4 = true;
          break;
        case 5:
          this.render5 = true;
          break;
        case 6:
          this.render6 = true;
          break;
        case 7:
          this.render7 = true;
          break;
        case 8:
          this.render8 = true;
          break;
        case 9:
          this.render9 = true;
          break;
        case 10:
          this.render10 = true;
          break;
        case 11:
          this.render11 = true;
          break;
        case 12:
          this.render12 = true;
          break;
        case 13:
          this.render13 = true;
          break;
        case 14:
          this.render14 = true;
          break;
      }
    },
    hide(index) {
      console.log(index);
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
        case 3:
          this.render3 = false;
          break;
        case 4:
          this.render4 = false;
          break;
        case 5:
          this.render5 = false;
          break;
        case 6:
          this.render6 = false;
          break;
        case 7:
          this.render7 = false;
          break;
        case 8:
          this.render8 = false;
          break;
        case 9:
          this.render9 = false;
          break;
        case 10:
          this.render10 = false;
          break;
        case 11:
          this.render11 = false;
          break;
        case 12:
          this.render12 = false;
          break;
        case 13:
          this.render13 = false;
          break;
        case 14:
          this.render14 = false;
          break;
      }
    },
  },
};
</script>

<style scoped>
#contentSectionTitle {
  margin-bottom: 35px;
}
.flexBox {
  display: flex;
  flex-wrap: wrap;
}
/* 最外层盒子 */
.category {
  position: relative;
  padding-bottom: 40px;
  margin-right: 2vw;
  margin-top: 10px;
  min-height: 200px;
  width: 600px;
  border: 1px solid rgba(128, 128, 128, 0.301);
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
.componentSubsection.category >>> .historyTitle,
.componentSubsection.category >>> .addNewTitle {
  font-weight: 500;
  font-size: 15px;
  color: rgb(242, 191, 72);
}
.componentSubsection.category >>> .historyTitle span.historyDisplayBtn {
  margin-left: 10px;
  font-family: "icomoon";
  font-size: 14px;
  color: rgba(128, 128, 128, 0.733);
  cursor: pointer;
}
.componentSubsection.category >>> .historyTitle span.historyDisplayBtn:hover {
  color: rgba(48, 60, 78, 0.774);
}
.componentSubsection.category >>> .historyTableWrapper {
  position: relative;
  border-radius: 3px;
  height: 100px;
  background-color: rgba(215, 186, 125, 0.082);
}

/* 填报与添加区域 */
.componentSubsection.category >>> .addNew td {
  padding-top: 10px;
  font-size: 15px;
}
.componentSubsection.category >>> .addNew tr > td:nth-child(1) {
  font-weight: 500;
  border-bottom: 4px solid rgba(128, 128, 128, 0.116);
}
.componentSubsection.category >>> .addNew tr > td:nth-child(2) {
  padding-left: 15px;
}
.componentSubsection.category >>> .addNew td label {
  margin-left: 5px;
  margin-right: 18px;
}
.componentSubsection.category >>> .addNew .dynamic td label {
  margin-right: 5px;
}
.componentSubsection.category >>> .addNew td input[type="number"],
.componentSubsection.category >>> .addNew td input[type="text"],
.componentSubsection.category >>> .addNew td input[type="month"],
.componentSubsection.category >>> .addNew td input[type="date"],
.componentSubsection.category >>> .addNew td input[type="textarea"] {
  width: 200px;
  height: 30px;
  border: 1px solid rgb(128, 128, 128);
  border-radius: 5px;
  padding-left: 5px;
}
.componentSubsection.category >>> .addNew td input[type="number"] {
  width: 60px;
}
.componentSubsection.category >>> .addNew td input[type="text"] {
  width: 200px;
}
.componentSubsection.category >>> .addNew td input[type="textarea"] {
  width: 300px;
  height: 100px;
}
.componentSubsection.category >>> .addNew td select {
  height: 30px;
  border: 1px solid gray;
  border-radius: 5px;
}
/* 保存、确认按钮 */
.componentSubsection.category >>> button.complete,
.componentSubsection.category >>> button.save {
  position: absolute;
  bottom: 10px;
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
</style>


