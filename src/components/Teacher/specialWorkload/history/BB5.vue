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
            id="authority"
            value="权威"
            v-model="level"
            :disabled="!isEditing"
          />
          <label for="authority">权威</label>

          <input
            type="radio"
            id="core"
            value="核心"
            v-model="level"
            :disabled="!isEditing"
          />
          <label for="core">核心</label>

          <input
            type="radio"
            id="noncore"
            value="非核心"
            v-model="level"
            :disabled="!isEditing"
          />
          <label for="noncore">非核心</label>
        </td>
      </tr>

      <tr>
        <td style="width: 80px">文章名称</td>
        <td>
          <input
            type="text"
            placeholder="请输入文章名称"
            v-model="articleName"
            :disabled="!isEditing"
          />
        </td>
      </tr>

      <tr>
        <td>刊物名称</td>
        <td>
          <input
            type="text"
            placeholder="请输入刊物名称"
            v-model="publicationName"
            :disabled="!isEditing"
          />
        </td>
      </tr>
      <tr>
        <td>刊物期数</td>
        <td>
          <input
            type="month"
            placeholder="请选择出版月份"
            v-model="month"
            :disabled="!isEditing"
          />

          <input
            type="number"
            min="1"
            v-model="stage"
            style="margin-left: 10px"
            :disabled="!isEditing"
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
            :disabled="!isEditing"
          />
        </td>
      </tr>
      <label v-show="isVisible" style="font-size: 12px">已上传文件:</label>
      <div
        v-show="isVisible"
        v-for="(fileName, item) in fileNames"
        :key="item"
        style="font-size: 10px"
      >
        <label>{{ fileName }}</label>
        <button
          @click="deleteFile(item)"
          :disabled="!isEditing"
          style="
            margin-left: 5px;
            border: 0;
            font-family: 'icomoon';
            color: gray;
            vertical-align: text-bottom;
          "
        >
          
        </button>
      </div>

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
      commited: true,
      level: "",
      articleName: "",
      publicationName: "",
      month: "",
      stage: "",
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
  props: ["data"],
  created() {
    console.log(this.data);
    if (this.data.status == "已提交") {
      this.$data.canDelete = true;
      this.committed = true;
    } else {
      this.committed = false;
    }
    this.$data.level = this.data.level;
    this.$data.articleName = this.data.achievementName;
    this.$data.publicationName = this.data.publicationName;
    this.$data.month = this.data.awardDate;
    this.$data.stage = this.data.publicationsNumber;
    this.$data.participants = this.data.somePeople;
    this.$data.fileNames = this.data.fileName;
    if (this.fileNames == "") {
      this.isVisible = false;
    } else {
      this.isVisible = true;
    }
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
      var flag = true;
      for (let i = 0; i++; i < this.$data.fileNames.length) {
        if (this.$data.fileNames[i] == inputFile.name) {
          alert("请勿上传同名文件！");
          flag = false;
        } else {
          flag = true;
        }
      }
      if (flag) {
        this.$data.uploadFile.push(inputFile);
        this.$data.fileNames.push(inputFile.name);
        this.$data.thisFiles.push(inputFile.name);
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
      const formData = new FormData();

      var specialVo = {
        level: this.$data.level,
        achievementName: this.$data.articlename,
        publicationName: this.$data.publicationname,
        awardDate: this.$data.month,
        publicationsNumber: this.$data.stage,
        declarantName: this.$currentUser,
        type: "BB5",
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

      var specialVo = {
        level: this.$data.level,
        achievementName: this.$data.articlename,
        publicationName: this.$data.publicationname,
        awardDate: this.$data.month,
        publicationsNumber: this.$data.stage,
        declarantName: this.$currentUser,
        type: "BB5",
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
};
</script>


<style scoped>
</style>