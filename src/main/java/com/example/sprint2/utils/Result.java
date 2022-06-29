package com.example.sprint2.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.PropertyFilter;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.example.sprint2.models.enumerate.CustomResponse;
import lombok.Getter;

/**
 * @author ZWForum
 * @description 结果类，调用toString()返回给前端
 * @date 2022/6/22
 */
@Getter
public class Result {
    @JSONField
    private final CustomResponse response;
    @JSONField
    private final Object data;

    public Result(CustomResponse response) {
        this.response = response;
        this.data = null;
    }

    public Result(CustomResponse response, Object data) {
        this.response = response;
        this.data = data;
    }

    /**
     * @param
     * @return String
     * @author ZWForum
     * @description 输出JSON字符串
     * @date 2022/6/29
     */
    @Override
    public String toString() {
        SerializeConfig config = new SerializeConfig();
        PropertyFilter filter = (object, name, value) -> !(name.equals("data") && value == null);
        config.addFilter(Result.class, filter);
        return JSON.toJSONString(this, config, SerializerFeature.PrettyFormat, SerializerFeature.WriteNullStringAsEmpty,
                SerializerFeature.WriteNullListAsEmpty, SerializerFeature.WriteMapNullValue, SerializerFeature.WriteNullBooleanAsFalse);
    }
}
