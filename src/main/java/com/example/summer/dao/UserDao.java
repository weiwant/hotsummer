package com.example.summer.dao;

import com.example.summer.entity.UserLogin;
import com.example.summer.mapper.UserloginMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author hy
 * @Description 用户登录数据操作
 * @Date 2022/6/23
 */

@Repository
public class UserDao {

    @Autowired
    UserloginMapper userloginMapper;

    public UserLogin selectById(String username){      //根据用户名查找
        UserLogin ul;
        if (userloginMapper.selectById(username)!= null){
            ul=userloginMapper.selectById(username);
            return ul;
        }
        else {
            return  null;
        }

    }




}
