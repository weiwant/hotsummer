import Vue from 'vue'
import VueRouter from 'vue-router'
import Teacherhome from '../components/HSH/Teacherhome.vue'
import SpecialWorkload from '../components/HSH/SpecialWorkload.vue'
import TeacherWorkloadTable from '../components/HSH/TeacherWorkloadTable.vue'
import PersonalInfo from '../components/HSH/PersonalInfo.vue'
import Managerhome from '../components/LJY/Managerhome.vue'
import CheckTable from '../components/LJY/CheckTable.vue'
import Login from '../components/LJY/Login.vue'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'Login',
    component: Login
  },
  {
    path: '/teacherhome',
    name: 'Teacherhome',
    component: Teacherhome,
    children: [
      { path: '', redirect: "PersonalInfo" },
      { path: 'PersonalInfo', component: PersonalInfo },
      { path: "SpecialWorkload", component: SpecialWorkload },
      { path: "TeacherWorkloadTable", component: TeacherWorkloadTable },
    ]
  },
  {
    path: '/managerhome',
    name: 'Managerhome',
    component: Managerhome,
    children: [
      {
        path: '/checktable',
        name: 'CheckTable',
        component: CheckTable
      }
    ]
  },
]

const router = new VueRouter({
  mode: 'history',
  routes
})

export default router
