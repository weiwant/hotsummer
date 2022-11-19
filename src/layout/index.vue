<template>
    <div id="app-wrapper" :class="classObj">
        <SideBar />
        <TopBar />
        <transition name="slideFromLeftFade" mode="out-in">
            <div class="main-container">
                <router-view></router-view>
            </div>
        </transition>
    </div>
</template>
<script>
import SideBar from "@/layout/components/SideBar.vue"
import TopBar from "@/layout/components/TopBar.vue"
export default {
    components: { SideBar, TopBar },
    name: "Layout",
    computed: {
        sidebar() {
            return this.$store.state.app.sidebar
        },
        classObj() {
            return {
                hideSidebar: !this.sidebar.opened,
                withoutAnimation: this.sidebar.withoutAnimation
            }
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
</style>