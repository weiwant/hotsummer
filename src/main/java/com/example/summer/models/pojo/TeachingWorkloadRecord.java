package com.example.summer.models.pojo;

import com.alibaba.fastjson.annotation.JSONField;
import com.example.summer.entity.TeachingWorkloadStatistics;
import lombok.Getter;
import lombok.Setter;

/**
 * @author 24047
 * @date 2022/6/27
 * 一个实体类，基于教学工作量，面向教师端的实体类，目前写死了
 */

@Getter
@Setter
public class TeachingWorkloadRecord {
    public TeachingWorkloadRecord(){
        //空的构造函数哈哈哈哈哈哈哈
    }
    public TeachingWorkloadRecord(TeachingWorkloadStatistics statistics){
        //哈哈哈哈哈被我写完了
        this.setAssistant(statistics.getAssistant());
        this.setCalculatingClassHours(statistics.getCalculatingClassHours());
        this.setClassNatureExplanation(statistics.getClassNatureExplanation());
        this.setCourseName(statistics.getCourseName());
        this.setCourseNature(statistics.getCourseNature());
        this.setCredit(statistics.getCredit());
        this.setFinalTeachingScores(statistics.getFinalTeachingScores());
        this.setJointDepartment(statistics.getJointDepartment());
        this.setExperimentArrangement(statistics.getExperimentArrangement());
        this.setCourseNumber(statistics.getCourseNumber());
        this.setMainTeacherName(statistics.getMainTeacherName());
        this.setMajor(statistics.getMajor());
        this.setOtherTeacherName(statistics.getOtherTeacherName());
        this.setRemarks(statistics.getRemarks());
        this.setSpecialClassRemarks(statistics.getSpecialClassRemarks());
        this.setStudentAmount(statistics.getStudentAmount());
        this.setStudentGrade(statistics.getStudentGrade());
        this.setSpecialLanguageRemarks(statistics.getSpecialLanguageRemarks());
        this.setTeachingClass(statistics.getTeachingClass());
        this.setTeachingSchool(statistics.getTeachingSchool());
        this.setMainTeacherTitle(statistics.getMainTeacherTitle());

    }

    /**
     * 课程号
     */
    @JSONField(ordinal=1)
    private String courseNumber;

    /**
     * 课程名
     */

    @JSONField(ordinal=2)
    private String courseName;

    /**
     * 教学班
     */

    @JSONField(ordinal=3)
    private String teachingClass;

    /**
     * 开课学院
     */

    @JSONField(ordinal=4)
    private String teachingSchool;

    /**
     * 学分
     */

    @JSONField(ordinal=5)
    private Double credit;

    /**
     * 课程性质
     */

    @JSONField(ordinal=6)
    private String courseNature;

    /**
     * 年级
     */
    @JSONField(ordinal=6)
    private Integer studentGrade;

    /**
     * 专业
     */
    @JSONField(ordinal=7)

    private String major;

    /**
     * 上课教师名字
     */
    @JSONField(ordinal=8)
    private String mainTeacherName;

    /**
     * 职称
     */
    @JSONField(ordinal=9)
    private String mainTeacherTitle;

    /**
     * 上课人数
     */
    @JSONField(ordinal=10)
    private Integer studentAmount;
    /**
     * 计算用课时
     */
    @JSONField(ordinal=11)
    private Integer calculatingClassHours;

    /**
     * 合课单位
     */
    @JSONField(ordinal=12)
    private String jointDepartment;

    /**
     * 备注
     */
    @JSONField(ordinal=13)
    private String remarks;

    /**
     * 实验安排
     */
    @JSONField(ordinal=14)
    private String experimentArrangement;

    /**
     * 其他教师名
     */
    @JSONField(ordinal=15)
    private String otherTeacherName;


    /**
     * 教分
     */
    @JSONField(ordinal=16)
    private Double finalTeachingScores;

    /**
     * 辅助
     */
    @JSONField(ordinal=17)
    private String assistant;

    /**
     * 课程性质解释
     */
    @JSONField(ordinal=18)
    private String classNatureExplanation;

    /**
     * 是否为特殊班级
     */
    @JSONField(ordinal=19)
    private String specialClassRemarks;

    /**
     * 是否全英教学
     */
    @JSONField(ordinal=20)
    private String specialLanguageRemarks;
}
