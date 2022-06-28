<template>
  <div id="workloadDisplay">
    <!--component标题-->
    <div class="sectionTitle">教学工作量</div>
    <!--学年和学期筛选-->
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
      <button @click="getTableData()" class="confirmYearAndSemesterBtn">
        确&nbsp;认
      </button>
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
      <label class="customFileName" for="exportFileName">文件名：</label>
      <input type="text" v-model="exportFileName" id="exportFileName" />
      <span class="defaultExportfileName"
        >（默认：{{ this.currentAcademicYear }}学年_{{
          this.currentSemester
        }}学期工作量）</span
      >
      <button
        class="export"
        @click="exportFile()"
        :disabled="!dataExists"
        :class="{ disabled: !dataExists }"
      >
         导出Excel至本地
      </button>
    </div>
    <!--数据列表-->
    <div class="workloadTableWrapper">
      <div class="noDataHint" v-if="!dataExists" v-text="noDataHint"></div>
      <table class="workloadDataTable">
        <thead>
          <tr>
            <th v-for="(item, index) in workloadTableHeader" :key="index">
              {{ item }}
            </th>
          </tr>
        </thead>
        <tbody>
          <tr
            class="tableDataLine"
            v-for="arrayItem in workloadTableData"
            :key="arrayItem.id"
          >
            <td v-for="(value, index) in arrayItem" :key="index">
              {{ value }}
            </td>
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
      currentTeacherName: "",
      academicYearRange: [], //学年select列表范围
      currentAcademicYear: "", //选择的学年
      currentSemester: 1, //选择的学期
      workloadTableHeader: [], //显示数据表头
      workloadTableData: [], //显示数据体
      dataExists: false, //“暂无数据”提示的显示
      noDataHint: "",
      exportFileName: "",
    };
  },
  computed: {
    //统计数据
    totalClassCount() {
      return this.workloadTableData.length; //总课程数
    },
  },

  methods: {
    //根据当前学年和学期获取对应工作量的数据
    getTableData() {
      axios
        .post(" http://abcds.vaiwan.com/resource/customIndeed", {
          year: this.currentAcademicYear,
          semester: this.currentSemester,
          teacherName: this.currentTeacherName,
        })
        .then((res) => {
          console.log(res);
          if (res.data.response.code != 204) {
            this.dataExists = true;
            this.workloadTableHeader = [
              "课程号",
              "课程名称",
              "教学班",
              "开课学院",
              "学分",
              "课程性质",
              "年级",
              "专业",
              "上课老师",
              "职称",
              "上课人数",
              "计算用学时",
              "合课单位",
              "备注",
              "实验安排",
              "其他教师",
              "辅助",
              "课程性质说明",
              "是否卓工或弘毅",
              "是否全英文",
            ];
            this.workloadTableData = res.data.data;
          }
          //如果没有数据
          else {
            this.dataExists = false;
            this.workloadTableHeader = [];
            this.workloadTableData = [];
            this.noDataHint = `暂无${this.currentAcademicYear}学年，第${this.currentSemester}学期的数据！`;
          }
        });
    },
    exportFile() {
      import("xlsx").then((XLSX) => {
        const data = XLSX.utils.json_to_sheet(this.workloadTableData);
        const col = XLSX.utils.decode_range(data["!ref"]).e.c; //获取数据的列数（起始列序号为0）
        //替换第一行的值为中文表头
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
    },
  },

  created() {
    //获取当前老师用户的姓名
    this.currentTeacherName = localStorage.getItem("teacherName");
    console.log(this.currentTeacherName);
    // 设置学年选择范围（近5年），设置default学年学期
    const date = new Date();
    let currentYear = date.getFullYear();
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
* {
  list-style: none;
  text-decoration: none;
  margin: 0;
  padding: 0;
}
#workloadDisplay {
  float: left;
  overflow: hidden;
  width: 85vw;
  height: 100vh;
  padding: 10px 15px;
  background-color: rgb(239, 241, 247);
}
.sectionTitle {
  text-align: left;
  font-size: 27px;
  font-weight: 600;
  color: rgb(29, 74, 51);
  height: 50px;
  line-height: 50px;
}
/* 学年学期筛选 */
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
.toolBar button.export {
  padding: 5px;
  border: 1px solid rgba(128, 128, 128, 0.603);
  border-radius: 5px;
  background-color: rgb(255, 254, 254);
  font-family: "icomoon";
  color: rgb(51, 114, 96);
  font-weight: 500;
}
.toolBar button.export:hover {
  background-color: rgb(209, 207, 207);
}
.toolBar button.disabled {
  background-color: rgb(209, 207, 207);
}

/* 数据列表 */
.workloadTableWrapper {
  position: relative;
  overflow: scroll;
  height: 60vh;
}
table.workloadDataTable {
  width: 2000px;
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