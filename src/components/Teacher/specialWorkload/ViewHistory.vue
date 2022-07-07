<template>
  <div class="componentWrapper">
    <div class="componentSectionTitle">特殊工作量历史上报</div>
    <div class="warning">
      <span class="icon"></span>
      仅保存未提交的项目，将在<span class="time">{{ ddl }}</span
      >日自动提交
    </div>
    <div v-if="BB1Arr.length > 0">
      <div class="componentSubtitle">课程建设BB1</div>
      <div class="flexBox">
        <BB1 v-for="(item, index) in BB1Arr" :data="item" :key="index" />
      </div>
    </div>
    <div v-if="BB2Arr.length > 0">
      <div class="componentSubtitle">教研项目BB2</div>
      <div class="flexBox">
        <BB2 v-for="(item, index) in BB2Arr" :data="item" :key="index" />
      </div>
    </div>
    <div v-if="BB3Arr.length > 0">
      <div class="componentSubtitle">教学成果奖BB3</div>
      <div class="flexBox">
        <BB3 v-for="(item, index) in BB3Arr" :data="item" :key="index" />
      </div>
    </div>
    <div v-if="BB4Arr.length > 0">
      <div class="componentSubtitle">教学竞赛奖励BB4</div>
      <div class="flexBox">
        <BB4 v-for="(item, index) in BB4Arr" :data="item" :key="index" />
      </div>
    </div>
    <div v-if="BB5Arr.length > 0">
      <div class="componentSubtitle">发表教学研究论文BB5</div>
      <div class="flexBox">
        <BB5 v-for="(item, index) in BB5Arr" :data="item" :key="index" />
      </div>
    </div>
    <div v-if="BB6Arr.length > 0">
      <div class="componentSubtitle">出版教材BB6</div>
      <div class="flexBox">
        <BB6 v-for="(item, index) in BB6Arr" :data="item" :key="index" />
      </div>
    </div>
    <div v-if="BB7Arr.length > 0">
      <div class="componentSubtitle">教学名师BB7</div>
      <div class="flexBox">
        <BB7 v-for="(item, index) in BB7Arr" :data="item" :key="index" />
      </div>
    </div>
    <div v-if="BB8Arr.length > 0">
      <div class="componentSubtitle">指导优秀毕业论文BB8</div>
      <div class="flexBox">
        <BB8 v-for="(item, index) in BB8Arr" :data="item" :key="index" />
      </div>
    </div>
    <div v-if="BB9Arr.length > 0">
      <div class="componentSubtitle">指导学生竞赛获奖BB9</div>
      <div class="flexBox">
        <BB9 v-for="(item, index) in BB9Arr" :data="item" :key="index" />
      </div>
    </div>
    <div v-if="BB10Arr.length > 0">
      <div class="componentSubtitle">指导学生科研立项并顺利完成项目BB10</div>
      <div class="flexBox">
        <BB10 v-for="(item, index) in BB10Arr" :data="item" :key="index" />
      </div>
    </div>
    <div v-if="BB11Arr.length > 0">
      <div class="componentSubtitle">教学兼职BB11</div>
      <div class="flexBox">
        <BB11 v-for="(item, index) in BB11Arr" :data="item" :key="index" />
      </div>
    </div>
    <div v-if="BB12Arr.length > 0">
      <div class="componentSubtitle">未知BB12</div>
      <div class="flexBox">
        <BB12 v-for="(item, index) in BB12Arr" :data="item" :key="index" />
      </div>
    </div>
    <div v-if="BB13Arr.length > 0">
      <div class="componentSubtitle">本科生培养活动BB13</div>
      <div class="flexBox">
        <BB13 v-for="(item, index) in BB13Arr" :data="item" :key="index" />
      </div>
    </div>
    <div v-if="BB14Arr.length > 0">
      <div class="componentSubtitle">本科生主考之外的监考计BB14</div>
      <div class="flexBox">
        <BB14 v-for="(item, index) in BB14Arr" :data="item" :key="index" />
      </div>
    </div>
    <div v-if="BB15Arr.length > 0">
      <div class="componentSubtitle">其他BB15</div>
      <div class="flexBox">
        <BB15 v-for="(item, index) in BB15Arr" :data="item" :key="index" />
      </div>
    </div>
  </div>
</template>

<script>
export default {
  components: {
    BB1: () => import("./history/BB1.vue"),
    BB2: () => import("./history/BB2.vue"),
    BB3: () => import("./history/BB3.vue"),
    BB4: () => import("./history/BB4.vue"),
    BB5: () => import("./history/BB5.vue"),
    BB6: () => import("./history/BB6.vue"),
    BB7: () => import("./history/BB7.vue"),
    BB8: () => import("./history/BB8.vue"),
    BB9: () => import("./history/BB9.vue"),
    BB10: () => import("./history/BB10.vue"),
    BB11: () => import("./history/BB11.vue"),
    BB12: () => import("./history/BB12.vue"),
    BB13: () => import("./history/BB13.vue"),
    BB14: () => import("./history/BB14.vue"),
    BB15: () => import("./history/BB15.vue"),
  },
  data() {
    return {
      historyWorkloads: [],
      ddl: "",
    };
  },
  computed: {
    BB1Arr() {
      return this.historyWorkloads.filter((item) => item.type == "BB1");
    },
    BB2Arr() {
      return this.historyWorkloads.filter((item) => item.type == "BB2");
    },
    BB3Arr() {
      return this.historyWorkloads.filter((item) => item.type == "BB3");
    },
    BB4Arr() {
      return this.historyWorkloads.filter((item) => item.type == "BB4");
    },
    BB5Arr() {
      return this.historyWorkloads.filter((item) => item.type == "BB5");
    },
    BB6Arr() {
      return this.historyWorkloads.filter((item) => item.type == "BB6");
    },
    BB7Arr() {
      return this.historyWorkloads.filter((item) => item.type == "BB7");
    },
    BB8Arr() {
      return this.historyWorkloads.filter((item) => item.type == "BB8");
    },
    BB9Arr() {
      return this.historyWorkloads.filter((item) => item.type == "BB9");
    },
    BB10Arr() {
      return this.historyWorkloads.filter((item) => item.type == "BB10");
    },
    BB11Arr() {
      return this.historyWorkloads.filter((item) => item.type == "BB11");
    },
    BB12Arr() {
      return this.historyWorkloads.filter((item) => item.type == "BB12");
    },
    BB13Arr() {
      return this.historyWorkloads.filter((item) => item.type == "BB13");
    },
    BB14Arr() {
      return this.historyWorkloads.filter((item) => item.type == "BB14");
    },
    BB15Arr() {
      return this.historyWorkloads.filter((item) => item.type == "BB15");
    },
  },
  created() {
    //获取ddl
    const formData = new FormData();
    formData.append("year", this.$currentYear);
    this.$axios
      .post("http://abcd.vaiwan.com/deadline/get", formData)
      .then((res) => {
        this.ddl = res.data.data;
      });
    //获取当前老师，所申报的所有特殊工作量historyWorkloads[]
    const formData2 = new FormData();
    // formData2.append("year", this.$currentYear);
    // formData2.append("declarantName", this.$currentUser);
    formData2.append("year", 2022);
    formData2.append("declarantName", "丁建利");
    this.$axios
      .post("http://abkkds.vaiwan.com/special-join/select/teacher", formData2)
      .then((res) => {
        this.historyWorkloads = res.data.data;
      });
  },
};
</script>

<style scoped>
/* ddl提示 */
.warning {
  text-align: left;
}
.warning span.icon {
  font-family: "icomoon";
  font-size: 20px;
}
.warning span.time {
  color: rgb(216, 170, 52);
  font-weight: 600;
}
.flexBox {
  display: flex;
  overflow: scroll;
  padding-top: 25px;
  padding-bottom: 10px;
}
#contentSectionTitle {
  margin-bottom: 35px;
}
.category {
  flex-basis: 600px;
  flex-grow: 0;
  flex-shrink: 0;
  position: relative;
  padding-bottom: 55px;
  margin-right: 2vw;
  margin-top: 10px;
  min-height: 200px;
  border: 1px solid rgba(128, 128, 128, 0.301);
  transition: all 0.2s;
}
/* 标题 */
.componentSubsection.category >>> .categoryTitle {
  display: inline-block;
  position: absolute;
  top: -35px;
  padding: 5px;
  font-weight: 500;
  font-size: 16px;
  color: white;
  text-align: center;
  border: 1px solid rgba(128, 128, 128, 0.301);
  border-bottom: 0;
  border-top-left-radius: 5px;
  border-top-right-radius: 5px;
}
.componentSubsection.category >>> .categoryTitle.saved {
  background-color: rgb(79, 121, 122);
}
.componentSubsection.category >>> .categoryTitle.commited {
  background-color: rgb(141, 177, 148);
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

.componentSubsection.category >>> button.complete {
  position: absolute;
  bottom: 10px;
  padding: 5px;
  border-radius: 8px;
  border: 1px solid rgba(128, 128, 128, 0.555);
}
.componentSubsection.category >>> button.complete.notCommit {
  right: 15px;
}

.componentSubsection.category >>> button.commit.complete {
  left: 15px;
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
