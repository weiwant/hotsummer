package com.example.sprint2.service.impl;

import com.example.sprint2.dao.UserDao;
import com.example.sprint2.models.vo.LoginVo;
import com.example.sprint2.mybatis.entity.UserLogin;
import com.example.sprint2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author hy
 * @Description 用户登录
 * @Date 2022/6/23
 */

@Service
public class LoginServiceImpl implements UserService {
    @Autowired
    UserDao userdao;

    @Override
    public boolean checkUserExist(LoginVo loginVo) {//检测该id的账户是否存在（更新）
        UserLogin ul;
        String s = loginVo.getUserId();
        ul = userdao.selectById(s);
        if (ul == null) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public String selectUserName(LoginVo loginVo){
        String name = userdao.selectById(loginVo.getUserId()).getUsername();
        return name;
    }

    @Override
    public boolean checkPsw(LoginVo loginVo) {     //检测该账号输入的密码是否正确
        UserLogin user;
        boolean result = false;
        user = userdao.selectById(loginVo.getUserId());
        if (user.getPassword() != null && user.getPassword().equals(loginVo.getPassword())) {
            result = true;
        }
        return result;
    }

    @Override
    public int selectIdentify(LoginVo loginVo) {   //根据id,返回用户身份：0为教师，1为主管理员，2为负责监考管理员，3为负责论文管理员。
        int identify = userdao.selectById(loginVo.getUserId()).getIdentify();
        return identify;
    }

}
