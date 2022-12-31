<template>
    <div id="topbar">
        <div class="leftMenu">
            <div class="folder">
                <button class="noBorder green" @click="toggleSidebar"><span class="icon"
                        v-html="sidebarOpened ? '' : ''"></span></button>
            </div>
            <div class="title">{{ this.$route.matched[0].meta.title }}</div>
        </div>
        <div class="rightMenu">
            <div class="settings" :class="{ open: showSettingsList }">
                <button class="noBorder green toggle" @click="showSettingsList = !showSettingsList"><span
                        class="icon"></span></button>
                <transition name="expandHeight">
                    <ul class="settings-list" v-if="showSettingsList">
                        <li class="settings-list-item">
                            <button class="noBorder whitesmoke" @click="logout">退出登录</button>
                        </li>
                    </ul>
                </transition>
            </div>
        </div>
    </div>


</template>
<script>
import { Message } from 'element-ui'

export default {
    name: "TopBar",

    data() {
        return {
            showSettingsList: false,
        }
    },
    computed: {
        sidebarOpened() {
            return this.$store.state.app.sidebar.opened
        }
    },
    methods: {
        toggleSidebar() {
            this.$store.dispatch('app/toggleSidebar', { withoutAnimation: false })
        },
        logout() {
            //清除token、username、identity
            this.$store.dispatch('user/logout').then(() => {
                //重置路由
                this.$store.commit('permission/resetRouter');
                //返回登录页
                this.$router.push(`/login?redirect=${this.$route.fullPath}`)
                Message({
                    message: "退出成功",
                    type: "success",
                    duration: 2000,
                });
            }
            )
        }

    }
}
</script>
<style scoped lang="scss">
@import '@/style/variables.scss';

#topbar {
    position: absolute;
    z-index: 20;
    right: 0;
    top: 0;
    width: calc(100% - #{$sidebarWidth});
    height: 50px;
    background-color: $topbarBg;
    border-bottom: 1px solid #ddd;
    display: flex;
    justify-content: space-between;
}

.leftMenu {
    display: flex;
    align-items: center;
    gap: 10px;
    margin-left: 10px;

    .title {
        color: $topbarTitle;
        font-weight: 500;
    }
}

//右侧
.rightMenu {
    display: flex;
    align-items: center;
    gap: 10px;
    margin-right: 10px;

}

.settings {
    position: relative;

    //齿轮按钮
    button.toggle {
        transition: all 0.2s;

        .icon {
            font-size: 20px;
            margin-right: 0; //把button.css里全局设置的margin值干掉，否则旋转以后会出现中心点偏移
        }
    }

    &.open {
        button.toggle {
            transform: rotate(45deg);
        }
    }

    .settings-list {
        position: absolute;
        top: 40px;
        right: 5px;
        background-color: $themeColor;
        font-size: 14px;
        color: whitesmoke;
        word-break: keep-all;
        border-radius: 5px;

        &:before {
            display: block;
            content: '';
            position: absolute;
            top: -10px;
            right: 9px;
            width: 0;
            height: 0;
            border: 5px solid transparent;
            border-bottom-color: $themeColor;
        }


    }
}

.expandHeight-enter-active,
.expandHeight-leave-active {
    transition: all 0.2s;
}

.expandHeight-enter,
.expandHeight-leave-to {
    height: 5px;
    opacity: 0;
    transform: scaleY(0.6);
}

.expandHeight-leave,
.expandHeight-enter-to {
    height: 37px;
    opacity: 1;
    transform: scaleY(1);
}







//transition
#topbar {
    transition: width 0.2s;
}

.withoutAnimation {
    #topbar {
        transition: none;
    }
}


//sidebat隐藏时的样式
.hideSidebar {
    #topbar {
        width: calc(100% - 81px);
    }
}

.mobile {
    #topbar {
        width: 100%;
    }
}
</style>
