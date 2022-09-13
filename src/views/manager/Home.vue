<template>
  <div id="managerHome">
    <ManagerSidebar></ManagerSidebar>
    <router-view></router-view>
  </div>
</template>

<script>
import ManagerSidebar from "./Sidebar.vue";
export default {
  components: { ManagerSidebar },
  name: "ManagerHome",
  created() {
    this.$getUser();
  },
};
</script>

<style scoped>
#managerHome {
  width: 100vw;
  height: 100vh;
}
</style>
