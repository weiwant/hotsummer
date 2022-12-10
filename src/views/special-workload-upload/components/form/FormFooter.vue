<template>
    <div class="form-footer">
        <template v-if="formStatus === '新项目'">
            <button class="themeTextColor withBorder" @click="save">保&nbsp;存</button>
        </template>
        <template v-if="formStatus === '仅保存'">
            <button class="themeTextColor withBorder" v-if='!isEditing' @click="edit">编&nbsp;辑</button>
            <button class="themeTextColor withBorder" v-else @click="save">保&nbsp;存</button>
        </template>
        <template v-if="formStatus === '待审核'"></template>
        <template v-if="formStatus === '已审核'"></template>
    </div>
</template>
<script>
export default {
    name: "SpecialWorkloadFormFooter",
    // 所查看的项目的审核状态，决定了本组件要提供哪些操作
    // 新项目：保存
    // 仅保存：编辑/保存、提交
    // 待审核：撤销？
    // 已审核：对结果有疑问，申请核查？
    props: ['formStatus'],
    data() {
        return {
            isEditing: false,
        }
    },
    methods: {
        edit() {
            this.isEditing = true,
                this.$emit('edit')
        },
        save() {
            this.isEditing = false,
                this.$emit('save')
        }
    }
}
</script>
<style scoped>
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
