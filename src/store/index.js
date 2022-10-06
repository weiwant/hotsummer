import Vue from 'vue'
import Vuex from 'vuex'
import user from './modules/user'
import teaching_workload from './modules/teaching_workload'


Vue.use(Vuex)

const store = new Vuex.Store({
    modules: {
        user,
        teaching_workload
    },
    state: {
        currentYear: `${new Date().getFullYear()}`
    }

})

export default store