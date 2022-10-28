<template>
  <div id="sidebar">
    <div class="sidebar-logo">
      <img src="../../assets/schoolImg/schoolLogo.png" alt="" />
    </div>
    <div class="sidebar-title" v-text="sidebarTitle"></div>
    <ul class="sidebar-body">
      <li v-for='item in routes' :key="item.path">
        <router-link :to="item.path">
          <span class="icon">{{item.children[0].mata.icon}}</span><span
            class="title">{{item.children[0].mata.title}}</span>
        </router-link>
      </li>
    </ul>
  </div>
</template>

<script>
export default {
  name: "ManagerSidebar",
  computed: {
    sidebarTitle() {
      switch (this.$store.getters.identity) {
        case 0: return '教师界面';
        case 1:
        case 2:
          return '管理员界面';
        default:
          return '';
      }
    },
    routes() {
      return this.$state.getters.routes.filter((item) => {
        if (item.path === '/login') return false;
        return true;
      })
    }
  }
};
</script>

<style scoped>
#sidebar {
  position: absolute;
  top: 0;
  left: 0;
  padding: 10px;
  width: 220px;
  height: 100vh;
  background-color: #81a299;
  text-align: left;
  z-index: 1;
  color: white;
}

.sidebar-logo {
  width: 70%;
}

.sidebar-logo img {
  width: 100%;
}

.sidebar-title {
  font-size: 22px;
  font-weight: 900;
  margin-bottom: 40px;
}

.sidebar-body li a {
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

.sidebar-body .router-link-exact-active,
.sidebar-body .router-link-active {
  background-color: rgb(143 172 164)
}

@media screen and (max-width: 800px) {
  #sidebar {
    display: none;
  }
}
</style>

