<template>
    <div id="feedback-detail">
        <div class="mask">
            <div class="detail-box">
                <div class="loading" v-if="showLoading">
                    <Loading />
                </div>
                <div class="content" v-else>
                    <header class="section-header">反馈详情</header>
                    <button class="noBorder red cancle" @click="() => this.$emit('hide')"></button>
                    <!-- send部分 -->
                    <div class="send">
                        <div class="send-title">
                            <h3>主题</h3>
                            <p>
                                {{ detail.title }}
                            </p>
                        </div>

                        <div class="send-content">
                            <h3>内容</h3>
                            <p>
                                {{ detail.sendContent }}
                            </p>
                        </div>

                        <div class="origin">
                            {{ detail.sender }}&nbsp;&nbsp;&nbsp;&nbsp;{{ detail.sendDate }}
                        </div>
                    </div>
                    <!-- 分割线 -->
                    <div class="crossline">
                        ------------------------------------------------管理员回复------------------------------------------------
                    </div>
                    <!-- response部分 -->
                    <div class="response">
                        <div class="no-response" v-if="detail.responseStatus == -1">
                            <slot name="no-response"></slot>
                        </div>
                        <div v-else>
                            <p class="response-content">
                                {{ detail.responseContent }}
                            </p>
                            <div class="origin">
                                {{ detail.responder }}&nbsp;&nbsp;&nbsp;&nbsp;{{ detail.responseDate }}
                            </div>
                        </div>

                    </div>
                </div>
            </div>
        </div>
    </div>
</template>
<script>
import Loading from "@/components/Loading.vue"
export default {
    name: "FeedbackDetail",
    components: { Loading },
    props: {
        detail: {
            type: Object,
            required: true,
        },
        showLoading: {
            type: Boolean,
            default: false,
        }
    }
}
</script>
<style scoped lang="scss">
@import '@/style/variables.scss';

.mask {
    position: fixed;
    z-index: 30;
    top: 0;
    right: 0;
    width: 100vw;
    height: 100vh;
    background-color: rgba(221, 221, 221, 0.497);
    display: flex;
    justify-content: center;
    align-items: center;
}

.detail-box {
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

.section-header {
    font-size: 20px;
    font-weight: 600;
    margin-bottom: 20px;
}

.send,
.response {
    color: black;

    h3 {
        font-size: 17px;
        text-decoration: underline;
    }

    p {
        color: #333;
        font-size: 15px;
        text-indent: 2em;
        max-height: 100px;
        overflow: auto;
        background-color: #e8e8e87a;
        padding: 5px;
        border-radius: 5px;
    }

    .origin {
        color: #555;
        font-size: 13px;
        text-align: right;
    }
}

.no-response {
    text-align: center;

    .teacher-end {
        .icon {
            color: $yellow;
        }
    }
}


.crossline {
    margin: 20px 0;
    text-align: center;
    font-size: 12px;
    color: gray;
}

.loading {
    width: 100%;
    height: 100%;
    display: flex;
    justify-content: center;
    align-items: center;
}
</style>
