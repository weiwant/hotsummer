import Vue from 'vue'
import VueRouter from 'vue-router'
import Teacherhome from '../components/HSH/Teacherhome.vue'
import Managerhome from '../components/LJY/Managerhome.vue'
import CheckTable from '../components/LJY/CheckTable.vue'

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
