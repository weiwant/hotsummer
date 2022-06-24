<template>
  <div id="teacherHome">
    <sidebar></sidebar>
    <router-view></router-view>
  </div>
</template>

<script>
import Sidebar from "./Sidebar.vue";
// import TeacherWorkloadTable from "./TeacherWorkloadTable.vue";
export default {
  components: { Sidebar },
  name: "Teacherhome",
  data() {},
};
</script>

<style scoped>
* {
  margin: 0;
  padding: 0;
}
#teacherHome {
  width: 100vw;
  height: 100vh;
}
</style>
