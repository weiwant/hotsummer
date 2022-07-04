package com.example.sprint2.mybatis.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * <p>
 *
 * </p>
 *
 * @author 24047
 * @since 2022-07-01
 */
@Getter
@Setter
@TableName("special_workload")
public class SpecialWorkload implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 自增主键
     */
    @JSONField(ordinal = 1)
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 申报时间
     */
    @JSONField(ordinal = 2)
    @TableField("report_time")
    private LocalDate reportTime;

    /**
     * 申报人
     */
    @JSONField(ordinal = 3)
    @TableField("declarant_name")
    private String declarantName;

    /**
     * 教师姓名
     */
    @JSONField(ordinal = 4)
    @TableField("teacher_name")
    private String teacherName;

    /**
     * 排序情况/位次
     */
    @JSONField(ordinal = 5)
    @TableField("author_order")
    private String authorOrder;

    /**
     * BB类型
     */
    @JSONField(ordinal = 6)
    @TableField("type")
    private String type;

    /**
     * 教分
     */
    @JSONField(ordinal = 7)
    @TableField("teaching_scores")
    private Double teachingScores;

    /**
     * 成果/作品/参赛项目/参评项目/论文/专著名称
     */
    @JSONField(ordinal = 8)
    @TableField("achievement_name")
    private String achievementName;

    /**
     * 级别
     */
    @JSONField(ordinal = 9)
    @TableField("level")
    private String level;

    /**
     * 课程/项目/奖项/竞赛/论文指导类别
     */
    @JSONField(ordinal = 10)
    @TableField("project_category")
    private String projectCategory;

    /**
     * 课程/项目/奖项/竞赛详细名称
     */
    @JSONField(ordinal = 11)
    @TableField("project_name")
    private String projectName;

    /**
     * 获奖等级
     */
    @JSONField(ordinal = 12)
    @TableField("award_level")
    private String awardLevel;

    /**
     * 获奖/获评/出版日期
     */
    @JSONField(ordinal = 13)
    @TableField("award_date")
    private String awardDate;

    /**
     * 项目进展
     */
    @JSONField(ordinal = 14)
    @TableField("project_status")
    private String projectStatus;

    /**
     * 授奖单位
     */
    @JSONField(ordinal = 15)
    @TableField("award_apartment")
    private String awardApartment;

    /**
     * 刊物/出版社名称
     */
    @JSONField(ordinal = 16)
    @TableField("publication_name")
    private String publicationName;

    /**
     * 刊物期数/出版版次
     */
    @JSONField(ordinal = 17)
    @TableField("publications_number")
    private String publicationsNumber;

    /**
     * ISBN号
     */
    @JSONField(ordinal = 18)
    @TableField("isbn")
    private String isbn;

    /**
     * 内容简介
     */
    @JSONField(ordinal = 19)
    @TableField("brief_introduction")
    private String briefIntroduction;

    /**
     * 所获荣誉
     */
    @JSONField(ordinal = 20)
    @TableField("receiving_honor")
    private String receivingHonor;

    /**
     * 指导学生团队名
     */
    @JSONField(ordinal = 21)
    @TableField("guiding_student_team")
    private String guidingStudentTeam;

    /**
     * 指导学生姓名
     */
    @JSONField(ordinal = 22)
    @TableField("guiding_student_name")
    private String guidingStudentName;

    /**
     * 指导学生学号
     */
    @JSONField(ordinal = 23)
    @TableField("guiding_student_id")
    private String guidingStudentId;

    /**
     * 审核状态
     */
    @JSONField(ordinal = 24)
    @TableField("status")
    private String status;

    /**
     * 备注
     */
    @JSONField(ordinal = 25)
    @TableField("remarks")
    private String remarks;

    /**
     * 文件路径
     */
    @JSONField(serialize = false)
    @TableField("file_path")
    private String filePath;


}
