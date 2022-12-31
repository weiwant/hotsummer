<template>
    <div id="message">
        <div class="app-view-container">
            <TableFilter :yearOnly="true" @search="search" />
            <StatisticBar :yearChosen="yearChosen" />
            <FeedbackSection :yearChosen="yearChosen" />
        </div>
    </div>
</template>
<script>
import TableFilter from '@/components/table/TableFilter.vue';
import StatisticBar from './components/StatisticBar.vue';
import FeedbackSection from './components/FeedbackSection.vue';
export default {
    name: "Message",
    components: { TableFilter, StatisticBar, FeedbackSection },
    data() {
        return {
            // 消息列表
            yearChosen: this.$store.getters.currentYear,
            typeChosen: 'all',
            originalMessageArray: [
                {
                    id: 1,
                    title: '2022年度教学工作量再核查申请',
                    sendContent: '示例文字示例文字示例文字示例文字示例文字示例文字示例文字示例文字示例文字示例文字示例文字示例文字示例文字示例文字示例文字示例文字示例文字示例文字示例文字示例文字示例文字示例文字示例文字示例文字',
                    sender: {
                        name: '张三',
                        id: 123,
                        faculty: '计院'
                    },
                    sendDate: '2022-01-23',
                    responder: {
                        name: '张三',
                        id: 123,
                    },
                    responseContent: '示例文字示例文字示例文字示例文字示例文字示例文字示例文字示例文字示例文字示例文字示例文字示例文字示例文字示例文字示例文字示例文字示例文字示例文字示例文字示例文字示例文字示例文字示例文字示例文字示例文字示例文字示例文字示例文字示例文字示例文字示例文字示例文字示例文字示例文字示例文字示例文字',
                    responseDate: '2022-01-24',
                    sendStatus: 1,
                    responseStatus: 0,
                },
                {
                    id: 1,
                    title: '2022年度教学工作量再核查申请',
                    sendContent: '示例文字示例文字示例文字示例文字示例文字示例文字示例文字示例文字示例文字示例文字示例文字示例文字示例文字示例文字示例文字示例文字示例文字示例文字示例文字示例文字示例文字示例文字示例文字示例文字',
                    sender: {
                        name: '张三',
                        id: 123,
                        faculty: '计院'
                    },
                    sendDate: '2022-01-23',
                    responder: {
                        name: '张三',
                        id: 123,
                    },
                    responseContent: '示例文字示例文字示例文字示例文字示例文字示例文字示例文字示例文字示例文字示例文字示例文字示例文字示例文字示例文字示例文字示例文字示例文字示例文字示例文字示例文字示例文字示例文字示例文字示例文字示例文字示例文字示例文字示例文字示例文字示例文字示例文字示例文字示例文字示例文字示例文字示例文字',
                    responseDate: '2022-01-24',
                    sendStatus: 1,
                    responseStatus: 1,
                },

            ],
            showLoading: false,
            // 消息详情
            showDetail: false,
            messageChosen: null,
        }
    },
    computed: {
        displayedMessageArray() {
            switch (this.typeChosen) {
                case 'all':
                    return this.originalMessageArray;
                case 'not-replied':
                    return this.originalMessageArray.filter(item => { return item.responseStatus == -1 });
                case 'replied':
                    return this.originalMessageArray.filter(item => { return item.responseStatus != -1 });
            }
        }
    },
    methods: {
        search(year) {
            if (this.yearChosen == year) return;
            this.yearChosen = year;
        }
    },
    created() {

    }

}
</script>
<style scoped>
#feedback-section {
    margin-top: 20px;
}
</style>
