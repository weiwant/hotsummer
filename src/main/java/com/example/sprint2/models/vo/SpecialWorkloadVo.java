package com.example.sprint2.models.vo;

import lombok.Data;

/**
 * @author hy
 * @Date 2022/7/1
 * @Description
 */

@Data
public class SpecialWorkloadVo {
    private String type;                    //BB类型
    private String declarantName;           //申报人姓名
    private String teacherName;             //教师姓名
    private Integer id;                     //主键
    private int pagenumber;            //当前页号
    private String year;


}
