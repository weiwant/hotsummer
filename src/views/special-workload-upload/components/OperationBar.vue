<template>
    <div id="upload-special-workload-operation-bar">
        <div class="bar-body">
            <!-- 添加上报项目 -->
            <div class="bar-item add">
                <div class="type-select">
                    <label>工作量类型</label>
                    <el-select v-model="workloadTypeChosen" placeholder="请选择工作量类型">
                        <el-option v-for="item in workloadTypes " :key="item.key" :label="item.name + item.key"
                            :value="item.key">
                        </el-option>
                    </el-select>
                </div>
                <button class="add-btn white" @click="() => { this.$emit('showForm', this.workloadTypeChosen) }"><span
                        class="icon"></span>添加项目</button>
            </div>
        </div>
    </div>
</template>
<script>
export default {
    name: "UploadSpecialWorkloadOperationBar",
    data() {
        return {
            workloadTypes: this.$store.getters.workloadTypes_special,
            workloadTypeChosen: "BB1",      //选择申报的工作量类型，默认为BB1
        }
    },
    methods: {

    }
}
</script>
<style scoped>
#upload-special-workload-operation-bar {
    margin-top: 15px;
}

.bar-item.add {
    display: flex;
    align-items: flex-end;
    gap: 10px;
}

.bar-item.add .add-btn {
    font-family: 'icomoon';
}


.bar-item.add .type-select label {
    display: block;
    padding-left: 2px;
    margin-bottom: 3px;
    font-size: 14px;
}
</style>
