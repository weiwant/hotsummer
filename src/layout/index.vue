<template>
    <div id="app-wrapper" :class="classObj">
        <SideBar />
        <TopBar />
        <div class="mask" v-if="device === 'mobile' && sidebar.opened" @click="clickOutside"></div>
        <div class="main-container">
            <transition name="slideFromLeftFade" mode="out-in">
                <router-view></router-view>
            </transition>
        </div>

    </div>
</template>
<script>
import SideBar from "./components/SideBar.vue"
import TopBar from "./components/TopBar.vue"
import resizeMixin from './mixin/resizeHandler'
export default {
    components: { SideBar, TopBar },
    name: "Layout",
    mixins: [resizeMixin],
    computed: {
        sidebar() {
            return this.$store.state.app.sidebar
        },
        device() {
            return this.$store.state.app.device
        },
        classObj() {
            return {
                hideSidebar: !this.sidebar.opened,
                showSidebar: this.sidebar.opened,
                mobile: this.device === 'mobile',
                withoutAnimation: this.sidebar.withoutAnimation
            }
        }
    },
    methods: {
        clickOutside() {
            this.$store.dispatch('app/closeSidebar', { withoutAnimation: false })
        }
    }
}
</script>


<style scoped lang="scss">
@import "@/style/variables.scss";

.main-container {
    width: 100%;
    height: 100vh;
    padding-top: 50px;
    padding-left: $sidebarWidth;
    overflow-y: auto;
    transition: all 0.2s;
    z-index: -1;
}

.withoutAnimation {
    .main-container {
        transition: none;
    }
}

.hideSidebar {
    .main-container {
        padding-left: 81px;
    }
}

.mobile {
    .main-container {
        padding-left: 0px;
    }
}

.mask {
    position: absolute;
    z-index: 1000;
    width: 100vw;
    height: 100vw;
    background-color: rgba(255, 255, 255, 0.667)
}
</style>