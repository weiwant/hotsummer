package com.example.sprint2.models.vo;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.sprint2.mybatis.entity.ExaminationWorkload;
import lombok.Data;

/**
 * @author:wwq
 * @Date：2022/6/29：16:33
 */
@Data
public class ExaminationVo {
    private String naturalYear;//自然年
    private String year;//学年
    private int pageNumber;//页数
    IPage<ExaminationWorkload> examPage;//页
    private String teacher;//教师
    private String courseName;//课程名称

}
