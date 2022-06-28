package com.example.summer.models.vo;

/**
 * @author hy
 * @Date 2022/6/27
 * @Description
 */
public class SearchPersonVo {

    String name = "蔡朝晖";

    String startYear = "2019";
    String endYear = "2020";

    public void setName(String name) {
        this.name = name;
    }

    public void setStartYear(String startYear) {
        this.startYear = startYear;
    }

    public void setEndYear(String endYear) {
        this.endYear = endYear;
    }

    public String getName() {
        return name;
    }

    public String getStartYear() {
        return startYear;
    }

    public String getEndYear() {
        return endYear;
    }
}
