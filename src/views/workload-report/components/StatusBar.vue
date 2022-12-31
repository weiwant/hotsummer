<template>
    <div id="status-bar">
        <div class="app-section">
            <div class="app-section-title">工作量数据确认状态</div>
            <div class="status-item" :class="[statusConfig.cssClass, 'status-item']"
                @mouseenter="() => { if (hasFeedback) showTip = true; }" @mouseleave="() => { showTip = false }"
                @click="showDetail">
                <div class="text">{{ statusConfig.label }}</div>
                <div class="red-point" v-if="showRedPoint"></div>
                <div class="tooltip" v-if="showTip">查看详情</div>
            </div>
        </div>
    </div>
</template>
<script>
export default {
    name: "ReportStatusBar",

    props: {
        status: {
            type: Number,
            required: true,
        },
        showRedPoint: {
            type: Boolean,
            required: true,
            default: false
        }
    },
    data() {
        return {
            showTip: false,
            statusEnum: this.$store.getters.statusEnum,
            statusConfig: this.$store.getters['workload_report/teachingStatusEnumConfig'].get(this.status)
        }
    },
    computed: {
        hasFeedback() {
            return this.status == this.statusEnum.NOT_REPLIED || this.status == this.statusEnum.REPLIED
        },

    },
    methods: {
        showDetail() {
            if (!this.hasFeedback) return;
            this.$emit('displayFeedbackDetail');
        }
    }

}
</script>
<style scoped lang="scss">
@import '@/style/variables.scss';
@import '@/style/mixins.scss';

.status-item {
    display: inline-block;
    padding-left: 10px;
    padding-right: 10px;
    position: relative;
    height: 30px;
    line-height: 30px;
    color: white;
    font-weight: 500;
    border-radius: 5px;

    &.not-confirmed {
        background-color: $red;
    }

    &.confirmed {
        background-color: $green;
    }

    &.unknown {
        background-color: gray;
    }

    &.not-replied {
        cursor: pointer;
        background-color: $yellow;

        &:hover {
            opacity: 0.9;
        }
    }

    &.replied {
        cursor: pointer;
        background-color: $blue;

        &:hover {
            opacity: 0.9;
        }
    }





}


.red-point {
    position: absolute;
    top: -5px;
    right: -5px;
    width: 10px;
    height: 10px;
    border-radius: 50%;
    background-color: $red;
}

.tooltip {
    @include singlelineTip(left, center);
    top: 0;
    right: -6em;
    color: $themeTextColor;
}
</style>

