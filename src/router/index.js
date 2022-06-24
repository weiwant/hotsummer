import Vue from 'vue'
import VueRouter from 'vue-router'
import Teacherhome from '../components/HSH/Teacherhome.vue'
import SpecialWorkload from '../components/HSH/SpecialWorkload.vue'
import TeacherWorkloadTable from '../components/HSH/TeacherWorkloadTable.vue'
import PersonalInfo from '../components/HSH/PersonalInfo.vue'
import Managerhome from '../components/LJL/Managerhome.vue'
import ManagerUpload from '../components/LJY/ManagerUpload.vue'


Vue.use(VueRouter)

const routes = [
  {
    path: "/",
    redirect: "/Teacherhome"
  },
  {
    path: '/Teacherhome',
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
    component: Managerhome
  },
  {
    path: '/managerupload',
    name: 'ManagerUpload',
    component: ManagerUpload
  }
]

const router = new VueRouter({
  mode: 'history',
  routes
})

export default router
