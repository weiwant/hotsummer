<template>
  <div id="app">
    <router-view></router-view>
  </div>
</template>

<style>
@font-face {
  font-family: "icomoon";
  src: url("assets/fonts/icomoon.eot?5679dk");
  src: url("assets/fonts/icomoon.eot?5679dk#iefix") format("embedded-opentype"),
    url("assets/fonts/icomoon.ttf?5679dk") format("truetype"),
    url("assets/fonts/icomoon.woff?5679dk") format("woff"),
    url("assets/fonts/icomoon.svg?5679dk#icomoon") format("svg");
  font-weight: normal;
  font-style: normal;
  font-display: block;
}
* {
  margin: 0;
  padding: 0;
  box-sizing: border-box;
  list-style: none;
}
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}
a {
  text-decoration: none;
}
input:focus {
  outline: none;
}
/*侧边栏*/
#sideBar {
  float: left;
  position: relative;
  padding-top: 10px;
  padding-left: 10px;
  padding-right: 10px;
  width: 200px;
  height: 100vh;
  background-color: rgb(71, 159, 134);
  text-align: left;
  z-index: 1;
  color: white;
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
  color: white;
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
/*每个section最外层盒子的样式*/
.componentWrapper {
  position: absolute;
  overflow-y: auto;
  width: 100vw;
  height: 100vh;
  padding: 10px 15px 10px 215px;
  background-color: rgb(239, 241, 247);
}
.componentWrapper::-webkit-scrollbar {
  display: none;
}
.componentWrapper {
  -ms-overflow-style: none;
  scrollbar-width: none;
}
@media screen and (max-width: 800px) {
  .componentWrapper {
    padding-left: 15px;
  }
}
/*每个section的主标题*/
.componentSectionTitle {
  text-align: left;
  font-size: 27px;
  font-weight: 600;
  color: rgb(29, 74, 51);
  height: 50px;
  line-height: 50px;
}
/*每个section内部子板块最外层盒子的样式*/
.componentSubsection {
  position: relative;
  margin-bottom: 5px;
  padding: 15px;
  background-color: #fff;
  border-radius: 5px;
  text-align: left;
}
/*每个section子板块的子标题*/
.componentSubtitle {
  margin-top: 3vh;
  margin-bottom: 2vh;
  width: 40%;
  border-bottom: 1px solid rgba(128, 128, 128, 0.3);
  text-align: left;
  font-size: 20px;
  font-weight: 600;
}
/* 处理excel文件的绿色btn*/
button.workloadFileProcessingBtn {
  padding: 5px;
  border: 1px solid rgba(128, 128, 128, 0.452);
  border-radius: 5px;
  background-color: rgb(255, 254, 254);
  font-family: "icomoon";
  color: rgb(51, 114, 96);
  font-weight: 500;
}
button.workloadFileProcessingBtn:hover {
  background-color: rgba(209, 207, 207, 0.479);
}
button.workloadFileProcessingBtn.disabled {
  background-color: rgba(209, 207, 207, 0.479);
}
/*处理非文件操作的蓝色btn样式*/
button.universalBlueBtn {
  border: 0;
  border-radius: 5px;
  font-weight: 550;
  font-family: "icomoon";
  color: #fff;
  background-color: rgba(41, 34, 106);
}
button.universalBlueBtn:hover {
  background-color: rgba(41, 34, 106, 0.815);
}
/******管理员文件上传部分的e-input控件样式******/
.componentSubsection .el-input input.el-input__inner {
  border: 0px;
  padding: 0;
}
</style>
