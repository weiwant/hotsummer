<template>
  <div id="teacherHome">
    <TeacherSidebar></TeacherSidebar>
    <router-view></router-view>
  </div>
</template>

<script>
import TeacherSidebar from "./Sidebar.vue";
export default {
  components: { TeacherSidebar },
  name: "TeacherHome",
};
</script>

<style scoped>
#teacherHome {
  width: 100vw;
  height: 100vh;
}
</style>
