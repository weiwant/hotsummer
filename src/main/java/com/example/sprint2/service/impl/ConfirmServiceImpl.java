package com.example.sprint2.service.impl;

import com.example.sprint2.dao.WorkloadConfirmDao;
import com.example.sprint2.models.enumerate.impl.ResponseCode;
import com.example.sprint2.models.vo.ConfirmVo;
import com.example.sprint2.mybatis.entity.WorkloadConfirm;
import com.example.sprint2.service.ConfirmService;
import com.example.sprint2.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author ZWForum
 * @description
 * @date 2022/7/6
 */
@Service
public class ConfirmServiceImpl implements ConfirmService {
    @Autowired
    WorkloadConfirmDao workloadConfirmDao;

    public String updateConfirm(ConfirmVo confirmVo) {
        WorkloadConfirm workloadConfirm = new WorkloadConfirm();
        workloadConfirm.setConfirm(confirmVo.getConfirm());
        workloadConfirm.setNaturalYear(confirmVo.getYear());
        workloadConfirm.setTeacherId(workloadConfirm.getTeacherId());
        workloadConfirm.setTeacherName(confirmVo.getTeacherName());
        if (workloadConfirmDao.updateRecord(workloadConfirm)) {
            return new Result(ResponseCode.SUCCESS).toString();
        } else {
            return new Result(ResponseCode.UnknownFailure).toString();
        }
    }

    @Override
    public String confirmRecord(ConfirmVo confirmVo) {
        WorkloadConfirm workloadConfirm = new WorkloadConfirm();
        workloadConfirm.setNaturalYear(confirmVo.getYear());
        workloadConfirm.setTeacherId(confirmVo.getId());
        if(workloadConfirmDao.confirmRecord(workloadConfirm)){
            return new Result(ResponseCode.SUCCESS).toString();
        }else {
            return new Result(ResponseCode.UnknownFailure,"确认失败").toString();
        }
    }

    @Override
    public int getConfirmStatus(ConfirmVo confirmVo) {
        if(confirmVo.getYear()!= null && confirmVo.getId()!=null){
            int status = workloadConfirmDao.getConfirmStatus(confirmVo.getYear(),confirmVo.getId());
            return status;
        }else {
         return -1;
        }
    }

    @Override
    public int[] getCount(ConfirmVo confirmVo) {
        int[] count = new int[2];
        if (confirmVo.getYear()!=null){
            count = workloadConfirmDao.getCount(confirmVo.getYear());
            return count;
        }else {
            return null;
        }
    }
}
