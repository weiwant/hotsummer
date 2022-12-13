<template>
    <div id="not-found">
        <div class="notification">
            <p> 抱歉，待访问页面不存在或无权限访问 !</p>
            <p><button class="withBorder green" @click="goHome">
                    返回主页
                </button></p>

        </div>
        <div class="img">

        </div>
    </div>
</template>
<script>
export default {
    name: "PageNotFound",
    methods: {
        goHome() {
            this.$router.push('/')
        }
    }

}
</script>
<style scoped>
.img,
.notification {
    margin: 0 auto;
    width: 500px;

}

.notification {
    margin-top: 30vh;
    text-align: center;
}

.img {
    height: 400px;
    background-image: url('@/assets/schoolImg/404.png');
    background-repeat: no-repeat;
    background-size: contain;
}
</style>
