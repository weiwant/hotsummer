<template>
    <div id="editDDL">
        <div class="app-section-title">上报截止时间</div>
        <div class="ddl">
            <el-date-picker v-model="newValue" type="date" placeholder="选择日期" :disabled="!isEditing">
            </el-date-picker>
            <button class="green" v-if="!isEditing" @click="edit">
                重&nbsp;置
            </button>
            <button class="green" v-if="isEditing" @click="cancle">
                取&nbsp;消
            </button>
            <button class="green" v-if="isEditing" @click="confirm">
                确&nbsp;认
            </button>
        </div>
    </div>
</template>
<script>
import { Message } from 'element-ui'
import { setDDL, getDDL } from '@/api/special-workload'
export default {
    name: "EditDDL",
    data() {
        return {
            currentValue: '2022-10-1',
            newValue: "2022-10-1",
            isEditing: false,
        }
    },
    methods: {
        getCurrent() {
            //获取当前ddl
            const formData = new FormData();
            formData.append("year", this.$store.state.currentYear);
            // getDDL().then(data=>{
            //     this.currentValue = data
            //     this.newValue = data
            // }).catch(err=>{
            //     console.log(err)
            // })
        },
        edit() {
            this.isEditing = true;
        },
        confirm() {
            this.isEditing = false;
            //更新后端数据
            // const formData = new FormData();
            // formData.append("year", this.$store.state.currentYear);
            // formData.append("date", this.ddl);
            // setDDL(formData).then(() => {
            //     Message({
            //         message: "截止日期设置成功！",
            //         type: 'success',
            //         duration: 2000
            //     })
            // }).catch(err => {
            //     Message({
            //         message: `截止日期设置失败！`,
            //         type: 'error',
            //         duration: 2000
            //     })
            // })
            //重新获取
            // this.getCurrent();
        },
        cancle() {
            this.isEditing = false;
            this.newValue = this.currentValue;   //将编辑值恢复
        },
    },
    created() {
        this.getCurrent();
    }
}
</script>
<style scoped>
#editDDL {
    margin-top: 15px;
}

.ddl {
    margin-bottom: 15px;
}

.ddl button {
    margin-left: 50px;
}

.ddl .current {
    font-size: 18px;
    font-weight: 800;
    color: rgb(242, 193, 67);
}
</style>
