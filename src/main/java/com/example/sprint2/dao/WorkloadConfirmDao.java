package com.example.sprint2.dao;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.sprint2.mybatis.entity.WorkloadConfirm;
import com.example.sprint2.mybatis.mapper.WorkloadConfirmMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.lang.reflect.Array;
import java.util.List;

/**
 * @author ZWForum
 * @description 工作量确认表操作
 * @date 2022/7/4
 */
@Repository
public class WorkloadConfirmDao {
    @Autowired
    WorkloadConfirmMapper workloadConfirmMapper;

    //插入记录
    public boolean insertEntity(WorkloadConfirm workloadConfirm) {
        return workloadConfirmMapper.insert(workloadConfirm) != -1;
    }

    //
    public boolean updateRecord(WorkloadConfirm workloadConfirm) {
        UpdateWrapper<WorkloadConfirm> wrapper = new UpdateWrapper<>();
        wrapper.eq("teacher_id",workloadConfirm.getTeacherId());
        wrapper.eq("teacher_name", workloadConfirm.getTeacherName());
        wrapper.eq("natural_year", workloadConfirm.getNaturalYear());
        wrapper.set("confirm", workloadConfirm.getConfirm());
        return workloadConfirmMapper.update(null, wrapper) != -1;
    }

    /**
     * @author hy
     * @Receive
     * @Param自然年naturalYear、教师teacherId
     * @description工作量确认
     */
    public boolean confirmRecord(WorkloadConfirm workloadConfirm){
        UpdateWrapper<WorkloadConfirm> wrapper = new UpdateWrapper<>();
        wrapper.eq("teacher_id", workloadConfirm.getTeacherId());
        wrapper.eq("natural_year", workloadConfirm.getNaturalYear());
        WorkloadConfirm workloadConfirm1 = new WorkloadConfirm();
        workloadConfirm1.setConfirm(1);
        boolean flag = false;
        if(workloadConfirm1!=null){
            flag = workloadConfirmMapper.update(workloadConfirm1,wrapper)!= -1;
        }
        return flag;

    }

    /**
     * @author hy
     * @Receive
     * @Param自然年naturalYear、教师teacherId
     * @description获取工作量的确认状态
     */
    public int getConfirmStatus(String year,String id){
        QueryWrapper<WorkloadConfirm> wrapper = new QueryWrapper<>();
        wrapper.eq("teacher_id",id);
        wrapper.eq("natural_year",year);
        if(workloadConfirmMapper.selectOne(wrapper) != null){
            return workloadConfirmMapper.selectOne(wrapper).getConfirm();
        }else {
            return -1;
        }

    }

    /**
     * @author hy
     * @Receive
     * @Param自然年Natural_year
     * @description回复统计的已确认与未确认人数
     */
    public int[] getCount(String year){
        QueryWrapper<WorkloadConfirm> wrapper = new QueryWrapper<>();
        wrapper.eq("natural_year",year);
        wrapper.eq("confirm",1);
            //已确认人数
            int count1 = workloadConfirmMapper.selectCount(wrapper).intValue();
            QueryWrapper<WorkloadConfirm> wrapper1 = new QueryWrapper<>();
            wrapper1.eq("natural_year", year);
            //剩余的为未确认人数
            int count2 = workloadConfirmMapper.selectCount(wrapper1).intValue() - count1;
            int[] array = {count1, count2};
            return array;

    }

    public IPage<WorkloadConfirm> selectRecords(WorkloadConfirm workloadConfirm, Integer page) {
        int size = 40;
        QueryWrapper<WorkloadConfirm> wrapper = new QueryWrapper<>();
        if(workloadConfirm.getTeacherId()!=null){
            wrapper.eq("teacher_id",workloadConfirm.getTeacherId());
        }
        if (workloadConfirm.getTeacherName() != null) {
            wrapper.eq("teacher_name", workloadConfirm.getTeacherName());
        }
        if (workloadConfirm.getNaturalYear() != null) {
            wrapper.eq("natural_year", workloadConfirm.getNaturalYear());
        }
        if (workloadConfirm.getConfirm() != null) {
            wrapper.eq("confirm", workloadConfirm.getConfirm());
        }
        return workloadConfirmMapper.selectPage(new Page<>(page, size), wrapper);
    }

    public Boolean selectIfExist(WorkloadConfirm workloadConfirm) {
        QueryWrapper<WorkloadConfirm> wrapper = new QueryWrapper<>();
        wrapper.exists("select * from workload_confirm where natural_year = {0}", workloadConfirm.getNaturalYear());
        return !workloadConfirmMapper.selectList(wrapper).isEmpty();
    }
}
