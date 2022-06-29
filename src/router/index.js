import Vue from 'vue'
import VueRouter from 'vue-router'
import Teacherhome from '../components/HSH/teacher/Teacherhome.vue'
import SpecialWorkload from '../components/HSH/teacher/SpecialWorkload.vue'
import TeacherWorkloadTable from '../components/HSH/teacher/TeacherWorkloadTable.vue'
import TeacherPersonalInfo from '../components/HSH/teacher/TeacherPersonalInfo.vue'
import Managerhome from '../components/HSH/manager/Managerhome.vue'
import ManagerPersonalInfo from '../components/HSH/manager/ManagerPersonalInfo.vue'
import CheckWorkload from '../components/HSH/manager/CheckWorkload.vue'
import UploadWorkload from '../components/HSH/manager/UploadWorkload.vue'
import Login from '../components/LJY/Login.vue'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'Login',
    // component: Login
    redirect: '/managerhome/checkWorkload'
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
