<template>
  <div class="componentSubsection category">
    <div class="categoryTitle saved" v-if="!committed">仅保存</div>
    <div class="categoryTitle commited" v-if="committed">已提交</div>
    <!-- 填报与添加区域 -->
    <div class="addNew">
      <tr>
        <td>级别</td>
        <td>
          <input
            type="radio"
            id="nation"
            value="国家级"
            v-model="level"
            :disabled="!isEditing"
          />
          <label for="nation">国家级</label>

          <input
            type="radio"
            id="provinciallevel"
            value="省级"
            v-model="level"
            :disabled="!isEditing"
          />
          <label for="provinciallevel">省级</label>

          <input
            type="radio"
            id="schoollevel"
            value="校级"
            v-model="level"
            :disabled="!isEditing"
          />
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

          <input
            type="radio"
            id="done"
            value="结题"
            v-model="projectStatus"
            :disabled="!isEditing"
          />
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
        :data="participants"
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
      projectStatus: "",
      participants: [],
    };
  },
  props: ["data"],
  created() {
    if (this.data.status == "已提交") {
      this.committed = true;
    } else {
      this.committed = false;
    }
    this.$data.level = this.data.level;
    this.$data.projectName = this.data.projectName;
    this.$data.projectCategory = this.data.projectCategory;
    this.$data.projectStatus = this.data.projectStatus;
    this.$data.participants = this.data.somePeople;
  },
  mounted() {
    this.$refs.dynamic.changeState(); //默认没有disable，需要调整,而调整需要在dynamic组件的created之后，故放置在本组件的mounted中
  },
  methods: {
    updateParticipants(participants) {
      this.participants = participants;
    },
    // 编辑
    edit() {
      this.$refs.dynamic.changeState();
      this.isEditing = true;
    },
    // 提交
    commit() {
      this.$refs.dynamic.changeState();
      this.isEditing = false;
      //点击保存，调用DynamicCollection组件的方法，将其中含有的数据同步至本组件内
      this.$refs.dynamic.transmitData();
      const formData = new FormData();

      let specialVo = {
        level: this.$data.level,
        projectStatus: this.$data.projectStatus,
        projectCategory: this.$data.projectCategory,
        projectName: this.$data.projectName,
        declarantName: this.$currentUser,
        type: "BB2",
        id: this.data.id,
        status: "已提交",
      };
      for (const key in specialVo) {
        formData.append(key, specialVo[key]);
      }

      formData.append("teachers", JSON.stringify(this.$data.participants));
      formData.append("specialVo", specialVo);

      //以下需要修改接口
      this.$axios
        .post(`/special-workload/update/teacher`, formData, {
          headers: {
            "Content-Type": "multipart/form-datas",
          },
        })
        .then((res) => {
          if (res.data.response.code == 200) {
            this.committed = true; //按道理应该重新请求，但是暂时直接改状态吧
            alert("提交申报成功！");
          } else {
            alert("提交申报失败！");
          }
        })
        .catch(function (error) {
          console.log(error);
        });
    },
    // 保存
    save() {
      this.$refs.dynamic.changeState();
      this.isEditing = false;
      //点击保存，调用DynamicCollection组件的方法，将其中含有的数据同步至本组件内
      this.$refs.dynamic.transmitData();
      const formData = new FormData();
      let specialVo = {
        level: this.$data.level,
        projectStatus: this.$data.projectStatus,
        projectCategory: this.$data.projectCategory,
        projectName: this.$data.projectName,
        declarantName: this.$currentUser,
        type: "BB2",
        id: this.data.id,
      };
      for (const key in specialVo) {
        formData.append(key, specialVo[key]);
      }

      formData.append("teachers", JSON.stringify(this.$data.participants));
      formData.append("specialVo", specialVo);

      //以下需要修改接口
      this.$axios
        .post(`/special-workload/update/teacher`, formData, {
          headers: {
            "Content-Type": "multipart/form-datas",
          },
        })
        .then((res) => {
          if (res.data.response.code == 200) {
            alert("提交申报成功！");
          } else {
            alert("提交申报失败！");
          }
        })
        .catch(function (error) {
          console.log(error);
        });
    },
  },
};
</script>


<style scoped>
</style>