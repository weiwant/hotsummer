package com.example.sprint2.dao;

import com.example.sprint2.models.vo.SpecialVo;
import com.example.sprint2.mybatis.entity.SpecialProject;
import com.example.sprint2.mybatis.mapper.SpecialProjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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
    public Integer saveSpecialProject(SpecialProject specialProject){
        specialProjectMapper.insert(specialProject);
        Integer num=specialProject.getId();
        return num;
    }

    /**
     * @Author：wwq
     * @Return：
     * @Url:
     * @Description：更新特殊Project。
     */
    public boolean uploadSpecialProject(SpecialProject specialProject) {
        if(specialProjectMapper.updateById(specialProject)==1){
            return true;
        } else {
            return  false;
        }
    }
}
