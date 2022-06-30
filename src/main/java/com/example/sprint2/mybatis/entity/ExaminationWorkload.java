package com.example.sprint2.mybatis.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author 24047
 * @since 2022-06-30
 */
@Getter
@Setter
@TableName("examination_workload")
public class ExaminationWorkload implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 学年
     */
    @TableField("academic_year")
    private String academicYear;

    /**
     * 学期
     */
    @TableField("semester")
    private Double semester;

    /**
     * 课程名称
     */
    @TableField("course_name")
    private String courseName;

    /**
     * 上课老师
     */
    @TableField("main_teacher_name")
    private String mainTeacherName;

    /**
     * 计算用学时
     */
    @TableField("calculating_class_hours")
    private Double calculatingClassHours;

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
     * 教分（BA1\ 3\ 15）
     */
    @TableField("final_teaching_scores")
    private Double finalTeachingScores;

    /**
     * 自然年
     */
    @TableField("natural_year")
    private String naturalYear;

    /**
     * 工作性质
     */
    @TableField("workload_attribute")
    private String workloadAttribute;


}
