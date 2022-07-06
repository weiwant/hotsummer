package com.example.sprint2.dao;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.sprint2.mybatis.entity.WorkloadConfirm;
import com.example.sprint2.mybatis.mapper.WorkloadConfirmMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author ZWForum
 * @description 工作量确认表操作
 * @date 2022/7/4
 */
@Repository
public class WorkloadConfirmDao {
    @Autowired
    WorkloadConfirmMapper workloadConfirmMapper;

    public boolean insertEntity(WorkloadConfirm workloadConfirm) {
        return workloadConfirmMapper.insert(workloadConfirm) != -1;
    }

    public boolean updateRecord(WorkloadConfirm workloadConfirm) {
        UpdateWrapper<WorkloadConfirm> wrapper = new UpdateWrapper<>();
        wrapper.eq("teacher_name", workloadConfirm.getTeacherName());
        wrapper.eq("natural_year", workloadConfirm.getNaturalYear());
        wrapper.set("confirm", workloadConfirm.getConfirm());
        return workloadConfirmMapper.update(null, wrapper) != -1;
    }

    public IPage<WorkloadConfirm> selectRecords(WorkloadConfirm workloadConfirm, Integer page) {
        int size = 40;
        QueryWrapper<WorkloadConfirm> wrapper = new QueryWrapper<>();
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
