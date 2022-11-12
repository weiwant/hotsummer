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
 * @author 25906
 * @since 2022-11-12
 */
@Getter
@Setter
@TableName("userlogin")
public class UserLogin implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableField("userid")
    private String userid;

    @TableId("username")
    private String username;

    @TableField("password")
    private String password;

    @TableField("identify")
    private Integer identify;


}
