<template>
  <div class="componentSubsection category">
    <div class="categoryTitle">教学竞赛奖励BB4</div>
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
          <input type="radio" id="nation" value="国家级" v-model="awardLevel" />
          <label for="nation">国家级</label>
          <input type="radio" id="province" value="省级" v-model="awardLevel" />
          <label for="province">省级</label>
          <input type="radio" id="school" value="校级" v-model="awardLevel" />
          <label for="school">校级</label>
        </td>
      </tr>
      <tr>
        <td>竞赛名称</td>
        <td>
          <input
            type="text"
            placeholder="请输入比赛的名称"
            v-model="competitionname"
          />
        </td>
      </tr>
      <tr>
        <td>奖项类别</td>
        <td>
          <input
            type="text"
            placeholder="请输入所获奖项目的类别"
            v-model="awardCategory"
          />
        </td>
      </tr>
      <tr>
        <td>获奖等级</td>
        <td>
          <input type="text" placeholder="请输入获奖等级" v-model="level" />
        </td>
      </tr>
      <tr>
        <td>授奖单位</td>
        <td>
          <input
            type="text"
            placeholder="请输入授奖单位"
            v-model="awardingunit"
          />
        </td>
      </tr>
      <tr>
        <td>获奖时间</td>
        <td>
          <input type="date" placeholder="请选择获奖时间" v-model="time" />
        </td>
      </tr>
      <tr>
        <td>证明文件</td>
        <td>
          <input
           type="file"
           ref="file"
           name="file"
           @change="getFileData()"
           multiple="true" />
        </td>
      </tr>

      <DynamicCollection @update="changeParticipant"></DynamicCollection>

      <button class="universalBlueBtn complete" @click="commit">
        提&nbsp;交
      </button>
    </div>
  </div>
</template>

<script>
import DynamicCollection from "./DynamicCollection.vue";
export default {
  components: { DynamicCollection },
  data() {
    return {
      historyDisplayBtnText: "展开 ",
      historyShown: false,
      //填报数据
      awardLevel: "",
      competitionname: "",
      awardCategory: "",
      level: "",
      awardingunit: "",
      time: "",
      participants: [],
      //文件列表
      uploadFile: [],
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
    // 动态增删participants
    changeParticipant(participants) {
      this.participants = participants;
      console.log(this.participants);
    },
    //点击触发上传方法
    uploadMaterial() {
      this.$refs.file.dispatchEvent(new MouseEvent("click"));
    },
    //添加文件数据
    getFileData(file){
      var _this = this;
      const inputFile = this.$refs.file.files[0];
      this.$data.uploadFile.push(inputFile);
    },
    /*提交上报数据*/
    commit() {
      var _this = this;
      const formData = new FormData();

      var data = JSON.stringify([{
        awardLevel: this.$data.awardLevel,
        competitionname: this.$data.competitionname,
        awardCategory: this.$data.awardCategory,
        level: this.$data.level,
        awardingunit: this.$data.awardingunit,
        time: this.$data.time,
      }]);

      formData.append("data", data);

      for(let i = 0; i < this.$data.uploadFile.length; i++){
        formData.append("files", this.$data.uploadFile[i]);
      }

      console.log(formData.get("data"));
      console.log(formData.get("files"));

      //以下需要修改接口
      this.$axios
        .post(`${this.$domainName}/special-workload/upload`, formData, {
            headers: {
              "Content-Type": "multipart/form-datas",
            },
          })
          .then((res) => {
            if (res.data.response.code == 200) {
              alert("报表文件上传成功！");
            } else {
              alert("上传失败！");
            }
          })
          .catch(function (error) {
            console.log(error);
          });
    },
  },
  created() {},
};
</script>


<style scoped>
</style>