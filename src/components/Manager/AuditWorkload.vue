<template>
  <div class="componentWrapper">
    <div class="componentSectionTitle">审批特殊工作量</div>
    <div class="componentSubtitle">截止时间设置</div>
    <div class="componentSubsection ddl">
      已将特殊工作量提交截止时间设置为:<span>{{ ddl }}</span>
      <button class="universalBlueBtn">重&nbsp;置</button>
    </div>
    <div class="componentSubtitle">已提交上报</div>
    <FilterWithSearch
      :searchKeywords="searchKeywords"
      @getData="confirmSearchValue"
    ></FilterWithSearch>
    <TableStatisticsBar :keyValuePairs="keyValuePairs"></TableStatisticsBar>
    <div class="componentSubsection toolBar">
      <DownloadExcelFile
        :btnText="'导出excel至本地'"
        :disabled="!dataExists"
        :defaultFileName="`${yearChosen}年度特殊工作量审批记录`"
        @exportFile="exportFile"
      ></DownloadExcelFile>
      <!-- 批量下载申报项目文件 -->
      <div class="auditDownloadTool">
        <button class="workloadFileProcessingBtn"> 批量下载审核附件</button>
      </div>
    </div>

    <div class="tableWrapper">
      <div class="noDataHint" v-if="!dataExists">{{ noDataHint }}</div>
      <table class="specialWorkloadTable">
        <thead v-if="dataExists">
          <tr>
            <th v-for="(item, index) in tableHeader" :key="index">
              {{ item }}
            </th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="(object, index) in tableData" :key="index">
            <!-- 序号 -->
            <td style="padding-top: 8px; font-weight: 500">
              {{ index + 1 }}
            </td>
            <!-- 只读数据 -->
            <td v-for="(key, index) in readonly" :key="index + 1000">
              <input type="text" v-model="object[key]" disabled />
            </td>
            <!-- 不需要特殊处理的可编辑数据 -->
            <td v-for="(key, index) in editable" :key="index + 2000">
              <input
                type="text"
                v-model="object[key]"
                class="editable"
                disabled
              />
            </td>
            <!-- 需要特殊处理的可编辑数据 -->
            <!-- 不使用input[type="text"]的 -->
            <td>
              <textarea
                v-model="object['briefIntroduction']"
                cols="30"
                rows="5"
                class="editable"
                disabled
              ></textarea>
            </td>
            <td>
              <textarea
                v-model="object['remarks']"
                cols="30"
                rows="5"
                class="editable"
                disabled
              ></textarea>
            </td>
            <!-- 文件下载 -->
            <td style="text-align: center; padding: 30px 5px">
              <button class="file" @click="downloadFile(index)">
                下载附件
              </button>
            </td>
            <!-- 评教分 -->
            <td class="score">
              <ul>
                <li v-for="(item, index) in object['somePeople']" :key="index">
                  <label>{{ index == 0 ? "负责人" : "参与人" }}</label
                  ><input
                    type="text"
                    v-model="item.teacherName"
                    style="
                      width: 100px;
                      border-right: 1px solid rgba(128, 128, 128, 0.212);
                      border-radius: 5px;
                    "
                    class="editable"
                    disabled
                  />
                  <label>排序：</label
                  ><input
                    type="number"
                    v-model="item.authorOrder"
                    style="
                      width: 50px;
                      border-right: 1px solid rgba(128, 128, 128, 0.212);
                      border-radius: 5px;
                    "
                    class="editable"
                    disabled
                  />
                  <label>教分：</label
                  ><input
                    type="text"
                    v-model="item.teachingScores"
                    style="
                      width: 100px;
                      border-right: 1px solid rgba(128, 128, 128, 0.212);
                      border-radius: 5px;
                    "
                    class="editable"
                    disabled
                  />
                </li>
              </ul>
            </td>
            <!-- 编辑保存控件 -->
            <td>
              <button
                @click="edit(index)"
                class="universalBlueBtn edit"
                id="edit"
              >
                编&nbsp;辑
              </button>
              <button
                @click="save(index)"
                class="universalBlueBtn edit"
                id="save"
                style="display: none"
              >
                保&nbsp;存
              </button>
            </td>
          </tr>
        </tbody>
      </table>
    </div>
    <Pagination
      :currentPage="currentPage"
      :allPageCount="allPageCount"
      @pageAfter="pageAfter"
      @pageBefore="pageBefore"
    ></Pagination>
  </div>
</template>

<script>
import FilterWithSearch from "../SharingComponent/FilterWithSearch.vue";
import TableStatisticsBar from "../SharingComponent/TableStatisticsBar.vue";
import DownloadExcelFile from "../SharingComponent/DownloadExcelFile.vue";
import Pagination from "../SharingComponent/Pagination.vue";
export default {
  name: "AuditWorkload",
  components: {
    FilterWithSearch,
    TableStatisticsBar,
    DownloadExcelFile,
    Pagination,
  },
  data() {
    return {
      //上报截止时间
      ddl: "2022-12-31",
      //当前查询的状态
      searchKeywords: ["教学业绩类型", "申报人", "审核状态"],
      yearChosen: this.$currentYear,
      searchKeywordChosen: "",
      searchValueChosen: "",
      //Pagination
      currentPage: 1,
      allPageCount: 1,
      //自定义的展示的表头
      tableHeader: [
        "序号",
        "审核状态",
        "申报人",
        "申报时间",
        "教学业绩类型",
        "成果/作品/参赛项目/参评项目/论文/专著名称",
        "级别",
        "课程/项目/奖项/竞赛/论文指导类别",
        "课程/项目/奖项/竞赛详细名称",
        "获奖等级",
        "获奖/获评/出版日期",
        "项目进展",
        "授奖单位",
        "刊物/出版社名称",
        "刊物期数/出版版次",
        "ISBN号",
        "所获荣誉",
        "指导学生团队名",
        "指导学生姓名",
        // "指导学生学号",
        "内容简介",
        "备注",
        "相关文件",
        "审核",
      ],
      //iterable的字段，顺序是按照tableHeader来的，一些需要特殊处理的字段不在其中
      readonly: ["status", "declarantName", "reportTime"],
      editable: [
        "type",
        "achievementName",
        "level",
        "projectCategory",
        "projectName",
        "awardLevel",
        "awardDate",
        "projectStatus",
        "awardApartment",
        "publicationName",
        "publicationsNumber",
        "isbn",
        "receivingHonor",
        "guidingStudentTeam",
        "guidingStudentName",
        // "briefIntroduction",
        // guidingStudentId,
        // "remarks",
        // filePath,
        // somePeople
      ],
      tableData: [
        // {
        //   reportTime: "hhh",
        //   declarantName: "hhhh",
        //   type: "BB1",
        //   achievementName: "hhhh",
        //   level: "hhhh",
        //   projectCategory: "hhhhh",
        //   projectName: "hhhhh",
        //   awardLevel: "hhhhh",
        //   awardDate: "hhhhhh",
        //   projectStatus: "hhhhh",
        //   awardApartment: "hhhhhh",
        //   publicationName: "hhhhh",
        //   publicationsNumber: "hhhhh",
        //   isbn: "hhhhh",
        //   briefIntroduction:
        //     "hhhThis example uses a fixed table layout, combined with the width propert a fixed table layout, combined with the width propert a fixed table layout, combined with the width property, to restrict the table's width. The text-overflow property is used to apply an ellipsis to words that are too long to fit. If the table layout were auto, the table would grow to accommodate its contents, despite the specified width.hh",
        //   receivingHonor: "hhhhhh",
        //   guidingStudentTeam: "hhhhhh",
        //   guidingStudentName: "hhhhhh",
        //   guidingStudentId: "hhhhhhh",
        //   auditStatus: "hhhhhhh",
        //   remarks: "hhhhhhh",
        //   filePath: "hhhhhh",
        //   somePeople: [
        //     {
        //   teacherName: "hhhhhh",
        //   order: 0,
        //   score: "hhhhh",
        // },
        // {
        //   teacherName: "hhhhhh",
        //   order: 1,
        //     //   score: "hhhhh",
        //     // },
        //   ],
        // },
      ],
      dataExists: false,
      totalItems: 0,
      noDataHint: "",
    };
  },
  computed: {
    //展示在统计栏的数据
    keyValuePairs() {
      return [
        {
          key: "查询结果数",
          value: this.totalItems,
        },
        {
          key: "总页数",
          value: this.allPageCount,
        },
      ];
    },
  },
  methods: {
    //编辑
    edit(index) {
      // 调整选中的行的样式与状态
      document
        .querySelectorAll(`table tr:nth-child(${index + 1}) .editable`)
        .forEach((item) => {
          item.classList.add("editing");
          item.disabled = false;
        });
      document.querySelector(
        `table tr:nth-child(${index + 1}) #edit`
      ).style.display = "none";
      document.querySelector(
        `table tr:nth-child(${index + 1}) #save`
      ).style.display = "block";
    },
    //保存编辑
    save(index) {
      // 调整选中的行的样式与状态
      document
        .querySelectorAll(`table tr:nth-child(${index + 1}) .editable`)
        .forEach((item) => {
          item.classList.remove("editing");
          item.disabled = true;
        });
      document.querySelector(
        `table tr:nth-child(${index + 1}) #edit`
      ).style.display = "block";
      document.querySelector(
        `table tr:nth-child(${index + 1}) #save`
      ).style.display = "none";
      //向后端同步数据
      this.$axios
        .post(
          "http://abcs.vaiwan.com/special-workload/mark",
          this.tableData[index]
        )
        .then((res) => {
          console.log(res);
        });
    },
    //分页查询
    getTableData() {
      const formData = new FormData();
      formData.append("year", this.yearChosen);
      formData.append("pageNumber", this.currentPage);
      // 如果搜索值不为空
      if (this.searchValueChosen != "") {
        switch (this.searchKeywordChosen) {
          case "教学业绩类型":
            formData.append("type", this.searchValue);
            break;
          case "申报人":
            formData.append("declarantName", this.searchValue);
            break;
        }
      }
      this.$axios
        .post(`http://abwjkds.vaiwan.com/special-join/select`, formData)
        .then((res) => {
          console.log(res);
          if (res.data.response.code == 200) {
            this.dataExists = true;
            this.tableData = res.data.data.records;
            this.allPageCount = res.data.data.pages;
            this.totalItems = res.data.data.total;
          } else {
            this.dataExists = false;
            this.tableData = [];
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
          // this.dataExists = false;
          this.dataExists = true;
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
    pageBefore() {
      this.currentPage = this.currentPage - 1;
      this.getTableData();
    },
    pageAfter() {
      this.currentPage = this.currentPage + 1;
      this.getTableData();
    },
    //导出Excel文件
    exportFile(filename) {
      const formData = new FormData();
      formData.append("naturalYear", this.yearChosen);
      this.$axios
        .post(`http://abcd.vaiwan.com/total/records`, formData)
        .then((res) => {
          console.log(res);
          this.$exportExcelFile(
            res.data.data,
            this.workloadTableHeader,
            filename
          );
        });
    },
  },
  created() {
    this.yearChosen = this.$currentYear;
    this.currentPage = 1;
    this.getTableData();
  },
};
</script>

<style scoped>
.tableWrapper {
  position: relative;
  overflow: scroll;
  height: 86vh;
  padding-bottom: 15px;
  transition: all 0.5s;
}
.componentSubsection.ddl {
  width: 530px;
  font-weight: 500;
}
.ddl button {
  margin-left: 50px;
  width: 50px;
  height: 25px;
  border: 1px solid rgba(128, 128, 128, 0.555);
}
.ddl span {
  margin-left: 20px;
  font-size: 18px;
  font-weight: 800;
  color: rgb(242, 193, 67);
}

table.specialWorkloadTable {
  width: 4000px;
  border-spacing: 0;
}
table.specialWorkloadTable thead {
  position: sticky;
  top: 0;
  font-size: 10px;
  font-weight: 500;
  background-color: rgb(239, 241, 247);
}
table.specialWorkloadTable tbody {
  font-size: 13px;
  background-color: transparent;
  padding: 3px 5px;
}
table.specialWorkloadTable tbody td {
  vertical-align: top;
  padding: 0;
  padding-bottom: 10px;
}
table.specialWorkloadTable tbody td input {
  width: 100%;
  height: 30px;
  border: 1px solid rgba(128, 128, 128, 0.212);
  border-right: 0;
  border-radius: 0;
  text-align: center;
}

table.specialWorkloadTable tbody td label {
  font-weight: 500;
  font-size: 13px;
  margin-left: 5px;
}
table.specialWorkloadTable tbody td ul li {
  margin-bottom: 5px;
}
textarea {
  width: 100%;
  overflow: scroll;
  border-radius: 0;
  border: 1px solid rgba(128, 128, 128, 0.212);
}
table.specialWorkloadTable tbody td input.editing {
  border: 2px solid rgba(95, 120, 130, 0.32);
  border-radius: 5px;
}
textarea.editing {
  border: 2px solid rgba(95, 120, 130, 0.32);
  border-radius: 5px;
}
button.file {
  border: 0;
  text-decoration: underline;
  cursor: pointer;
  color: rgb(96, 157, 135);
}
button.file:hover {
  color: rgb(33, 164, 116);
}
button.edit {
  width: 50px;
  height: 25px;
  border: 1px solid rgba(128, 128, 128, 0.267);
  /* border-radius: 5px; */
}
.auditDownloadTool {
  margin-top: 20px;
  padding-top: 15px;
  max-width: 800px;
  border-top: 1px solid rgba(128, 128, 128, 0.185);
}
</style>