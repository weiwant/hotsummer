<template>
  <div class="app-right-wrapper">
    <div class="app-right-title">查看教学工作量</div>
    <TableFilter :filters="filters" @search="search" />
    <!-- <div class="app-section toolbar">
      <DownloadExcelFile
        :btnText="'导出excel至本地'"
        :disabled="!dataExists"
        :defaultFileName="`${yearChosen}年度教学工作量`"
        @exportFile="exportFile"
      />
    </div> -->
    <div class="app-section-flexwrapper">
      <TableInformationBar
        :currentYear="yearChosen"
        :filterValues="filterAdded"
      />
      <TableHeaderSelection
        :headerGroups="headerGroups"
        @change="adjustHeader"
      />
    </div>

    <PlainTable :header="tableHeaderDisplayed" :data="tableData" />
    <div class="pagination">
      <el-pagination
        background
        layout="total,prev, pager, next"
        page-size="20"
        :total="totalPage"
        @current-change="changePage"
        :hide-on-single-page="true"
      >
      </el-pagination>
    </div>
  </div>
</template>

<script>
import DownloadExcelFile from "../../components/DownloadExcelFile.vue";
import TableFilter from "../../components/table/TableFilter.vue";
import TableInformationBar from "../../components/table/TableInformationBar.vue";
import TableHeaderSelection from "../../components/table/TableHeaderSelection.vue";
import PlainTable from "../../components/table/PlainTable.vue";
export default {
  name: "CheckWorkload",
  components: {
    DownloadExcelFile,
    TableFilter,
    TableInformationBar,
    TableHeaderSelection,
    PlainTable,
  },
  data() {
    return {
      //初始化TableFilter
      filters: [
        {
          type_filter: "工作量性质",
          type_input: "select",
          options: ["教学工作量", "监考工作量", "论文工作量"], //当输入类型为select时，需要提供options
        },
        {
          type_filter: "课程名称",
          type_input: "text",
          options: [],
        },
      ],
      //查询条件
      yearChosen: `${this.$currentYear}`,
      filterAdded: [],
      //分页
      totalPage: 10,
      currentPage: 1,
      //查询结果
      headerChosen: [true, false, false],
      tableHeader: [
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
        {
          key: "开课学院",
          value: "teachingSchool",
        },
        {
          key: "计划学院",
          value: "planingSchool",
        },
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
        {
          key: "上课老师",
          value: "mainTeacherName",
        },
        {
          key: "职称",
          value: "mainTeacherTitle",
        },
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
        {
          key: "备注",
          value: "remarks",
        },
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
        {
          key: "实验室核对结果",
          value: "laboratoryVerificationResults",
        },
      ],
      tableData: [
        {
          workloadNature: "1111",
          academicYear: "111111",
          semester: "11111",
          courseNumber: "111211",
          courseName: "12111",
          teachingClass: "111111",
          teachingSchool: "1111111",
          planingSchool: "1111111",
          credit: "1111111",
          courseNature: "11111",
          studentGrade: "11111",
          major: "11111",
          mainTeacherName: "111111",
          mainTeacherTitle: "11111",
          studentAmount: "111111",
          theoreticalClassHours: "111111",
          computerClassHours: "11111",
          experimentalClassHours: "111111111",
          practicalClassHours: "1111111111",
          calculatingClassHours: "1111111",
          jointDepartment: "11111111111",
          remarks: "11111111",
          experimentArrangement: "111111111",
          otherTeacherName: "111111111",
          originalTeachingScores: "111111111",
          teachingCoefficient: "1111111111",
          finalTeachingScores: "111111111",
          classNatureExplanation: "111111111",
          specialClassRemarks: "1111111111",
          specialLanguageRemarks: "11111111111",
          specialClassRemarks: "11111111111",
          discount: "11111111111",
          noDiscountTeachingCoefficient: "11111111",
          laboratoryVerificationResults: "111111111",
        },
        {
          workloadNature: "1111",
          academicYear: "111111",
          semester: "11111",
          courseNumber: "111111",
          courseName: "11111",
          teachingClass: "111111",
          teachingSchool: "1111111",
          credit: "1111111",
          courseNature: "11111",
          studentGrade: "11111",
          major: "11111",
          mainTeacherName: "111111",
          mainTeacherTitle: "11111",
          studentAmount: "111111",
          theoreticalClassHours: "111111",
          computerClassHours: "11111",
          experimentalClassHours: "111111111",
          practicalClassHours: "1111111111",
          calculatingClassHours: "1111111",
          jointDepartment: "11111111111",
          remarks: "11111111",
          experimentArrangement: "111111111",
          otherTeacherName: "111111111",
          originalTeachingScores: "111111111",
          teachingCoefficient: "1111111111",
          finalTeachingScores: "111111111",
          classNatureExplanation: "111111111",
          specialClassRemarks: "1111111111",
          specialLanguageRemarks: "11111111111",
          specialClassRemarks: "11111111111",
          discount: "11111111111",
          noDiscountTeachingCoefficient: "11111111",
          laboratoryVerificationResults: "111111111",
        },
      ],
      totalItems: 0,
      dataExists: false,
      noDataHint: "", //“暂无数据”提示
    };
  },
  computed: {
    //临时的表头选择组
    headerGroups() {
      let result = [];
      let group = this.tableHeader[0].key;
      for (let i = 1; i < this.tableHeader.length; i++) {
        if (i === 15 || i === 24) {
          result.push(group);
          group = "";
          group += this.tableHeader[i].key;
        } else {
          group += "、";
          group += this.tableHeader[i].key;
        }
      }
      result.push(group);
      return result;
    },
    //用户选择的需要展示的表头
    tableHeaderDisplayed() {
      const result = [];
      // this.headerChosen.forEach((chosen, index) => {
      //   if (chosen) {
      //     for (let item of this.headerGroups[index]) {
      //       result.push(item);
      //     }
      //   }
      // });
      if (this.headerChosen[0]) {
        for (let i = 0; i < 15; i++) {
          result.push(this.tableHeader[i]);
        }
      }
      if (this.headerChosen[1]) {
        for (let i = 15; i < 24; i++) {
          result.push(this.tableHeader[i]);
        }
      }
      if (this.headerChosen[2]) {
        for (let i = 24; i < this.tableHeader.length; i++) {
          result.push(this.tableHeader[i]);
        }
      }
      return result;
    },
  },
  methods: {
    //查询列表数据
    search(yearChosen, filterAdded) {
      this.yearChosen = yearChosen;
      this.filterAdded = JSON.parse(JSON.stringify(filterAdded)); //深复制，当前组件保存的added永远是上一次点击查询时的
    },
    //更改展示表头
    adjustHeader(chosen) {
      this.headerChosen = [...chosen];
    },
    //分页
    changePage(currentPage) {
      this.currentPage = currentPage;
    },
    getTableData() {
      const formData = new FormData();
      formData.append("naturalYear", this.yearChosen);
      formData.append("page", this.currentPage);
      // 如果搜索值不为空
      if (this.searchValueChosen != "") {
        switch (this.searchKeywordChosen) {
          case "上课老师":
            formData.append("mainTeacherName", this.searchValueChosen);
            break;
          // case "教分":
          //   formData.append("mainTeacherName", this.searchValue);
          //   break;
        }
      }
      this.$axios
        .post(`/total/records/page`, formData)
        .then((res) => {
          //如果有数据
          if (res.data.response.code == 200) {
            this.dataExists = true;
            this.teachingWorkloadTableData = res.data.data.records;
            this.allPageCount = res.data.data.pageNum;
            this.totalItems = res.data.data.itemNum;
          }
          //如果没有数据
          else {
            this.dataExists = false;
            this.teachingWorkloadTableData = [];
            this.allPageCount = 1;
            this.totalItems = 0;
            if (this.searchValueChosen == "") {
              this.noDataHint = `暂无&nbsp;&nbsp;${this.yearChosen}&nbsp;&nbsp;年度的数据！`;
            } else {
              this.noDataHint = `暂无&nbsp;&nbsp;${this.yearChosen}&nbsp;&nbsp;年度，${this.searchKeywordChosen}&nbsp;为&nbsp;${this.searchValueChosen}&nbsp;的数据！`;
            }
          }
        })
        .catch((err) => {
          console.log(err);
          this.noDataHint = "获取数据出错！";
        });
    },
    //选择了年份和搜索条件，并给出了搜索值后确认
    confirmSearchValue(year, searchKeyword, searchValue) {
      //在请求数据前设置好参数
      this.yearChosen = year;
      this.searchKeywordChosen = searchKeyword;
      this.searchValueChosen = searchValue;
      this.currentPage = 1;
      this.getTableData();
    },

    //文件导出(只导出全年的)
    exportFile(filename) {
      const formData = new FormData();
      formData.append("naturalYear", this.yearChosen);
      //中文表头
      const tableHeader = [
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
      ];
      this.$axios.post(`/total/records`, formData).then((res) => {
        console.log(res);
        this.$exportExcelFile(res.data.data, tableHeader, filename);
      });
    },
  },
  created() {
    //获取default年度数据;
    this.getTableData();
  },
};
</script>

<style scoped>
#table-wrapper {
  margin-top: 10px;
}
.pagination {
  margin: 10px 0;
  margin-left: 50%;
  transform: translateX(-50%);
}
</style>