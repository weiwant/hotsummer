package com.example.sprint2.dao;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.sprint2.models.vo.SpecialReceiveVo;
import com.example.sprint2.mybatis.entity.SpecialWorkload;
import com.example.sprint2.mybatis.mapper.SpecialWorkloadMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

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

    /**
     * @Author：wwq
     * @Return：
     * @Url:
     * @Description：根据id插入教分及备注
     */
    public boolean mark(SpecialReceiveVo specialReceiveVo) {
        boolean flag=true;
        QueryWrapper<SpecialWorkload> qw=new QueryWrapper<>();
        //查询有无对应id


        if(specialReceiveVo.getId()==null) {
            flag = false;
        } else {
            SpecialWorkload specialWorkload=new SpecialWorkload();
            specialWorkload.setId(specialReceiveVo.getId());//id
            specialWorkload.setTeachingScores(specialReceiveVo.getTeachingScore());//教分
            specialWorkload.setRemarks(specialReceiveVo.getRemark());//备注
            specialWorkload.setStatus("已审核");
            specialWorkloadMapper.updateById(specialWorkload);
        }
        return flag;
    }
}
