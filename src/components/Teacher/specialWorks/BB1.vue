<template>
  <!-- 
1. tr是块级元素，不需要</br>再换行了
2. id在一个document内不可重复
3. 每一个input的对应文本都要用<label>来指代，最好不要直接字符串
4. 命名非必要最好不要用拼音
5. 暂时不加reset了，少写点方法，也怕不小心点到，用户体验不好
6. input name属性没必要
7. 一个select选框和一个变量绑定，这个变量的值将会是选中的option的value
   多个单选框和一个变量绑定，这个变量的值将会是选中的radio的value
8. selected checked默认选中可以不填
-->
  <div class="componentSubsection category">
    <div class="categoryTitle">课程建设BB1</div>
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
      <!-- radio -->
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
      <!-- text -->
      <tr>
        <td>项目名称</td>
        <td>
          <input
            type="text"
            placeholder="请输入所建设项目的名称"
            v-model="projectName"
          />
        </td>
      </tr>
      <!-- select -->
      <tr>
        <td>课程类别</td>
        <td>
          <select v-model="projectCategory">
            <option value="课程建设项目">课程建设项目</option>
            <option value="企业-教育部课程建设项目">
              企业-教育部课程建设项目
            </option>
            <option value="国家级课程建设项目">国家级课程建设项目</option>
            <option value="线上国家级一流本科课程建设项目">
              线上国家级一流本科课程建设项目
            </option>
            <option value="线下国家级一流本科课程建设项目">
              线下国家级一流本科课程建设项目
            </option>
            <option value="线上线下混合式国家级一流本科课程建设项目">
              线上线下混合式国家级一流本科课程建设项目
            </option>
            <option value="社会实践国家级一流本科课程建设项目">
              社会实践国家级一流本科课程建设项目
            </option>
            <option value="武大通识3.0">武大通识3.0</option>
            <option value="慕课课程">慕课课程</option>
            <option value="其他">其他</option>
          </select>
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
          <input type="radio" 
          id="done" value="结题" 
          v-model="projectStatus" 
          />
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
      <!-- file -->
      <tr>
        <td>上传附件</td>
        <td>
          <input type="file" />
        </td>
      </tr>
      
      <!-- 动态增删填报项组件 -->
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
      // 历史上报
      historyDisplayBtnText: "展开 ",
      historyShown: false,
      // 填报数据
      awardLevel: "",
      projectStatus: "",
      projectCategory: "",
      projectName: "",
      awardLevel: "",
      participants: [],
    };
  },
  methods: {
    // 展示历史上报
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
    /*提交上报数据*/
    commit() {
      var _this = this;
      const formData = new FormData();
      formData.append("award_level", this.$data.awardLevel);
      formData.append("project_status", this.$data.projectStatus);
      formData.append("project_category", this.$data.projectCategory);
      formData.append("project_name", this.$data.projectName);
      formData.append("files", this.$data.uploadFile);
      //以下需要修改接口
      this.axios
        .post(`${this.$domainName}/file/upload`, formData, {
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
