package com.example.summer.controller;

import com.example.summer.models.pojo.ResponseCode;
import com.example.summer.models.vo.LoginVo;
import com.example.summer.service.impl.LoginServiceImpl;
import com.example.summer.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author hy
 * @Description 用户登录控制器：/users/login
 * @Date 2022/6/23
 */
@RestController
@RequestMapping("/users")
public class UserLoginController {
    @Autowired
    private LoginServiceImpl login;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String checkPsw(@RequestBody LoginVo loginVo) {
        boolean exist = login.checkUserExist(loginVo);    //检测账户是否存在
        if (exist) {
            boolean rightPsw = login.checkPsw(loginVo);     //检测密码是否正确
            if (rightPsw) {
                LoginVo loginVo1 = new LoginVo();
                loginVo1.setIdentify(login.selectIdentify(loginVo));
                loginVo1.setUsername(loginVo.getUsername());
                return new Result(ResponseCode.SUCCESS, loginVo1).toString();
            } else {
                return new Result(ResponseCode.LoginFailure).toString();
            }
        } else {
            return new Result(ResponseCode.LoginFailure).toString();
        }

    }


}


