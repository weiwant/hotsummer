package com.example.sprint2.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.sprint2.dao.ExaminationDao;
import com.example.sprint2.models.enumerate.impl.ResponseCode;
import com.example.sprint2.models.vo.ExaminationVo;
import com.example.sprint2.mybatis.entity.ExaminationWorkload;
import com.example.sprint2.service.ExaminationService;
import com.example.sprint2.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author:wwq
 * @Date：2022/6/29：16:15
 */
@Service
public class ExaminationServiceImpl implements ExaminationService {
    @Autowired
    ExaminationDao examinationDao;

    /**
     * @Author：wwq
     * @Return：
     * @Url:/resource/riyear
     * @Description：获取年份考务工作量。分页查询
     */
    @Override
    public IPage<ExaminationWorkload> getPageYearWorkload(ExaminationVo exam) {
        IPage<ExaminationWorkload> examinationIPage=examinationDao.getPageYearWorkload(exam);
        return examinationIPage;
    }

    /**
     * @Author：wwq
     * @Return：
     * @Url:
     * @Description：管理员/教师不分页查询上课教师
     */
    @Override
    public List<ExaminationWorkload> getTeacherWorkload(ExaminationVo examinationVo) {
        List<ExaminationWorkload> examinationWorkloadList=examinationDao.getTeacherWorkload(examinationVo);
        return examinationWorkloadList;
    }

    /**
     * @Author：wwq
     * @Return：
     * @Url:
     * @Description：按课程性质（即监考课程名称）不分页查询
     */
    @Override
    public List<ExaminationWorkload> getCourseWorkload(ExaminationVo examinationVo) {
        List<ExaminationWorkload> examinationWorkloadList=examinationDao.getCourseWorkload(examinationVo);
        return examinationWorkloadList;
    }

    /**
     * @Author：wwq
     * @Return：
     * @Url:
     * @Description：管理员不分页按年份查询考务
     */
    @Override
    public List<ExaminationWorkload> getYearWorkload(ExaminationVo examinationVo){
        List<ExaminationWorkload> examinationWorkloadList=examinationDao.getYearWorkload(examinationVo);
        return examinationWorkloadList;
    }
}
