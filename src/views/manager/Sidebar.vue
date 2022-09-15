<template>
  <div id="sidebar">
    <div class="sidebar-logo">
      <img src="../../assets/schoolImg/schoolLogo.png" alt="" />
    </div>
    <div class="sidebar-title">管理员界面</div>
    <ul class="sidebar-body">
      <li>
        <router-link to="PersonalInfo"> 个人主页</router-link>
      </li>
      <li>
        <router-link to="UploadWorkload"> 上传教学工作量 </router-link>
      </li>
      <li>
        <router-link to="CheckWorkload"> 查看教学工作量 </router-link>
      </li>
      <li>
        <router-link to="AuditWorkload"> 审批特殊工作量</router-link>
      </li>
    </ul>
  </div>
</template>

<script>
export default {
  name: "ManagerSidebar",
};
</script>


