package com.example.sprint2.mybatis.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author lenovo
 * @since 2022-07-05
 */
@Getter
@Setter
@TableName("special_project")
public class SpecialProject implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 申报时间
     */
    @TableField("report_time")
    private LocalDate reportTime;

    /**
     * 申报人
     */
    @TableField("declarant_name")
    private String declarantName;

    /**
     * BB类型
     */
    @TableField("type")
    private String type;

    /**
     * 成果/作品/参赛项目/参评项目/论文/专著名称
     */
    @TableField("achievement_name")
    private String achievementName;

    /**
     * 级别
     */
    @TableField("level")
    private String level;

    /**
     * 课程/项目/奖项/竞赛/论文指导类别
     */
    @TableField("project_category")
    private String projectCategory;

    /**
     * 课程/项目/奖项/竞赛详细名称
     */
    @TableField("project_name")
    private String projectName;

    /**
     * 获奖等级
     */
    @TableField("award_level")
    private String awardLevel;

    /**
     * 获奖/获评/出版日期
     */
    @TableField("award_date")
    private String awardDate;

    /**
     * 项目进展
     */
    @TableField("project_status")
    private String projectStatus;

    /**
     * 授奖单位
     */
    @TableField("award_apartment")
    private String awardApartment;

    /**
     * 刊物/出版社名称
     */
    @TableField("publication_name")
    private String publicationName;

    /**
     * 刊物期数/出版版次
     */
    @TableField("publications_number")
    private String publicationsNumber;

    /**
     * ISBN号
     */
    @TableField("isbn")
    private String isbn;

    /**
     * 内容简介
     */
    @TableField("brief_introduction")
    private String briefIntroduction;

    /**
     * 所获荣誉
     */
    @TableField("receiving_honor")
    private String receivingHonor;

    /**
     * 指导学生团队名
     */
    @TableField("guiding_student_team")
    private String guidingStudentTeam;

    /**
     * 指导学生姓名
     */
    @TableField("guiding_student_name")
    private String guidingStudentName;

    /**
     * 指导学生学号
     */
    @TableField("guiding_student_id")
    private String guidingStudentId;

    /**
     * 审核状态
     */
    @TableField("status")
    private String status;

    /**
     * 备注
     */
    @TableField("remarks")
    private String remarks;

    /**
     * 文件路径
     */
    @TableField("file_path")
    private String filePath;


}
