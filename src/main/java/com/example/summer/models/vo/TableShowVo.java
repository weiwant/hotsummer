package com.example.summer.models.vo;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * @author:wwq
 * @Date：2022/6/27：9:35
 */
public class TableShowVo {
    private String year;//学年
    private int semester;//学期
    private int pageNumber;//页数

    public String getYear(){
        return year;
    }

    public int getSemester() {
            return semester;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

}
