package com.example.summer.models.vo;

/**
 * @author 24047
 * @date 2022/6/27
 * @Description 由前端负责Excel文件导出，这个类现在被弃用了！
 */
public class ExcelSearch {
    private String startYear;

    private String endYear;

    private String semester;

    private String teacherName;

    public String getStartYear() {
        return startYear;
    }

    public String getEndYear() {
        return endYear;
    }

    public String getSemester() {
        return semester;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public ExcelSearch(String startYear, String endYear, String semester, String teacherName) {
        this.startYear = startYear;
        this.endYear = endYear;
        this.semester = semester;
        this.teacherName = teacherName;
    }

    public ExcelSearch() {

    }
}
