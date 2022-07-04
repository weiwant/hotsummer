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
        qw.eq("id",specialReceiveVo.getId());
        List<SpecialWorkload> thisWorkload=specialWorkloadMapper.selectList(qw);
        if(thisWorkload.size()==0){
            flag=false;
        }else{

        }
        return flag;
    }
}
