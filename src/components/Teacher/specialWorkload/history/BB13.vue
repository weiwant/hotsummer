<template>
  <div class="componentSubsection category">
    <div class="categoryTitle saved" v-if="!committed">仅保存</div>
    <div class="categoryTitle commited" v-if="committed">已提交</div>
    <!-- 填报与添加区域 -->
    <div class="addNew">
      <tr>
        <td>类别</td>
        <td>
          <select v-model="category" :disabled="!isEditing">
            <option value="卓越班选拔面试">卓越班选拔面试</option>
            <option value="校园开放日咨询">校园开放日咨询</option>
            <option value="本科生分专业宣讲">本科生分专业宣讲</option>
            <option value="转专业面试">转专业面试</option>
            <option value="本科生招生咨询">本科生招生咨询</option>
            <option value="各类评审">各类评审</option>
            <option value="其他">其他</option>
          </select>
        </td>
      </tr>

      <tr>
        <td>时间</td>
        <td>
          <input
            type="date"
            placeholder="请选择时间"
            v-model="time"
            :disabled="!isEditing"
          />
        </td>
      </tr>

      <tr>
        <td>姓名</td>
        <td>
          <input
            type="text"
            placeholder="请输入姓名"
            v-model="name"
            :disabled="!isEditing"
          />
        </td>
      </tr>

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
export default {
  data() {
    return {
      //编辑状态
      isEditing: false,
      //提交状态
      committed: true,
      category: "",
      time: "",
      name: "",
      participants: [],
    };
  },
  props: ["data"],
  created() {
    if ((this.data.status == "已提交")) {
      this.committed = true;
    } else {
      this.committed = false;
    }
    this.$data.category = this.data.projectCategory;
    this.$data.time = this.data.awardDate;
    this.$data.name = this.data.declarantName;
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
      // console.log(this.participants);
      var _this = this;
      const formData = new FormData();
      // console.log("响应");

      var specialVo = {
          projectCategory: this.$data.category,
          awardDate: this.$data.time,
          declarantName: this.$currentUser,
          type: "BB13",
          id: this.data.id,
          status: "已提交"
        }
      for (const key in specialVo) {
        formData.append(key,specialVo[key]);
      }

      formData.append("teachers", JSON.stringify(this.$data.participants));
      formData.append("specialVo",specialVo);

      //以下需要修改接口
      this.$axios
        .post('http://abkkds.vaiwan.com/special-workload/update/teacher', formData, {
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
    // 保存
    save() {
      this.$refs.dynamic.changeState();
      this.isEditing = false;
      //点击保存，调用DynamicCollection组件的方法，将其中含有的数据同步至本组件内
      this.$refs.dynamic.transmitData();
      // console.log(this.participants);
      var _this = this;
      const formData = new FormData();
      // console.log("响应");

      var specialVo = {
          projectCategory: this.$data.category,
          awardDate: this.$data.time,
          declarantName: this.$currentUser,
          type: "BB13",
          id: this.data.id,
        }
      for (const key in specialVo) {
        formData.append(key,specialVo[key]);
      }

      formData.append("teachers", JSON.stringify(this.$data.participants));
      formData.append("specialVo",specialVo);

      //以下需要修改接口
      this.$axios
        .post('http://abkkds.vaiwan.com/special-workload/update/teacher', formData, {
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