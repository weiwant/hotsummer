<template>
    <div id="special-workload-form">
        <div class="mask">
            <div class="form">
                <div class="form-header">
                    <h3>新建上报项目<span class="detail">({{ fullName }})</span></h3>
                    <div class="cancle">
                        <button class="cancle-btn" @mouseenter="() => { showCancleNotification = true }"
                            @mouseleave="() => { showCancleNotification = false }" @click="() => this.$emit('cancle'
                            )"></button>
                        <transition name="fadeIn">
                            <div class="notification" v-show="showCancleNotification">
                                撤销后填报内容会丢失！
                            </div>
                        </transition>
                    </div>
                </div>
                <div class="form-body">
                    <el-form label-width="80px" label-position="left" :rules="rules" @submit.native.prevent>
                        <el-form-item v-for="item in formItems" :label="item.label" :prop="item.key">
                            <!-- 文本框 -->
                            <el-input v-if="item.inputType === 'text'" v-model="formValues[item.key]"
                                placeholder="请输入内容">
                            </el-input>
                            <!-- 文本域 -->
                            <el-input v-if="item.inputType === 'textarea'" type="textarea"
                                v-model="formValues[item.key]" placeholder="请输入内容"></el-input>
                            <!-- 单选 -->
                            <el-radio-group v-if="item.inputType === 'radio'" v-model="formValues[item.key]">
                                <el-radio v-for="option in item.radioOptions" :label="option" :key="option">{{
                                        option
                                }}
                                </el-radio>
                            </el-radio-group>
                            <!-- select -->
                            <el-select v-if='item.inputType === "select"' v-model="formValues[item.key]"
                                placeholder="请选择">
                                <el-option v-for="option in item.selectOptions" :key="option" :label="option"
                                    :value="item.value">
                                </el-option>
                            </el-select>
                            <!-- 日期 -->
                            <el-date-picker v-if="item.inputType === 'time'" v-model="formValues[item.key]"
                                :type="item.timeType" placeholder="选择日期">
                            </el-date-picker>
                            <!-- 参与人员 -->
                            <DynamicCollection v-if="item.inputType === 'dynamic'" />
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
                <div class="form-footer">
                    <div class="save">
                        <button class="save-btn dark-blue">保&nbsp;存</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
import DynamicCollection from './DynamicCollection.vue';
export default {
    name: "SpecialWorkloadForm",
    components: { DynamicCollection },
    props: {
        type: {
            type: String,
            required: true,
        }
    },
    data() {
        return {
            //撤销操作的提示的显隐
            showCancleNotification: false,
            workloadTypes: this.$store.getters.workloadTypes_special,
            formItems: this.$store.getters.formItems_special.get(this.type),
            rules: {},
            projectName: '',
            formValues: {},  //formItems是数组，按顺序遍历；value也按同样的顺序绑定，同index的key和item.key和value是一组
        }
    },
    computed: {
        fullName() {
            let name
            for (let item of this.workloadTypes) {
                if (item.key === this.type) {
                    name = item.name;
                    break
                }
            }
            return name + this.type;
        }
    },
    created() {
        for (let item of this.formItems) {
            this.rules[item.key] = item.rule
            this.formValues[item.key] = ''
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

/* 表格头部  */

.form-header {
    padding: 20px 20px;
}

.form-header h3 .detail {
    font-weight: 400;
    font-size: 16px;
    margin-left: 1em;
    color: gray;
}

.form-header .cancle {
    position: absolute;
    right: 10px;
    top: 10px;
    color: #303d4f;
}

.form-header .cancle .cancle-btn {
    font-family: 'icomoon';
    padding: 2px;
}

.form-header .cancle .cancle-btn:hover {
    background-color: #ba9291;
    color: white;
}

.form-header .cancle .notification {
    position: absolute;
    right: -10em;
    top: 30px;
    z-index: 100;
    padding: 5px 10px;
    font-size: 14px;
    background-color: white;
    border-radius: 10px;
    border: 1px solid #ddd;
    box-shadow: 0px 2px 10px #bbb;
    word-break: keep-all
}

.form-header .cancle .notification:before {
    content: '';
    position: absolute;
    top: -5.5px;
    left: 20px;
    width: 8px;
    height: 8px;
    border-top: 1px solid #ddd;
    border-right: 1px solid #ddd;
    border-bottom-color: #dadde6;
    background-color: white;
    transform: rotate(-45deg);
}

/* 表格主体 */
.form-body {
    padding: 20px;
    min-height: 200px;
    max-height: 600px;
    overflow: auto;

}



/* 表格footer */
.form-footer {
    display: flex;
    justify-content: flex-end;
    border-bottom-left-radius: 10px;
    border-bottom-right-radius: 10px;
    padding: 10px 20px;
    background-color: #eeedef;
}
</style>
