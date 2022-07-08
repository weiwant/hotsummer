<template>
  <div class="componentSubsection category">
    <div class="categoryTitle saved" v-if="!committed">仅保存</div>
    <div class="categoryTitle commited" v-if="committed">已提交</div>
    <!-- 填报与添加区域 -->
    <div class="addNew">
      <!-- 立项时间 -->
      <tr>
        <td>立项时间</td>
        <td><input type="month" v-model="awardDate" /></td>
      </tr>
      <!--级别 -->
      <tr>
        <td>级别</td>
        <td>
          <input
            type="radio"
            id="nation"
            value="国家级"
            :selected="awardLevel == '国家级'"
            v-model="awardLevel"
          />
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
            disabled="!isEditing"
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
      <div v-show="isVisible" v-for="(fileName, item) in fileNames" :key="item">
        <label>{{ fileName }}</label>
        <button @click="howDeleteFile(item)" disabled="canDelete">删除</button>
      </div>

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
  props: ["data"],
  data() {
    return {
      //编辑状态
      isEditing: false,
      //提交状态
      committed: false,
      //填报数据
      awardDate: "",
      projectStatus: "",
      projectCategory: "",
      projectName: "",
      awardLevel: "",
      participants: [],
      //封装文件信息
      uploadFile: [],
      isVisible: false,
      //后端返回文件列表
      fileNames: [],
      //前端更新本地文件列表
      thisFiles: [],
      //能否删除
      canDelete: false,
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
      this.$data.isVisible = true;
      const inputFile = this.$refs.file.files[0];
      for (let i = 0; i++; i < this.$data.fileNames.length) {
        if (this.$data.fileNames[i] == inputFile.name) {
          alert("请勿上传同名文件！");
        } else {
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
        awardLevel: this.$data.awardLevel,
        projectStatus: this.$data.projectStatus,
        projectCategory: this.$data.projectCategory,
        projectName: this.$data.projectName,
        awardDate: this.$data.awardDate,
        declarantName: this.$currentUser,
        type: "BB1",
        id: this.data.id,
        status: "已提交",
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
        .post(`${this.$domainName}/special-workload/update/teacher`, formData, {
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
      this.$refs.dynamic.transmitData();
      const formData = new FormData();

      var specialVo = {
        awardLevel: this.$data.awardLevel,
        projectStatus: this.$data.projectStatus,
        projectCategory: this.$data.projectCategory,
        projectName: this.$data.projectName,
        awardDate: this.$data.awardDate,
        declarantName: this.$currentUser,
        type: "BB1",
        id: this.data.id,
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
        .post(`${this.$domainName}/special-workload/update/teacher`, formData, {
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
    howDeleteFile(item) {
      var hasUpload = true;
      for (let i = 0; i++; i <= this.$data.thisFiles.length) {
        if (this.$data.thisFiles[i] == this.$data.fileNames[item]) {
          //前端列表中存在该文件，说明文件没有上传过，本地删除
          hasUpload = false;
          this.$data.uploadFile.splice(item, 1);
          this.$data.fileNames.splice(item, 1);
          this.$data.thisFiles.splice(item, 1);
        }
      }
      if (hasUpload) {
        //循环后发现文件不存在前端列表，说明文件在后端上传过，传入delete方法请求删除
        this.deleteFile(item);
      }
    },
    deleteFile(item) {
      const formData = new FormData();

      var fileName = this.$data.fileNames[item];
      var id = this.data.id;

      formData.append("fileName", fileName);
      formData.append("id", id);

      this.$axios
        .post(`${this.$domainName}/file/delete-file`, formData)
        .then((res) => {
          if (res.data.response.code == 200) {
            this.$data.fileNames.splice(item, 1);
            alert("文件删除成功！");
          } else {
            alert("文件删除失败！");
          }
        })
        .catch(function (error) {
          console.log(error);
        });
    },
  },
  created() {
    //本组件的mounted会在dynamic组件的created之后执行，穿不了数据，尝试提前至created处执行
    if (this.data.status == "已提交") {
      this.$data.canDelete = true;
      this.committed = true;
    } else {
      this.committed = false;
    }
    this.$data.awardLevel = this.data.awardLevel;
    this.$data.projectName = this.data.projectName;
    this.$data.projectCategory = this.data.projectCategory;
    this.$data.projectStatus = this.data.projectStatus;
    this.$data.awardDate = this.data.awardDate;
    this.$data.participants = this.data.somePeople;
    this.$data.fileNames = this.data.fileName;
  },
  mounted() {
    this.$refs.dynamic.changeState(); //默认没有disable，需要调整
    //文件列表是否可见
    if (this.$data.fileNames != "") {
      this.$data.isVisible = true;
    } else {
      this.$data.isVisible = false;
    }
  },
};
</script>
<style scoped>
</style>
