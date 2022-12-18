package com.example.sprint2.mybatis.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author ZWForum
 * @since 2022-12-12
 */
@Getter
@Setter
@TableName("userinfo")
public class Userinfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 性别
     */
    @TableField("gender")
    private String gender;

    /**
     * 系
     */
    @TableField("faculty")
    private String faculty;

    /**
     * 职称
     */
    @TableField("title")
    private String title;

    /**
     * 职级
     */
    @TableField("professionrank")
    private String professionrank;

    @TableId("id")
    private String id;


}
