<template>
  <div id="sideBar">
    <div class="schoolLogo">
      <img src="../../assets/schoolImg/schoolLogo.png" alt="" />
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
      <li>
        <router-link to="ViewHistory"><span></span> 历史上报 </router-link>
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



