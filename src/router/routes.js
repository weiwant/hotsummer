import Layout from "@/layout"


//公共路由
export const constantRoutes = [
    {
        path: '/login',
        name: "Login",
        component: () => import("@/views/login")
    },

    {
        path: '/404',
        name: 'NotFound',
        component: () => import('@/views/error-page/404.vue')
    }

]

//专属于教师的
export const teacherRoutes = [
    {
        path: '/',
        redirect: '/workload-report'
    },
    {
        path: '/workload-report',  //查看工作量总报告
        name: 'WorkloadReport',
        component: Layout,
        redirect: 'workload-report/index',
        children: [
            {
                path: 'index',
                component: () => import('@/views/workload-report/index'),
            }
        ],
        meta: {
            title: '工作量报告',
            icon: ''
        }
    },
    {
        path: '/special-workload-upload',  //上报特殊工作量
        name: 'SpecialWorkloadUpload',
        component: Layout,
        redirect: '/special-workload-upload/index',
        children: [
            {
                path: 'index',
                // component: () => import('@/views/special-workload-upload/index')
                component: () => import('@/views/error-page/NotAvailable.vue')
            }
        ],
        meta: {
            title: '上报特殊工作量',
            icon: ''
        },
    },
    { path: '*', redirect: '/404', hidden: true }

]

//专属于管理员的
export const managerRoutes = [
    {
        path: '/',
        redirect: "/teaching-workload-feedback"
    },
    {
        path: '/teaching-workload-feedback',  //教学工作量确认情况
        name: 'TeachingWorkloadUpload',
        component: Layout,
        redirect: '/teaching-workload-feedback/index',
        children: [
            {
                path: 'index',
                component: () => import('@/views/teaching-workload-feedback/index'),
            }
        ],
        meta: {
            title: '工作量确认情况',
            icon: ''
        }
    },
    {
        path: '/teaching-workload-upload',  //上传教学工作量
        name: 'TeachingWorkloadUpload',
        component: Layout,
        redirect: '/teaching-workload-upload/index',
        children: [
            {
                path: 'index',
                component: () => import('@/views/teaching-workload-upload/index'),
            }
        ],
        meta: {
            title: '上传教学工作量',
            icon: ''
        }
    },
    {
        path: '/teaching-workload-check',  //查看教学工作量
        name: 'TeachingWorkloadCheck',
        component: Layout,
        redirect: "/teaching-workload-check/index",
        children: [
            {
                path: "index",
                component: () => import('@/views/teaching-workload-check/index'),
            }
        ],
        meta: {
            title: '查看教学工作量',
            icon: ''
        }
    },
    {
        path: '/special-workload-audit',  //审核特殊工作量
        name: 'SpecialWorkloadAudit',
        component: Layout,
        redirect: '/special-workload-audit/index',
        children: [
            {
                path: 'index',
                // component: () => import('@/views/special-workload-audit/index'),
                component: () => import('@/views/error-page/NotAvailable.vue')
            }
        ],
        meta: {
            title: '审批特殊工作量',
            icon: ''
        }
    },
    { path: '*', redirect: '/404', hidden: true }
]




