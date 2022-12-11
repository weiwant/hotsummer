<template>
    <div id="specialworkload-upload-list">
        <div class="flex-wrapper">
            <AddNew v-if="status === '仅保存'" @addNew="addNew" />
            <FormPreview v-for="(item, index) in formDataArray" :formData="item" :status='status'
                @showDetail="showDetail(index)" />
        </div>
        <WorkloadForm :type="formDataType" :formStatus="formStatus" :formData="formData" v-if="showForm"
            @hide="() => this.showForm = false" @refreshArrayData="getArrayData" />
    </div>
</template>
<script>
import WorkloadForm from './form/WorkloadForm.vue';
import AddNew from './AddNew.vue'
import FormPreview from './FormPreview.vue';
export default {
    name: "SpecialworkloadUploadList",
    props: ['status'],
    components: {
        FormPreview,
        AddNew,
        WorkloadForm
    },
    data() {
        return {
            // 是否展示表单
            showForm: false,
            // 表单的status：新项目、仅保存、待审核、已审核
            formStatus: null,
            // 表单对应的工作量类型
            formDataType: null,
            // 表单的显示数据
            formData: null,
            // 当前status下的历史上报数据
            formDataArray: [
                {
                    type: 'BB1',
                    projectName: '测试项目名称',
                    status: '仅保存',
                    declarantName: '张三',
                    reportTime: '2022-01-23',
                    awardDate: '2022-01',
                    projectCategory: '课程建设项目',
                    projectStatus: '建设中',
                    awardLevel: '国家级',
                },
                {
                    type: 'BB1',
                    projectName: '测试项目名称',
                    status: '仅保存',
                    declarantName: '张三',
                    reportTime: '2022-01-23',
                    awardDate: '2022-01',
                    projectCategory: '课程建设项目',
                    projectStatus: '建设中',
                    awardLevel: '国家级',
                },
                {
                    type: 'BB1',
                    projectName: '测试项目名称',
                    status: '仅保存',
                    declarantName: '张三',
                    reportTime: '2022-01-23',
                    awardDate: '2022-01',
                    projectCategory: '课程建设项目',
                    projectStatus: '建设中',
                    awardLevel: '国家级',
                },

            ]
        }
    },
    methods: {
        //获取列表数据
        getArrayData() {

        },
        // 添加新项目
        addNew(type) {
            this.formStatus = '新项目'
            this.formDataType = type;
            this.formData = null;
            this.showForm = true;

        },
        //查看上报详细信息
        showDetail(index) {
            this.formStatus = this.status
            this.formDataType = this.formDataArray[index].type;
            this.formData = this.formDataArray[index];
            this.showForm = true
        },

    }
}
</script>
<style scoped>
.flex-wrapper {
    display: flex;
    margin-top: 20px;
    gap: 20px;
    flex-wrap: wrap;
}
</style>
