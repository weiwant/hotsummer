<template>
    <div id="topbar">
        <div class="title">{{ title }}</div>
    </div>
</template>
<script>
export default {
    name: "TopBar",
    computed: {
        title() {
            return this.$store.getters.currentRoute.meta.title;
        }
    }
}
</script>
<style scoped>
.title {
    position: absolute;
    left: 235px;
    top: 10px;
    text-align: left;
    font-size: 27px;
    font-weight: 600;
    color: rgb(29, 74, 51);
    height: 50px;
    line-height: 50px;
}

@media screen and (max-width:800px) {
    .title {
        left: 15px;
    }
}
</style>
