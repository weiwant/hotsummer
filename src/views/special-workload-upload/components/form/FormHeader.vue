<template>
    <div class="form-header">
        <h3>{{ formTitle }}<span class="detail">({{ fullName }})</span></h3>
        <div class="cancle">
            <button class="noBorder red cancle-btn"
                @mouseenter="() => { if (formStatus !== '新项目') return; showCancleNotification = true }"
                @mouseleave="() => { if (formStatus !== '新项目') return; showCancleNotification = false }" @click="() => this.$emit('cancle'
                )"></button>
            <transition name="fadeIn">
                <div class="notification" v-show="showCancleNotification && this.formStatus === '新项目'">
                    撤销后填报内容会丢失！
                </div>
            </transition>
        </div>
    </div>
</template>
<script>
export default {
    name: "SpecialWorkloadFormHeader",
    props:['formStatus','formTitle','fullName'],
    data(){
        return{
            showCancleNotification: false,
        }
    }
}
</script>
<style scoped>
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
    font-size: 20px;
}

.form-header .cancle .notification {
    position: absolute;
    right: -132px;
    top: 40px;
    z-index: 100;
    padding: 5px 10px;
    font-size: 14px;
    background-color: white;
    border-radius: 10px;
    border: 1px solid #ddd;
    box-shadow: 1px 1px 5px #bbb;
    word-break: keep-all
}

.form-header .cancle .notification:before {
    content: '';
    position: absolute;
    top: -5px;
    left: 20px;
    width: 8px;
    height: 8px;
    border-top: 1px solid #ddd;
    border-right: 1px solid #ddd;
    border-bottom-color: #dadde6;
    background-color: white;
    transform: rotate(-45deg);
}
</style>
