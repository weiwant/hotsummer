<template>
  <div class="componentSubsection category">
    <div class="categoryTitle">课程建设BB1</div>
    <!-- 填报与添加区域 -->
    <div class="addNew">
      <!-- 立项时间 -->
      <tr>
        <td>立项时间</td>
        <td><input type="month" /></td>
      </tr>
      <!--级别 -->
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

      <!-- 项目名称 -->
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

      <!-- 课程类别 -->
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
      <!-- 进展情况 -->
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

      <!-- 附件 -->
      <tr>
        <td>上传附件</td>
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
      <div v-show="isVisible"
      v-for="(fileName, item) in fileNames" :key="item">
      <label>{{fileName}}</label>
      <button @click="deleteFile(item)">删除</button>
      </div>
      <!-- 动态增删填报项组件 -->
      <DynamicCollection
        ref="dynamic"
        @transmit="updateParticipants"
      ></DynamicCollection>
      <button class="universalBlueBtn save" @click="save">保&nbsp;存</button>
    </div>
  </div>
</template>

<script>
import DynamicCollection from "../sharing/DynamicCollection.vue";
export default {
  components: { DynamicCollection, History },
  data() {
    return {
      //填报数据
      awardLevel: "",
      projectStatus: "",
      projectCategory: "",
      projectName: "",
      awardLevel: "",
      participants: [],
      //封装文件信息
      uploadFile: [],
      //文件名
      fileNames: [],
      isVisible: false
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
      //console.log(inputFile.name);
      this.$data.fileNames.push(inputFile.name);
    },
    /*保存上报数据*/
    save() {
      //点击保存，调用DynamicCollection组件的方法，将其中含有的数据同步至本组件内
      this.$refs.dynamic.transmitData();
      console.log(this.participants);
      var _this = this;
      const formData = new FormData();
      //console.log("响应");

      var data = JSON.stringify([
        {
          awardLevel: this.$data.awardLevel,
          projectStatus: this.$data.projectStatus,
          projectCategory: this.$data.projectCategory,
          projectName: this.$data.projectName,
        },
      ]);

      formData.append("data", data);

      for (let i = 0; i < this.$data.uploadFile.length; i++) {
        formData.append("files", this.$data.uploadFile[i]);
      }
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
    deleteFile(item){
      var _this = this;
      // console.log(item);
      this.$data.uploadFile.splice(item,1);
      // console.log(this.$data.uploadFile);
      this.$data.fileNames.splice(item,1);
      // console.log(this.$data.fileNames);
      if(this.$data.uploadFile == ""){
        this.$data.isVisible = false;
      }else{
        this.$data.isVisible = true;
      }
    },
  },
  created() {},
};
</script>
<style scoped>
</style>
