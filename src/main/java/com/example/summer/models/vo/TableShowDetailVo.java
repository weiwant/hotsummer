package com.example.summer.models.vo;

/**
 * @author 24047
 * @date 2022/6/27
 */
public class TableShowDetailVo {

    private String year;//学年
    private int semester;//学期

    private String teacherName;

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getYear(){
        return year;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    public void setYear(String year) {
        this.year = year;
    }
}
