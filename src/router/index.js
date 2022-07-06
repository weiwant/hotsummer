import Vue from 'vue'
import VueRouter from 'vue-router'
const Teacherhome = () => import('../components/Teacher/Teacherhome.vue')
const ViewHistory = () => import('../components/Teacher/specialWorkload/ViewHistory.vue')
const UploadSpecialWorkload = () => import('../components/Teacher/specialWorkload/UploadSpecialWorkload.vue')
const TeacherWorkloadTable = () => import('../components/Teacher/TeacherWorkloadTable.vue')
const TeacherPersonalInfo = () => import('../components/Teacher/TeacherPersonalInfo.vue')
const Managerhome = () => import('../components/Manager/Managerhome.vue')
const ManagerPersonalInfo = () => import('../components/Manager/ManagerPersonalInfo.vue')
const CheckWorkload = () => import('../components/Manager/CheckWorkload.vue')
const UploadWorkload = () => import('../components/Manager/UploadWorkload.vue')
const AuditWorkload = () => import('../components/Manager/AuditWorkload.vue')
const Login = () => import('../Login.vue')

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'Login',
    component: Login
    // redirect: '/teacherhome/SpecialWorkload'
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
