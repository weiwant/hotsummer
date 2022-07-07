<template>
  <div class="componentSubsection category">
    <div class="categoryTitle saved" v-if="!committed">仅保存</div>
    <div class="categoryTitle commited" v-if="committed">已提交</div>

    <!-- 填报与添加区域 -->
    <div class="addNew">
      <tr>
        <td>书名</td>
        <td>
          <input
            type="text"
            placeholder="请输入出版书籍的书名"
            v-model="title"
            :disabled="!isEditing"
          />
        </td>
      </tr>

      <tr>
        <td>ISBN</td>
        <td>
          <input
            type="text"
            placeholder="请输入教材的国际标准书号"
            :disabled="!isEditing"
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
          <input type="date" v-model="time" :disabled="!isEditing" />
        </td>
      </tr>

      <tr>
        <td>教材版次</td>
        <td>
          <select v-model="edition" :disabled="!isEditing">
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
          <select
            v-model="number"
            :disabled="isEditing"
            style="margin-left: 10px"
          >
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
      title: "",
      isbn: "",
      edition: "",
      number: "",
      briefIntroduction: "",
      receivingHonor: "",
      participants: [],
      //封装文件信息
      uploadFile: [],
      isVisible: false,
      //后端返回文件列表
      fileNames: [],
      //前端更新本地文件列表
      thisFiles: [],
      //能否删除
      canDelete: false
    };
  },
  props: ["data"],
  created() {
    if ((this.data.status == "已提交")) {
      this.$data.canDelete = true;
      this.committed = true;
    } else {
      this.committed = false;
    }
    let numberString = this.data.publicationsNumber.substring(3, 8);
    let editionString = this.data.publicationsNumber.substring(0, 3);

    this.$data.title = this.data.achievementName;
    this.$data.isbn = this.data.isbn;
    this.$data.edition = editionString;
    this.$data.number = numberString;
    this.$data.receivingHonor = this.data.receivingHonor;
    this.$data.participants = this.data.somePeople;
  },
  mounted() {
    this.$refs.dynamic.changeState(); //默认没有disable，需要调整
    //文件列表是否可见
    if(this.$data.fileNames != ""){
      this.$data.isVisible = true;
    }else{
      this.$data.isVisible = false;
    }
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
      this.$data.isVisible = true;
      const inputFile = this.$refs.file.files[0];
      for(let i = 0; i++; i < this.$data.fileNames.length){
        if(this.$data.fileNames[i] == inputFile.name){
          alert("请勿上传同名文件！")
        }else{
      this.$data.uploadFile.push(inputFile);
      this.$data.fileNames.push(inputFile.name);
      this.$data.thisFiles.push(inputFile.name);
        }
      }
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
          achievementName: this.$data.title,
          isbn: this.$data.isbn,
          publicationsNumber: publicationsNumber,
          briefIntroduction: this.$data.briefIntroduction,
          receivingHonor: this.$data.receivingHonor,
          declarantName: this.$currentUser,
          type: "BB6",
          id: this.data.id,
          status: "已提交"
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
        .post('http://abkkds.vaiwan.com/special-workload/update/teacher', formData, {
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
    howDeleteFile(item){
      var hasUpload = true;
      for(let i = 0; i++; i <= this.$data.thisFiles.length){
        if(this.$data.thisFiles[i] == this.$data.fileNames[item]){  //前端列表中存在该文件，说明文件没有上传过，本地删除         
          hasUpload = false;
          this.$data.uploadFile.splice(item,1);
          this.$data.fileNames.splice(item,1);
          this.$data.thisFiles.splice(item,1);
        }
      }
      if(hasUpload){  //循环后发现文件不存在前端列表，说明文件在后端上传过，传入delete方法请求删除
        this.deleteFile(item);
      }
    },
    deleteFile(item){
      var _this = this;
      const formData = new FormData();

      var fileName = this.$data.fileNames[item];
      var id = this.data.id;

      formData.append("fileName", fileName);
      formData.append("id", id);

      this.$axios
      .post('http://abkkds.vaiwan.com/file/delete-file', formData)
      .then((res) => {
        if(res.data.response.code == 200){
          this.$data.fileNames.splice(item,1);
          alert("文件删除成功！");
          
        }else{
          alert("文件删除失败！")
        }
      })
      .catch(function(error){
        console.log(error);
      })
    }
  },
};
</script>


<style scoped>
</style>