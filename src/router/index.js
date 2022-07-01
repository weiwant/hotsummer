import Vue from 'vue'
import VueRouter from 'vue-router'
const Teacherhome = () => import('../components/Teacher/Teacherhome.vue')
const SpecialWorkload = () => import('../components/Teacher/SpecialWorkload.vue')
const TeacherWorkloadTable = () => import('../components/Teacher/TeacherWorkloadTable.vue')
const TeacherPersonalInfo = () => import('../components/Teacher/TeacherPersonalInfo.vue')
const Managerhome = () => import('../components/Manager/Managerhome.vue')
const ManagerPersonalInfo = () => import('../components/Manager/ManagerPersonalInfo.vue')
const CheckWorkload = () => import('../components/Manager/CheckWorkload.vue')
const UploadWorkload = () => import('../components/Manager/UploadWorkload.vue')
const Login = () => import('../Login.vue')

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'Login',
    // component: Login
    redirect: '/teacherhome/SpecialWorkload'
  },
  {
    path: '/teacherhome',
    component: Teacherhome,
    children: [
      { path: '', redirect: "PersonalInfo" },
      { path: 'PersonalInfo', component: TeacherPersonalInfo },
      { path: "SpecialWorkload", component: SpecialWorkload },
      { path: "TeacherWorkloadTable", component: TeacherWorkloadTable },
    ]
  },
  {
    path: '/managerhome',
    component: Managerhome,
    children: [
      { path: '', redirect: "personalInfo" },
      { path: 'personalInfo', component: ManagerPersonalInfo },
      { path: 'uploadWorkload', component: UploadWorkload },
      { path: "checkWorkload", component: CheckWorkload }
    ]
  },
]

const router = new VueRouter({
  mode: 'history',
  routes
})

export default router
