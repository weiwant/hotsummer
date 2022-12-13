<template>
    <div id="not-available">
        <div class="icon"></div>
        <div class="text">该板块暂未开放～</div>
    </div>
</template>
<script>
export default {
    name: "NotAvailable",

}
</script>
<style scoped>
#not-available {
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    gap: 10px;
    height: 100%;
    transform: translateY(-50px);
}

.icon {
    font-family: 'icomoon';
    font-size: 200px;
}
</style>
