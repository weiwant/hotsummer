const state = {
    //查询教学工作量的过滤器
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
    //教学工作量table的展示表头组
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
    ],
    // 工作量类型
    type: [
        {
            apiName: 'academic',
            label: '课程工作量'
        },
        {
            apiName: 'examination',
            label: '考务工作量'
        },
        {
            apiName: 'paper',
            label: '论文工作量'
        },
    ],
    //教学工作量文档模版
    classWorkloadTableTemplate: [
        {
            学年: "",
            学期: "",
            辅助: "",
            计算用学时: "",
            课程性质解释: "",
            计算机用时: "",
            课程名称: "",
            课程性质: "",
            课程号: "",
            学分: "",
            折扣: "",
            实验安排: "",
            实验课时: "",
            教分: "",
            合课单位: "",
            实验室核对结果: "",
            上课教师名字: "",
            教师职称: "",
            专业: "",
            折扣前BA1系数: "",
            原始教分: "",
            其他教师名: "",
            计划学院: "",
            实践课时: "",
            备注: "",
            学期: "",
            是否为特殊班级: "",
            是否全英教学: "",
            上课人数: "",
            年级: "",
            教学班: "",
            BA1系数: "",
            开课学院: "",
            理论课时: "",
            工作性质: "",
        },
    ],
    examinationWorkloadTableTemplate: [
        {
            学年: "",
            学期: "",
            课程名称: "",
            上课老师: "",
            计算用学时: "",
            教分原始分: "",
            BA1系数: "",
            教分: "",
        },
    ],
    paperWorkloadTableTemplate: [
        {
            学年: "",
            学期: "",
            课程名称: "",
            上课老师: "",
            计算用学时: "",
            教分原始分: "",
            BA1系数: "",
            教分: "",
        },
    ],
}



export default {
    namespaced: true,
    state
}