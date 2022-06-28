<template>
  <div id="workloadDisplay">
    <div class="sectionTitle">教学工作量管理</div>
    <!--上传文件-->
    <div class="subtitle">信息导入</div>
    <div class="uploadSection">
      <div class="segment">
        <label for="year">学年:&nbsp;</label>
        <select id="year" v-model="uploadFileYearInfo1">
          <option v-for="index in 5" :key="index">
            {{ currentYear - index + 1 }}
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
      <el-input
        type="file"
        ref="file"
        name="file"
        v-model="file"
        @change="getFileData"
        multiple="false"
        accept=".xls,.xlsx"
      ></el-input>
    </div>
    <!--学年和学期选择-->
    <div class="subtitle">信息查询</div>
    <div class="tableFilter">
      <div class="segment">
        <label for="year">学年</label>
        <select v-model="currentAcademicYear" id="year">
          <option
            v-for="item in academicYearRange"
            :key="item.id"
            :value="item"
          >
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
      <button
        class="confirmYearAndSemesterBtn"
        @click="confirmYearAndSemester()"
      >
        确&nbsp;认
      </button>
      <div class="segment">
        <select class="search" v-model="searchKeyword" id="">
          <option>上课老师</option>
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
    <!--查询结果的统计数据栏-->
    <div class="statistics">
      <ul>
        <li>
          <div class="name">查询结果数</div>
          <div class="value">{{ totalItems }}</div>
        </li>
        <li>
          <div class="name">总页数</div>
          <div class="value">{{ allPageCount }}</div>
        </li>
      </ul>
    </div>
    <!--下载文件工具栏-->
    <div class="toolBar">
      <label class="customFileName" for="exportFileName">文件名：</label>
      <input type="text" v-model="exportFileName" id="exportFileName" />
      <span class="defaultExportfileName"
        >（默认：{{ this.currentAcademicYear }}学年_{{
          this.currentSemester
        }}学期工作量）</span
      >
      <button
        class="download"
        @click="exportFile()"
        :disabled="!dataExists"
        :class="{ disabled: !dataExists }"
      >
         导出Excel至本地
      </button>
    </div>
    <!--数据列表-->
    <div class="workloadTableWrapper">
      <div class="noDataHint" v-if="!dataExists" v-html="noDataHint"></div>
      <table class="workloadDataTable">
        <thead v-if="dataExists">
          <tr>
            <th v-for="(item, index) in workloadTableHeader" :key="index">
              {{ item }}
            </th>
          </tr>
        </thead>
        <tbody>
          <tr
            class="tableDataLine"
            v-for="item in workloadTableData"
            :key="item.id"
          >
            <td v-for="(value, index) in item" :key="index">{{ value }}</td>
          </tr>
        </tbody>
      </table>
    </div>
    <div class="pagination">
      <button @click="pageBefore()" :disabled="noLessPage">&lt;</button>
      第
      <input v-model="currentPage" type="text" @keyup.enter="choosePage()" />
      页
      <button @click="pageAfter()" :disabled="noMorePage">&gt;</button>
    </div>
  </div>
</template>

<script>
import axios from "axios";
export default {
  name: "TeacherWorkloadTable",
  data() {
    return {
      //文件上传
      currentYear: 0, //当前年份
      uploadFileYearInfo1: "",
      uploadFileSemesterInfo: 1,
      file: "",
      //某学年学期的数据
      academicYearRange: [],
      currentAcademicYear: "", //学年
      currentSemester: 1, //学期
      workloadTableHeader: [
        "学年",
        "辅助",
        "计算用学时",
        "课程性质解释",
        "计算机用时",
        "课程名称",
        "课程性质",
        "课程号",
        "学分",
        "折扣",
        "实验安排",
        "实验课时",
        "教分",
        "合课单位",
        "实验室核对结果",
        "上课教师名字",
        "教师职称",
        "专业",
        "折扣前BA1系数",
        "原始教分",
        "其他教师名",
        "计划学院",
        "实践课时",
        "备注",
        "学期",
        "是否为特殊班级",
        "是否全英教学",
        "上课人数",
        "年级",
        "教学班",
        "BA1系数",
        "开课学院",
        "理论课时",
      ],
      workloadTableData: [],
      dataExists: false, //“暂无数据”提示的显示
      noDataHint: "",
      //条件筛查
      searchFilterRequired: false,
      searchKeyword: "上课老师",
      searchValue: "",
      //文件导出
      dataTobeExported: [],
      exportFileName: "",
      //pagination
      noLessPage: true,
      noMorePage: false,
      currentPage: 1,
      allPageCount: 0,
      totalItems: 0,
    };
  },
  computed: {
    //统计数据
    uploadFileYearInfo2() {
      return Number(this.uploadFileYearInfo1) + 1;
    },
  },
  methods: {
    //获取某学年学期数据
    getTableData() {
      axios
        .post(`${this.$domainName}/resource/tableinsemester`, {
          year: this.currentAcademicYear,
          semester: this.currentSemester,
          pageNumber: this.currentPage,
        })
        .then((res) => {
          //如果有数据
          if (res.data.response.code != 204) {
            this.dataExists = true;
            this.workloadTableData = res.data.data.records;
            this.allPageCount = res.data.data.pages;
            this.totalItems = res.data.data.total;
          }
          //如果没有数据
          else {
            this.dataExists = false;
            this.workloadTableData = [];
            this.noDataHint = `暂无&nbsp;&nbsp;${this.currentAcademicYear}&nbsp;&nbsp;学年，第&nbsp;&nbsp;${this.currentSemester}&nbsp;&nbsp;学期的数据！`;
          }
        });
    },
    //页码操作
    confirmYearAndSemester() {
      this.currentPage = 1;
      this.getTableData();
    },
    pageBefore() {
      if (this.currentPage == 1) return;
      this.currentPage = this.currentPage - 1;
      this.noMorePage = false;
      if (this.currentPage == 1) this.noLessPage = true;
      this.getTableData();
    },
    pageAfter() {
      if (this.currentPage == this.allPageCount) return;
      this.currentPage = this.currentPage + 1;
      this.noLessPage = false;
      if (this.currentPage == this.allPageCount) this.noMorePage = true;
      this.getTableData();
    },
    choosePage() {
      if (this.currentPage < 1 || this.currentPage > this.allPageCount) return;
      this.getTableData();
    },
    //条件搜索
    search() {
      if (this.searchValue == "") return;
      switch (this.searchKeyword) {
        case "上课老师":
          axios
            .post(`${this.$domainName}/search/searchIndeed`, {
              year: this.currentAcademicYear,
              semester: this.currentSemester,
              teacherName: this.searchValue,
            })
            .then((res) => {
              console.log(res);
              if (res.data.response.code == 200) {
                console.log("Y");
                this.dataExists = true;
                this.workloadTableData = res.data.data;
                this.totalItems = this.workloadTableData.length;
              } else {
                console.log("N");
                this.dataExists = false;
                this.workloadTableData = [];
                this.noDataHint = `暂无&nbsp;&nbsp;${this.currentAcademicYear}&nbsp;&nbsp;学年，第&nbsp;&nbsp;${this.currentSemester}&nbsp;&nbsp;学期<br>${this.searchKeyword}&nbsp;&nbsp;为&nbsp;&nbsp;${this.searchValue}&nbsp;&nbsp;的数据！`;
              }
            });
      }
    },
    //文件导出
    exportFile() {
      axios
        .post(`${this.$domainName}/resource/tabledownload`, {
          year: this.currentAcademicYear,
          semester: this.currentSemester,
        })
        .then((res) => {
          this.dataTobeExported = res.data.data;
          import("xlsx").then((XLSX) => {
            const data = XLSX.utils.json_to_sheet(this.dataTobeExported);
            const col = XLSX.utils.decode_range(data["!ref"]).e.c; //获取数据的列数（起始列序号为0）
            for (let i = 0; i <= col; i++) {
              data[XLSX.utils.encode_cell({ r: 0, c: i })].v =
                this.workloadTableHeader[i];
            }
            //获取文件名
            let filename = `${this.currentAcademicYear}学年_${this.currentSemester}学期工作量`; //default
            if (this.exportFileName != "") {
              //custom
              filename = this.exportFileName;
            }
            const wb = XLSX.utils.book_new();
            XLSX.utils.book_append_sheet(wb, data, "data");
            XLSX.writeFile(wb, filename + ".xlsx");
          });
        });
      this.dataTobeExported = [];
    },
    //点击触发上传方法
    uploadMaterial() {
      this.$refs.file.dispatchEvent(new MouseEvent("click"));
    },
    //触发选择文件，判断文件类型
    getFileData(file) {
      const inputFile = this.$refs.file;
      let filename = file;
      const isExcel = filename.substring(filename.lastIndexOf(".") + 1);
      this.uploadFile(inputFile.$refs.input.files[0]);
    },
    //上传文件，学年，学期
    uploadFile(file) {
      const formData = new FormData();
      var _this = this;
      var firstYear = this.$data.uploadFileYearInfo1;
      var secondYear = firstYear + 1;
      _this.year = firstYear + "-" + secondYear;
      _this.semester = this.$data.uploadFileSemesterInfo;
      formData.append("year", _this.year);
      formData.append("semester", _this.semester);
      formData.append("file", file);
      axios
        .post(`${this.$domainName}/file/upload`, formData, {
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
    // 设置学年选择范围（近5年），设置default学年学期
    const date = new Date();
    let currentYear = date.getFullYear();
    this.currentYear = currentYear;
    this.uploadFileYearInfo1 = currentYear;
    for (let i = 0; i < 5; i++) {
      this.academicYearRange[i] = `${currentYear}-${currentYear + 1}`;
      currentYear = currentYear - 1;
    }
    this.currentAcademicYear = this.academicYearRange[0];
    this.currentSemester = 1;
    //向后台获取default学年学期数据;
    this.getTableData();
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

/* 上传文件 */
.uploadSection {
  margin-bottom: 5px;
  padding: 15px 15px 10px;
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
.uploadSection .el-input input.el-input__inner {
  border: 0px;
  padding: 0;
}
/* 数据查询筛选栏 */
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
/* 查询结果的统计数据栏 */
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
/*文件下载工具栏*/
.toolBar {
  margin-bottom: 5px;
  padding-right: 15px;
  padding: 5px 15px;
  background-color: #fff;
  border-radius: 5px;
  text-align: right;
}
.toolBar label.customFileName {
  font-size: 16px;
}
.toolBar input#exportFileName {
  padding-left: 5px;
  height: 25px;
  border: 1px solid gray;
  border-radius: 5px;
}
.toolBar span.defaultExportfileName {
  font-size: 10px;
  color: gray;
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
.toolBar button.disabled {
  background-color: rgba(209, 207, 207, 0.479);
}
/* 数据列表 */
.workloadTableWrapper {
  position: relative;
  overflow: scroll;
  height: 60vh;
  padding-bottom: 15px;
}
table.workloadDataTable {
  width: 2500px;
}
.workloadDataTable thead {
  position: sticky;
  top: 0;
  font-size: 10px;
  font-weight: 500;
  background-color: rgb(239, 241, 247);
}
.workloadDataTable tbody {
  font-size: 13px;
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
/* 分页 */
.pagination button {
  border: 0;
  font-size: 20px;
  padding: 10px 20px;
  position: relative;
  top: 1px;
  background-color: rgb(239, 241, 246);
}
.pagination input {
  border: 1px solid gray;
  border-radius: 5px;
  width: 30px;
  text-align: center;
  padding: 3px 0;
}
</style>