package com.example.sprint2.dao;

import com.example.sprint2.mybatis.entity.SpecialWorkload;
import com.example.sprint2.mybatis.mapper.SpecialWorkloadMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author:wwq
 * @Date：2022/7/1：10:49
 */
@Repository
public class SpecialSaveDao {
    @Autowired
    SpecialWorkloadMapper specialWorkloadMapper;

    /**
     * @Author：wwq
     * @Return：
     * @Url:
     * @Description：插入一条记录。返回1代表成功，其他失败。
     */
    public boolean save(SpecialWorkload specialWorkload) {
        if (specialWorkloadMapper.insert(specialWorkload) == 1) {
            return true;
        } else {
            return false;
        }
    }
}
