package com.example.sprint2.mybatis.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author ZWForum
 * @since 2022-07-04
 */
@Getter
@Setter
@TableName("workload_confirm")
public class WorkloadConfirm implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableField("natural_year")
    private String naturalYear;

    @TableField("teacher_name")
    private String teacherName;

    @TableField("confirm")
    private Boolean confirm;


}
