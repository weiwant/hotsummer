package com.example.sprint2.mybatis.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author hy
 * @Description 用户实体类
 * @since 2022-06-23
 */
@Getter
@Setter
@TableName("userlogin")
public class UserLogin implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("username")
    private String username;

    @TableField("password")
    private String password;

    @TableField("identify")
    private Integer identify;


}
