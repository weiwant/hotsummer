<template>
  <div id="sideBar">
    <div class="title">教师界面</div>
    <ul class="mainFunctions">
      <li>
        <router-link to="/Teacherhome/PersonalInfo">个人信息</router-link>
      </li>
      <li>
        <router-link to="/Teacherhome/TeacherWorkloadTable">
          查看教学工作量
        </router-link>
      </li>
      <li>
        <router-link to="/Teacherhome/SpecialWorkload">
          上报特殊工作量
        </router-link>
      </li>
    </ul>
    <ul class="tool"></ul>
  </div>
</template>

<script>
export default {
  name: "Sidebar",
  data() {
    return {};
  },
  methods: {},
};
</script>


<style scoped>
* {
  list-style: none;
  color: white;
  margin: 0;
  padding: 0;
}
#sideBar {
  float: left;
  padding-top: 10px;
  padding-left: 10px;
  padding-right: 10px;
  width: 20vw;
  height: 100vh;
  background-color: rgb(71, 159, 134);
  text-align: left;
}
.title {
  font-size: 22px;
  font-weight: 900;
  margin-bottom: 40px;
}
.mainFunctions li a {
  display: block;
  padding-left: 10px;
  margin-bottom: 15px;
  height: 50px;
  font-size: 15px;
  line-height: 50px;
  border-radius: 5px;
}
.mainFunctions li a:hover {
  background-color: rgb(143, 196, 181);
}
.router-link-exact-active,
.router-link-active {
  background-color: rgb(143, 196, 181);
}
</style>