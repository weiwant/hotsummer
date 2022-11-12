package com.example.sprint2.dao;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.sprint2.mybatis.entity.UserLogin;
import com.example.sprint2.mybatis.mapper.UserloginMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * @author hy
 * @Description 用户登录数据操作
 * @Date 2022/6/23
 */

@Repository
public class UserDao {

    @Autowired
    UserloginMapper userloginMapper;

    public UserLogin selectByName(String username) {      //根据用户名查找
        UserLogin ul;
        if (userloginMapper.selectById(username) != null) {
            ul = userloginMapper.selectById(username);
            return ul;
        } else {
            return null;
        }

    }

    public UserLogin selectById(String userId){         //根据账号ID查找（userid）
        QueryWrapper<UserLogin> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("userid",userId);
        UserLogin userLogin = userloginMapper.selectOne(queryWrapper);
        if(userLogin !=null){
            return userLogin;
        }else {
            return null;
        }
    }

    public List<UserLogin> selectAll() {
        return userloginMapper.selectList(new QueryWrapper<>());
    }

    public List<String> getList() {
        List<String> nameList = new ArrayList<>();
        QueryWrapper<UserLogin> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("identify", 0);
        List<UserLogin> list = userloginMapper.selectList(queryWrapper);
        String name = null;
        for (UserLogin userLogin : list) {
            name = userLogin.getUsername();
            nameList.add(name);
        }
        return nameList;
    }


}
