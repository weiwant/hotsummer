<template>
  <div class="componentSubsection category">
    <div class="categoryTitle">教研项目BB2</div>
    <!-- 历史上报记录 -->
    <div class="history">
      <div class="historyTitle">
        历史
        <span class="historyDisplayBtn" @click="showHistory">{{
          historyDisplayBtnText
        }}</span>
      </div>
      <transition name="history">
        <div class="historyTableWrapper" v-if="historyShown"></div>
      </transition>
    </div>
    <!-- 填报与添加区域 -->
    <div class="addNew">
      <tr>
        <td>级别</td>
        <td>
          <input type="radio" id="nation" value="国家级" v-model="level" />
          <label for="nation">国家级</label>
          <input
            type="radio"
            id="provinciallevel"
            value="省级"
            v-model="level"
          />
          <label for="provinciallevel">省级</label>
          <input type="radio" id="schoollevel" value="校级" v-model="level" />
          <label for="schoollevel">校级</label>
        </td>
      </tr>
      <tr>
        <td>项目名称</td>
        <td>
          <input
            type="text"
            placeholder="请输入所建设项目的名称"
            v-model="projectname"
          />
        </td>
      </tr>
      <tr>
        <td>项目类别</td>
        <td>
          <select v-model="projectCategory">
            <option value="课程思政示范专业建设项目">
              课程思政示范专业建设项目
            </option>
            <option value="自由选题建设项目">自由选题建设项目</option>
            <option value="社会实践课程建设项目">社会实践课程建设项目</option>
            <option value="教师教学发展研究项目">教师教学发展研究项目</option>
            <option value="MOOC课程建设项目">MOOC课程建设项目</option>
            <option value="课程思政课程建设项目">课程思政课程建设项目</option>
          </select>
        </td>
      </tr>
      <tr>
        <td>负责人姓名</td>
        <td>
          <input
            type="text"
            placeholder="请输入项目负责人姓名"
            v-model="teacherName"
          />
        </td>
      </tr>
      <tr>
        <td>进展情况</td>
        <td>
          <input
            type="radio"
            id="established"
            value="立项"
            v-model="projectStatus"
          />
          <label for="established">立项</label>
          <input type="radio" id="done" value="结题" v-model="projectStatus" />
          <label for="done">结题</label>
          <input
            type="radio"
            id="doing"
            value="建设中"
            v-model="projectStatus"
          />
          <label for="doing">建设中</label>
        </td>
      </tr>
      <button class="universalBlueBtn complete" @click="commit">
        提&nbsp;交
      </button>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      historyDisplayBtnText: "展开 ",
      historyShown: false,
      //填报数据
      level: "",
      projectName: "",
      projectCategory: "",
      teacherName: "",
      projectStatus: "",
    };
  },
  methods: {
    showHistory() {
      if (!this.historyShown) {
        this.historyDisplayBtnText = "收起 ";
        this.historyShown = true;
      } else {
        this.historyDisplayBtnText = "展开 ";
        this.historyShown = false;
      }
    },

    /*提交上报数据*/
    commit() {
      this.$axios
        .post(`${this.$domainName}/special-workload/upload`, {
          data: [
            {
              level: this.level,
              projectName: this.projectName,
              projectCategory: this.projectCategory,
              teacherName: this.teacherName,
              projectStatus: this.projectStatus,
            },
          ],
        })
        .then((res) => {
          console.log(res);
        });
    },
  },
  created() {},
};
</script>


<style scoped>
</style>