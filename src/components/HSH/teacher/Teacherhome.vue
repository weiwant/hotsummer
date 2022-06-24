<template>
  <div id="teacherHome">
    <TeacherSidebar></TeacherSidebar>
    <router-view></router-view>
  </div>
</template>

<script>
import TeacherSidebar from "./TeacherSidebar.vue";
// import TeacherWorkloadTable from "./TeacherWorkloadTable.vue";
export default {
  components: { TeacherSidebar },
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
