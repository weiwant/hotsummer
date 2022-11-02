const state = {
    filters: [
        {
            type_filter: "workloadNature",
            label: '工作量性质',
            type_input: "select",
            options: ["教学工作量", "监考工作量", "论文工作量"], //当输入类型为select时，需要提供options
        },
        {
            type_filter: "courseName",
            label: '课程名称',
            type_input: "text",
            options: [],
        },
    ],
    headerGroups: [
        [
            {
                key: "工作量性质",
                value: "workloadNature",
            },
            {
                key: "学年",
                value: "academicYear",
            },
            {
                key: "学期",
                value: "semester",
            },
            {
                key: "课程号",
                value: "courseNumber",
            },
            {
                key: "课程名称",
                value: "courseName",
            },
            {
                key: "教学班",
                value: "teachingClass",
            },
            {
                key: "开课学院",
                value: "teachingSchool",
            },
            {
                key: "计划学院",
                value: "planingSchool",
            },
            {
                key: "学分",
                value: "credit",
            },
            {
                key: "课程性质",
                value: "courseNature",
            },
            {
                key: "年级",
                value: "studentGrade",
            },
            {
                key: "专业",
                value: "major",
            },
            {
                key: "上课老师",
                value: "mainTeacherName",
            },
            {
                key: "职称",
                value: "mainTeacherTitle",
            },
            {
                key: "上课人数",
                value: "studentAmount",
            }
        ],
        [
            {
                key: "理论课时",
                value: "theoreticalClassHours",
            },
            {
                key: "上机课时",
                value: "computerClassHours",
            },
            {
                key: "实验课时",
                value: "experimentalClassHours",
            },
            {
                key: "实践课时",
                value: "practicalClassHours",
            },
            {
                key: "计算用课时",
                value: "calculatingClassHours",
            },
            {
                key: "合课单位",
                value: "jointDepartment",
            },
            {
                key: "备注",
                value: "remarks",
            },
            {
                key: "实验安排",
                value: "experimentArrangement",
            },
            {
                key: "其它教师",
                value: "otherTeacherName",
            }
        ],
        [

            {
                key: "教分（BA1/3/15）原始分",
                value: "originalTeachingScores",
            },
            {
                key: "BA1系数",
                value: "teachingCoefficient",
            },
            {
                key: "教分（BA1/3/15）",
                value: "finalTeachingScores",
            },
            {
                key: "课程性质说明",
                value: "classNatureExplanation",
            },
            {
                key: "是否卓工或弘毅",
                value: "specialClassRemarks",
            },
            {
                key: "是否全英文",
                value: "specialLanguageRemarks",
            },
            {
                key: "是否卓工或弘毅",
                value: "specialClassRemarks",
            },
            {
                key: "是否打折",
                value: "discount",
            },
            {
                key: "未打折前",
                value: "noDiscountTeachingCoefficient",
            },
            {
                key: "实验室核对结果",
                value: "laboratoryVerificationResults",
            }
        ]
    ]
}



export default {
    namespaced: true,
    state
}