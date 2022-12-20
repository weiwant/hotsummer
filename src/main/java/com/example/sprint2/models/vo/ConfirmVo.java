package com.example.sprint2.models.vo;

import lombok.Data;

/**
 * @author ZWForum
 * @description
 * @date 2022/7/6
 */
@Data
public class ConfirmVo {
    private String year;
    private String teacherName;
    private int confirm;
    private String id;
    private int confirmCount; //已确认人数
    private int notConfirmCount;   //未确认人数
}
