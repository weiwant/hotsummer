package com.example.sprint2.dao;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.sprint2.models.vo.LoginVo;
import com.example.sprint2.mybatis.entity.Userinfo;
import com.example.sprint2.mybatis.mapper.UserinfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author ZWForum
 * @description
 * @date 2022/12/12
 */
@Repository
public class UserInfoDao {
    @Autowired
    UserinfoMapper userinfoMapper;
    public Userinfo getRecord(LoginVo loginVo) {
        return userinfoMapper.selectById(loginVo.getUserId());
    }

    /**
     * @Author：wwq
     * @Date：
     * @Url:
     * @Description：根据用户id获取院系
     */
    public String getFacultyById(String userId) {
        QueryWrapper<Userinfo> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("id",userId);
        Userinfo userinfo=userinfoMapper.selectOne(queryWrapper);
        return userinfo.getFaculty();
    }
}
