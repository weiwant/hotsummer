package com.example.sprint2.models.vo;

import com.example.sprint2.mybatis.entity.ExaminationWorkload;
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
    private String academicYear;

    /**
     * 学期
     */
    @ReferenceField(value = TeachingWorkloadStatistics.class)
    private Integer semester;

    /**
     * 课程号
     */
    private String courseNumber;

    /**
     * 课程名
     */
    private String courseName;

    /**
     * 教学班
     */
    private String teachingClass;

    /**
     * 开课学院
     */
    private String teachingSchool;

    /**
     * 计划学院
     */
    private String planSchool;

    /**
     * 学分
     */
    private Double credit;

    /**
     * 课程性质
     */
    private String courseNature;

    /**
     * 年级
     */
    private String studentGrade;

    /**
     * 专业
     */
    private String major;

    /**
     * 上课教师名字
     */
    @ReferenceField(value = ExaminationWorkload.class, dataBaseName = "main_teacher_name")
    @ReferenceField(value = TeachingWorkloadStatistics.class, dataBaseName = "main_teacher_name")
    private String mainTeacherName;

    /**
     * 职称
     */
    private String mainTeacherTitle;

    /**
     * 上课人数
     */
    private Integer studentAmount;

    /**
     * 理论课时
     */
    private Integer theoreticalClassHours;

    /**
     * 上机课时
     */
    private Integer computerClassHours;

    /**
     * 实验课时
     */
    private Integer experimentalClassHours;

    /**
     * 实践课时
     */
    private Integer practicalClassHours;

    /**
     * 计算用课时
     */
    private Integer calculatingClassHours;

    /**
     * 合课单位
     */
    private String jointDepartment;

    /**
     * 备注
     */
    private String remarks;

    /**
     * 实验安排
     */
    private String experimentArrangement;

    /**
     * 其他教师名
     */
    private String otherTeacherName;

    /**
     * 原始教分
     */
    private Double originalTeachingScores;

    /**
     * BA1系数
     */
    private Double teachingCoefficient;

    /**
     * 教分
     */
    private Double finalTeachingScores;

    /**
     * 辅助
     */
    private String assistant;

    /**
     * 课程性质解释
     */
    private String classNatureExplanation;

    /**
     * 是否为特殊班级
     */
    private String specialClassRemarks;

    /**
     * 是否全英教学
     */
    private String specialLanguageRemarks;

    /**
     * 折扣
     */
    private String discount;

    /**
     * 折扣前BA1系数
     */
    private Double noDiscountTeachingCoefficient;

    /**
     * 实验室核对结果
     */
    private String laboratoryVerificationResults;

    /**
     * 自然年
     */
    private String naturalYear;

    /**
     * 工作量性质
     */
    private String workloadNature;
}
