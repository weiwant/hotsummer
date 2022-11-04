<template>
    <div id="app-wrapper">
        <SideBar />
        <TopBar />
        <transition name="slideFade" mode="out-in">
            <router-view></router-view>
        </transition>
    </div>
</template>
<script>
import SideBar from "@/layout/components/SideBar.vue"
import TopBar from "@/layout/components/TopBar.vue"
export default {
    components: { SideBar, TopBar },
    name: "Layout",
}
</script>


<style scoped>
.slideFade-enter-active,
.slideFade-leave-active {
    transition: all 0.3s;
}

.slideFade-enter {
    transform: translateX(-20px);
    opacity: 0;
}

.slideFade-enter-to,
.slideFade-leave {
    transform: translateX(0px);
    opacity: 1;
}

.slideFade-leave-to {
    transform: translateX(20px);
    opacity: 0;
}
</style>