package com.example.sprint2.dao;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.sprint2.mybatis.entity.SpecialTeacher;
import com.example.sprint2.mybatis.mapper.SpecialTeacherMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * @author:wwq
 * @Date：2022/7/5：15:21
 */
@Repository
public class SpecialTeacherDao {
    @Autowired
    SpecialTeacherMapper specialTeacherMapper;

    /**
     * @Author：wwq
     * @Return：
     * @Url:
     * @Description：插入teacher表，外键是项目id。
     */
    public boolean saveSpecialTeacher(SpecialTeacher specialTeacher) {
        if (specialTeacherMapper.insert(specialTeacher) == 1) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * @Author：wwq
     * @Return：
     * @Url:
     * @Description：根据Projectid删除。再调用save方法
     */
    public void deleteByProjectId(Integer projectId) {
        QueryWrapper<SpecialTeacher> qw = new QueryWrapper<>();
        qw.eq("project_id", projectId);
        specialTeacherMapper.delete(qw);
    }

    /**
     * @Author：wwq
     * @Return：
     * @Url:
     * @Description：根据ProjectId查询教师姓名，返回List
     */
    public List<String> getTeacherNamesById(Integer projectId) {
        QueryWrapper<SpecialTeacher> qw = new QueryWrapper<>();
        qw.eq("project_id", projectId);
        List<SpecialTeacher> teachers = specialTeacherMapper.selectList(qw);
        List<String> names = new ArrayList<>();
        for (SpecialTeacher teacher : teachers) {
            String name = teacher.getTeacherName();
            names.add(name);
        }
        return names;
    }

    /**
     * @Author：wwq
     * @Return：
     * @Url:
     * @Description：管理员打教分
     */

    public void markSpecialTeacher(SpecialTeacher specialTeacher) {
        QueryWrapper<SpecialTeacher> qw= new QueryWrapper<>();
        qw.eq("project_id",specialTeacher.getProjectId());
        qw.eq("teacher_name",specialTeacher.getTeacherName());
        qw.eq("author_order",specialTeacher.getAuthorOrder());
        qw.eq("type",specialTeacher.getType());
        specialTeacherMapper.update(specialTeacher,qw);
    }
}
