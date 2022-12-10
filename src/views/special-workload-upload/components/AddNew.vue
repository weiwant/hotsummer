<template>
    <div id="add-new">
        <div class="wrapper">
            <div class="title">
                <span class="icon"></span> 新项目
            </div>
            <div class="input">
                <el-select v-model="value" placeholder="请选择上报类型">
                    <el-option v-for="item in options" :key="item.key" :label="item.key + item.name" :value="item.key">
                    </el-option>
                </el-select>
            </div>
            <div class="confirm">
                <button class="withBorder themeTextColor" @click="showForm" :disabled="!this.value">确认创建</button>
            </div>
        </div>
    </div>
</template>
<script>
export default {
    name: "UploadWorkloadAddNew",
    data() {
        return {
            options: this.$store.getters.workloadTypes_special,
            value: null,
        }
    },
    methods: {
        showForm() {
            this.$emit('addNew', this.value)
        }
    }
}
</script>
<style scoped lang="scss">
.wrapper {
    position: relative;
    width: 250px;
    height: 200px;
    padding-left: 20px;
    border: 2px solid #eee;
    border-style: dashed;
    border-radius: 5px;
    background-color: white;
}

.icon {
    font-family: 'icomoon';
    vertical-align: bottom;
    margin-right: 5px;
}

.title {
    margin-top: 20px;
    font-weight: 500;
}

.input {
    margin-top: 20px;
}

.confirm {
    position: absolute;
    bottom: 20px;
}
</style>
