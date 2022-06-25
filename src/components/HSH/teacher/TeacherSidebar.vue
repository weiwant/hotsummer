<template>
  <div id="sideBar">
    <div class="schoolLogo">
      <img src="../../../assets/schoolLogo.png" alt="" />
    </div>
    <div class="title">教师界面</div>
    <ul class="mainFunctions">
      <li>
        <router-link to="PersonalInfo"><span></span> 个人信息</router-link>
      </li>
      <li>
        <router-link to="TeacherWorkloadTable"
          ><span></span> 查看教学工作量
        </router-link>
      </li>
      <li>
        <router-link to="SpecialWorkload"
          ><span></span> 上报特殊工作量
        </router-link>
      </li>
    </ul>
    <ul class="tool"></ul>
  </div>
</template>

<script>
export default {
  name: "TeacherSidebar",
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
  width: 15vw;
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
  font-family: "icomoon";
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
@media screen and (max-width: 1000px) and (min-width: 600px) {
  #sideBar {
    width: 23vw;
  }
}
@media screen and (max-width: 600px) {
  #sideBar {
    display: none;
  }
}
</style>