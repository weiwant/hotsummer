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
            label: '上课老师',
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
            label: "上课老师",
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
            label: "BA1系数（未打折前）",
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
    academicWorkloadTableTemplate: [
        { header: "学年", key: "academicYear", width: 10 },
        { header: "学期", key: "semester", width: 10 },
        { header: "课程号", key: "courseNumber", width: 10 },
        { header: "课程名称", key: "courseName", width: 10 },
        { header: "教学班", key: "teachingClass", width: 10 },
        { header: "开课学院", key: "teachingSchool", width: 10 },
        { header: "计划学院", key: "planSchool", width: 10 },
        { header: "学分", key: "credit", width: 10 },
        { header: "课程性质", key: "courseNature", width: 10 },
        { header: "年级", key: "studentGrade", width: 10 },
        { header: "专业", key: "major", width: 10 },
        { header: "上课老师", key: "mainTeacherName", width: 10 },
        { header: "职称", key: "mainTeacherTitle", width: 10 },
        { header: "上课人数", key: "studentAmount", width: 10 },
        { header: "理论", key: "theoreticalClassHours", width: 10 },
        { header: "上机", key: "computerClassHours", width: 10 },
        { header: "实验", key: "experimentalClassHours", width: 10 },
        { header: "实践", key: "practicalClassHours", width: 10 },
        { header: "计算用学时", key: "calculatingClassHours", width: 10 },
        { header: "合课单位", key: "jointDepartment", width: 10 },
        { header: "备注", key: "remarks", width: 10 },
        { header: "实验安排", key: "experimentArrangement", width: 10 },
        { header: "其他教师", key: "otherTeacherName", width: 10 },
        { header: "教分（BA1\ 3\ 15）原始分", key: "originalTeachingScores", width: 20 },
        { header: "BA1系数", key: "teachingCoefficient", width: 10 },
        { header: "教分（BA1\ 3\ 15）", key: "finalTeachingScores", width: 20 },
        { header: "辅助", key: "assistant", width: 10 },
        { header: "课程性质说明", key: "classNatureExplanation", width: 20 },
        { header: "是否卓工或弘毅", key: "specialClassRemarks", width: 20 },
        { header: "是否全英文", key: "specialLanguageRemarks", width: 10 },
        { header: "是否打折", key: "discount", width: 20 },
        { header: "BA1系数（未打折前）", key: "noDiscountTeachingCoefficient", width: 20 },
        { header: "实验室核对结果", key: "laboratoryVerificationResults", width: 20 },

    ],
    examinationWorkloadTableTemplate: [
        { header: "学年", key: "academicYear", width: 10 },
        { header: "学期", key: "semester", width: 10 },
        { header: "课程名称", key: "courseName", width: 10 },
        { header: "上课老师", key: "mainTeacherName", width: 10 },
        { header: "计算用学时", key: "calculatingClassHours", width: 10 },
        { header: "教分（BA1\ 3\ 15）原始分", key: "originalTeachingScores", width: 20 },
        { header: "BA1系数", key: "teachingCoefficient", width: 10 },
        { header: "教分（BA1\ 3\ 15）", key: "finalTeachingScores", width: 20 },
    ],
    paperWorkloadTableTemplate: [
        { header: "学年", key: "academicYear", width: 10 },
        { header: "学期", key: "semester", width: 10 },
        { header: "课程名称", key: "courseName", width: 10 },
        { header: "上课老师", key: "mainTeacherName", width: 10 },
        { header: "计算用学时", key: "calculatingClassHours", width: 10 },
        { header: "教分（BA1\ 3\ 15）原始分", key: "originalTeachingScores", width: 20 },
        { header: "BA1系数", key: "teachingCoefficient", width: 10 },
        { header: "教分（BA1\ 3\ 15）", key: "finalTeachingScores", width: 20 },
    ],

}



export default {
    namespaced: true,
    state
}