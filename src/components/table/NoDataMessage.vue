<template>
    <div id="nodata-message">
        <span class="icon"></span><span class="text">暂无数据</span>
    </div>
</template>
<script>
export default {
    name: "NoTableDataMessage",
}
</script>
<style scoped>
#nodata-message {
    margin-top: 10px;
    margin-left: 10px;
}

.icon {
    font-family: 'icomoon';
    color: rgb(232, 217, 100);
    font-size: 20px;
    margin-right: 10px;
    vertical-align: bottom;
}

.text {
    font-size: 18px;
    font-weight: 500;
    color: #999;
}
</style>
