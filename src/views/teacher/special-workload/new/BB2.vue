<template>
  <div class="componentSubsection category">
    <div class="categoryTitle">教研项目BB2</div>
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
            v-model="projectName"
          />
        </td>
      </tr>
      <tr>
        <td>项目类别</td>
        <td>
          <select v-model="projectCategory">
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

      <!-- 动态增删填报项组件 -->
      <DynamicCollection
        ref="dynamic"
        @transmit="updateParticipants"
      ></DynamicCollection>
      <button class="universalBlueBtn complete" @click="save">
        保&nbsp;存
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
      //填报数据
      level: "",
      projectName: "",
      projectCategory: "",
      teacherName: "",
      projectStatus: "",
      participants: [],
    };
  },
  methods: {
    updateParticipants(participants) {
      this.participants = participants;
    },
    save() {
      //点击保存，调用DynamicCollection组件的方法，将其中含有的数据同步至本组件内
      this.$refs.dynamic.transmitData();
      const formData = new FormData();

      var specialVo = {
        level: this.$data.level,
        projectStatus: this.$data.projectStatus,
        projectCategory: this.$data.projectCategory,
        projectName: this.$data.projectName,
        declarantName: this.$currentUser,
        type: "BB2",
      };
      for (const key in specialVo) {
        formData.append(key, specialVo[key]);
      }

      formData.append("teachers", JSON.stringify(this.$data.participants));
      formData.append("specialVo", specialVo);

      //以下需要修改接口
      this.$axios
        .post(`/special-workload/save/teacher`, formData, {
          headers: {
            "Content-Type": "multipart/form-datas",
          },
        })
        .then((res) => {
          if (res.data.response.code == 200) {
            alert("保存申报成功！");
          } else {
            alert("保存申报失败！");
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