package com.example.sprint2.models.abc.sub;

import cn.hutool.core.exceptions.ValidateException;
import cn.hutool.crypto.CryptoException;
import cn.hutool.crypto.KeyUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTException;
import cn.hutool.jwt.JWTUtil;
import cn.hutool.jwt.JWTValidator;
import cn.hutool.jwt.signers.JWTSigner;
import cn.hutool.jwt.signers.JWTSignerUtil;
import com.example.sprint2.models.abc.Token;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Date;
import java.util.Map;

/**
 * @author ZWForum
 * @description
 * @date 2022/7/8
 */
public class HmacToken extends Token {
    private static final Log logger = LogFactory.getLog(HmacToken.class);
    private static final HmacToken factory = new HmacToken();
    private static final byte[] key = KeyUtil.generateKey("DES").getEncoded();

    @Override
    public String createToken(Map<String, Object> header, Map<String, Object> payload, Date expires) {
        JWT jwt = JWT.create();
        if (header != null) {
            header.forEach(jwt::setHeader);
        }
        if (payload != null) {
            payload.forEach(jwt::setPayload);
        }
        if (expires != null) {
            jwt.setExpiresAt(expires);
        }
        JWTSigner signer = JWTSignerUtil.hs256(key);
        jwt.setSigner(signer);
        return jwt.sign();
    }

    @Override
    public Boolean verify(String token) {
        try {
            return JWTUtil.parseToken(token).verify(JWTSignerUtil.hs256(key));
        } catch (JWTException | CryptoException e) {
            logger.info("token无效");
            throw e;
        }
    }

    @Override
    public Boolean validateDate(String token) {
        try {
            JWTValidator.of(token).validateDate();
            return true;
        } catch (ValidateException | CryptoException e) {
            logger.info("token过期");
            throw e;
        }
    }

    @Override
    public Boolean validateAlgorithm(String token) {
        try {
            JWTValidator.of(token).validateAlgorithm(JWTSignerUtil.hs256(key));
            return true;
        } catch (ValidateException | CryptoException e) {
            logger.info("token算法不一致");
            throw e;
        }
    }

    @Override
    public JSONObject getHeader(String token) {
        try {
            return JWTUtil.parseToken(token).getHeaders();
        } catch (JWTException | CryptoException e) {
            logger.info("token无效");
            throw e;
        }
    }

    @Override
    public JSONObject getPayload(String token) {
        try {
            return JWTUtil.parseToken(token).getPayloads();
        } catch (JWTException | CryptoException e) {
            logger.info("token无效");
            throw e;
        }
    }

    public static HmacToken getFactory() {
        return factory;
    }

    private HmacToken() {
    }
}
