package com.example.sprint2.dao;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.sprint2.models.pojo.TeacherAndOrder;
import com.example.sprint2.mybatis.entity.SpecialTeacher;
import com.example.sprint2.mybatis.mapper.SpecialTeacherMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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
    public boolean saveSpecialTeacher(SpecialTeacher specialTeacher){
        if(specialTeacherMapper.insert(specialTeacher)==1){
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
    public void deleteByProjectId(Integer projectId){
        QueryWrapper<SpecialTeacher> qw=new QueryWrapper<>();
        qw.eq("project_id",projectId);
        specialTeacherMapper.delete(qw);
    }


}
