package com.example.sprint2.models.abc;

import cn.hutool.json.JSONObject;

import java.util.Date;
import java.util.Map;

/**
 * @author ZWForum
 * @description Token抽象类
 * @date 2022/7/7
 */
public abstract class Token {
    abstract public String createToken(Map<String, Object> header, Map<String, Object> payload, Date expires);

    abstract public Boolean verify(String token);

    abstract public Boolean validateDate(String token);

    abstract public Boolean validateAlgorithm(String token);

    abstract public JSONObject getHeader(String token);

    abstract public JSONObject getPayload(String token);
}
