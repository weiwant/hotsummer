package com.example.sprint2.mybatis.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.example.sprint2.utils.annotations.ExcludeField;
import com.example.sprint2.utils.annotations.StartRow;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author 24047
 * @since 2022-07-01
 */
@StartRow(2)
@Getter
@Setter
@TableName("paper_coaching_workload")
public class PaperCoachingWorkload implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @JSONField(ordinal = 1)
    @ExcludeField
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 工作量类型
     */
    @JSONField(ordinal = 2)
    @ExcludeField(param = 1)
    @TableField("workload_type")
    private String workloadType;

    /**
     * 学年
     */
    @JSONField(ordinal = 3)
    @TableField("academic_year")
    private String academicYear;

    /**
     * 学期
     */
    @JSONField(ordinal = 4)
    @TableField("semester")
    private Double semester;

    /**
     * 课程名称
     */
    @JSONField(ordinal = 5)
    @TableField("course_name")
    private String courseName;

    /**
     * 上课教师姓名
     */
    @JSONField(ordinal = 6)
    @TableField("main_teacher_name")
    private String mainTeacherName;

    /**
     * 计算用人数
     */
    @JSONField(ordinal = 7)
    @TableField("student_amount")
    private Double studentAmount;

    /**
     * 原始教分
     */
    @JSONField(ordinal = 8)
    @TableField("original_teaching_scores")
    private Double originalTeachingScores;

    /**
     * BA1系数
     */
    @JSONField(ordinal = 9)
    @TableField("teaching_coefficient")
    private Double teachingCoefficient;

    /**
     * 教分（BA1/3/15）
     */
    @JSONField(ordinal = 10)
    @TableField("final_teaching_scores")
    private Double finalTeachingScores;

    /**
     * 自然年
     */
    @JSONField(serialize = false)
    @ExcludeField(method = "getNaturalYear")
    @TableField("nutural_year")
    private String nuturalYear;


}
