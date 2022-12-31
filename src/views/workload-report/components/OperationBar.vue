<template>
    <div id="operation-bar">
        <div class="app-section">
            <div class="app-section-title">操作</div>
            <div class="operation-container">
                <div class="operation-item">
                    我已核实工作量数据，<button class="withBorder white" @click="confirm"
                        :disabled="disableConfirmBtn">确认无误</button>
                </div>
                <div class="operation-item">
                    发现数据存在错误，<button class="withBorder white" @click="() => showFeedbackForm = true"
                        :disabled="disableFeedbackBtn">需要反馈</button>
                </div>
            </div>

        </div>
        <FeedbackForm v-show="showFeedbackForm" @hide="() => showFeedbackForm = false"
            @successfullySendFeedback="afterFeedbackSent" :yearChosen="yearChosen" />
    </div>
</template>
<script>
import { confirmTeachingWorkload } from '@/api/workload-report';
import { MessageBox, Message } from 'element-ui';
import FeedbackForm from './FeedbackForm.vue'
export default {
    name: "ReportOperationBar",
    components: {
        FeedbackForm
    },
    data() {
        return {
            showFeedbackForm: false,
        }
    },
    props: {
        disableConfirmBtn: {
            type: Boolean,
            required: true,
        },
        disableFeedbackBtn: {
            type: Boolean,
            required: true,
        },
        yearChosen: {
            type: String,
            required: true,
        }
    },
    methods: {
        confirm() {
            MessageBox({
                title: '提示',
                message: '教学工作量数据一旦确认无法撤回，确定继续操作吗？',
                type: 'confirm',
                callback: (action) => {
                    if (action == 'confirm') {
                        let requestData = {
                            userId: this.$store.getters.userInfo.userId,
                            naturalYear: this.yearChosen,
                        }
                        confirmTeachingWorkload(requestData).then(() => {
                            this.$emit('successfullyConfirmed')
                            Message({
                                message: "确认成功！",
                                type: 'success',
                                duration: 2000
                            })
                        })
                    }
                },
            })
        },
        afterFeedbackSent() {
            Message({
                message: "反馈已发送！",
                type: 'success',
                duration: 2000
            })
            this.showFeedbackForm = false;
            this.$emit('successfullySendFeedback');
        }
    }
}
</script>
<style scoped>
.operation-container {
    display: flex;
    gap: 20px 40px;
}

.operation-item button.withBorder {
    padding: 3px 5px;
}
</style>

