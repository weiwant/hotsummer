<template>
  <div class="componentSubsection category">
    <div class="categoryTitle">出版教材BB6</div>

    <!-- 填报与添加区域 -->
    <div class="addNew">
      <tr>
        <td>书名</td>
        <td>
          <input
            type="text"
            placeholder="请输入出版书籍的书名"
            v-model="title"
          />
        </td>
      </tr>
      <tr>
        <td>ISBN</td>
        <td>
          <input
            type="text"
            placeholder="请输入教材的国际标准书号"
            v-model="isbn"
          />
        </td>
      </tr>
      <tr>
        <td style="width: 60px; vertical-align: middle">内容简介</td>
        <td>
          <textarea
            cols="30"
            rows="10"
            placeholder="请在此输入教材的内容简介"
            v-model="briefIntroduction"
          ></textarea>
        </td>
      </tr>
      <tr>
        <td>出版日期</td>
        <td>
          <input type="date" v-model="time" />
        </td>
      </tr>
      <tr>
        <td>教材版次</td>
        <td>
          <select v-model="edition">
            <option value="第一版">第一版</option>
            <option value="第二版">第二版</option>
            <option value="第三版">第三版</option>
            <option value="第四版">第四版</option>
            <option value="第五版">第五版</option>
            <option value="第六版">第六版</option>
            <option value="第七版">第七版</option>
            <option value="第八版">第八版</option>
            <option value="第九版">第九版</option>
            <option value="第十版">第十版</option>
          </select>
          <select v-model="number" style="margin-left: 10px">
            <option value="第一次印刷">第一次印刷</option>
            <option value="第二次印刷">第二次印刷</option>
            <option value="第三次印刷">第三次印刷</option>
            <option value="第四次印刷">第四次印刷</option>
            <option value="第五次印刷">第五次印刷</option>
            <option value="第六次印刷">第六次印刷</option>
            <option value="第七次印刷">第七次印刷</option>
            <option value="第八次印刷">第八次印刷</option>
            <option value="第九次印刷">第九次印刷</option>
            <option value="第十次印刷">第十次印刷</option>
          </select>
        </td>
      </tr>
      <tr>
        <td>所获荣誉</td>
        <td>
          <textarea
            cols="30"
            rows="10"
            placeholder="请在此填入你所获的荣誉"
            v-model="receivingHonor"
          ></textarea>
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
      title: "",
      isbn: "",
      edition: "",
      number: "",
      briefIntroduction: "",
      receivingHonor: "",

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
          achievementName: this.$data.title,
          isbn: this.$data.isbn,
          publicationsNumber: publicationsNumber,
          briefIntroduction: this.$data.briefIntroduction,
          receivingHonor: this.$data.receivingHonor,
          declarantName: this.$currentUser,
          type: "BB6",
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