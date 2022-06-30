<template>
  <div id="managerHome">
    <ManagerSidebar></ManagerSidebar>
    <router-view></router-view>
  </div>
</template>

<script>
import ManagerSidebar from "./ManagerSidebar.vue";
export default {
  components: { ManagerSidebar },
  name: "Managerhome",
};
</script>

<style scoped>
#managerHome {
  width: 100vw;
  height: 100vh;
}
</style>
