<template>
    <div id="operation-bar">
        <div class="download-files">
            <button class="withBorder white"><span class="icon" @click="downloadFiles"></span>&nbsp;批量下载审核附件</button>
            <label class="note">（注：{{ tableInfo.yearChosen }}年度<span class="emphasis">所有</span>审核附件）</label>
        </div>
        <div class="export-result">
            <button class="withBorder white" @cilck="exportResult">&nbsp;导出审核结果</button>
        </div>
    </div>
</template>
<script>
import { downloadFiles_yearly, exportAuditingResult } from '@/api/special-workload/audit';
import { Message } from 'element-ui';
export default {
    name: "AuditTableOperationBar",
    inject: ['tableInfo'],
    data() {
        return {
        }
    },
    methods: {
        downloadFiles() {
            const formData = new FormData();
            formData.append("year", tableInfo.yearChosen);
            downloadFiles_yearly(formData).then(data => {
                // 组装a标签
                let elink = document.createElement("a");
                // 设置下载文件名
                elink.download = this.tableInfo.yearChosen + "年度特殊工作量审批记录.zip";
                elink.style.display = "none";
                let blob = new Blob([data], { type: "application/zip" });
                elink.href = URL.createObjectURL(blob);
                document.body.appendChild(elink);
                elink.click();
                document.body.removeChild(elink);
            }).catch(() => {
                Message({
                    message: "附件下载失败！",
                    type: 'error',
                    duration: 2000
                })
            })
        },
        exportResult() {
            const data = {
                year: this.tableInfo.yearChosen
            }
            exportAuditingResult(data).then((data) => {
            }).catch(() => {
            })
        }
    }
}
</script>
<style scoped>
#operation-bar {
    display: flex;
    flex-wrap: wrap;
    gap: 10px 20px;
}



.note {
    color: #aaa;
    font-size: 12px;
    vertical-align: bottom;
}

.emphasis {
    color: #ba9291;
}
</style>
