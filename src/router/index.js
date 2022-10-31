import Vue from 'vue'
import VueRouter from 'vue-router'


Vue.use(VueRouter)


import Layout from "@/layout"
import Login from "@/views/login"


//一些不需要权限的路由
export const constantRoutes = [
  {
    path: '/login',
    name: "Login",
    component: Login
  },
  {
    path: '/',
    component: Layout,
    redirect: '/userinfo',
    children: [
      {
        path: "userinfo",
        name: "Userinfo",
        component: () => import("@/views/user-info/index"),
        meta: {
          title: "个人主页",
          icon: ''
        }
      }
    ]
  }
]

export default new VueRouter({
  mode: 'history',
  routes: constantRoutes
})

//需要在获取用户的权限信息后挂载的路由
//专属于教师的
export const teacherRoutes = [
  {
    path: '/workload-report',  //查看工作量总报告
    component: Layout,
    redirect: 'workload-report/index',
    children: [
      {
        path: 'index',
        name: 'WorkloadReport',
        component: () => import('@/views/workload-report/index'),
        meta: {
          title: '总工作量报告',
          icon: ''
        }
      }
    ]
  },
  {
    path: '/special-workload-upload',  //上报特殊工作量
    component: Layout,
    redirect: '/special-workload-upload/index',
    children: [
      {
        path: 'index',
        name: 'SpecialWorkloadUpload',
        component: () => import('@/views/special-workload/UploadSpecialWorkload'),
        meta: {
          title: '上报特殊工作量',
          icon: ''
        }
      }
    ]
  },
  {
    path: '/special-workload-history', //查看特殊工作量上报历史
    component: Layout,
    redirect: '/special-workload-history/index',
    children: [
      {
        path: 'index',
        name: 'SpecialWorkloadHistory',
        component: () => import('@/views/special-workload/ViewHistory'),
        meta: {
          title: '历史上报',
          icon: ''
        }
      }
    ]
  },

]

//专属于管理员的
export const managerRoutes = [
  {
    path: '/teaching-workload-upload',  //上传教学工作量
    component: Layout,
    redirect: '/teaching-workload-upload/index',
    children: [
      {
        path: 'index',
        name: 'TeachingWorkloadUpload',
        component: () => import('@/views/teaching-workload-upload/index'),
        meta: {
          title: '上传教学工作量',
          icon: ''
        }
      }
    ]
  },
  {
    path: '/teaching-workload-check',  //查看全体教职工教学工作量
    component: Layout,
    redirect: "/teaching-workload-check/index",
    children: [
      {
        path: "index",
        name: 'TeachingWorkloadCheck',
        component: () => import('@/views/teaching-workload-check/index'),
        meta: {
          title: '查看教学工作量',
          icon: ''
        }
      }
    ]
  },
  {
    path: '/special-workload-audit',  //审核特殊工作量
    component: Layout,
    redirect: '/special-workload-audit/index',
    children: [
      {
        path: 'index',
        name: 'SpecialWorkloadAudit',
        component: () => import('@/views/special-workload-audit/index'),
        meta: {
          title: '审批特殊工作量',
          icon: ''
        }
      }
    ]
  }
]




