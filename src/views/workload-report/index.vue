<template>
  <div class="app-view-container">
    <TableFilter @search="searchTeachingData" :yearOnly="true" />
    <!-- 教学工作量详情 -->
    <div class="app-section-title">教学工作量详情</div>
    <PlainTable :headerArray="teachingWorkloadTableHeader" :dataArray="teachingWorkloadTableData"
      :showLoading="showLoading_teachingTable" />
    <StatusBar :status="confirmStatus" :showRedPoint="showRedPoint"
      @displayFeedbackDetail="() => showFeedbackDetail = true" />
    <OperationBar :disableConfirmBtn="disableConfirmBtn" :disableFeedbackBtn="disableFeedbackBtn"
      :yearChosen="yearChosen"
      @successfullyConfirmed="() => { confirmStatus = statusEnum.CONFIRMED; disableFeedbackBtn = disableConfirmBtn = true }"
      @successfullySendFeedback="() => { confirmStatus = statusEnum.NOT_REPLIED; disableFeedbackBtn = true }" />
    <FeedbackDetail v-if="showFeedbackDetail" :detail="feedbackDetail" :showLoading="showLoading_feedback"
      @hide="() => { displayFeedbackDetail = false }">
      <template v-slot:no-response>
        <div class="teacher-end"><span class="icon"></span>管理员暂未回复</div>
      </template>
    </FeedbackDetail>
  </div>
</template>

<script>
import { getTeachingWorkload } from "@/api/teaching-workload";
import { getConfirmStatus, getResponseStatus, getFeedbackDetail } from "@/api/workload-report"
import TableFilter from "../../components/table/TableFilter.vue";
import PlainTable from "@/components/table/PlainTable.vue";
import StatusBar from "./components/StatusBar.vue";
import OperationBar from "./components/OperationBar.vue";
import FeedbackDetail from "@/components/feedback/FeedbackDetail.vue";
export default {
  name: "TeacherWorkloadTable",
  components: {
    TableFilter,
    PlainTable,
    StatusBar,
    OperationBar,
    FeedbackDetail
  },
  data() {
    return {
      teachingWorkloadTableHeader: this.$store.state.teaching_workload.tableHeaders_teacher,
      statusEnum: this.$store.getters.statusEnum,
      //查询条件
      filterAdded: [],
      yearChosen: this.$store.getters.currentYear,
      // 查询结果
      showLoading_teachingTable: false,
      teachingWorkloadTableData: [],
      //教学工作量确认状态
      confirmStatus: this.$store.getters.statusEnum.UNKNOWN,
      showRedPoint: false,
      disableConfirmBtn: true,
      disableFeedbackBtn: true,
      //教学工作量反馈数据
      showFeedbackDetail: false,
      showLoading_feedback: false,
      feedbackDetail: {
        id: 123,
        year: '2022',
        title: '2022年度教学工作量反馈',
        sendContent: '测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试',
        sender: '张三',
        responder: "李四",
        sendDate: '2022-01-23',
        responseDate: '2022-01-24',
        responseContent: '测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试',
        responseStatus: 1
      },
    };
  },
  methods: {
    getTeachingWorkloadData() {
      this.showLoading_teachingTable = true;
      const formData = new FormData();
      formData.append('naturalYear', this.yearChosen);
      formData.append('mainTeacherName', this.$store.getters.userInfo.name);
      getTeachingWorkload(formData).then(res => {
        this.teachingWorkloadTableData = res ? res : [];
        this.showLoading_teachingTable = false;
      }).catch(() => {

      })
    },
    setStatus() {
      let requestData = {
        userId: this.$store.getters.userInfo.userId,
        naturalYear: this.yearChosen,
      }
      getConfirmStatus(requestData).then((res) => {
        switch (Number(res.confirmStatus)) {
          case 0:
            this.confirmStatus = statusEnum.NOT_CONFIRMED;
            this.showRedPoint = false;
            this.disableConfirmBtn = this.disableFeedbackBtn = true;
            break;
          case 1:
            this.confirmStatus = statusEnum.CONFIRMED;
            this.showRedPoint = false;
            this.disableConfirmBtn = this.disableFeedbackBtn = true;
            break;
          case 2:
            this.disableConfirmBtn = false;
            this.disableFeedbackBtn = true;
            getResponseStatus(requestData).then((result) => {
              let status = result.responseStatus;
              if (status == -1) {
                this.confirmStatus = statusEnum.NOT_REPLIED;
                this.showRedPoint = false;
              } else if (status == 0) {
                this.confirmStatus = statusEnum.REPLIED;
                this.showRedPoint = true;
              } else if (status == 1) {
                this.confirmStatus = statusEnum.REPLIED;
                this.showRedPoint = false;
              } else {
                this.confirmStatus = statusEnum.UNKNOWN;
                this.showRedPoint = false;
              }
            }).catch(() => {
              this.confirmStatus = statusEnum.UNKNOWN;
              this.showRedPoint = false;
            })
            break;
          default:
            this.disableConfirmBtn = this.disableFeedbackBtn = true;
            this.confirmStatus = statusEnum.UNKNOWN;
            this.showRedPoint = false;
        }
      })
    },
    getFeedback() {
      this.showLoading_feedback = true;
      this.showFeedbackDetail = true;
      let requestData = {
        userId: this.$store.getters.userInfo.userId,
        naturalYear: this.yearChosen,
      }
      getFeedbackDetail(requestData).then((res) => {
        this.feedbackDetail = res;
        this.showLoading_feedback = false;
      }).catch(() => {
        this.feedbackDetail = null;
        this.showFeedbackDetail = false;
      })
    },
    searchTeachingData(year) {
      if (year == this.yearChosen) return;
      this.yearChosen = year;
      this.getTeachingWorkloadData();
      this.setStatus();
    },
  },

  created() {
    this.getTeachingWorkloadData();
    // this.setStatus();
  },
};
</script>

<style scoped>
#status-bar {
  margin-top: 10px;
}
</style>