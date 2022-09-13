const TeacherHome = () => import('../views/teacher/Home.vue')
const ViewHistory = () => import('../views/teacher/special-workload/ViewHistory.vue')
const UploadSpecialWorkload = () => import('../views/teacher/special-workload/UploadSpecialWorkload.vue')
const TeacherWorkloadTable = () => import('../views/teacher/ViewWorkload.vue')
const TeacherPersonalInfo = () => import('../views/teacher/PersonalInfo.vue')
const ManagerHome = () => import('../views/manager/Home.vue')
const ManagerPersonalInfo = () => import('../views/manager/PersonalInfo.vue')
const CheckWorkload = () => import('../views/manager/CheckWorkload.vue')
const UploadWorkload = () => import('../views/manager/UploadWorkload.vue')
const AuditWorkload = () => import('../views/manager/AuditWorkload.vue')
const Login = () => import('../views/Login.vue')

const routes = [
    {
        path: '/',
        name: 'Login',
        // component: Login
        redirect: '/managerhome'
    },
    {
        path: '/TeacherHome',
        component: TeacherHome,
        children: [
            { path: '', redirect: "PersonalInfo" },
            { path: 'PersonalInfo', component: TeacherPersonalInfo },
            { path: "TeacherWorkloadTable", component: TeacherWorkloadTable },
            { path: "SpecialWorkload", component: UploadSpecialWorkload },
            { path: "ViewHistory", component: ViewHistory },
        ]
    },
    {
        path: '/ManagerHome',
        component: ManagerHome,
        children: [
            { path: '', redirect: "PersonalInfo" },
            { path: 'PersonalInfo', component: ManagerPersonalInfo },
            { path: 'UploadWorkload', component: UploadWorkload },
            { path: "CheckWorkload", component: CheckWorkload },
            { path: "AuditWorkload", component: AuditWorkload },
        ]
    },
]

export default routes 