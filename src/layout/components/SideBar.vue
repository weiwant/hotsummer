<template>
  <div id="sidebar">
    <header></header>
    <ul class="sidebar-body">
      <li v-for='(item, index) in routes' :key="item.path" @mouseenter="showDescription.splice(index, 1, true)"
        @mouseleave="showDescription.splice(index, 1, false)">
        <router-link :to="item.path">
          <span class="icon">{{ item.meta.icon }}</span><span class="title">{{ item.meta.title
          }}</span>
        </router-link>
        <transition name="slideFromBottomFade">
          <div class="explaination" v-if="!mobile && !sidebarOpened && showDescription[index]">{{ item.meta.title }}
          </div>
        </transition>
      </li>
    </ul>
  </div>
</template>

<script>

export default {
  name: "Sidebar",
  data() {
    return {
      showDescription: [],
    }
  },
  computed: {
    //从用户所有“可触及”路由中，过滤出登录以后需要通过sidebar导航的路由
    routes() {
      return this.$store.getters.routes.filter((item) => {
        if (item.path === '/login' || item.path === '/') return false;
        return true;
      })
    },
    sidebarOpened() {
      return this.$store.state.app.sidebar.opened
    },
    mobile() {
      return this.$store.state.app.device === 'mobile'
    }
  },
  methods: {
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
    this.showDescription = new Array(this.routes.length);
  }

};
</script>

<style scoped lang="scss">
@import '@/style/variables.scss';

#sidebar {
  position: absolute;
  top: 0;
  left: 0;
  padding: 10px;
  width: $sidebarWidth;
  height: 100vh;
  background-color: $sidebarBg;
  text-align: left;
  z-index: 10000;
  // overflow: hidden;
}

/* 头部 */


header {
  margin-bottom: 40px;
  height: 40px;
  background-image: url('@/assets/schoolImg/schoolLogo.png');
  background-repeat: no-repeat;
  background-size: 120px auto;
  background-position: 10px 0px;
}


/* 导航栏主体 */
.sidebar-body li {
  position: relative;
}

.sidebar-body li a {
  display: block;
  margin-bottom: 15px;
  height: 50px;
  font-size: 15px;
  line-height: 50px;
  border-radius: 5px;
  font-family: "icomoon";
  color: $sidebarText;
  cursor: pointer;
  overflow: hidden;

  .icon {
    padding: 0 20px;
    font-size: 21px;
    vertical-align: sub;
  }

  .title {
    font-size: 15px;
  }

  &:hover {
    transition: all 0.2s;
    background-color: $sidebarActiveBg;
  }
}

.sidebar-body .router-link-active {
  background-color: $sidebarActiveBg
}

.sidebar-body li .explaination {
  position: absolute;
  left: 75px;
  top: 50%;
  transform: translateY(-50%);
  padding: 10px 8px;
  background-color: white;
  border-radius: 10px;
  word-break: keep-all;
  background-color: $descriptionBg;
  color: $descriptionText;
  font-size: 14px;
}


//下面这个动画就不放在transition.css里了，因为.description元素为了最终居中translateY最终必须得-50%，这个不是很普遍。
//这里改用margin来进行动画，更不普遍了
.slideFromBottomFade-enter-active,
.slideFromBottomFade-leave-active {
  transition: all 0.1s;
}

.slideFromBottomFade-enter,
.slideFromBottomFade-leave-to {
  margin-top: 10px;
  opacity: 0;
}

.slideFromBottomFade-enter-to,
.slideFromBottomFade-leave {
  margin-top: 0;
  opacity: 1;
}


//withoutAnimation：导航栏展开与关闭是否带有transition
//当导航栏是因为viewport的调整而被强制隐藏/显示时，不加transition
#sidebar,
header,
.sidebar-body li a {
  transition: width 0.2s;
}

.withoutAnimation {

  #sidebar,
  header,
  .sidebar-body li a {
    transition: none;
  }
}

//隐藏时的样式
.hideSidebar {
  #sidebar {
    width: 81px;
  }

  header {
    width: 59px;
  }

  .sidebar-body li a {
    width: 61px;
  }
}

.mobile.hideSidebar {
  #sidebar {
    width: 0px;
    padding: 0;
  }

  header {
    width: 0px;
  }

  .sidebar-body li a {
    width: 0px;
  }
}
</style>

