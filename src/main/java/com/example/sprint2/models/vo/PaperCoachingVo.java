package com.example.sprint2.models.vo;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.sprint2.mybatis.entity.PaperCoachingWorkload;
import lombok.Data;

import java.util.List;

/**
 * @author hy
 * @Date 2022/6/29
 * @Description
 */

@Data
public class PaperCoachingVo {
    private int pagenumber;           //当前页数
    private String natural_year;       //指定自然年
    private String teacherName;       //指定教师名称
    private String courseName;        //指定课程名称


}
