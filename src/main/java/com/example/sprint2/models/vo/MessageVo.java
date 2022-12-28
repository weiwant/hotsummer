package com.example.sprint2.models.vo;

import lombok.Data;

/**
 * @author hy
 * @Date 2022/12/28
 * @Description
 */
@Data
public class MessageVo {
   //主键
    private int id;
    //工作量自然年
    private String year;
    //消息标题
    private String title;
    //发起人id
    private String sender;
    //回复人的id
    private String responder;
    //发起消息内容
    private String sendContent;
    //发起时间
    private String sendDate;
    //回复时间
    private String responseDate;
    //回复内容
    private String responseContent;
    //回复状态。0为为回复，1为已回复
    private int responseStatus;

}
