<template>
    <div id="app-wrapper">
        <SideBar />
        <router-view></router-view>
    </div>
</template>
<script>
import SideBar from "@/layout/components/SideBar.vue"
export default {
    components: { SideBar },
    name: "Layout",
}
</script>


<style scoped>
#app-wrapper {
    width: 100vw;
    height: 100vh;
}
</style>