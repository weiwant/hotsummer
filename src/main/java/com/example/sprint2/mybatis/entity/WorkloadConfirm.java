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
 * @since 2022-12-18
 */
@Getter
@Setter
@TableName("workload_confirm")
public class WorkloadConfirm implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableField("natural_year")
    private String naturalYear;

    @TableField("teacher_id")
    private String teacherId;

    @TableField("teacher_name")
    private String teacherName;

    @TableField("confirm")
    private Integer confirm;


}
