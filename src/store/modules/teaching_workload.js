const state = {
    //条件查询过滤器
    filters_teacher: [
        {
            key: "workloadNature",
            label: '工作量类型',
            inputType: "select",
            options: ["教学工作量", "监考工作量", "论文工作量"], //当输入类型为select时，需要提供options
        },
        {
            key: "courseName",
            label: '课程名称',
            inputType: "text",
            options: [],
        },
    ],
    filters_master: [
        {
            key: "workloadNature",
            label: '工作量类型',
            inputType: "select",
            options: ["教学工作量", "监考工作量", "论文工作量"],
        },
        {
            key: "courseName",
            label: '任课老师',
            inputType: "text",
            options: [],
        },
        {
            key: "courseName",
            label: '课程名称',
            inputType: "text",
            options: [],
        },
    ],
    // 教学工作量表头(管理员)
    tableHeaders: [
        {
            label: "工作量类型",
            key: "workloadNature",
            show: true,    //是否默认选中
            editable: false,     //是否可编辑
        },
        {
            label: "学年",
            key: "academicYear",
            show: true,
            editable: false,
        },
        {
            label: "学期",
            key: "semester",
            show: true,
            editable: false,
        },
        {
            label: "课程号",
            key: "courseNumber",
            show: true,
            editable: false,
        },
        {
            label: "课程名称",
            key: "courseName",
            show: true,
            editable: false,
        },
        {
            label: "任课老师",
            key: "mainTeacherName",
            show: true,
            editable: false,
        },
        {
            label: "职称",
            key: "mainTeacherTitle",
            show: false,
            editable: false,
        },
        {
            label: "上课人数",
            key: "studentAmount",
            show: true,
            editable: true,
        },
        {
            label: "理论课时",
            key: "theoreticalClassHours",
            show: true,
            editable: true,
        },
        {
            label: "上机课时",
            key: "computerClassHours",
            show: true,
            editable: true,
        },
        {
            label: "实验课时",
            key: "experimentalClassHours",
            show: true,
            editable: true,
        },
        {
            label: "实践课时",
            key: "practicalClassHours",
            show: true,
            editable: true,
        },
        {
            label: "计算用课时",
            key: "calculatingClassHours",
            show: true,
            editable: true,
        },
        {
            label: "教分（BA1/3/15）原始分",
            key: "originalTeachingScores",
            show: true,
            editable: true,
        },
        {
            label: "BA1系数",
            key: "teachingCoefficient",
            show: true,
            editable: true,
        },
        {
            label: "教分（BA1/3/15）",
            key: "finalTeachingScores",
            show: true,
            editable: true,
        },
        {
            label: "教学班",
            key: "teachingClass",
            show: false,
            editable: false,
        },
        {
            label: "开课学院",
            key: "teachingSchool",
            show: false,
            editable: false,
        },
        {
            label: "计划学院",
            key: "planSchool",
            show: false,
            editable: false,
        },
        {
            label: "学分",
            key: "credit",
            show: false,
            editable: false,
        },
        {
            label: "课程性质",
            key: "courseNature",
            show: false,
            editable: false,
        },
        {
            label: "年级",
            key: "studentGrade",
            show: false,
            editable: false,
        },
        {
            label: "专业",
            key: "major",
            show: false,
            editable: false,
        },
        {
            label: "合课单位",
            key: "jointDepartment",
            show: false,
            editable: false,
        },
        {
            label: "备注",
            key: "remarks",
            show: false,
            editable: false,
        },
        {
            label: "实验安排",
            key: "experimentArrangement",
            show: false,
            editable: false,
        },
        {
            label: "其它教师",
            key: "otherTeacherName",
            show: false,
            editable: false,
        },
        {
            label: "课程性质说明",
            key: "classNatureExplanation",
            show: false,
            editable: false,
        },
        {
            label: "是否卓工或弘毅",
            key: "specialClassRemarks",
            show: false,
            editable: false,
        },
        {
            label: "是否全英文",
            key: "specialLanguageRemarks",
            show: false,
            editable: false,
        },
        {
            label: "是否卓工或弘毅",
            key: "specialClassRemarks",
            show: false,
            editable: false,
        },
        {
            label: "是否打折",
            key: "discount",
            show: false,
            editable: false,
        },
        {
            label: "未打折前",
            key: "noDiscountTeachingCoefficient",
            show: false,
            editable: false,
        },
        {
            label: "实验室核对结果",
            key: "laboratoryVerificationResults",
            show: false,
            editable: false,
        }
    ],
    // 教学工作量表头(教师)
    tableHeaders_teacher: [
        {
            label: "工作量类型",
            key: "workloadNature",
        },
        {
            label: "学年",
            key: "academicYear",
        },
        {
            label: "学期",
            key: "semester",
        },
        {
            label: "课程名称",
            key: "courseName",
        },
        {
            label: "学分",
            key: "credit",
        },
        {
            label: "课程性质",
            key: "courseNature",
        },
        {
            label: "年级",
            key: "studentGrade",
        },
        {
            label: "专业",
            key: "major",
        },
        {
            label: "理论课时",
            key: "theoreticalClassHours",
        },
        {
            label: "上机课时",
            key: "computerClassHours",
        },
        {
            label: "实验课时",
            key: "experimentalClassHours",
        },
        {
            label: "计算用课时",
            key: "calculatingClassHours",
        },
        {
            label: "教分（BA1/3/15）",
            key: "finalTeachingScores",
        },
    ],
    // 教学工作量类型
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