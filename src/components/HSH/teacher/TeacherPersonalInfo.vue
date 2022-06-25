

<template>
  <div id="teacherPersonalInfo">
    <div class="sectionTitle">个人信息</div>
  </div>
</template>

<script>
export default {
  name: "TeacherPersonalInfo",
};
</script>

<style scoped>
#teacherPersonalInfo {
  float: left;
  overflow-y: auto;
  width: 85vw;
  height: 100vh;
  padding: 10px 15px;
  background-color: rgb(239, 241, 247);
}
#teacherPersonalInfo::-webkit-scrollbar {
  display: none;
}
#teacherPersonalInfo {
  -ms-overflow-style: none;
  scrollbar-width: none;
}
@media screen and (max-width: 1000px) and (min-width: 600px) {
  #teacherPersonalInfo {
    width: 77vw;
  }
}
@media screen and (max-width: 600px) {
  #teacherPersonalInfo {
    width: 100vw;
  }
}
.sectionTitle {
  text-align: left;
  font-size: 27px;
  font-weight: 600;
  color: rgb(29, 74, 51);
  height: 50px;
  line-height: 50px;
  margin-bottom: 3vh;
}
.subtitle {
  margin-top: 3vh;
  margin-bottom: 2vh;
  width: 40%;
  border-bottom: 1px solid rgba(128, 128, 128, 0.3);
  text-align: left;
  font-size: 20px;
  font-weight: 600;
}
</style>

