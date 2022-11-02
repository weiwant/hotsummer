<template>
  <div id="sidebar">
    <header>

      <!-- <button class="folder" v-text="sidebarOpened ? '' : ''"></button> -->
    </header>
    <ul class="sidebar-body">
      <li v-for='item in routes' :key="item.path">
        <div class="router-link" @click="changeCurrent(item.path)" :class="{ active: currentPath === item.path }">
          <span class="icon">{{ item.children[0].meta.icon }}</span><span class="title">{{ item.children[0].meta.title
          }}</span>
        </div>
      </li>
    </ul>
    <div class="settings" :class="{ open: settingsOpened }">
      <button class="folder" @click="changeSettingsStatus"></button>
      <ul class="settings-list">
        <li class="settings-item" @click="logout">
          <span class="icon"></span>&nbsp;退出登录
        </li>
      </ul>
    </div>
  </div>
</template>

<script>

export default {
  name: "Sidebar",
  data() {
    return {
      currentPath: '',
      sidebarOpened: true,  //导航栏的开合状态
      settingsOpened: false,  //设置栏的开关状态
    }
  },
  computed: {
    routes() {
      return this.$store.getters.routes.filter((item) => {
        if (item.path === '/login') return false;
        return true;
      })
    }
  },
  methods: {
    changeCurrent(current) {
      this.$router.push(current);
      this.currentPath = current;
    },
    changeSettingsStatus() {
      this.settingsOpened = !this.settingsOpened
    },
    logout() {
      //清除token、username、identity
      this.$store.dispatch('user/logout').then(() => {
        //重置路由
        this.$store.commit('permission/resetRouter');
        //返回登录页

        this.$router.push(`/login?redirect=${this.$route.fullPath}`)
      }).catch(() => { })
    }
  },
  created() {
    //获取最初进入页面时所在的路径
    let path = this.$route.path
    if (path.endsWith('index')) {
      this.currentPath = path.slice(0, path.length - 6);  //删掉结尾可能存在的'/index', 因为要和路由的path比较，path!== redirect
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
  overflow: hidden;
}


button.folder {
  border: 0;
  background-color: transparent;
  color: white;
  font-size: 22px;
  font-family: 'icomoon';
}

/* 头部 */

header {
  margin-bottom: 40px;
  height: 40px;
  background-image: url('@/assets/schoolImg/schoolLogo.png');
  background-repeat: no-repeat;
  background-size: contain;
  background-position: 10px 0px;
}


header button.folder {
  float: right;
}

header button.folder:hover {
  background-color: rgb(143 172 164)
}

/* 导航栏主体 */

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
  font-size: 20px;
  vertical-align: bottom;
}

.sidebar-body li .router-link:hover {
  background-color: rgb(143 172 164);
}

.sidebar-body .router-link.active {
  background-color: rgb(143 172 164)
}

@media screen and (max-width: 800px) {
  #sidebar {
    display: none;
  }
}

/* 设置栏 */

.settings {
  position: absolute;
  bottom: 20px;
  width: 200px
}

.settings button.folder {
  position: absolute;
  bottom: 0;
  left: 0;
  padding: 0;
  transition: all 0.2s;
}

.settings .settings-list {
  overflow: clip;
  width: 0;
  position: absolute;
  bottom: 0;
  left: 40px;
  background-color: rgb(143 172 164);
  padding: 0px;
  border-radius: 5px;
  transition: all 0.2s;
}

.settings .settings-list::before {
  content: '';
  position: absolute;
  left: -16px;
  width: 0;
  height: 0;
  border: 8px solid transparent;
  border-right-color: rgb(143 172 164);
}

.settings .settings-list .settings-item {
  cursor: pointer;
  white-space: nowrap;
  font-size: 15px;
}

.settings .settings-list .settings-item .icon {
  font-family: 'icomoon';
  vertical-align: bottom;
}


.settings .settings-list .settings-item:hover {
  color: #eee;
}

/* 设置栏打开样式 */
.settings.open button.folder {
  transform: rotate(30deg);
}

.settings.open .settings-list {
  overflow: visible;
  width: auto;
  padding: 10px;
}
</style>

