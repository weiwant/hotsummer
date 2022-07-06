package com.example.sprint2.models.pojo;

import lombok.Data;

/**
 * @author:wwq
 * @Date：2022/7/5：15:48
 */
@Data
public class TeacherAndOrder {
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
