import Vue from 'vue'
import VueRouter from 'vue-router'
const Teacherhome = () => import('../pages/Teacher/Teacherhome.vue')
const ViewHistory = () => import('../pages/Teacher/specialWorkload/ViewHistory.vue')
const UploadSpecialWorkload = () => import('../pages/Teacher/specialWorkload/UploadSpecialWorkload.vue')
const TeacherWorkloadTable = () => import('../pages/Teacher/TeacherWorkloadTable.vue')
const TeacherPersonalInfo = () => import('../pages/Teacher/TeacherPersonalInfo.vue')
const Managerhome = () => import('../pages/Manager/Managerhome.vue')
const ManagerPersonalInfo = () => import('../pages/Manager/ManagerPersonalInfo.vue')
const CheckWorkload = () => import('../pages/Manager/CheckWorkload.vue')
const UploadWorkload = () => import('../pages/Manager/UploadWorkload.vue')
const AuditWorkload = () => import('../pages/Manager/AuditWorkload.vue')
const Login = () => import('../Login.vue')

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'Login',
    // component: Login
    redirect: '/managerhome'
  },
  {
    path: '/teacherhome',
    component: Teacherhome,
    children: [
      { path: '', redirect: "PersonalInfo" },
      { path: 'PersonalInfo', component: TeacherPersonalInfo },
      { path: "TeacherWorkloadTable", component: TeacherWorkloadTable },
      { path: "SpecialWorkload", component: UploadSpecialWorkload },
      { path: "ViewHistory", component: ViewHistory },
    ]
  },
  {
    path: '/managerhome',
    component: Managerhome,
    children: [
      { path: '', redirect: "personalInfo" },
      { path: 'personalInfo', component: ManagerPersonalInfo },
      { path: 'uploadWorkload', component: UploadWorkload },
      { path: "checkWorkload", component: CheckWorkload },
      { path: "auditWorkload", component: AuditWorkload },
    ]
  },
]

const router = new VueRouter({
  mode: 'history',
  routes
})

export default router
