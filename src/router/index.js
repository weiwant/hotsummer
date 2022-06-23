import Vue from 'vue'
import VueRouter from 'vue-router'
import Teacherhome from '../components/HSH/Teacherhome.vue'
import Managerhome from '../components/LJL/Managerhome.vue'
import ManagerUpload from '../components/LJY/ManagerUpload.vue'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'Teacherhome',
    component: Teacherhome
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
