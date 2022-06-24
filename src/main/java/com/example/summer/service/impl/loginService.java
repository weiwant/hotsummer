package com.example.summer.service.impl;

import com.example.summer.dao.UserDao;
import com.example.summer.entity.UserLogin;
import com.example.summer.models.vo.LoginVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author hy
 * @Description 用户登录
 * @Date 2022/6/23
 */

@Service
public class loginService implements userservice {
    @Autowired
    UserDao userdao;
    @Override
    public boolean checkUserExist(LoginVo loginVo) {//检测该用户名的账户是否存在
        UserLogin ul = new UserLogin();
        System.out.println("0");
        ul = userdao.selectById(loginVo.getUsername());
        System.out.println("1");

        if (ul == null) {
            System.out.println("2");
            return false;
        } else {
            System.out.println("3");
            return true;
        }
    }

    @Override
    public boolean checkPsw(LoginVo loginVo){     //检测输入密码是否正确
        UserLogin user;
        boolean result = false;
        user =userdao.selectById(loginVo.getUsername());
        if(user.getPassword()!=null&&user.getPassword().equals(loginVo.getPassword())){
            result= true;
        }
        return result;
    }

    @Override
    public int selectIdentify(LoginVo loginVo) {   //返回用户身份：1为管理员。2为教师，3为空身份
        int identify=userdao.selectById(loginVo.getUsername()).getIdentify();
        return identify;
    }

}
