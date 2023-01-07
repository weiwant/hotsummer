package com.example.sprint2.service.impl;

import com.example.sprint2.dao.WorkloadConfirmDao;
import com.example.sprint2.models.enumerate.impl.ResponseCode;
import com.example.sprint2.models.vo.ConfirmVo;
import com.example.sprint2.mybatis.entity.WorkloadConfirm;
import com.example.sprint2.service.ConfirmService;
import com.example.sprint2.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
            return -2;
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

        /**
         * @Author：wwq
         * @Description：根据year获取未确认教师姓名字符串
         * @Date:2023/1/7
         */
    @Override
    public String getConfirmTeachers(ConfirmVo confirmVo) {
        WorkloadConfirm workloadConfirm=new WorkloadConfirm();
        workloadConfirm.setNaturalYear(confirmVo.getYear());//传递年份
        List<WorkloadConfirm> workloadConfirmList=workloadConfirmDao.getUnconfirmTeacher(workloadConfirm);
        //将教师姓名提取
        StringBuffer unconfirmTeachers=new StringBuffer();
        if(workloadConfirmList!=null){
            for (WorkloadConfirm confirm : workloadConfirmList) {
                if(unconfirmTeachers.length()==0){//首个直接添加
                    unconfirmTeachers.append(confirm.getTeacherName());
                }else{//用逗号隔开
                    unconfirmTeachers.append(",");
                    unconfirmTeachers.append(confirm.getTeacherName());
                }

            }
        }
        return unconfirmTeachers.toString();
    }


}
