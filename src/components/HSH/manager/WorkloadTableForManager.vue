<template>
  <div id="workloadDisplay">
    <!--component标题-->
    <div class="sectionTitle">教学工作量管理</div>
    <!--上传文件-->
    <div class="subtitle">信息导入</div>
    <div class="uploadSection">
      <div class="segment">
        <label for="year">学年:&nbsp;</label>
        <select id="year" v-model="uploadFileYearInfo1">
          <option v-for="index in 5" :key="index">
            {{ 2023 - index }}
          </option>
        </select>
        - {{ uploadFileYearInfo2 }}
      </div>
      <div class="segment">
        <label for="semester">学期:&nbsp;&nbsp;</label>
        <select id="semester" v-model="uploadFileSemesterInfo">
          <option>1</option>
          <option>2</option>
          <option>3</option>
        </select>
      </div>

      <el-input class="upload"
      type="file"
      ref="file"
      name="file"
      v-model="file"
      @click="e => {e.target.value = '';}"
      @change="getFileData"
      multiple="false"
      accept=".xls,.xlsx">&nbsp;上传工作量excel文件</el-input>

    </div>
    <!--查看已上传信息-->
    <div class="subtitle">信息查询</div>
    <!--学年和学期筛选-->
    <div class="tableFilter">
      <div class="segment">
        <label for="year">学年</label>
        <select v-model="currentAcademicYear" id="year">
          <option v-for="item in academicYears" :key="item.id" :value="item">
            {{ item }}
          </option>
        </select>
      </div>
      <div class="segment">
        <label for="semester">学期</label>
        <select v-model="currentSemester" id="semester">
          <option value="1">1</option>
          <option value="2">2</option>
          <option value="3">3</option>
        </select>
      </div>
      <button class="confirmYearAndSemesterBtn" @click="refreshData()">
        确&nbsp;认
      </button>
      <div class="segment">
        <select class="search" v-model="searchKeyword" id="">
          <option>上课老师</option>
          <option>计划学院</option>
        </select>
        <!-- 搜索框 -->
        <input
          class="search"
          type="search"
          v-model="searchValue"
          placeholder="查询内容"
          @keyup.enter="search()"
        />
        <button class="search searchBtn" @click="search()"></button>
      </div>
    </div>
    <!--统计数据栏-->
    <div class="statistics">
      <ul>
        <li>
          <div class="name">总课程数</div>
          <div class="value">{{ totalClassCount }}</div>
        </li>
        <li>
          <div class="name">XXX</div>
          <div class="value">yyyy</div>
        </li>
      </ul>
    </div>
    <!--工具栏-->
    <div class="toolBar">
      <button class="download"> 导出Excel至本地</button>
    </div>
    <!--数据列表-->
    <div class="workloadTableWrapper">
      <table class="workloadDataTable">
        <thead>
          <tr>
            <th v-for="item in workloadTableHeader" :key="item.id">
              {{ item }}
            </th>
          </tr>
        </thead>
        <tbody>
          <tr
            class="tableDataLine"
            v-for="item in workloadDataToBeDisplayed"
            :key="item.id"
          >
            <td v-for="value in item" :key="value.id">{{ value }}</td>
          </tr>
        </tbody>
      </table>
    </div>
  </div>
</template>

<script>
import axios from "axios";
export default {
  name: "TeacherWorkloadTable",
  data() {
    return {
      uploadFileYearInfo1: "2021",
      uploadFileSemesterInfo: 1,
      academicYears: [],
      currentAcademicYear: "2019-2020", //学年
      currentSemester: 2, //学期
      searchFilterRequired: false,
      searchKeyword: "上课老师",
      searchValue: "",
      workloadTableHeader: [],
      workloadTableData: [],
      file: ''
    };
  },
  computed: {
    //统计数据
    totalClassCount() {
      return this.workloadDataToBeDisplayed.length; //总课程数
    },
    uploadFileYearInfo2() {
      return Number(this.uploadFileYearInfo1) + 1;
    },
    //列表数据
    workloadDataToBeDisplayed() {
      console.log("computing");
      if (!this.searchFilterRequired) return this.workloadTableData;
      else {
        switch (this.searchKeyword) {
          case "上课老师":
            return this.workloadTableData.filter(
              (item) => item.teacher == `${this.searchValue}`
            );
          case "计划学院":
            return this.workloadTableData.filter(
              (item) => item.planFaculty == `${this.searchValue}`
            );
        }
      }
    },
  },
  methods: {
    //信息筛选
    //学年学期筛选
    refreshData() {
      this.getTableData(this.currentAcademicYear, this.currentSemester);
    },
    getTableData(year, semester) {
      console.log(year);
      console.log(semester);
      axios({
        method: "get",
        url: `http://localhost:3000/teacherWorkload`,
      }).then((res) => {
        this.workloadTableHeader = res.data.workloadTableHeader;
        this.workloadTableData = res.data.workloadTableData;
      });
    },
    //搜索
    search() {
      console.log(this.searchKeyword);
      console.log(this.searchValue);
      if (this.searchValue == "") this.searchFilterRequired = false;
      else {
        this.searchFilterRequired = true;
      }
    },

    //点击触发上传方法
    uploadMaterial(){
      this.$refs.file.dispatchEvent(new MouseEvent('click'));
    },

    //触发选择文件，判断文件类型
    getFileData(file){
      const inputFile = this.$refs.file;
      let filename = this.$data.file;
      const isExcel = filename.substring(filename.lastIndexOf('.')+ 1);
      if(isExcel != "xls" && isExcel != "xlsx"){
        alert("文件格式不对，请选择.xls或.xlsx文件！");
      }else{
        this.uploadFile(inputFile);
      }
    },

    //上传文件，学年，学期
    uploadFile(file){
      const formData = new FormData();
      var _this = this;
      _this.year = this.$data.uploadFileYearInfo1;
      _this.semester = this.$data.uploadFileSemesterInfo;
      formData.append("year", _this.year);
      formData.append("semester", _this.semester);
      formData.append("file", file);
      // console.log(formData.get("year"));
      // console.log(formData.get("semester"));
      // console.log(formData.get("file"));

      输入后端url
      axios.post('', {
        formData
      })
      .then(function(response){
        if(response.data.data){
          alert("报表文件上传成功！");
        }else{
          alert("上传失败！");
          console.log(response);
        }
      })
      .catch(function(error){
        console.log(error);
      })
    }

  },
  created() {
    //向后台获取学年列表
    axios
      .get("http://abcs.vaiwan.com/resource/tableinsemester", {
        params: {
          year: this.currentAcademicYear,
          semester: this.currentSemester,
        },
      })
      .then(function (res) {
        console.log(res);
      })
      .catch((resp) => {
        console.log("请求失败：" + resp);
      });
    // axios({
    //   method: "get",
    //   url: `http://10.128.141.58:8080/academicYearList`,
    // }).then((res) => {
    //   this.academicYears = res.data;
    //   // console.log(res.data[0]);
    //   //设置默认学年学期
    //   this.currentAcademicYear = res.data[0];
    //   this.currentSemester = 1;
    //   //调用getTableData()方法获取数据
    //   this.getTableData(this.currentAcademicYear, this.currentSemester);
    // });
  },
};
</script>

<style scoped>
#workloadDisplay {
  float: left;
  overflow-y: auto;
  width: 85vw;
  height: 100vh;
  padding: 10px 15px;
  background-color: rgb(239, 241, 247);
}
#workloadDisplay::-webkit-scrollbar {
  display: none;
}
#workloadDisplay {
  -ms-overflow-style: none;
  scrollbar-width: none;
}

.sectionTitle {
  text-align: left;
  font-size: 27px;
  font-weight: 600;
  color: rgb(29, 74, 51);
  height: 50px;
  line-height: 50px;
  margin-bottom: 3vh;
}
.subtitle {
  margin-top: 3vh;
  margin-bottom: 2vh;
  width: 40%;
  border-bottom: 1px solid rgba(128, 128, 128, 0.3);
  text-align: left;
  font-size: 20px;
  font-weight: 600;
}

/* 上传文件板块 */
.uploadSection {
  margin-bottom: 5px;
  padding-right: 15px;
  padding: 5px 15px;
  background-color: #fff;
  border-radius: 5px;
  text-align: left;
}
.uploadSection .segment {
  display: inline-block;
  margin-right: 5%;
}
.uploadSection .segment {
  font-size: 14px;
  font-weight: 500;
}
.uploadSection button.upload {
  display: block;
  margin-top: 2%;
  padding: 5px;
  border: 1px solid rgba(128, 128, 128, 0.452);
  border-radius: 5px;
  background-color: rgb(255, 254, 254);
  font-family: "icomoon";
  color: rgb(51, 114, 96);
  font-weight: 500;
}

.uploadSection button.upload:hover {
  background-color: rgba(209, 207, 207, 0.479);
}

/* 筛选栏 */
.tableFilter {
  font-size: 14px;
  margin-bottom: 5px;
  text-align: left;
}
.tableFilter .segment {
  display: inline-block;
  margin-right: 10px;
  padding: 8px;
  border-radius: 5px;
}
.tableFilter select {
  height: 20px;
}
.tableFilter label {
  margin-right: 3px;
}
.tableFilter .confirmYearAndSemesterBtn {
  /* margin-left: 10px; */
  margin-right: 10%;
  width: 50px;
  height: 25px;
  border: 1px solid rgba(128, 128, 128, 0.555);
  border-radius: 5px;
  color: #fff;
  font-weight: 550;
  background-color: rgba(41, 34, 106);
}
.tableFilter .confirmYearAndSemesterBtn:hover {
  background-color: rgba(41, 34, 106, 0.815);
}
/* .tableFilter .searchSegment {
  margin-right: 10px;
  padding: 8px;
  border-radius: 5px;
} */
.tableFilter .search {
  height: 30px;
  border: 0px;
  background-color: #fff;
}
.tableFilter select.search {
  vertical-align: bottom;
  border-top-left-radius: 5px;
  border-bottom-left-radius: 5px;
}
.tableFilter input.search {
  padding-left: 10px;
}
.tableFilter button.searchBtn {
  width: 35px;
  vertical-align: bottom;
  border-top-right-radius: 5px;
  border-bottom-right-radius: 5px;
  font-family: "icomoon";
  color: #fff;
  background-color: rgba(41, 34, 106);
}
.tableFilter button.searchBtn:hover {
  background-color: rgba(41, 34, 106, 0.815);
}
/* 统计数据 */
.statistics {
  background-color: #fff;
  border-radius: 5px;
  margin-bottom: 5px;
}
.statistics ul {
  padding: 5px 15px;
  text-align: left;
  padding: 10px;
}
.statistics li {
  display: inline-block;
  padding: 5px 30px;
  text-align: center;
  border-right: 1px solid rgba(128, 128, 128, 0.212);
}
.statistics li .name {
  font-size: 10px;
}
.statistics li .value {
  font-size: 20px;
  font-weight: 500;
}
/*工具栏*/
.toolBar {
  margin-bottom: 5px;
  padding-right: 15px;
  padding: 5px 15px;
  background-color: #fff;
  border-radius: 5px;
  text-align: right;
}
.toolBar button.download {
  padding: 5px;
  border: 1px solid rgba(128, 128, 128, 0.452);
  border-radius: 5px;
  background-color: rgb(255, 254, 254);
  font-family: "icomoon";
  color: rgb(51, 114, 96);
  font-weight: 500;
}
.toolBar button.download:hover {
  background-color: rgba(209, 207, 207, 0.479);
}
/* 数据列表 */
.workloadTableWrapper {
  position: relative;
  overflow: scroll;
  height: 60vh;
}
table.workloadDataTable {
  width: 1300px;
}
.workloadDataTable thead {
  position: sticky;
  top: 0;
  font-size: 10px;
  font-weight: 500;
  background-color: rgb(239, 241, 247);
}
.workloadDataTable tbody {
  background-color: #fff;
  padding: 3px 5px;
}
@media screen and (max-width: 1000px) and (min-width: 600px) {
  #workloadDisplay {
    width: 77vw;
  }
}
@media screen and (max-width: 600px) {
  #workloadDisplay {
    width: 100vw;
  }
}
</style>