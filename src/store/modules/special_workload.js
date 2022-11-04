const state = {
    filters: [
        {
            type_filter: "status",
            label: '审核状态',
            type_input: "select",
            options: ["未审核", "已审核"], //当输入类型为select时，需要提供options
        },
        {
            type_filter: "type",
            label: '教学业绩类型',
            type_input: "text",
            options: [],
        }
    ],
    headerGroups: [
        [
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
                value: "planingSchool",
            },


        ],
        [
            {
                key: "获奖等级",
                value: "awardLevel",
            },
            {
                key: "获奖/获评/出版日期",
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
            }
        ],
        [
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
            {
                key: "备注",
                value: "remarks",
            },
            {
                key: "相关文件",
                value: "filePath",
            },
            {
                key: "审核",
                value: "somePeople",
            }
        ],

    ],
}



export default {
    namespaced: true,
    state
}