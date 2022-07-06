<template>
  <div class="componentSubsection category">
    <div class="categoryTitle saved" v-if="!committed">仅保存</div>
    <div class="categoryTitle commited" v-if="committed">已提交</div>
    <!-- 填报与添加区域 -->
    <div class="addNew">
      <tr>
        <td>级别</td>
        <td>
          <input type="radio" 
          id="nation" 
          value="国家级" 
          v-model="level"
          :disabled="!isEditing"/>
          <label for="nation">国家级</label>

          <input
            type="radio"
            id="provinciallevel"
            value="省级"
            v-model="level"
            :disabled="!isEditing"
          />
          <label for="provinciallevel">省级</label>

          <input type="radio"
           id="schoollevel" 
           value="校级"
           v-model="level" 
           :disabled="!isEditing"/>
          <label for="schoollevel">校级</label>
        </td>
      </tr>

      <tr>
        <td>项目名称</td>
        <td>
          <input
            type="text"
            placeholder="请输入所建设项目的名称"
            v-model="projectName"
            :disabled="!isEditing"
          />
        </td>
      </tr>

      <tr>
        <td>项目类别</td>
        <td>
          <select v-model="projectCategory" :disabled="!isEditing">
            <option value="课程思政示范专业建设项目" selected="selected">
              课程思政示范专业建设项目
            </option>
            <option value="自由选题建设项目">自由选题建设项目</option>
            <option>社会实践课程建设项目</option>
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
            :disabled="!isEditing"
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
            :disabled="!isEditing"
          />
          <label for="established">立项</label>

          <input type="radio" 
          id="done"
           value="结题" 
           v-model="projectStatus" 
           :disabled="!isEditing"/>
          <label for="done">结题</label>

          <input
            type="radio"
            id="doing"
            value="建设中"
            v-model="projectStatus"
            :disabled="!isEditing"
          />
          <label for="doing">建设中</label>
        </td>
      </tr>

      <!-- 动态增删填报项组件 -->
      <DynamicCollection
        ref="dynamic"
        @transmit="updateParticipants"
      ></DynamicCollection>
      <button
        class="universalBlueBtn complete commit"
        @click="commit"
        v-if="!committed"
      >
        提&nbsp;交
      </button>
      <button
        class="universalBlueBtn complete notCommit"
        @click="save"
        v-if="isEditing && !committed"
      >
        保&nbsp;存
      </button>
      <button
        class="universalBlueBtn complete notCommit"
        @click="edit"
        v-if="!isEditing && !committed"
      >
        编&nbsp;辑
      </button>
    </div>
  </div>
</template>

<script>
import DynamicCollection from "../sharing/DynamicCollection.vue";
export default {
  components: { DynamicCollection },
  data() {
    return {
      //编辑状态
      isEditing: false,
      //提交状态
      committed: true,
      //填报数据
      level: "",
      projectName: "",
      projectCategory: "",
      teacherName: "",
      projectStatus: "",
      participants: [],
    };
  },
  props: ["data"],
  methods: {
    updateParticipants(participants) {
      this.participants = participants;
    },
    // 编辑
    edit() {
      this.isEditing = true;
    },
    // 提交
    commit() {
      this.isEditing = false;
      this.$refs.dynamic.transmitData();
    },
    // 保存
    save() {
      this.isEditing = false;
      //点击保存，调用DynamicCollection组件的方法，将其中含有的数据同步至本组件内
      this.$refs.dynamic.transmitData();
      if(this.$data.level==""||this.$data.projectName==""||this.$data.projectCategory==""||this.$data.projectStatus==""||this.$data.teacherName==""){
        alert("数据填报不可为空！！！")
      }
      var _this = this;
      const formData = new FormData();

      var data = JSON.stringify([
        {
          awardLevel: this.$data.awardLevel,
          projectStatus: this.$data.projectStatus,
          projectCategory: this.$data.projectCategory,
          projectName: this.$data.projectName,
        },
      ]);

      formData.append("data", data);

      console.log(formData.get("data"));

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
  created() {
    this.$refs.dynamic.changeState(); //默认没有disable，需要调整
  },
};
</script>


<style scoped>
</style>