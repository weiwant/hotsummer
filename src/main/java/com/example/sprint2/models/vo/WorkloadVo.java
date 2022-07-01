package com.example.sprint2.models.vo;

import com.example.sprint2.mybatis.entity.ExaminationWorkload;
import com.example.sprint2.mybatis.entity.PaperCoachingWorkload;
import com.example.sprint2.mybatis.entity.TeachingWorkloadStatistics;
import com.example.sprint2.utils.annotations.ReferenceField;
import lombok.Getter;
import lombok.Setter;

/**
 * @author ZWForum
 * @description 工作量总表对象
 * @date 2022/6/29
 */
@Getter
@Setter
public class WorkloadVo {
    /**
     * 学年
     */
    @ReferenceField(value = ExaminationWorkload.class, dataBaseName = "academic_year")
    @ReferenceField(value = TeachingWorkloadStatistics.class, dataBaseName = "academic_year")
    @ReferenceField(value = PaperCoachingWorkload.class, dataBaseName = "academic_year")
    private String academicYear;

    /**
     * 学期
     */
    @ReferenceField(value = ExaminationWorkload.class, dataBaseName = "semester")
    @ReferenceField(value = TeachingWorkloadStatistics.class, dataBaseName = "semester")
    @ReferenceField(value = PaperCoachingWorkload.class, dataBaseName = "semester")
    private Integer semester;

    /**
     * 课程号
     */
    @ReferenceField(value = TeachingWorkloadStatistics.class, dataBaseName = "course_number")
    private String courseNumber;

    /**
     * 课程名
     */
    @ReferenceField(value = ExaminationWorkload.class, dataBaseName = "course_name")
    @ReferenceField(value = PaperCoachingWorkload.class, dataBaseName = "course_name")
    @ReferenceField(value = TeachingWorkloadStatistics.class, dataBaseName = "course_name")
    private String courseName;

    /**
     * 教学班
     */
    @ReferenceField(value = TeachingWorkloadStatistics.class, dataBaseName = "teaching_class")
    private String teachingClass;

    /**
     * 开课学院
     */
    @ReferenceField(value = TeachingWorkloadStatistics.class, dataBaseName = "teaching_school")
    private String teachingSchool;

    /**
     * 计划学院
     */
    @ReferenceField(value = TeachingWorkloadStatistics.class, dataBaseName = "plan_school")
    private String planSchool;

    /**
     * 学分
     */
    @ReferenceField(value = TeachingWorkloadStatistics.class, dataBaseName = "credit")
    private Double credit;

    /**
     * 课程性质
     */
    @ReferenceField(value = TeachingWorkloadStatistics.class, dataBaseName = "course_nature")
    private String courseNature;

    /**
     * 年级
     */
    @ReferenceField(value = TeachingWorkloadStatistics.class, dataBaseName = "student_grade")
    private String studentGrade;

    /**
     * 专业
     */
    @ReferenceField(value = TeachingWorkloadStatistics.class, dataBaseName = "major")
    private String major;

    /**
     * 上课教师名字
     */
    @ReferenceField(value = ExaminationWorkload.class, dataBaseName = "main_teacher_name")
    @ReferenceField(value = PaperCoachingWorkload.class, dataBaseName = "main_teacher_name")
    @ReferenceField(value = TeachingWorkloadStatistics.class, dataBaseName = "main_teacher_name")
    private String mainTeacherName;

    /**
     * 职称
     */
    @ReferenceField(value = TeachingWorkloadStatistics.class, dataBaseName = "main_teacher_title")
    private String mainTeacherTitle;

    /**
     * 上课人数
     */
    @ReferenceField(value = TeachingWorkloadStatistics.class, dataBaseName = "student_amount")
    @ReferenceField(value = PaperCoachingWorkload.class, dataBaseName = "student_amount")
    private Integer studentAmount;

    /**
     * 理论课时
     */

    @ReferenceField(value = TeachingWorkloadStatistics.class, dataBaseName = "theoretical_class_hours")
    private Integer theoreticalClassHours;

    /**
     * 上机课时
     */

    @ReferenceField(value = TeachingWorkloadStatistics.class, dataBaseName = "computer_class_hours")
    private Integer computerClassHours;

    /**
     * 实验课时
     */
    @ReferenceField(value = TeachingWorkloadStatistics.class, dataBaseName = "experiment_class_hours")
    private Integer experimentalClassHours;

    /**
     * 实践课时
     */
    @ReferenceField(value = TeachingWorkloadStatistics.class, dataBaseName = "practical_class_hours")
    private Integer practicalClassHours;

    /**
     * 计算用课时
     */

    @ReferenceField(value = ExaminationWorkload.class, dataBaseName = "calculating_class_hours")
    @ReferenceField(value = TeachingWorkloadStatistics.class, dataBaseName = "calculating_class_hours")
    private Integer calculatingClassHours;

    /**
     * 合课单位
     */
    @ReferenceField(value = TeachingWorkloadStatistics.class, dataBaseName = "joint_department")
    private String jointDepartment;

    /**
     * 备注
     */
    @ReferenceField(value = TeachingWorkloadStatistics.class, dataBaseName = "remarks")
    private String remarks;

    /**
     * 实验安排
     */
    @ReferenceField(value = TeachingWorkloadStatistics.class, dataBaseName = "experiment_arrangement")
    private String experimentArrangement;

    /**
     * 其他教师名
     */

    @ReferenceField(value = TeachingWorkloadStatistics.class, dataBaseName = "other_teacher_name")
    private String otherTeacherName;

    /**
     * 原始教分
     */

    @ReferenceField(value = ExaminationWorkload.class, dataBaseName = "original_teaching_scores")
    @ReferenceField(value = PaperCoachingWorkload.class, dataBaseName = "original_teaching_scores")
    @ReferenceField(value = TeachingWorkloadStatistics.class, dataBaseName = "origin_teaching_scores")
    private Double originalTeachingScores;

    /**
     * BA1系数
     */
    @ReferenceField(value = ExaminationWorkload.class, dataBaseName = "teaching_coefficient")
    @ReferenceField(value = PaperCoachingWorkload.class, dataBaseName = "teaching_coefficient")
    @ReferenceField(value = TeachingWorkloadStatistics.class, dataBaseName = "teaching_coefficient")
    private Double teachingCoefficient;

    /**
     * 教分
     */
    @ReferenceField(value = ExaminationWorkload.class, dataBaseName = "final_teaching_scores")
    @ReferenceField(value = PaperCoachingWorkload.class, dataBaseName = "final_teaching_scores")
    @ReferenceField(value = TeachingWorkloadStatistics.class, dataBaseName = "final_teaching_scores")
    private Double finalTeachingScores;

    /**
     * 辅助
     */
    @ReferenceField(value = TeachingWorkloadStatistics.class, dataBaseName = "assistant")
    private String assistant;

    /**
     * 课程性质解释
     */
    @ReferenceField(value = TeachingWorkloadStatistics.class, dataBaseName = "class_nature_explanation")
    private String classNatureExplanation;

    /**
     * 是否为特殊班级
     */
    @ReferenceField(value = TeachingWorkloadStatistics.class, dataBaseName = "special_class_remarks")
    private String specialClassRemarks;

    /**
     * 是否全英教学
     */

    @ReferenceField(value = TeachingWorkloadStatistics.class, dataBaseName = "special_language_remarks")
    private String specialLanguageRemarks;

    /**
     * 折扣
     */

    @ReferenceField(value = TeachingWorkloadStatistics.class, dataBaseName = "discount")
    private String discount;

    /**
     * 折扣前BA1系数
     */

    @ReferenceField(value = TeachingWorkloadStatistics.class, dataBaseName = "no_discount_teaching_coefficient")
    private Double noDiscountTeachingCoefficient;

    /**
     * 实验室核对结果
     */

    @ReferenceField(value = TeachingWorkloadStatistics.class, dataBaseName = "laboratory_verification_results")
    private String laboratoryVerificationResults;

    /**
     * 自然年
     */
    @ReferenceField(value = ExaminationWorkload.class, dataBaseName = "natural_year")
    @ReferenceField(value = PaperCoachingWorkload.class, dataBaseName = "nutural_year", fieldName = "nuturalYear")
    @ReferenceField(value = TeachingWorkloadStatistics.class, dataBaseName = "natural_year")
    private String naturalYear;

    /**
     * 工作量性质
     */
    @ReferenceField(value = ExaminationWorkload.class, dataBaseName = "workload_attribute", fieldName = "workloadAttribute")
    @ReferenceField(value = PaperCoachingWorkload.class, dataBaseName = "workload_type", fieldName = "workloadType")
    @ReferenceField(value = TeachingWorkloadStatistics.class, dataBaseName = "workload_nature")
    private String workloadNature;
}
