<template>
  <div id="workloadDisplay">
    <!--component标题-->
    <div class="sectionTitle">教学工作量</div>
    <!--学年和学期筛选-->
    <div class="tableFilter">
      <div class="segment">
        <label for="year">学年</label>
        <select v-model="currentAcademicYear" id="year" @change="refreshData()">
          <option v-for="item in academicYears" :key="item.id" :value="item">
            {{ item }}
          </option>
        </select>
      </div>
      <div class="segment">
        <label for="semester">学期</label>
        <select v-model="currentSemester" id="semester" @change="refreshData()">
          <option value="1">1</option>
          <option value="2">2</option>
          <option value="3">3</option>
        </select>
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
      <button class="export"> 导出Excel至本地</button>
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
            v-for="item in workloadTableData"
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
      academicYears: [],
      currentAcademicYear: "", //学年
      currentSemester: 1, //学期
      workloadTableHeader: [],
      workloadTableData: [],
    };
  },
  computed: {
    //统计数据
    totalClassCount() {
      return this.workloadTableData.length; //总课程数
    },
  },
  methods: {
    //根据学年和学期获取对应工作量的数据
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
    refreshData() {
      this.getTableData(this.currentAcademicYear, this.currentSemester);
    },
  },
  created() {
    //向后台获取学年列表
    axios({
      method: "get",
      url: `http://localhost:3000/academicYearList`,
    }).then((res) => {
      this.academicYears = res.data;
      // console.log(res.data[0]);
      //设置默认学年学期
      this.currentAcademicYear = res.data[0];
      this.currentSemester = 1;
      //调用getTableData()方法获取数据
      this.getTableData(this.currentAcademicYear, this.currentSemester);
    });
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
.toolBar button {
  padding: 5px;
  border: 1px solid rgba(128, 128, 128, 0.603);
  border-radius: 5px;
  background-color: rgb(255, 254, 254);
  font-family: "icomoon";
  color: rgb(51, 114, 96);
  font-weight: 500;
}
.toolBar button:hover {
  background-color: rgb(209, 207, 207);
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