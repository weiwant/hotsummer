<template>
    <div id="message-box">
        <div class="app-mask">
            <div class="box" :style="{ width: width + 'px', minHeight: minHeight + 'px' }">
                <button class="noBorder red cancle" @click="() => this.$emit('cancle')"></button>
                <header class="box-title">{{ title }}</header>
                <div class="content">
                    <div class="loading" v-if="showLoading">
                        <Loading />
                    </div>
                    <slot></slot>
                </div>
            </div>
        </div>
    </div>
</template>
<script>
import Loading from "@/components/Loading.vue"
export default {
    name: "MessageBox",
    components: { Loading },
    props: {
        width: {
            type: Number,
            required: true,
        },
        minHeight: {
            type: Number,
            required: true,
        },
        title: {
            type: String,
        },
        showLoading: {
            type: Boolean,
            default: false,
        }
    },

}
</script>
<style scoped>
.app-mask {
    position: fixed;
    z-index: 300;
    top: 0;
    right: 0;
    width: 100vw;
    height: 100vh;
    background-color: rgba(0, 0, 0, 0.4);
    display: flex;
    justify-content: center;
    align-items: center;
}

.box {
    position: relative;
    padding: 20px;
    padding-bottom: 40px;
    width: 500px;
    min-height: 200px;
    background-color: white;
    border: 1px solid #eee;
    border-radius: 5px;
}

button.cancle {
    position: absolute;
    right: 0;
    top: 0;
    font-size: 20px;
}

.box-title {
    font-size: 20px;
    font-weight: 600;
    margin-bottom: 20px;
}

.content {
    position: relative;
}

.loading {
    position: absolute;
    z-index: 100;
    display: flex;
    justify-content: center;
    align-items: center;
    width: 100%;
    height: 100%;
    background-color: rgba(255, 255, 255, 0.212);
}
</style>
