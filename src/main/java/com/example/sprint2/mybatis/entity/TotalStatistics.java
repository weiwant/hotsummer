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
 * @since 2022-07-07
 */
@Getter
@Setter
@TableName("total_statistics")
public class TotalStatistics implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableField("teacher_name")
    private String teacherName;

    @TableField("year")
    private String year;

    @TableField("ba1")
    private Double ba1;

    @TableField("ba2")
    private Double ba2;

    @TableField("ba3")
    private Double ba3;

    @TableField("bb1")
    private Double bb1;

    @TableField("bb2")
    private Double bb2;

    @TableField("bb3")
    private Double bb3;

    @TableField("bb4")
    private Double bb4;

    @TableField("bb5")
    private Double bb5;

    @TableField("bb6")
    private Double bb6;

    @TableField("bb7")
    private Double bb7;

    @TableField("bb8")
    private Double bb8;

    @TableField("bb9")
    private Double bb9;

    @TableField("bb10")
    private Double bb10;

    @TableField("bb11")
    private Double bb11;

    @TableField("bb12")
    private Double bb12;

    @TableField("bb13")
    private Double bb13;

    @TableField("bb14")
    private Double bb14;

    @TableField("bb15")
    private Double bb15;

    @TableField("total")
    private Double total;


}
