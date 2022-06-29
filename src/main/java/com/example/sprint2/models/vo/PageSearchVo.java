package com.example.sprint2.models.vo;

import lombok.Getter;
import lombok.Setter;

/**
 * @author 24047
 * @description 分页查询请求对象
 * @date 2022/6/29
 */
@Getter
@Setter
public class PageSearchVo {
    private String naturalYear;//自然年

    private String teacherName;//教师名

    private String studentGrade;//学生年级

    private String courseName;//课程名

    private String major;//专业

    private int pageNumber;//页序号

    private int pageSize;//每一页的大小


}
