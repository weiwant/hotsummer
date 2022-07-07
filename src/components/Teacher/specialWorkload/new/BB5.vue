<template>
  <div class="componentSubsection category">
    <div class="categoryTitle">发表教学研究论文BB</div>

    <!-- 填报与添加区域 -->
    <div class="addNew">
      <tr>
        <td>级别</td>
        <td>
          <input type="radio" id="authority" value="权威" v-model="level" />
          <label for="authority">权威</label>
          <input type="radio" id="core" value="核心" v-model="level" />
          <label for="core">核心</label>
          <input type="radio" id="noncore" value="非核心" v-model="level" />
          <label for="noncore">非核心</label>
        </td>
      </tr>
      <tr>
        <td style="width: 80px">文章名称</td>
        <td>
          <input
            type="text"
            placeholder="请输入文章名称"
            v-model="articlename"
          />
        </td>
      </tr>
      <tr>
        <td>刊物名称</td>
        <td>
          <input
            type="text"
            placeholder="请输入刊物名称"
            v-model="publicationname"
          />
        </td>
      </tr>
      <tr>
        <td>刊物期数</td>
        <td>
          <input type="month" placeholder="请选择出版月份" v-model="month" />
          <input
            type="number"
            min="1"
            v-model="stage"
            style="margin-left: 10px"
          />&nbsp;期
        </td>
      </tr>
      <tr>
        <td style="vertical-align: middle">证明文件</td>
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
      level: "",
      articlename: "",
      publicationname: "",
      month: "",
      stage: "",
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
      var _this = this;
      this.isVisible = true;
      const inputFile = this.$refs.file.files[0];
      this.$data.uploadFile.push(inputFile);
      this.$data.fileNames.push(inputFile.name);
    },
    //保存
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
          level: this.$data.level,
          achievementName: this.$data.articlename,
          publicationName: this.$data.publicationname,
          awardDate: this.$data.month,
          publicationsNumber: this.$data.stage,
          declarantName: this.$currentUser,
          type: "BB5",
          id: this.data.id,
        }
      for (const key in specialVo) {
        formData.append(key,specialVo[key]);
      }

      formData.append("teachers", JSON.stringify(this.$data.participants));
      formData.append("specialVo",specialVo);

      for (let i = 0; i < this.$data.uploadFile.length; i++) {
        formData.append("files", this.$data.uploadFile[i]);
      }
      //以下需要修改接口
      this.$axios
        .post('http://abkkds.vaiwan.com/special-workload/save/teacher', formData, {
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