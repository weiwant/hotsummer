<template>
  <div id="sideBar">
    <div class="schoolLogo">
      <img src="../../assets/schoolImg/schoolLogo.png" alt="" />
    </div>
    <div class="title">管理员界面</div>
    <ul class="mainFunctions">
      <li>
        <router-link to="personalInfo"> 个人主页</router-link>
      </li>
      <li>
        <router-link to="uploadWorkload"> 上传教学工作量 </router-link>
      </li>
      <li>
        <router-link to="checkWorkload"> 查看教学工作量 </router-link>
      </li>
      <li>
        <router-link to="auditWorkload"> 审批特殊工作量</router-link>
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


