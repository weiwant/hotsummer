package com.example.sprint2.models.vo;

import java.time.LocalDate;

/**
 * @author:wwq
 * @Date：2022/7/5：11:57
 */
public class SpecialVo {
    /**
     * 主键
     */
    private Integer id;

    /**
     * 申报时间
     */
    private LocalDate reportTime;

    /**
     * 申报人
     */
    private String declarantName;

    /**
     * BB类型
     */
    private String type;

    /**
     * 成果/作品/参赛项目/参评项目/论文/专著名称
     */
    private String achievementName;

    /**
     * 级别
     */
    private String level;

    /**
     * 课程/项目/奖项/竞赛/论文指导类别
     */
    private String projectCategory;

    /**
     * 课程/项目/奖项/竞赛详细名称
     */
    private String projectName;

    /**
     * 获奖等级
     */
    private String awardLevel;

    /**
     * 获奖/获评/出版日期
     */
    private String awardDate;

    /**
     * 项目进展
     */
    private String projectStatus;

    /**
     * 授奖单位
     */
    private String awardApartment;

    /**
     * 刊物/出版社名称
     */
    private String publicationName;

    /**
     * 刊物期数/出版版次
     */
    private String publicationsNumber;

    /**
     * ISBN号
     */
    private String isbn;

    /**
     * 内容简介
     */
    private String briefIntroduction;

    /**
     * 所获荣誉
     */
    private String receivingHonor;

    /**
     * 指导学生团队名
     */
    private String guidingStudentTeam;

    /**
     * 指导学生姓名
     */
    private String guidingStudentName;

    /**
     * 指导学生学号
     */
    private String guidingStudentId;

    /**
     * 审核状态
     */
    private String status;

    /**
     * 备注
     */
    private String remarks;

    /**
     * 文件路径
     */
    private String filePath;


    //teacher


    /**
     * 教师姓名
     */
    private String teacherName;

    /**
     * 排序情况/位次
     */
    private String authorOrder;


    /**
     * 教分
     */
    private Double teachingScores;


}
