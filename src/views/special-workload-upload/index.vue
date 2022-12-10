<template>
    <div id="special-workload-upload" class="app-view-container">
        <!-- 事件委托 -->
        <div class="selector" @click="(event) => { toggleStatus(event) }">
            <div class="selector-item" v-for="status in workloadStatus" :data-status="status" :key="status"
                :class="{ active: currentStatus === status }">{{ status }}</div>
        </div>
        <SpecialworkloadUploadList :status="this.currentStatus" />
    </div>
</template>
<script>
import SpecialworkloadUploadList from './components/WorkloadList.vue'
export default {
    name: "UploadSpecialWorkload",
    components: {
        SpecialworkloadUploadList
    },
    data() {
        return {
            showForm: false,
            workloadTypeChosen: 'BB1',
            workloadStatus: this.$store.getters.workloadStatus_special, //特殊工作量上报的状态
            currentStatus: this.$store.getters.workloadStatus_special[0]  //选中要查看的状态,默认第一项
        }
    },
    methods: {
        toggleStatus(event) {
            let status = event.target.dataset.status
            //更改状态（主要为了修改样式）
            this.currentStatus = status;

        }
    }

}
</script>
<style scoped>
.selector {
    display: inline-block;
    padding: 5px;
    background-color: #e3e8ed;
    border-radius: 10px;
    margin-top: 15px;
}

.selector-item {
    display: inline-block;
    padding: 5px 10px;
    margin-right: 5px;
    background-color: #e3e8ed;
    color: #9399a2;
    border-radius: 5px;
    font-size: 15px;
    font-weight: 500;
    cursor: pointer;
    transition: all 0.2s;
}

.selector-item:last-child {
    margin-right: 0;
}

.selector-item.active {
    color: #303d4e;
    background-color: white;
}
</style>
