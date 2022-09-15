<template>
  <div id="sidebar">
    <div class="sidebar-logo">
      <img src="../../assets/schoolImg/schoolLogo.png" alt="" />
    </div>
    <div class="sidebar-title">教师界面</div>
    <ul class="sidebar-body">
      <li>
        <router-link to="PersonalInfo"><span></span> 个人主页</router-link>
      </li>
      <li>
        <router-link to="TeacherWorkloadTable"
          ><span></span> 总工作量报告
        </router-link>
      </li>
      <li>
        <router-link to="SpecialWorkload"
          ><span></span> 上报特殊工作量
        </router-link>
      </li>
      <li>
        <router-link to="ViewHistory"><span></span> 历史上报 </router-link>
      </li>
    </ul>
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



