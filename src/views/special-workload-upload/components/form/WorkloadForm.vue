<template>
    <div id="special-workload-form">
        <div class="mask">
            <div class="form">
                <FormHeader @cancle="() => this.$emit('hide')" :formStatus="formStatus" :formTitle="formTitle"
                    :fullName="fullName" />
                <div class="form-body">
                    <el-form label-width="80px" label-position="left" @submit.native.prevent :model="formValues"
                        :disabled="formDisabled">
                        <el-form-item v-for="item in formItems" :key="item.key" :label="item.label" :prop="item.key"
                            :rules="item.rule ? item.rule : []">
                            <!-- 文本框 -->
                            <el-input v-if="item.inputType === 'text'" v-model="formValues[item.key]"
                                @input="forceInput" placeholder="请输入内容">
                            </el-input>
                            <!-- 文本域 -->
                            <el-input v-if="item.inputType === 'textarea'" type="textarea"
                                v-model="formValues[item.key]" placeholder="请输入内容" @input="forceInput"></el-input>
                            <!-- 单选 -->
                            <el-radio-group v-if="item.inputType === 'radio'" v-model="formValues[item.key]"
                                @input="forceInput">
                                <el-radio v-for="option in item.radioOptions" :label="option" :key="option">{{
                                        option
                                }}
                                </el-radio>
                            </el-radio-group>
                            <!-- select -->
                            <el-select v-if='item.inputType === "select"' v-model="formValues[item.key]"
                                placeholder="请选择" @input="forceInput">
                                <el-option v-for="option in item.selectOptions" :key="option" :label="option"
                                    :value="item.value">
                                </el-option>
                            </el-select>
                            <!-- 日期 -->
                            <el-date-picker v-if="item.inputType === 'time'" v-model="formValues[item.key]"
                                :type="item.timeType" placeholder="选择日期" @input="forceInput">
                            </el-date-picker>
                            <!-- 参与人员 -->
                            <DynamicCollection v-if="item.inputType === 'dynamic'" :disabled="formDisabled" />
                            <!-- 附件 -->
                            <el-upload v-if='item.inputType === "file"' class="upload-demo" drag
                                action="https://jsonplaceholder.typicode.com/posts/" multiple>
                                <i class="el-icon-upload"></i>
                                <div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
                                <div class="el-upload__tip" slot="tip">只能上传jpg/png文件，且不超过500kb</div>
                            </el-upload>
                        </el-form-item>
                    </el-form>
                </div>
                <FormFooter :formStatus="formStatus" @edit="() => this.isEditing = true" @save="save" />
            </div>
        </div>
    </div>
</template>

<script>
import DynamicCollection from './DynamicCollection.vue';
import FormFooter from './FormFooter.vue';
import FormHeader from './FormHeader.vue';
import { Message } from "element-ui";
export default {
    name: "SpecialWorkloadForm",
    components: { DynamicCollection, FormFooter, FormHeader },
    props: {
        // 工作量类型
        type: {
            type: String,
            required: true,
        },
        // 表格展示数据（如果是新项目填报，则为null）
        formData: {
            required: true
        },
        formStatus: {
            type: String,
            required: true
        },
    },
    data() {
        return {
            workloadTypes: this.$store.getters.workloadTypes_special,
            formItems: this.$store.getters.formItems_special.get(this.type),
            formValues: {},  //1.在created处初始化了 2.formItems是数组，按顺序遍历；value也按同样的顺序绑定，同index的key和item.key和value是一组
            isEditing: false,
        }
    },
    computed: {
        formTitle() {
            if (this.formStatus === '新项目') return "新建上报项目"
            return this.formData.projectName
        },
        fullName() {
            let name
            for (let item of this.workloadTypes) {
                if (item.key === this.type) {
                    name = item.name;
                    break
                }
            }
            return name + this.type;
        },
        //是否禁用表单，禁用情形：仅保存的非编辑状态、待审核、已审核
        //即，只有在创建表单时，以及表单提交前的编辑状态下，才可以编辑表单
        formDisabled() {
            return !(this.formStatus === '新项目') && !(this.formStatus === '仅保存' && this.isEditing)
        }
    },
    methods: {
        forceInput() {
            this.$forceUpdate();
        },
        save() {
            /* 向后端发数据 */
            // 成功后：
            if (this.formStatus === '新项目') this.$emit('hide');
            if (this.formStatus === '仅保存') this.isEditing = false;
            this.$emit('refreshArrayData');   //获取最新的列表数据
            Message({
                message: "保存成功！",
                type: "success",
                duration: 2000,
            });
        }
    },

    created() {
        //如果formData不为空，则将value填充为formData内的值，反之（即为新建的项目时），将value初始化为空
        for (let item of this.formItems) {
            this.formValues[item.key] = this.formData ? this.formData[item.key] : null;
        }
    }

}
</script>
<style scoped>
h3 {
    margin: 0;
    padding: 0;
}

.mask {
    position: fixed;
    z-index: 1000;
    top: 0;
    right: 0;
    display: flex;
    justify-content: center;
    align-items: center;
    width: 100vw;
    height: 100vh;
    background-color: rgba(221, 221, 221, 0.497);
}

.form {
    flex-shrink: 0;
    position: relative;
    width: 800px;
    background-color: white;
    border-radius: 10px;
    box-shadow: 0px 3px 10px #ddd;
}

/* 表格主体 */
.form-body {
    padding: 20px;
    min-height: 200px;
    max-height: 600px;
    overflow: auto;
}
</style>
