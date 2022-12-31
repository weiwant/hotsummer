<template>
    <div id="feedback-section">
        <div class="app-section-title">
            反馈消息列表
        </div>

    </div>
</template>
<script>
import { Pagination } from 'element-ui';
import FeedbackTable from './FeedbackTable.vue'
export default {
    name: "AdminFeedbackSection",
    components: { Pagination, FeedbackTable },
    props: {
        yearChosen: {
            type: String,
            required: true
        }
    },
}
</script>
<style scoped>

</style>
