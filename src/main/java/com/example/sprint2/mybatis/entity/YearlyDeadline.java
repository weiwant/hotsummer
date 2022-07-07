package com.example.sprint2.mybatis.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * <p>
 *
 * </p>
 *
 * @author ZWForum
 * @since 2022-07-06
 */
@Getter
@Setter
@TableName("yearly_deadline")
public class YearlyDeadline implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("year")
    private String year;

    @TableField("deadline")
    private LocalDate deadline;


}
