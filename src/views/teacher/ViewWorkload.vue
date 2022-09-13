<template>
  <div class="componentWrapper">
    <!--component标题-->
    <div class="componentSectionTitle">工作量报告</div>
    <YearFilter @yearConfirmed="yearConfirmed"></YearFilter>
    <TableStatisticsBar :keyValuePairs="keyValuePairs"></TableStatisticsBar>
    <!-- 教学工作量详情 -->
    <div class="componentSubtitle">教学工作量详情</div>
    <!--数据列表-->
    <PlainTable
      :dataExists="dataExists_teaching"
      :tableData="teachingWorkloadTableData"
      :tableHeader="teachingWorkloadTableHeader"
      :noDataHint="noDataHint_teaching"
    ></PlainTable>
    <!-- 特殊工作量详情 -->
    <div class="componentSubtitle">特殊工作量详情</div>
    <PlainTable
      :dataExists="dataExists_special"
      :tableData="specialWorkloadTableData"
      :tableHeader="specialWorkloadTableHeader"
      :noDataHint="noDataHint_special"
    ></PlainTable>
  </div>
</template>

<script>
import TableStatisticsBar from "../../components/TableStatisticsBar.vue";
import YearFilter from "../../components/YearFilter.vue";
import PlainTable from "../../components/PlainTable.vue";
export default {
  name: "TeacherWorkloadTable",
  components: {
    TableStatisticsBar,
    YearFilter,
    PlainTable,
  },
  data() {
    return {
      //统计数据
      teachingScore: 0,
      specialScore: 0,
      //分页
      currentPage: 1,
      //表格数据
      yearChosen: this.$currentYear,
      // 教学工作量数据(字段按照老师习惯的顺序排序)
      teachingWorkloadTableHeader: [
        {
          key: "工作量性质",
          value: "workloadNature",
        },
        {
          key: "学年",
          value: "academicYear",
        },
        {
          key: "学期",
          value: "semester",
        },
        {
          key: "课程号",
          value: "courseNumber",
        },
        {
          key: "课程名称",
          value: "courseName",
        },
        {
          key: "教学班",
          value: "teachingClass",
        },
        // {
        //   key: "开课学院",
        //   value: "teachingSchool",
        // },
        // {
        //   key: "计划学院",
        //   value: "teachingSchool",
        // },
        {
          key: "学分",
          value: "credit",
        },
        {
          key: "课程性质",
          value: "courseNature",
        },
        {
          key: "年级",
          value: "studentGrade",
        },
        {
          key: "专业",
          value: "major",
        },
        // {
        //   key: "上课老师",
        //   value: "mainTeacherName",
        // },
        // {
        //   key: "职称",
        //   value: "mainTeacherTitle",
        // },
        {
          key: "上课人数",
          value: "studentAmount",
        },
        {
          key: "理论课时",
          value: "theoreticalClassHours",
        },
        {
          key: "上机课时",
          value: "computerClassHours",
        },
        {
          key: "实验课时",
          value: "experimentalClassHours",
        },
        {
          key: "实践课时",
          value: "practicalClassHours",
        },
        {
          key: "计算用课时",
          value: "calculatingClassHours",
        },
        {
          key: "合课单位",
          value: "jointDepartment",
        },
        // {
        //   key: "备注",
        //   value: "remarks",
        // },
        {
          key: "实验安排",
          value: "experimentArrangement",
        },
        {
          key: "其它教师",
          value: "otherTeacherName",
        },
        {
          key: "教分（BA1/3/15）原始分",
          value: "originalTeachingScores",
        },
        {
          key: "BA1系数",
          value: "teachingCoefficient",
        },
        {
          key: "教分（BA1/3/15）",
          value: "finalTeachingScores",
        },
        // {
        //   key: "辅助",
        //   value: "assistant",
        // },
        {
          key: "课程性质说明",
          value: "classNatureExplanation",
        },
        {
          key: "是否卓工或弘毅",
          value: "specialClassRemarks",
        },
        {
          key: "是否全英文",
          value: "specialLanguageRemarks",
        },
        {
          key: "是否卓工或弘毅",
          value: "specialClassRemarks",
        },
        {
          key: "是否打折",
          value: "discount",
        },
        {
          key: "未打折前",
          value: "noDiscountTeachingCoefficient",
        },
        // {
        //   key: "实验室核对结果",
        //   value: "laboratoryVerificationResults",
        // },
      ],
      teachingWorkloadTableData: [],
      dataExists_teaching: false,
      noDataHint_teaching: "",
      //特殊工作量数据
      specialWorkloadTableHeader: [
        {
          key: "申报人",
          value: "declarantName",
        },
        {
          key: "申报时间",
          value: "reportTime",
        },
        {
          key: "状态",
          value: "status",
        },
        {
          key: "教学业绩类型",
          value: "projectCategory",
        },
        {
          key: "成果/作品/参赛项目/参评项目/论文/专著名称",
          value: "achievementName",
        },
        {
          key: "级别",
          value: "awardLevel",
        },
        {
          key: "课程/项目/奖项/竞赛/论文指导类别",
          value: "projectCategory",
        },
        {
          key: "课程/项目/奖项/竞赛详细名称",
          value: "projectName",
        },
        {
          key: "获奖等级",
          value: "awardLevel",
        },
        {
          key: "获奖/获评/出版日期",
          value: "awardDate",
        },
        {
          key: "项目进展",
          value: "projectStatus",
        },
        {
          key: "授奖单位",
          value: "awardApartment",
        },
        {
          key: "刊物/出版社名称",
          value: "publicationName",
        },
        {
          key: "刊物期数/出版版次",
          value: "publicationsNumber",
        },
        {
          key: "ISBN号",
          value: "isbn",
        },
        {
          key: "所获荣誉",
          value: "receivingHonor",
        },
        {
          key: "指导学生团队名",
          value: "guidingStudentTeam",
        },
        {
          key: "指导学生姓名",
          value: "guidingStudentName",
        },
        //从participants中提取出来的属于这个老师的教分数据
        {
          key: "教分",
          value: "theScore",
        },
      ],
      specialWorkloadTableData: [],
      dataExists_special: false,
      noDataHint_special: "",
    };
  },
  computed: {
    //展示在统计栏的数据
    keyValuePairs() {
      return [
        {
          key: "总教分",
          value: this.totalScore,
        },
        {
          key: "教学工作量教分",
          value: this.teachingScore,
        },
        {
          key: "特殊工作量教分",
          value: this.specialScore,
        },
      ];
    },
    totalScore() {
      return this.teachingScore + this.specialScore;
    },
  },
  methods: {
    //获取当前教师用户的教学工作量
    getTeachingData() {
      const formData = new FormData();
      formData.append("mainTeacherName", this.$currentUser);
      formData.append("naturalYear", this.yearChosen);
      this.$axios
        .post(`/total/records`, formData)
        .then((res) => {
          console.log(res);
          if (res.data.response.code == 200) {
            this.dataExists_teaching = true;
            this.teachingWorkloadTableData = res.data.data;
            //计算教学总教分
            this.teachingWorkloadTableData.forEach((item) => {
              this.teachingScore =
                this.teachingScore + item["finalTeachingScores"];
            });
          } else {
            this.dataExists_teaching = false;
            this.teachingWorkloadTableData = [];
            this.teachingScore = 0;
            this.noDataHint_teaching = `暂无${this.yearChosen}年度的教学工作量数据！`;
          }
        })
        .catch((err) => {
          console.log(err);
          this.dataExists_teaching = false;
          this.teachingWorkloadTableData = [];
          this.teachingScore = 0;
          this.noDataHint_teaching = "获取数据出错！";
        });
    },
    //获取当前教师用户的特殊工作量
    getSpecialData() {
      const formData = new FormData();
      formData.append("year", this.yearChosen);
      formData.append("teacherName", this.$currentUser);
      formData.append("statusList", JSON.stringify(["已审核"]));
      this.$axios
        .post(`/special-join/select/teacher`, formData)
        .then((res) => {
          console.log(res);
          if (res.data.response.code == 200) {
            this.dataExists_special = true;
            this.specialWorkloadTableData = res.data.data; //获取特殊工作量对象
            this.specialWorkloadTableData.forEach((item) => {
              //遍历每个数据对象的somePeople属性，找到当前用户在本项目内对应的教分
              for (let object of item.somePeople) {
                if (object.teacherName == this.$currentUser) {
                  item["theScore"] = object.score;
                  this.specialScore = this.specialScore + object.teachingScores;
                  break;
                }
              }
            });
          } else {
            this.dataExists_special = false;
            this.specialWorkloadTableData = [];
            this.specialScore = 0;
            this.noDataHint_special = `暂无${this.yearChosen}年度的特殊工作量教分审核数据！`;
          }
        })
        .catch((err) => {
          console.log(err);
          this.specialScore = 0;
          this.dataExists_special = false;
          this.specialWorkloadTableData = [];
          this.noDataHint_special = "获取数据出错！";
        });
    },
    yearConfirmed(year) {
      this.yearChosen = year;
      console.log(this.yearChosen);
      this.getTeachingData();
    },
  },
  created() {
    //向后台获取default学年学期数据;
    this.getTeachingData();
    this.getSpecialData();
  },
};
</script>

<style scoped>
</style>