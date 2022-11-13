const state = {
    //特殊工作量类型及名称（vue2不能遍历map，将信息和formItems分开成两个部分好一些）
    workloadTypes: [
        {
            key: 'BB1',
            name: '课程建设'
        },
        {
            key: 'BB2',
            name: '教研项目'
        },
        {
            key: 'BB3',
            name: '教学成果奖'
        },
        {
            key: 'BB4',
            name: '教学竞赛奖励'
        },
        {
            key: 'BB5',
            name: '发表教学研究论文'
        },
        {
            key: 'BB6',
            name: '?????'
        },
        {
            key: 'BB7',
            name: '教学名师'
        },
        {
            key: 'BB8',
            name: '指导优秀毕业论文'
        },
        {
            key: 'BB9',
            name: '指导学生竞赛获奖'
        },

        {
            key: 'BB10',
            name: '指导学生科研立项并顺利完成项目'
        },
        {
            key: 'BB11',
            name: '教学兼职'
        },
        // {
        //     key:'BB12',
        //     name:'?????'
        // },
        {
            key: 'BB13',
            name: '本科生培养活动'
        },
        {
            key: 'BB14',
            name: '本科生主考之外的监考计'
        },
        {
            key: 'BB15',
            name: '其他'
        },
    ],
    //填报特殊工作量每个类型的表单组成
    formItems: new Map([
        [
            "BB1",
            [
                {
                    label: "项目名称",
                    key: "projectName",
                    inputType: 'text',
                    rule: [
                        { required: true, message: '该项不可为空', trigger: 'blur' },
                    ]
                },
                {
                    label: "立项时间",
                    key: "awardDate",
                    inputType: 'time',
                    timeType: 'month',  //timeType的合法值只能是el-ui里有的
                    rule: [
                        { required: true, message: '该项不可为空', trigger: 'blur' },
                    ]
                },
                {
                    label: "课程类别",
                    key: "projectCategory",
                    inputType: 'select',
                    selectOptions: ['课程建设项目', '企业-教育部课程建设项目', '国家级课程建设项目', '线上国家级一流本科课程建设项目', '线下国家级一流本科课程建设项目', '线上线下混合式国家级一流本科课程建设项目', '社会实践国家级一流本科课程建设项目', '武大通识3.0', '慕课课程', '其他'],
                    rule: [
                        { required: true, message: '该项不可为空', trigger: 'blur' },
                    ]
                },
                {
                    label: '进展情况',
                    key: 'projectStatus',
                    inputType: 'radio',
                    radioOptions: ['立项', '结题', '建设中'],
                    rule: [
                        { required: true, message: '该项不可为空', trigger: 'blur' },
                    ]
                },
                {
                    label: "获奖级别",
                    key: "awardLevel",
                    inputType: 'radio',
                    radioOptions: ['国家级', '省级', '校级'],
                    rule: [
                        { required: true, message: '该项不可为空', trigger: 'blur' },
                    ]
                },
                {
                    label: '参与人员',
                    key: 'teachers',
                    inputType: 'dynamic',
                    rule: [
                        // { required: true, message: '该项不可为空', trigger: 'blur' },
                    ]
                },
                {
                    label: "附件",
                    key: "files",
                    inputType: 'file',

                },

            ]
        ],
        [
            "BB2",
            [
                {
                    label: "项目名称",
                    key: "projectName",
                    inputType: 'text',
                },
                {
                    label: "项目类别",
                    key: "projectCategory",
                    inputType: 'select',
                    selectOprions: ["课程思政示范专业建设项目", "自由选题建设项目", "教师教学发展研究项目", "MOOC课程建设项目", "课程思政课程建设项目"]
                },
                {
                    label: '进展情况',
                    key: 'projectStatus',
                    inputType: 'radio',
                    radioOptions: ['立项', '结题', '建设中']
                },
                {
                    label: "获奖级别",
                    key: "awardLevel",
                    inputType: 'radio',
                    radioOptions: ['国家级', '省级', '校级']
                },
                {
                    label: '参与人员',
                    key: 'teachers',
                    inputType: 'dynamic'
                },
                {
                    label: "附件",
                    key: "files",
                    inputType: 'file',
                },
            ]


        ],
        [
            "BB3",
            {
                name: '教学成果奖',
                formItems: [
                    {
                        label: "项目名称",
                        key: "projectName",
                        inputType: 'text',
                    },
                    {
                        label: "获奖级别",
                        key: "awardLevel",
                        inputType: 'radio',
                        radioOptions: ['国家级', '省级', '校级']
                    },
                    {
                        label: '奖项名称',
                        key: 'awardName',
                        inputType: 'text'
                    },
                    {
                        label: '奖项类别',
                        key: 'awardCategory',
                        inputType: 'text'
                    },
                    {
                        label: '授奖单位',
                        key: 'awardApartment',
                        inputType: 'text'
                    },
                    {
                        label: '获奖时间',
                        key: 'awardDate',
                        inputType: 'time',
                        timeType: 'month'
                    },
                    {
                        label: '参与人员',
                        key: 'teachers',
                        inputType: 'dynamic'
                    },
                    {
                        label: '附件',
                        key: 'files',
                        inputType: 'file'
                    }
                ]
            },

        ],
        [
            "BB4",
            {
                name: '教学竞赛奖励',
                formItems: [
                    {
                        label: '竞赛名称',
                        key: 'projectName',
                        inputType: 'text'
                    },
                    {
                        label: "获奖级别",
                        key: "awardLevel",
                        inputType: 'radio',
                        radioOptions: ['国家级', '省级', '校级']
                    },
                    {
                        label: '奖项类别',
                        key: 'projectCategory',
                        inputType: 'text'
                    },
                    {
                        label: '授奖单位',
                        key: 'awardApartment',
                        inputType: 'text'
                    },
                    {
                        label: '获奖时间',
                        key: 'awardDate',
                        inputType: 'time',
                        timeType: 'month'
                    },
                    {
                        label: '参与人员',
                        key: 'teachers',
                        inputType: 'dynamic'
                    },
                    {
                        label: '附件',
                        key: 'files',
                        inputType: 'file'
                    },

                ]
            }

        ],
        [
            "BB5",
            {
                name: '发表教学研究论文',
                formItems: [
                    {
                        label: '级别',
                        key: 'level',
                        inputType: 'radio',
                        radioOptions: ['权威', '核心', '非核心']
                    },
                    {
                        label: '文章名称',
                        key: 'achievementName',
                        inputType: 'text'
                    },
                    {
                        label: '刊物名称',
                        key: 'publicationName',
                        inputType: 'text'
                    },
                    {
                        label: '刊物期数',
                        key: 'publicationsNumber',
                        inputType: 'number'
                    },
                    {
                        label: '参与人员',
                        key: 'teachers',
                        inputType: 'dynamic'
                    },
                    {
                        label: '附件',
                        key: 'files',
                        inputType: 'file'
                    }
                ]
            }

        ],
        [
            "BB6",
            {
                name: '???',
                formItems: [
                    {
                        label: '书名',
                        key: 'achievementName',
                        inputType: 'text',
                    },
                    {
                        label: 'ISBN号',
                        key: 'isbn',
                        inputType: 'text'
                    },
                    {
                        label: '内容简介',
                        key: 'briefIntroduction',
                        inputType: 'textarea'
                    },
                    {
                        label: '出版日期',
                        key: 'awardDate',
                        inputType: 'time',
                        timeType: 'month'
                    },
                    {
                        label: '教材版次',
                        key: 'publicationsNumber',
                        inputType: 'text'
                    },
                    {
                        label: '所获荣誉',
                        key: 'receivingHonor',
                        inputType: 'text'
                    },
                    {
                        label: '参与人员',
                        key: 'teachers',
                        inputType: 'dynamic'
                    },
                    {
                        label: '附件',
                        key: 'files',
                        inputType: 'file'
                    }
                ]
            }
        ],
        [
            "BB7",
            {
                name: '教学名师',
                formItems: [
                    {
                        label: "获奖级别",
                        key: "awardLevel",
                        inputType: 'radio',
                        radioOptions: ['国家级', '省级', '校级']
                    },
                    {
                        label: '获奖时间',
                        key: 'awardDate',
                        inputType: 'time',
                        timeType: 'month'
                    },
                ]
            }
        ],
        [
            "BB8",
            {
                name: "指导优秀毕业论文",
                formItems: [
                    {
                        label: '指导类型',
                        key: 'projectCategory',
                        inputType: 'radio',
                        radioOptions: ['独立执导', '与院外合作指导']
                    },
                    {
                        label: '论文名称',
                        key: 'achievementName',
                        inputType: 'text',
                    },
                    {
                        label: '执导学生姓名',
                        key: 'guidingStudentName',
                        inputType: 'text'
                    },
                ]
            }
        ],
        [
            "BB9",
            {
                name: "指导学生竞赛获奖",
                formItems: [
                    {
                        label: '竞赛类别',
                        key: 'projectCategory',
                        inputType: 'radio',
                        radioOptions: ['A', 'B', 'C']
                    },
                    {
                        label: '竞赛名称',
                        key: 'projectName',
                        inputType: 'text'
                    },
                    {
                        label: '竞赛级别',
                        key: 'level',
                        inputType: 'radio',
                        radioOptions: ["国际", "全国", "省级", "校级"],
                    },
                    {
                        label: '获奖等级',
                        key: 'awardLevel',
                        inputType: 'radio',
                        radioOptions: ['一等奖', '二等奖', '三等奖', '其它']
                    },
                    {
                        label: '参赛作品名称',
                        key: 'projectName',
                        inputType: 'text'
                    },
                    {
                        label: '参赛队伍名称',
                        key: 'guidingStudentTeam',
                        inputType: 'text'
                    },
                    {
                        label: '执导学生姓名',
                        key: 'guidingStudentName',
                        inputType: 'text'
                    },
                    {
                        label: '附件',
                        key: 'files',
                        inputType: 'file'
                    }
                ]
            },

        ],
        [
            "BB10",
            {
                name: '指导学生科研立项并顺利完成项目',
                formItems: [
                    {
                        label: '项目级别',
                        key: 'awardLevel',
                        inputType: 'radio',
                        radioOptions: ['国家级', '省级', '校级', '院级']
                    },
                    {
                        label: '所获荣誉',
                        key: 'receivingHonor',
                        inputType: 'text',
                    },
                    {
                        label: '项目名称',
                        key: 'achievementName',
                        inputType: 'text'
                    },
                    {
                        label: '学生团队名称',
                        key: 'guidingStudentTeam',
                        inputType: 'text'
                    },
                    {
                        label: '执导学生姓名',
                        key: 'guidingStudentName',
                        inputType: 'text'
                    },
                ]
            },

        ],
        [
            "BB11",
            {
                name: '教学兼职',
                formItems: [
                    {
                        label: '内容',
                        key: 'briefIntroduction',
                        inputType: 'textarea'
                    }
                ]
            }
        ],
        [
            "BB13",
            {
                name: '本科生培养活动',
                formItems: [
                    {
                        label: '类别',
                        key: 'projectCategory',
                        inputType: 'select',
                        selectOptions: ["卓越班选拔面试", "校园开放日咨询", "本科生分专业宣讲", "转专业面试", "本科生招生咨询", "各类评审", "其他"]
                    },
                    {
                        label: '时间',
                        key: 'awardDate',
                        inputType: 'time',
                        timeType: 'month'
                    }
                ]
            }
        ],
        [
            "BB14",
            {
                name: '本科生主考之外的监考计',
                formItems:
                    [
                        {
                            label: '内容',
                            key: 'briefIntroduction',
                            inputType: 'textarea'
                        }
                    ]
            },
        ],
        [
            "BB15",
            {
                name: '',
                formItems:
                    [
                        {
                            label: '内容',
                            key: 'briefIntroduction',
                            inputType: 'textarea'
                        }
                    ]
            },

        ]
    ]),
    //填报特殊工作量添加参与人员时，查询职工列表的表头
    teacherListHeaders: [
        {
            label: '姓名',
            key: 'name'
        },
        {
            label: '职工号',
            key: 'account'
        }
    ],
    //审核特殊工作量时，查询列表的条件过滤
    filters: [
        {
            type_filter: "statusList",
            label: '审核状态',
            type_input: "select",
            options: ['全部', "未审核", "已审核",], //当输入类型为select时，需要提供options
        },
        {
            type_filter: "type",
            label: '教学业绩类型',
            type_input: "select",
            options: ['BB1', 'BB2', 'BB3', 'BB4', 'BB5', 'BB6', 'BB7', 'BB8', 'BB9', 'BB10', 'BB11', 'BB13', 'BB14', 'BB15'],
        }
    ],
    //审核特殊工作量时，每个类型的共有表头
    constantHeaders: [
        {
            label: "教学业绩类型",
            key: "type",
        },
        {
            label: "审核状态",
            key: "status",
        },

        {
            label: "申报人",
            key: "declarantName",
        },
        {
            label: "申报时间",
            key: "reportTime",
        },
        // {
        //     label: "附件",
        //     key: "filePath",
        // },
    ],
    //审核特殊工作量时，每个类型的专有表头（也就是嵌套列表内的表头）
    specificHeaders: new Map([
        [
            "BB1",
            [
                {
                    label: "项目名称",
                    key: "projectName",
                },
                {
                    label: "立项时间",
                    key: "awardDate",
                },
                {
                    label: "课程类别",
                    key: "projectCategory",
                },
                {
                    label: '进展情况',
                    key: 'projectStatus',
                },

            ]
        ],
        [
            "BB2",
            [
                {
                    label: "项目名称",
                    key: "projectName",
                },
                {
                    label: "项目类别",
                    key: "projectCategory",
                },
                {
                    label: '进展情况',
                    key: 'projectStatus',
                },
                {
                    label: "获奖级别",
                    key: "awardLevel",
                },

            ]
        ],
        [
            "BB3",
            [
                {
                    label: "项目名称",
                    key: "projectName",
                },
                {
                    label: "获奖级别",
                    key: "awardLevel",
                },
                {
                    label: '奖项名称',
                    key: 'awardName',
                },
                {
                    label: '奖项类别',
                    key: 'awardCategory',
                },
                {
                    label: '授奖单位',
                    key: 'awardApartment',
                },
                {
                    label: '获奖时间',
                    key: 'awardDate',
                },
            ]
        ]
    ]),
}



export default {
    namespaced: true,
    state
}