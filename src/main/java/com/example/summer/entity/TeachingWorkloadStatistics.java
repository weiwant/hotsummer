package com.example.summer.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author 24047
 * @since 2022-06-25
 */
@Getter
@Setter
@TableName("teaching_workload_statistics")
public class TeachingWorkloadStatistics implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 学年
     */
    @TableField("academic_year")
    private String academicYear;

    /**
     * 学期
     */
    @TableField("semester")
    private Integer semester;

    /**
     * 课程号
     */
    @TableField("course_number")
    private String courseNumber;

    /**
     * 课程名
     */
    @TableField("course_name")
    private String courseName;

    /**
     * 教学班
     */
    @TableField("teaching_class")
    private String teachingClass;

    /**
     * 开课学院
     */
    @TableField("teaching_school")
    private String teachingSchool;

    /**
     * 计划学院
     */
    @TableField("plan_school")
    private String planSchool;

    /**
     * 学分
     */
    @TableField("credit")
    private Double credit;

    /**
     * 课程性质
     */
    @TableField("course_nature")
    private String courseNature;

    /**
     * 年级
     */
    @TableField("student_grade")
    private Integer studentGrade;

    /**
     * 专业
     */
    @TableField("major")
    private String major;

    /**
     * 上课教师名字
     */
    @TableField("main_teacher_name")
    private String mainTeacherName;

    /**
     * 职称
     */
    @TableField("main_teacher_title")
    private String mainTeacherTitle;

    /**
     * 上课人数
     */
    @TableField("student_amount")
    private Integer studentAmount;

    /**
     * 理论课时
     */
    @TableField("theoretical_class_hours")
    private Integer theoreticalClassHours;

    /**
     * 上机课时
     */
    @TableField("computer_class_hours")
    private Integer computerClassHours;

    /**
     * 实验课时
     */
    @TableField("experimental_class_hours")
    private Integer experimentalClassHours;

    /**
     * 实践课时
     */
    @TableField("practical_class_hours")
    private Integer practicalClassHours;

    /**
     * 计算用课时
     */
    @TableField("calculating_class_hours")
    private Integer calculatingClassHours;

    /**
     * 合课单位
     */
    @TableField("joint_department")
    private String jointDepartment;

    /**
     * 备注
     */
    @TableField("remarks")
    private String remarks;

    /**
     * 实验安排
     */
    @TableField("experiment_arrangement")
    private String experimentArrangement;

    /**
     * 其他教师名
     */
    @TableField("other_teacher_name")
    private String otherTeacherName;

    /**
     * 原始教分
     */
    @TableField("original_teaching_scores")
    private Double originalTeachingScores;

    /**
     * BA1系数
     */
    @TableField("teaching_coefficient")
    private Double teachingCoefficient;

    /**
     * 教分
     */
    @TableField("final_teaching_scores")
    private Double finalTeachingScores;

    /**
     * 辅助
     */
    @TableField("assistant")
    private String assistant;

    /**
     * 课程性质解释
     */
    @TableField("class_nature_explanation")
    private String classNatureExplanation;

    /**
     * 是否为特殊班级
     */
    @TableField("special_class_remarks")
    private String specialClassRemarks;

    /**
     * 是否全英教学
     */
    @TableField("special_language_remarks")
    private String specialLanguageRemarks;

    /**
     * 折扣
     */
    @TableField("discount")
    private Double discount;

    /**
     * 折扣前BA1系数
     */
    @TableField("no_discount_teaching_coefficient")
    private Double noDiscountTeachingCoefficient;

    /**
     * 实验室核对结果
     */
    @TableField("laboratory_verification_results")
    private String laboratoryVerificationResults;


}
