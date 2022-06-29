<template>
  <div id="sideBar">
    <div class="schoolLogo">
      <img src="../../../assets/schoolImg/schoolLogo.png" alt="" />
    </div>
    <div class="title">管理员界面</div>
    <ul class="mainFunctions">
      <li>
        <router-link to="personalInfo"> 个人信息</router-link>
      </li>
      <li>
        <router-link to="uploadWorkload"> 上传教学工作量 </router-link>
      </li>
      <li>
        <router-link to="checkWorkload"> 查看教学工作量 </router-link>
      </li>
    </ul>
    <ul class="tool"></ul>
  </div>
</template>

<script>
export default {
  name: "ManagerSidebar",
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
  width: 200px;
  height: 100vh;
  background-color: rgb(71, 159, 134);
  text-align: left;
}
.schoolLogo {
  width: 70%;
}
.schoolLogo img {
  width: 100%;
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
  font-family: "icomoon";
}
.mainFunctions li a:hover {
  background-color: rgb(143, 196, 181);
}
.router-link-exact-active,
.router-link-active {
  background-color: rgb(143, 196, 181);
}

@media screen and (max-width: 800px) {
  #sideBar {
    display: none;
  }
}
</style>