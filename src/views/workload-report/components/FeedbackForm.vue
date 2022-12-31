<template>
    <div id="feedback-form">
        <MessageBox :width="500" :minHeight="400" title='反馈表单填写' :showLoading="showLoading"
            @cancle="() => { this.$emit('hide') }">
            <div class="form-item">
                <label>主题</label>
                <el-input v-model="title" placeholder="请输入内容"></el-input>
            </div>
            <div class="form-item">
                <label>内容</label>
                <div class="notice"><span class="icon"></span>每年数据的反馈机会只有<span
                        class="emphasis">1次</span>，请详细准确地阐述您的问题，便于管理员及时处理！</div>
                <el-input type="textarea" :autosize="{ minRows: 4, maxRows: 6 }" placeholder="请输入内容" v-model="content">
                </el-input>
            </div>

            <div class="form-footer">
                <button class="withBorder green" @click="sendFeedback">提&nbsp;交</button>
            </div>
        </MessageBox>
    </div>
</template>
<script>
import MessageBox from '@/components/MessageBox.vue';
import { sendFeedbackToAdmin } from '@/api/workload-report';
export default {
    name: "ReportFeedbackform",
    components: { MessageBox },
    props: {
        yearChosen: {
            type: String,
            required: true
        }
    },
    data() {
        return {
            title: "教学工作量问题反馈",
            content: null,
            showLoading: false,
        }
    },
    methods: {
        sendFeedback() {
            let requestData = {
                userId: this.$store.getters.userInfo.userId,
                naturalYear: this.yearChosen,
                title: this.title,
                sendContent: this.content
            }

            sendFeedbackToAdmin(requestData).then(() => {
                this.$emit('successfullySendFeedback')
            }).catch(() => {

            })

        }
    }
}
</script>
<style scoped lang="scss">
@import "@/style/variables.scss";

.form-wrapper {
    width: 300px;
    height: 400px;
    background-color: #fff;
}

.form-item {
    margin-bottom: 30px;

    label {
        display: block;
        font-weight: 500;
        margin-bottom: 10px;
    }

    .notice {
        color: rgb(174, 173, 173);
        font-size: 13px;
        line-height: 1.44em;
        margin-bottom: 20px;

        .icon {
            color: $yellow;
            margin-right: 5px;
        }

        .emphasis {
            color: $red;
        }
    }
}

.form-footer {
    position: absolute;
    bottom: -60px;
    right: 0;
    text-align: right;
}
</style>
