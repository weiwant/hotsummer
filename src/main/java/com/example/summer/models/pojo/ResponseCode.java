package com.example.summer.models.pojo;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.annotation.JSONType;
import lombok.Getter;

/**
 * @author ZWForum
 * @description
 * @date 2022/6/22
 */
@Getter
@JSONType(serializeEnumAsJavaBean = true)
public enum ResponseCode {
    SUCCESS(200, "成功"),
    LoginFailure(605, "用户名或密码错误"),
    NoContentFailure(204,"无内容"),
    UnknownFailure(900, "未知错误");

    @JSONField(name = "code")
    private final Integer responseCode;
    @JSONField
    private final String message;

    ResponseCode(Integer responseCode, String message) {
        this.responseCode = responseCode;
        this.message = message;
    }
}
