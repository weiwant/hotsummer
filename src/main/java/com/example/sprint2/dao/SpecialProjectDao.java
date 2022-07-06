package com.example.sprint2.dao;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.sprint2.mybatis.entity.SpecialProject;
import com.example.sprint2.mybatis.mapper.SpecialProjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author:wwq
 * @Date：2022/7/5：14:58
 */
@Repository
public class SpecialProjectDao {
    @Autowired
    SpecialProjectMapper specialProjectMapper;

    /**
     * @Author：wwq
     * @Return：返回当前插入的数据id
     * @Url:
     * @Description：先保存project，返回id，再保存teacehr；
     */
    public Integer saveSpecialProject(SpecialProject specialProject) {
        specialProjectMapper.insert(specialProject);
        Integer num = specialProject.getId();
        return num;
    }

    /**
     * @Author：wwq
     * @Return：
     * @Url:
     * @Description：更新特殊Project。
     */
    public boolean uploadSpecialProject(SpecialProject specialProject) {
        if (specialProjectMapper.updateById(specialProject) == 1) {
            return true;
        } else {
            return false;
        }
    }

    public String selectFilePath(int id) {
        SpecialProject specialProject = specialProjectMapper.selectById(id);
        if (specialProject == null) {
            return "error1";//不存在记录
        }
        return specialProject.getFilePath();//返回文件路
    }

    public SpecialProject selectSpecialProjectById(Integer id) {
        return specialProjectMapper.selectById(id);
    }

    public boolean setFilePath(String filePath, Integer id) {
        if (selectSpecialProjectById(id) == null) {
            return false;
        }
        QueryWrapper<SpecialProject> qw = new QueryWrapper<>();
        SpecialProject specialProject = new SpecialProject();
        specialProject.setId(id);
        specialProject.setFilePath(filePath);
        specialProjectMapper.updateById(specialProject);
        return true;
    }

    public List<String> getTeacherNameById(Integer id) {
        return null;
    }
}
