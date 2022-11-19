<template>
    <div id="topbar">
        <div class="leftMenu">
            <div class="folder">
                <button class="transparent_green" @click="toggleSidebar"><span class="icon"
                        v-html="sidebarOpened ? '' : ''"></span></button>
            </div>
            <div class="title">{{ this.$route.matched[0].meta.title }}</div>
        </div>
        <div class="rightMenu">
            <div class="settings" :class="{ open: showSettingsList }">
                <button class="transparent_green" @click="showSettingsList = !showSettingsList"><span
                        class="icon"></span></button>
                <transition name="expandHeight">
                    <ul class="settings-list" v-if="showSettingsList">
                        <li class="settings-list-item">退出登录</li>
                    </ul>
                </transition>
            </div>
        </div>
    </div>


</template>
<script>
export default {
    name: "TopBar",
    data() {
        return {
            sidebarOpened: true,
            showSettingsList: false,
        }
    },
    methods: {
        toggleSidebar() {
            this.$store.dispatch('app/toggleSidebar', { withoutAnimation: false })
        }
    }

}
</script>
<style scoped lang="scss">
@import '@/style/variables.scss';

#topbar {
    position: absolute;
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


}

.settings {
    position: relative;

    button {
        transition: all 0.2s;

        .icon {
            font-size: 20px;
        }
    }

    .settings-list {
        position: absolute;
        top: 40px;
        right: 10px;
        background-color: $themeGreen;
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
            border-bottom-color: $themeGreen;
        }

        li {
            padding: 10px;
        }
    }
}

.expandHeight-enter-active,
.expandHeight-leave-active {
    transition: all 0.3s;
}

.expandHeight-enter,
.expandHeight-leave-to {
    height: 0;
    opacity: 0;
}

.expandHeight-leave,
.expandHeight-enter-to {
    height: 37px;
    opacity: 1;
}



.settings.open {
    button {
        transform: rotate(45deg);
    }
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
</style>
