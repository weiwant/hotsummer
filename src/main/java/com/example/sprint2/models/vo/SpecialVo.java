package com.example.sprint2.models.vo;

import com.example.sprint2.models.pojo.TeacherAndOrder;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;

/**
 * @author:wwq
 * @Date：2022/7/5：11:57
 */
@Data
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
    //文件名
    private List<String> fileName;


    //teacher
    /**
     * 教师列表。包括教师名，排序和教分。
     */

    private List<TeacherAndOrder> somePeople;

    private MultipartFile[] files;
    //当前页数
    private Integer pageNumber;

    private String year;

}
