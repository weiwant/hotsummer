<template>
  <div id="sidebar">
    <div class="sidebar-logo">
      <img src="../../assets/schoolImg/schoolLogo.png" alt="" />
    </div>
    <div class="sidebar-title" v-text="sidebarTitle"></div>
    <ul class="sidebar-body">
      <li v-for='item in routes' :key="item.path">
        <div class="router-link" @click="changeCurrent(item.path)" :class="{ active: currentPath === item.path }">
          <span class="icon">{{ item.children[0].meta.icon }}</span><span class="title">{{ item.children[0].meta.title
          }}</span>
        </div>
      </li>
    </ul>
  </div>
</template>

<script>

export default {
  name: "ManagerSidebar",
  data() {
    return {
      currentPath: ''
    }
  },
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
      console.log(this.$store.getters.routes)
      return this.$store.getters.routes.filter((item) => {
        if (item.path === '/login') return false;
        return true;
      })
    }
  },
  methods: {
    changeCurrent(current) {
      this.$router.push(current);
      this.currentPath = current
    }
  },
  created() {
    //获取最初进入页面时所在的路径
    let path = this.$route.path
    if (path.endsWith('index')) {
      this.currentPath = path.slice(0, path.length - 6);  //删掉结尾可能存在的'/index', 因为要和路由的path比较，path!== redirect
    }
    console.log(this.$route.path)
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

.sidebar-body li .router-link {
  display: block;
  padding-left: 10px;
  margin-bottom: 15px;
  height: 50px;
  font-size: 15px;
  line-height: 50px;
  border-radius: 5px;
  font-family: "icomoon";
  color: white;
  cursor: pointer;
}

.sidebar-body li .router-link .icon {
  margin-right: 5px;
}

.sidebar-body .active {
  background-color: rgb(143 172 164)
}

@media screen and (max-width: 800px) {
  #sidebar {
    display: none;
  }
}
</style>

