<template>
    <div id="audit-table">
        <div class="app-section">
            <OperationBar />
        </div>
        <div class="overflow-wrapper">
            <table class="outer-table">
                <thead class="outer-table-head">
                    <tr>
                        <th>详情</th>
                        <th v-for="item in constantHeaders" :key="item.key">{{ item.label }}</th>
                        <th>附件</th>
                        <th>审核</th>
                    </tr>
                </thead>
                <tbody class="outer-table-body" v-for="(record, index) in tableData" :key="record.iddwer34t5">
                    <!-- 公共单元 -->
                    <tr class="constant-part">
                        <td class="detail">
                            <button @click="toggleDetail(index)"><span class="icon"
                                    :style="{ transform: detailUnfolded[index] ? 'rotate(180deg)' : 'rotate(0deg)' }"></span></button>
                        </td>
                        <td v-for="item in constantHeaders" :key="item.key">
                            {{ record[item.key] }}
                        </td>
                        <td class="downloadFiles"><button @click="downloadFiles(record.fileName)">下载</button>
                        </td>
                        <td class="audit">
                            <button @click="toggleAudit(index)" :class="{ active: isAuditing[index] }"><span
                                    class="icon"></span></button>
                            <transition name="fadeIn">
                                <div class="audit-form" v-if="isAuditing[index]">
                                </div>
                            </transition>
                        </td>
                    </tr>
                    <!-- 嵌套表格，包含不同type特殊工作量各自的单元 -->
                    <tr class="specific-part" v-show='detailUnfolded[index]'>
                        <td :colspan="3 + constantHeaders.length">
                            <table class="inner-table">
                                <thead class="inner-table-head">
                                    <th v-for="formItemObject in specificHeaders.get(record.type)">
                                        {{ formItemObject.label }}</th>
                                </thead>
                                <tbody>
                                    <tr>
                                        <td v-for="formItemObject in specificHeaders.get(record.type)">
                                            {{ record[formItemObject.key] }}</td>
                                    </tr>
                                </tbody>
                            </table>
                        </td>
                    </tr>
                </tbody>
            </table>
        </div>
    </div>
</template>
<script>
import OperationBar from './OperationBar.vue'
export default {
    components: {
        OperationBar
    },
    name: "AuditTable",
    data() {
        return {
            // constantHeader部分
            constantHeaders: this.$store.getters.constantHeaders_special,
            detailUnfolded: [],   //是否展开某项详情
            isAuditing: [],  //是否正在审核某条项目
            //嵌套table部分
            specificHeaders: this.$store.getters.specificHeaders_special,
            //假数据
            tableData: [
                {
                    id: '1',
                    status: "未审核",
                    declarantName: '丁建利',
                    reportTime: '2022-10-1',
                    type: "BB1",
                    filePath: 'https:...',
                    projectName: '企业-教育部课程建设项目',
                    awardDate: '2022-9',
                    projectCategory: '课程建设项目',
                    projectStatus: '结题',
                    awardLevel: '国家级',
                    somePeople: [
                        {
                            teacherName: '张三',
                            authorOrder: 0
                        },
                        {
                            teacherName: '李四',
                            authorOrder: 1,
                        },
                        {
                            teacherName: '王五',
                            authorOrder: 2,
                        }
                    ],
                    fileNames: ['1.txt', '2.jpg']
                },
                {
                    id: '1',
                    status: "未审核",
                    declarantName: '丁建利',
                    reportTime: '2022-10-1',
                    type: "BB1",
                    filePath: 'https:...',
                    projectName: '企业-教育部课程建设项目',
                    awardDate: '2022-9',
                    projectCategory: '课程建设项目',
                    projectStatus: '结题',
                    awardLevel: '国家级',
                    somePeople: [
                        {
                            teacherName: '张三',
                            authorOrder: 0
                        },
                        {
                            teacherName: '李四',
                            authorOrder: 1,
                        },
                        {
                            teacherName: '王五',
                            authorOrder: 2,
                        }
                    ],
                    fileNames: ['1.txt', '2.jpg']
                }
            ],
            //分页
            pageSize: 20,
        }
    },
    methods: {
        enterNewPage() {   //每次切换页面（包括首次进入页面），都要初始化表格状态
            for (let i = 0; i < this.pageSize; i++) {
                this.detailUnfolded.push(false);
                this.isAuditing.push(false);
            }
        },
        toggleDetail(index) {
            let newValue = !this.detailUnfolded[index]
            this.detailUnfolded.splice(index, 1, newValue)
        },
        toggleAudit(index) {
            let newValue = !this.isAuditing[index];
            this.isAuditing.splice(index, 1, newValue);
        },
        //下载某一项填报的附件
        downloadFiles(fileName) {
            if (fileName) {   //如果有文件的话
                //do something
            }
            return;
        }


    },
    created() {
        this.enterNewPage();
    }
}
</script>
<style scoped>
.overflow-wrapper {
    width: 100%;
    max-height: 45vh;
    overflow: auto;
    padding-bottom: 100px;
}

/* 共有样式 */
table {
    white-space: nowrap;
    text-align: center;
    font-size: 13px;
    transition: all 0.2s;
}

th,
td {
    padding: 10px 50px;
    transition: all 0.2s;
}

/* 表格整体样式（反映布局结构，很重要） */
.outer-table {
    border-collapse: collapse;
}

.outer-table-head th {
    background-color: #677f84;
    color: whitesmoke;
}

.outer-table-head th:first-child {
    border-top-left-radius: 1em;
}

.outer-table-head th:last-child {
    border-top-right-radius: 1em;
}

.constant-part td,
.specific-part td {
    background-color: white;
}

.constant-part td {
    border-top: 1px solid #ccc;
}

.inner-table {
    position: relative;
    left: 50%;
    transform: translateX(-50%);
    min-width: 900px;
    border: 1px solid #ccc;
    border-radius: 1em;
    overflow: hidden;
    font-size: 12px;
}

.inner-table th,
.inner-table td {
    padding: 10px 20px;
}

/* 细节展开按钮、审核按钮 */
.constant-part .detail button,
.constant-part .audit button {
    padding: 2px;
    font-family: 'icomoon';
    background-color: #f4f4f5;
    color: #aaa;
}


.outer-table-body .detail button span {
    display: block;
    transition: all 0.2s;
}

.constant-part .audit button.active {
    background-color: #677f84;
    color: white;
}

/* 下载附件按钮 */
.constant-part .downloadFiles button {
    padding: 0;
    background-color: transparent;
    border: 0;
    font-size: 12px;
    color: #447161;
}

.constant-part .downloadFiles button:hover {
    text-decoration: underline;
}

/* 审核表单 */
.constant-part td.audit {
    position: relative;
}

.constant-part td.audit .audit-form {
    position: absolute;
    right: 32px;
    top: 45px;
    z-index: 100;
    width: 300px;
    min-height: 100px;
    padding-top: 10px;
    background-color: white;
    border-radius: 10px;
    border: 1px solid #ddd;
    box-shadow: 0px 2px 10px #bbb;
}

.constant-part td.audit .audit-form:before {
    content: '';
    position: absolute;
    top: -5.5px;
    right: 20px;
    width: 8px;
    height: 8px;
    border-top: 1px solid #ddd;
    border-right: 1px solid #ddd;
    border-bottom-color: #dadde6;
    background-color: white;
    transform: rotate(-45deg);
}
</style>
