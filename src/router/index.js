import Vue from 'vue'
import VueRouter from 'vue-router'
import Teacherhome from '../components/HSH/teacher/Teacherhome.vue'
import SpecialWorkload from '../components/HSH/teacher/SpecialWorkload.vue'
import TeacherWorkloadTable from '../components/HSH/teacher/TeacherWorkloadTable.vue'
import TeacherPersonalInfo from '../components/HSH/teacher/TeacherPersonalInfo.vue'
import Managerhome from '../components/HSH/manager/Managerhome.vue'
import ManagerPersonalInfo from '../components/HSH/manager/ManagerPersonalInfo.vue'
import WorkloadTableForManager from '../components/HSH/manager/WorkloadTableForManager.vue'
// import CheckTable from '../components/LJY/CheckTable.vue'
import Login from '../components/LJY/Login.vue'

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
    // name: 'Teacherhome',
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
    // name: 'Managerhome',
    component: Managerhome,
    children: [
      // {
      //   path: '/checktable',
      //   name: 'CheckTable',
      //   component: CheckTable
      // }
      { path: '', redirect: "personalInfo" },
      { path: 'managerPersonalInfo', component: ManagerPersonalInfo },
      { path: 'workloadTableForManager', component: WorkloadTableForManager }
    ]
  },
]

const router = new VueRouter({
  mode: 'history',
  routes
})

export default router
