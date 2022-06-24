package com.example.summer.controller;

import com.example.summer.entity.UserLogin;
import com.example.summer.models.pojo.ResponseCode;
import com.example.summer.models.vo.LoginVo;
import com.example.summer.service.impl.loginService;
//import com.example.summer.utils.ResponseCode;
import com.example.summer.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author hy
 * @Description 用户登录控制器：/users/login
 * @Date 2022/6/23
 */
@RestController
@RequestMapping("/users")
public class UserLoginController {
    @Autowired
    private loginService login;

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public String checkPsw(@RequestBody LoginVo loginVo){
        boolean exist=login.checkUserExist(loginVo);    //检测账户是否存在
        if(exist){
            boolean rightPsw=login.checkPsw(loginVo);     //检测密码是否正确
            if(rightPsw) {
                UserLogin userIdentify = new UserLogin();
                userIdentify.setIdentify(login.selectIdentify(loginVo));
                userIdentify.setUsername(loginVo.getUsername());
                return new Result(ResponseCode.SUCCESS,userIdentify).toString();
            }
            else {
                return new Result(ResponseCode.LoginFailure).toString();
            }
        }
        else {
            return new Result(ResponseCode.LoginFailure).toString();
         }

    }


    }


