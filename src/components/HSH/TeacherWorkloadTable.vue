<template>
  <div id="workloadDisplay">
    <!--component标题-->
    <div class="sectionTitle">课程工作量</div>
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
        <select v-model="currentSemester" id="semester">
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
      <button class="export">导出Excel至本地</button>
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
export default {
  name: "TeacherWorkloadTable",
  data() {
    return {
      academicYears: ["2021-2022", "2020-2021", "2019-2020"],
      currentAcademicYear: "2021-2022", //学年
      currentSemester: 1, //学期
      workloadTableHeader: [
        //表头
        "课程名称",
        "教学班",
        "计划学院",
        "学分",
        "课程性质",
        "年级",
        "专业",
        "上课人数",
        "理论",
        "上机",
        "实验",
        "实践",
        "计算学用时",
        "教分（BA1 3 15）原始分",
        "BA1系数",
        "教分（BA1 3 15）",
        "辅助",
      ],
      workloadTableData: [
        {
          className: "数据库系统",
          classNO: "20202021663",
          planFaculty: "计算机学院",
          credit: 2.5,
          classType: "专业教育必修",
          studentGrade: "2019",
          speciality: "计算机科学与技术",
          numberOfStudent: 30,
          LiLunClassHour: 40,
          ShangJiClassHour: 0,
          ShiYanClassHour: 0,
          ShiJianClassHour: 0,
        },
        {
          className: "数据库系统",
          classNO: "20202021663",
          planFaculty: "计算机学院",
          credit: 2.5,
          classType: "专业教育必修",
          studentGrade: "2019",
          speciality: "计算机科学与技术",
          numberOfStudent: 30,
          LiLunClassHour: 40,
          ShangJiClassHour: 0,
          ShiYanClassHour: 0,
          ShiJianClassHour: 0,
        },
        {
          className: "数据库系统",
          classNO: "20202021663",
          planFaculty: "计算机学院",
          credit: 2.5,
          classType: "专业教育必修",
          studentGrade: "2019",
          speciality: "计算机科学与技术",
          numberOfStudent: 30,
          LiLunClassHour: 40,
          ShangJiClassHour: 0,
          ShiYanClassHour: 0,
          ShiJianClassHour: 0,
        },
        {
          className: "数据库系统",
          classNO: "20202021663",
          planFaculty: "计算机学院",
          credit: 2.5,
          classType: "专业教育必修",
          studentGrade: "2019",
          speciality: "计算机科学与技术",
          numberOfStudent: 30,
          LiLunClassHour: 40,
          ShangJiClassHour: 0,
          ShiYanClassHour: 0,
          ShiJianClassHour: 0,
        },
        {
          className: "数据库系统",
          classNO: "20202021663",
          planFaculty: "计算机学院",
          credit: 2.5,
          classType: "专业教育必修",
          studentGrade: "2019",
          speciality: "计算机科学与技术",
          numberOfStudent: 30,
          LiLunClassHour: 40,
          ShangJiClassHour: 0,
          ShiYanClassHour: 0,
          ShiJianClassHour: 0,
        },
        {
          className: "数据库系统",
          classNO: "20202021663",
          planFaculty: "计算机学院",
          credit: 2.5,
          classType: "专业教育必修",
          studentGrade: "2019",
          speciality: "计算机科学与技术",
          numberOfStudent: 30,
          LiLunClassHour: 40,
          ShangJiClassHour: 0,
          ShiYanClassHour: 0,
          ShiJianClassHour: 0,
        },
        {
          className: "数据库系统",
          classNO: "20202021663",
          planFaculty: "计算机学院",
          credit: 2.5,
          classType: "专业教育必修",
          studentGrade: "2019",
          speciality: "计算机科学与技术",
          numberOfStudent: 30,
          LiLunClassHour: 40,
          ShangJiClassHour: 0,
          ShiYanClassHour: 0,
          ShiJianClassHour: 0,
        },
        {
          className: "数据库系统",
          classNO: "20202021663",
          planFaculty: "计算机学院",
          credit: 2.5,
          classType: "专业教育必修",
          studentGrade: "2019",
          speciality: "计算机科学与技术",
          numberOfStudent: 30,
          LiLunClassHour: 40,
          ShangJiClassHour: 0,
          ShiYanClassHour: 0,
          ShiJianClassHour: 0,
        },
        {
          className: "数据库系统",
          classNO: "20202021663",
          planFaculty: "计算机学院",
          credit: 2.5,
          classType: "专业教育必修",
          studentGrade: "2019",
          speciality: "计算机科学与技术",
          numberOfStudent: 30,
          LiLunClassHour: 40,
          ShangJiClassHour: 0,
          ShiYanClassHour: 0,
          ShiJianClassHour: 0,
        },
        {
          className: "数据库系统",
          classNO: "20202021663",
          planFaculty: "计算机学院",
          credit: 2.5,
          classType: "专业教育必修",
          studentGrade: "2019",
          speciality: "计算机科学与技术",
          numberOfStudent: 30,
          LiLunClassHour: 40,
          ShangJiClassHour: 0,
          ShiYanClassHour: 0,
          ShiJianClassHour: 0,
        },
        {
          className: "数据库系统",
          classNO: "20202021663",
          planFaculty: "计算机学院",
          credit: 2.5,
          classType: "专业教育必修",
          studentGrade: "2019",
          speciality: "计算机科学与技术",
          numberOfStudent: 30,
          LiLunClassHour: 40,
          ShangJiClassHour: 0,
          ShiYanClassHour: 0,
          ShiJianClassHour: 0,
        },
        {
          className: "数据库系统",
          classNO: "20202021663",
          planFaculty: "计算机学院",
          credit: 2.5,
          classType: "专业教育必修",
          studentGrade: "2019",
          speciality: "计算机科学与技术",
          numberOfStudent: 30,
          LiLunClassHour: 40,
          ShangJiClassHour: 0,
          ShiYanClassHour: 0,
          ShiJianClassHour: 0,
        },
        {
          className: "数据库系统",
          classNO: "20202021663",
          planFaculty: "计算机学院",
          credit: 2.5,
          classType: "专业教育必修",
          studentGrade: "2019",
          speciality: "计算机科学与技术",
          numberOfStudent: 30,
          LiLunClassHour: 40,
          ShangJiClassHour: 0,
          ShiYanClassHour: 0,
          ShiJianClassHour: 0,
        },
        {
          className: "数据库系统",
          classNO: "20202021663",
          planFaculty: "计算机学院",
          credit: 2.5,
          classType: "专业教育必修",
          studentGrade: "2019",
          speciality: "计算机科学与技术",
          numberOfStudent: 30,
          LiLunClassHour: 40,
          ShangJiClassHour: 0,
          ShiYanClassHour: 0,
          ShiJianClassHour: 0,
        },
        {
          className: "数据库系统",
          classNO: "20202021663",
          planFaculty: "计算机学院",
          credit: 2.5,
          classType: "专业教育必修",
          studentGrade: "2019",
          speciality: "计算机科学与技术",
          numberOfStudent: 30,
          LiLunClassHour: 40,
          ShangJiClassHour: 0,
          ShiYanClassHour: 0,
          ShiJianClassHour: 0,
        },
        {
          className: "数据库系统",
          classNO: "20202021663",
          planFaculty: "计算机学院",
          credit: 2.5,
          classType: "专业教育必修",
          studentGrade: "2019",
          speciality: "计算机科学与技术",
          numberOfStudent: 30,
          LiLunClassHour: 40,
          ShangJiClassHour: 0,
          ShiYanClassHour: 0,
          ShiJianClassHour: 0,
        },
        {
          className: "数据库系统",
          classNO: "20202021663",
          planFaculty: "计算机学院",
          credit: 2.5,
          classType: "专业教育必修",
          studentGrade: "2019",
          speciality: "计算机科学与技术",
          numberOfStudent: 30,
          LiLunClassHour: 40,
          ShangJiClassHour: 0,
          ShiYanClassHour: 0,
          ShiJianClassHour: 0,
        },
        {
          className: "数据库系统",
          classNO: "20202021663",
          planFaculty: "计算机学院",
          credit: 2.5,
          classType: "专业教育必修",
          studentGrade: "2019",
          speciality: "计算机科学与技术",
          numberOfStudent: 30,
          LiLunClassHour: 40,
          ShangJiClassHour: 0,
          ShiYanClassHour: 0,
          ShiJianClassHour: 0,
        },
        {
          className: "数据库系统",
          classNO: "20202021663",
          planFaculty: "计算机学院",
          credit: 2.5,
          classType: "专业教育必修",
          studentGrade: "2019",
          speciality: "计算机科学与技术",
          numberOfStudent: 30,
          LiLunClassHour: 40,
          ShangJiClassHour: 0,
          ShiYanClassHour: 0,
          ShiJianClassHour: 0,
        },
        {
          className: "数据库系统",
          classNO: "20202021663",
          planFaculty: "计算机学院",
          credit: 2.5,
          classType: "专业教育必修",
          studentGrade: "2019",
          speciality: "计算机科学与技术",
          numberOfStudent: 30,
          LiLunClassHour: 40,
          ShangJiClassHour: 0,
          ShiYanClassHour: 0,
          ShiJianClassHour: 0,
        },
        {
          className: "数据库系统",
          classNO: "20202021663",
          planFaculty: "计算机学院",
          credit: 2.5,
          classType: "专业教育必修",
          studentGrade: "2019",
          speciality: "计算机科学与技术",
          numberOfStudent: 30,
          LiLunClassHour: 40,
          ShangJiClassHour: 0,
          ShiYanClassHour: 0,
          ShiJianClassHour: 0,
        },
        {
          className: "数据库系统",
          classNO: "20202021663",
          planFaculty: "计算机学院",
          credit: 2.5,
          classType: "专业教育必修",
          studentGrade: "2019",
          speciality: "计算机科学与技术",
          numberOfStudent: 30,
          LiLunClassHour: 40,
          ShangJiClassHour: 0,
          ShiYanClassHour: 0,
          ShiJianClassHour: 0,
        },
        {
          className: "数据库系统",
          classNO: "20202021663",
          planFaculty: "计算机学院",
          credit: 2.5,
          classType: "专业教育必修",
          studentGrade: "2019",
          speciality: "计算机科学与技术",
          numberOfStudent: 30,
          LiLunClassHour: 40,
          ShangJiClassHour: 0,
          ShiYanClassHour: 0,
          ShiJianClassHour: 0,
        },
        {
          className: "数据库系统",
          classNO: "20202021663",
          planFaculty: "计算机学院",
          credit: 2.5,
          classType: "专业教育必修",
          studentGrade: "2019",
          speciality: "计算机科学与技术",
          numberOfStudent: 30,
          LiLunClassHour: 40,
          ShangJiClassHour: 0,
          ShiYanClassHour: 0,
          ShiJianClassHour: 0,
        },
      ],
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
    getTableData(year, semester) {},
    refreshData() {
      this.getTableData(this.currentAcademicYear, this.currentSemester);
    },
  },
  created() {
    //向后台获取学年列表
    //设置currentYear为列表最新学年值   currentSemester为1
    //调用getTableData()方法获取数据
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
  width: 80vw;
  height: 100vh;
  padding: 10px 15px;
  background-color: rgb(239, 241, 247);
}
.sectionTitle {
  text-align: left;
  font-size: 27px;
  font-weight: 600;
  color: rgb(51, 114, 96);
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
  border: 1px solid gray;
  border-radius: 5px;
  background-color: rgb(255, 254, 254);
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
</style>