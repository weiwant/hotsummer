package com.example.sprint2.mybatis.entity;

import com.baomidou.mybatisplus.annotation.IdType;
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
 * @since 2022-12-28
 */
@Getter
@Setter
@TableName("message")
public class Message implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField("year")
    private String year;

    @TableField("title")
    private String title;

    @TableField("sender")
    private String sender;

    @TableField("responder")
    private String responder;

    @TableField("sendDate")
    private String sendDate;

    @TableField("sendContent")
    private String sendContent;

    @TableField("responseDate")
    private String responseDate;

    @TableField("responseContent")
    private String responseContent;

    /**
     * 0为未回复，1为已回复
     */
    @TableField("responseStatus")
    private Integer responseStatus;


}
