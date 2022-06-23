package com.example.summer.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.PropertyFilter;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;
import lombok.Getter;

/**
 * @author ZWForum
 * @description
 * @date 2022/6/22
 */
@Getter
public class Result {
    @JSONField
    private final ResponseCode response;
    @JSONField
    private final Object data;

    public Result(ResponseCode response) {
        this.response = response;
        this.data = null;
    }

    public Result(ResponseCode response, Object data) {
        this.response = response;
        this.data = data;
    }

    @Override
    public String toString() {
        SerializeConfig config = new SerializeConfig();
        PropertyFilter filter = (object, name, value) -> !(name.equals("data") && value == null);
        config.addFilter(Result.class, filter);
        return JSON.toJSONString(this, config, SerializerFeature.PrettyFormat, SerializerFeature.WriteNullStringAsEmpty,
                SerializerFeature.WriteNullListAsEmpty, SerializerFeature.WriteMapNullValue, SerializerFeature.WriteNullBooleanAsFalse);
    }
}
