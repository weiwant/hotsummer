<template>
    <div id="statistic-bar">
        <div class="app-section">
            <div class="app-section-title">统计数据</div>
            <div class="content" v-if="statisticDataExist">
                教师总人数<span class="value total">{{ confirmCount + notConfirmCount }}</span>。其中：<span
                    class="value confirm">{{
        confirmCount
}}</span>人已确认，<span class="value not-confirm">{{ notConfirmCount }}</span>人暂未确认。
            </div>
            <NoDataMessage v-else />
        </div>
    </div>
</template>
<script>
import NoDataMessage from '@/components/NoDataMessage.vue';
export default {
    name: "FeedbackStatisticBar",
    components: { NoDataMessage },
    props: {
        yearChosen: {
            type: "String",
            required: true
        }
    },
    data() {
        return {
            confirmCount: 1,
            notConfirmCount: 123,
        }
    },
    computed: {
        statisticDataExist() {
            return true;
        }
    },
    methods: {
        getStatisticData() {

        }
    },

}
</script>
<style scoped lang="scss">
@import '@/style/variables.scss';

.content {
    font-size: 14px;

    .value {
        font-weight: 500;

        &.total {
            color: $blue;
        }

        &.confirm {
            color: $green;
        }

        &.not-confirm {
            color: $yellow;
        }
    }


}
</style>
