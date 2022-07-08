<template>
  <div class="componentSubsection category">
    <div class="categoryTitle">教学成果奖BB3</div>

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
        <td>奖项名称</td>
        <td>
          <input
            type="text"
            placeholder="请输入所获奖项目的名称"
            v-model="awardname"
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
            v-model="Awardingunit"
          />
        </td>
      </tr>
      <tr>
        <td>获奖时间</td>
        <td>
          <input type="date" placeholder="请输入获奖时间" v-model="time" />
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
            multiple="true"
          />
        </td>
      </tr>
      <label v-show="isVisible">已上传文件:</label>
      <div v-show="isVisible" v-for="(fileName, item) in fileNames" :key="item">
        <label>{{ fileName }}</label>
        <button @click="deleteFile(item)">删除</button>
      </div>

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
      awardLevel: "",
      awardname: "",
      awardCategory: "",
      level: "",
      Awardingunit: "",
      time: "",
      participants: [],
      //文件列表
      uploadFile: [],
      //文件名
      fileNames: [],
      isVisible: false,
    };
  },
  methods: {
    updateParticipants(participants) {
      this.participants = participants;
    },
    //点击触发上传方法
    uploadMaterial() {
      this.$refs.file.dispatchEvent(new MouseEvent("click"));
    },
    //添加文件数据
    getFileData(file) {
      this.isVisible = true;
      const inputFile = this.$refs.file.files[0];
      this.$data.uploadFile.push(inputFile);
      this.$data.fileNames.push(inputFile.name);
    },
    save() {
      this.isEditing = false;
      //点击保存，调用DynamicCollection组件的方法，将其中含有的数据同步至本组件内
      this.$refs.dynamic.transmitData();
      const formData = new FormData();

      var specialVo = {
        awardLevel: this.$data.awardLevel,
        projectName: this.$data.awardname,
        awardCategory: this.$data.awardCategory,
        level: this.$data.level,
        awardApartment: this.$data.Awardingunit,
        time: this.$data.awardDate,
        declarantName: this.$currentUser,
        type: "BB3",
      };
      for (const key in specialVo) {
        formData.append(key, specialVo[key]);
      }

      formData.append("teachers", JSON.stringify(this.$data.participants));
      formData.append("specialVo", specialVo);

      for (let i = 0; i < this.$data.uploadFile.length; i++) {
        formData.append("files", this.$data.uploadFile[i]);
      }
      //以下需要修改接口
      this.$axios
        .post(`${this.$domainName}/special-workload/save/teacher`, formData, {
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
    deleteFile(item) {
      var _this = this;
      // console.log(item);
      this.$data.uploadFile.splice(item, 1);
      // console.log(this.$data.uploadFile);
      this.$data.fileNames.splice(item, 1);
      // console.log(this.$data.fileNames);
      if (this.$data.uploadFile == "") {
        this.$data.isVisible = false;
      } else {
        this.$data.isVisible = true;
      }
    },
  },
  created() {},
};
</script>


<style scoped>
</style>