package com.example.summer.models.pojo;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.TableField;
import com.example.summer.entity.TeachingWorkloadStatistics;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

/**
 * @author 24047
 * @date 2022/6/27
 * 这个类被抛弃了
 * 它重生了
 */

@Getter
@Setter
public class TeachingWorkloadMap {
    public TeachingWorkloadMap(){
        //空的构造函数哈哈哈哈哈哈哈
    }
    public TeachingWorkloadMap(TeachingWorkloadStatistics statistics){
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

/*    public void setCourseNumber(String courseNumber) {
        this.courseNumber = courseNumber;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public void setTeachingClass(String teachingClass) {
        this.teachingClass = teachingClass;
    }

    public void setTeachingSchool(String teachingSchool) {
        this.teachingSchool = teachingSchool;
    }

    public void setCredit(Double credit) {
        this.credit = credit;
    }

    public void setCourseNature(String courseNature) {
        this.courseNature = courseNature;
    }

    public void setStudentGrade(Integer studentGrade) {
        this.studentGrade = studentGrade;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public void setMainTeacherName(String mainTeacherName) {
        this.mainTeacherName = mainTeacherName;
    }

    public void setMainTeacherTitle(String mainTeacherTitle) {
        this.mainTeacherTitle = mainTeacherTitle;
    }

    public void setStudentAmount(Integer studentAmount) {
        this.studentAmount = studentAmount;
    }

    public void setCalculatingClassHours(Integer calculatingClassHours) {
        this.calculatingClassHours = calculatingClassHours;
    }

    public void setJointDepartment(String jointDepartment) {
        this.jointDepartment = jointDepartment;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public void setExperimentArrangement(String experimentArrangement) {
        this.experimentArrangement = experimentArrangement;
    }

    public void setOtherTeacherName(String otherTeacherName) {
        this.otherTeacherName = otherTeacherName;
    }

    public void setFinalTeachingScores(Double finalTeachingScores) {
        this.finalTeachingScores = finalTeachingScores;
    }

    public void setAssistant(String assistant) {
        this.assistant = assistant;
    }

    public void setClassNatureExplanation(String classNatureExplanation) {
        this.classNatureExplanation = classNatureExplanation;
    }

    public void setSpecialClassRemarks(String specialClassRemarks) {
        this.specialClassRemarks = specialClassRemarks;
    }

    public void setSpecialLanguageRemarks(String specialLanguageRemarks) {
        this.specialLanguageRemarks = specialLanguageRemarks;
    }

    public void setLaboratoryVerificationResults(String laboratoryVerificationResults) {
        this.laboratoryVerificationResults = laboratoryVerificationResults;
    }*/
}
