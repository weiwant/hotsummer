<template>
  <div id="teacherHome">
    <TeacherSidebar></TeacherSidebar>
    <router-view></router-view>
  </div>
</template>

<script>
import TeacherSidebar from "./TeacherSidebar.vue";
export default {
  components: { TeacherSidebar },
  name: "Teacherhome",
  created() {
    this.$getUser();
  },
};
</script>

<style scoped>
#teacherHome {
  width: 100vw;
  height: 100vh;
}
</style>
