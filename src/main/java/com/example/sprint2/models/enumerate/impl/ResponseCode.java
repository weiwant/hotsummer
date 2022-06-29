package com.example.sprint2.models.enumerate.impl;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.annotation.JSONType;
import com.example.sprint2.models.enumerate.CustomResponse;
import lombok.Getter;

/**
 * @author ZWForum
 * @description 响应码
 * @date 2022/6/22
 */
@Getter
@JSONType(serializeEnumAsJavaBean = true)
public enum ResponseCode implements CustomResponse {
    SUCCESS(200, "成功"),
    LoginFailure(605, "用户名或密码错误"),
    NoContentFailure(204, "无内容"),
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
