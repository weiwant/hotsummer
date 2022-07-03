package com.example.sprint2.dao;

import com.example.sprint2.mybatis.entity.SpecialWorkload;
import com.example.sprint2.mybatis.mapper.SpecialWorkloadMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author 24047
 * @date 2022/7/1
 */
@Repository
public class FileDealDao {
    @Autowired
    SpecialWorkloadMapper specialWorkloadMapper;

    public String selectFilePath(int id) {
        SpecialWorkload specialWorkload = specialWorkloadMapper.selectById(id);
        return specialWorkload.getFilePath();//返回文件路径，前期是绝对路径，后期要改成相对路径
    }
}
