package com.example.summer.utils;

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
    SUCCESS(200, "成功");
    @JSONField(name = "code")
    private final Integer responseCode;
    @JSONField
    private final String message;

    ResponseCode(Integer responseCode, String message) {
        this.responseCode = responseCode;
        this.message = message;
    }
}
