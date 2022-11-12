package com.example.sprint2.controller;

import cn.hutool.extra.spring.SpringUtil;
import com.example.sprint2.models.abc.Token;
import com.example.sprint2.models.enumerate.impl.ResponseCode;
import com.example.sprint2.models.vo.LoginVo;
import com.example.sprint2.service.impl.LoginServiceImpl;
import com.example.sprint2.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author hy
 * @Date 2022/6/29
 * @Description
 */
@RestController
@RequestMapping("/users")
public class UserLoginController {
    @Autowired
    private LoginServiceImpl login;
    private final Token factory = SpringUtil.getBean("tokenType");

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String checkPsw(@RequestBody LoginVo loginVo) {                  //根据账号密码返回token
        boolean exist = login.checkUserExist(loginVo);    //检测账户是否存在
        if (exist) {
            boolean rightPsw = login.checkPsw(loginVo);     //检测密码是否正确
            if (rightPsw) {
                LoginVo loginVo1 = new LoginVo();
                loginVo1.setIdentify(login.selectIdentify(loginVo));
                loginVo1.setUsername(login.selectUserName(loginVo));
                Map<String, Object> payload = new HashMap<>();
                payload.put("identity", loginVo1.getIdentify());
                //payload.put("username",loginVo1.getUsername());
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(new Date());
                calendar.add(Calendar.HOUR, 2);
                loginVo1.setToken(factory.createToken(null, payload, calendar.getTime()));
                return new Result(ResponseCode.SUCCESS, loginVo1).toString();
            } else {
                return new Result(ResponseCode.LoginFailure).toString();
            }
        } else {
            return new Result(ResponseCode.LoginFailure).toString();
        }

    }


}

