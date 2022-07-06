<template>
  <div class="componentSubsection category">
    <div class="categoryTitle saved" v-if="!committed">仅保存</div>
    <div class="categoryTitle commited" v-if="committed">已提交</div>

    <!-- 填报与添加区域 -->
    <div class="addNew">
      <tr>
        <td>级别</td>
        <td>
          <input type="radio" 
          id="nation"
           value="国家级" 
           v-model="awardLevel"
           :disabled="!isEditing" />
          <label for="nation">国家级</label>

          <input type="radio"
           id="province" 
           value="省级" 
           v-model="awardLevel" 
           :disabled="!isEditing "
           />
          <label for="province">省级</label>

          <input type="radio" 
          id="school" 
          value="校级" 
          v-model="awardLevel" 
          :disabled="!isEditing"/>
          <label for="school">校级</label>
        </td>
      </tr>

      <tr>
        <td>竞赛名称</td>
        <td>
          <input
            type="text"
            placeholder="请输入比赛的名称"
            v-model="competitionname"
            :disabled="!isEditing"
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
            :disabled="!isEditing"
          />
        </td>
      </tr>

      <tr>
        <td>获奖等级</td>
        <td>
          <input type="text" 
          placeholder="请输入获奖等级"
           v-model="level"
           :disabled="isEditing" />
        </td>
      </tr>

      <tr>
        <td>授奖单位</td>
        <td>
          <input
            type="text"
            placeholder="请输入授奖单位"
            v-model="awardingunit"
            :disabled="isEditing"
          />
        </td>
      </tr>

      <tr>
        <td>获奖时间</td>
        <td>
          <input type="date"
           placeholder="请选择获奖时间" 
           v-model="time" 
           :disabled="!isEditing"/>
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
            :disabled="!isEditing"
          />
        </td>
      </tr>

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
      //填报数据
      awardLevel: "",
      competitionname: "",
      awardCategory: "",
      level: "",
      awardingUnit: "",
      time: "",
      participants: [],
      //文件列表
      uploadFile: [],
    };
  },
  props: ["data"],
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
      const inputFile = this.$refs.file.files[0];
      this.$data.uploadFile.push(inputFile);
    },
    // 编辑
    edit() {
      this.isEditing = true;
    },
    // 提交
    commit() {
      this.isEditing = false;
      this.$refs.dynamic.transmitData();
    },
    // 保存
    save() {
      this.isEditing = false;
      //点击保存，调用DynamicCollection组件的方法，将其中含有的数据同步至本组件内
      this.$refs.dynamic.transmitData();
      if(this.$data.awardLevel==""||this.$data.  competitionname==""||this.$data.awardCategory==""||this.$data.level==""||this.$data.awardingUnit==""||this.$data.time==""||this.$data.participants==""||this.$data.uploadFile==""){
        alert("数据填报不可为空！！！")
        return;
      }
      var _this = this;
      const formData = new FormData();

      var data = JSON.stringify([
        {
          awardLevel: this.$data.awardLevel,
          competitionname: this.$data.competitionname,
          awardCategory: this.$data.awardCategory,
          level: this.$data.level,
          awardingunit: this.$data.awardingunit,
          time: this.$data.time,
        },
      ]);

      formData.append("data", data);

      for (let i = 0; i < this.$data.uploadFile.length; i++) {
        formData.append("files", this.$data.uploadFile[i]);
      }

      console.log(formData.get("data"));
      console.log(formData.get("files"));

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
  },
  created() {
    this.$refs.dynamic.changeState(); //默认没有disable，需要调整
  },
};
</script>


<style scoped>
</style>