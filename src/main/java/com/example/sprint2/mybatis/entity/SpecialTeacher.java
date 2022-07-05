package com.example.sprint2.mybatis.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author 25906
 * @since 2022-07-05
 */
@Getter
@Setter
@TableName("special_teacher")
public class SpecialTeacher implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 项目id
     */
    @TableField("project_id")
    private Integer projectId;

    /**
     * 教师姓名
     */
    @TableField("teacher_name")
    private String teacherName;

    /**
     * 排序情况/位次
     */
    @TableField("author_order")
    private String authorOrder;

    /**
     * BB类型
     */
    @TableField("type")
    private String type;

    /**
     * 教分
     */
    @TableField("teaching_scores")
    private Double teachingScores;


}
