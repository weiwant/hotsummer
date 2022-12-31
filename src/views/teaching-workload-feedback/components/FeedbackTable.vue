<template>
    <div id="feedback-table">
        <div class="mask" v-show="showLoading">
            <Loading />
        </div>
        <div class="table-wrapper">

        </div>
    </div>
</template>
<script>
export default {
    name: "FeedbackTable",
    props: {
        dataArray: {
            type: Array,
            required: true
        }
    },

}
</script>
<style scoped>

</style>
