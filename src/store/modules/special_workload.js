const state = {
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
            type_input: "text",
            options: [],
        }
    ],
    //审核特殊工作量时，每个类型的共有表头
    constantHeaders: [
        {
            key: "审核状态",
            value: "status",
        },
        {
            key: "申报人",
            value: "declarantName",
        },
        {
            key: "申报时间",
            value: "reportTime",
        },
        {
            key: "教学业绩类型",
            value: "type",
        },
        {
            key: "附件",
            value: "filePath",
        },
    ],
    //填报特殊工作量每个类型的表单组成、审核时也依据这个决定展开列表具体显示什么
    specificHeaders: new Map([
        [
            "BB1",
            [
                {
                    lable: "项目名称",
                    key: "projectName",
                    inputType: 'text',
                },
                {
                    lable: "立项时间",
                    key: "awardDate",
                    inputType: 'month',
                },
                {
                    lable: "课程类别",
                    key: "projectCategory",
                    inputType: 'select',
                    selectOptions: ['课程建设项目', '企业-教育部课程建设项目', '国家级课程建设项目', '线上国家级一流本科课程建设项目', '线下国家级一流本科课程建设项目', '线上线下混合式国家级一流本科课程建设项目', '社会实践国家级一流本科课程建设项目', '武大通识3.0', '慕课课程', '其他']
                },
                {
                    label: '进展情况',
                    key: 'projectStatus',
                    inputType: 'radio',
                    radioOptions: ['立项', '结题', '建设中']
                },
                {
                    lable: "获奖级别",
                    key: "awardLevel",
                    inputType: 'radio',
                    radioOptions: ['国家级', '省级', '校级']
                },
                {
                    lable: '参与人员',
                    key: 'teachers',
                    inputType: 'dynamic'
                },
                {
                    lable: "附件",
                    key: "files",
                    inputType: 'file',
                },

            ]
        ],
        [
            "BB2",
            [
                {
                    lable: "项目名称",
                    key: "projectName",
                    inputType: 'text',
                },
                {
                    lable: "项目类别",
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
                    lable: "获奖级别",
                    key: "awardLevel",
                    inputType: 'radio',
                    radioOptions: ['国家级', '省级', '校级']
                },
                {
                    lable: '参与人员',
                    key: 'teachers',
                    inputType: 'dynamic'
                },
                {
                    lable: "附件",
                    key: "files",
                    inputType: 'file',
                },
            ]
        ],
        [
            "BB3",
            [
                {
                    lable: "项目名称",
                    key: "projectName",
                    inputType: 'text',
                },
                {
                    lable: "获奖级别",
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
                    inputType: 'date'
                },
                {
                    lable: '参与人员',
                    key: 'teachers',
                    inputType: 'dynamic'
                },
                {
                    label: '附件',
                    key: 'files',
                    inputType: 'file'
                }
            ]
        ],
        [
            "BB4",
            [
                {
                    label: '竞赛名称',
                    key: 'projectName',
                    inputType: 'text'
                },
                {
                    lable: "获奖级别",
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
                    inputType: 'date'
                },
                {
                    lable: '参与人员',
                    key: 'teachers',
                    inputType: 'dynamic'
                },
                {
                    label: '附件',
                    key: 'files',
                    inputType: 'file'
                },

            ]
        ],
        [
            "BB5",
            [
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
                    lable: '参与人员',
                    key: 'teachers',
                    inputType: 'dynamic'
                },
                {
                    label: '附件',
                    key: 'files',
                    inputType: 'file'
                }
            ]
        ],
        [
            "BB6",
            [
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
                    inputType: 'date'
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
                    lable: '参与人员',
                    key: 'teachers',
                    inputType: 'dynamic'
                },
                {
                    label: '附件',
                    key: 'files',
                    inputType: 'file'
                }
            ]
        ],
        [
            "BB7",
            [
                {
                    lable: "获奖级别",
                    key: "awardLevel",
                    inputType: 'radio',
                    radioOptions: ['国家级', '省级', '校级']
                },
                {
                    label: '获奖时间',
                    key: 'awardDate',
                    inputType: 'date'
                },
            ]
        ],
        [
            "BB8",
            [
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
        ],
        [
            "BB9",
            [
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
        ],
        [
            "BB10",
            [
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
        ],
        [
            "BB11",
            [
                {
                    label: '内容',
                    key: 'briefIntroduction',
                    inputType: 'textarea'
                }
            ]
        ],
        [
            "BB13",
            [
                {
                    label: '类别',
                    key: 'projectCategory',
                    inputType: 'select',
                    selectOptions: ["卓越班选拔面试", "校园开放日咨询", "本科生分专业宣讲", "转专业面试", "本科生招生咨询", "各类评审", "其他"]
                },
                {
                    label: '时间',
                    key: 'awardDate',
                    inputType: 'date'
                }
            ]
        ],
        [
            "BB14",
            [
                {
                    label: '内容',
                    key: 'briefIntroduction',
                    inputType: 'textarea'
                }
            ]
        ],
        [
            "BB15",
            [
                {
                    label: '内容',
                    key: 'briefIntroduction',
                    inputType: 'textarea'
                }
            ]
        ]
    ]),
    allHeaders: [
        {
            key: "成果/作品/参赛项目/参评项目/论文/专著名称",
            value: "achievementName",
        },
        {
            key: "级别",
            value: "level",
        },
        {
            key: "课程/项目/奖项/竞赛/论文指导类别",
            value: "projectCategory",
        },
        {
            key: "课程/项目/奖项/竞赛详细名称",
            value: "projectName",
        },

        {
            key: "获奖等级",
            value: "awardLevel",
        },
        {
            key: "获奖/获评/出版日期/立项时间",
            value: "awardDate",
        },
        {
            key: "项目进展",
            value: "projectStatus",
        },
        {
            key: "授奖单位",
            value: "awardApartment",
        },
        {
            key: "刊物/出版社名称",
            value: "publicationName",
        },
        {
            key: "刊物期数/出版版次",
            value: "publicationsNumber",
        },
        {
            key: "ISBN号",
            value: "isbn",
        },

        {
            key: "所获荣誉",
            value: "receivingHonor",
        },
        {
            key: "指导学生团队名",
            value: "guidingStudentTeam",
        },
        {
            key: "指导学生姓名",
            value: "guidingStudentName",  //guidingStudentId
        },
        {
            key: "内容简介",
            value: "briefIntroduction",
        },
        // {
        //     key: "备注",
        //     value: "remarks",
        // },

        {
            key: "审核",
            value: "somePeople",
        }
    ]
}



export default {
    namespaced: true,
    state
}