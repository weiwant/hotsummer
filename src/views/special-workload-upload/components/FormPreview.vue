<template>
    <div id="form-preview">
        <div class="wrapper">
            <div class="title">
                {{ formData.projectName }}
            </div>
            <ul class="preview" @mouseover="(showDetailWrapper = true)">
                <li class="preview-item" v-for="item in previewHeaders">
                    <span class="label">{{ item.label }}</span>
                    <span class="data">{{ formData[item.key] }}</span>
                </li>
            </ul>
            <transition name="fadeIn">
                <div class="detail" v-if="showDetailWrapper" @mouseleave="(showDetailWrapper = false)">
                    <button class="withBorder white" @click="showDetail">查看详情</button>
                </div>
            </transition>
            <div class="operation">
                <button class="noBorder " @click="() => this.showOperationList = !this.showOperationList"> <span
                        class="icon"></span></button>
                <template v-if="showOperationList">
                    <transition name="fadeIn">
                        <ul class="operation-list" v-if="status === '仅保存'">
                            <li><button class="noBorder green" @click="submitProject">提交</button></li>
                            <li><button class="noBorder green" @click="deleteProject">删除</button></li>
                        </ul>
                        <ul class="operation-list" v-else-if="status === '待审核'">
                            <li><button class="noBorder green">撤销</button></li>
                        </ul>
                        <ul class="operation-list" v-else-if="status === '已审核'">
                            <li><button class="noBorder green">...</button></li>
                        </ul>
                    </transition>
                </template>
            </div>
        </div>
    </div>
</template>
<script>
export default {
    name: "UploadWorkloadFormPreview",
    props: {
        //历史上报的数据对象
        formData: {
            type: Object,
            required: true
        },
        status: {
            required: true,
        }
    },
    data() {
        return {
            type: this.formData.type,
            previewHeaders: this.$store.getters.previewHeaders_special, //所有特殊工作量类型的共有信息，作为preview展示
            showDetailWrapper: false,
            showOperationList: false,
        }
    },
    methods: {
        // 显示某一条上报的详情
        showDetail() {
            this.$emit('showDetail')
        },
        submitProject() {
            this.showOperationList = false;
            this.$confirm('是否确定提交该条上报？', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
            }).then(() => {

                /*向后端提交数据 */
                //成功后
                this.$message({
                    type: 'success',
                    message: '提交成功!'
                });
                this.$emit('refreshArrayData')
            }).catch(() => {
                this.$message({
                    type: 'info',
                    message: '已取消提交'
                });
            });
        },
        deleteProject() {
            this.showOperationList = false;

            this.$confirm('此操作将永久删除该条记录, 是否继续?', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
            }).then(() => {
                /*向后端提交数据 */
                //成功后
                this.$message({
                    type: 'success',
                    message: '删除成功!'
                });
                this.$emit('refreshArrayData');

            }).catch(() => {
                this.$message({
                    type: 'info',
                    message: '已取消删除'
                });
            });
        }
    }
}
</script>
<style scoped lang="scss">
$WIDTH :250px;
$HEIGHT: 200px;
$titleHeightRatio: 3/10;
$previewHeightRatio: 5/10;
$operationHeightRatio: 2/10;

.wrapper {
    position: relative;
    width: $WIDTH;
    height: $HEIGHT;
    border: 1px solid #eee;
    border-radius: 5px;
    background-color: white;
    box-shadow: 1px 1px 5px #ddd;
}

.title {
    height: $HEIGHT*$titleHeightRatio;
    line-height: 60px;
    padding-left: 20px;
    font-weight: 500;
    font-size: 18px;
    border-bottom: 1px solid #eee;
}

.preview {
    display: flex;
    flex-direction: column;
    justify-content: center;
    gap: 5px;
    height: $HEIGHT*$previewHeightRatio;
    padding: 10px;
    font-size: 14px;

    .label {
        color: #777;
        font-weight: 500;
        margin-right: 10px;
    }

    .data {
        color: #999;
    }
}

.operation {
    position: relative;
    height: $HEIGHT*$operationHeightRatio;
    line-height: 40px;
    background-color: #f6f9fa;
    text-align: right;

    .icon {
        margin-right: 20px;
        font-family: 'icomoon';
        cursor: pointer;
        color: #555;
        transition: all 0.2s;

        &:hover {
            color: #777;
        }
    }
}

.operation-list {
    position: absolute;
    z-index: 1000;
    right: 0;
    top: $HEIGHT*$operationHeightRatio - 10px;
    background-color: white;
    border-radius: 5px;
    border: 1px solid #eee;
}

.detail {
    position: absolute;
    display: flex;
    justify-content: center;
    align-items: center;
    top: $HEIGHT*$titleHeightRatio;
    width: $WIDTH;
    height: $HEIGHT*$previewHeightRatio;
    backdrop-filter: blur(3px);
}
</style>
