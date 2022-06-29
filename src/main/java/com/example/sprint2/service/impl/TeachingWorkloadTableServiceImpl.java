package com.example.sprint2.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.sprint2.dao.TeachingWorkloadStatisticsDao;
import com.example.sprint2.models.vo.PageSearchVo;
import com.example.sprint2.mybatis.entity.TeachingWorkloadStatistics;
import com.example.sprint2.service.TeachingWorkloadTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 24047
 * @date 2022/6/29
 */
@Service
public class TeachingWorkloadTableServiceImpl implements TeachingWorkloadTableService {
    @Autowired
    TeachingWorkloadStatisticsDao teachingWorkloadStatisticsDao;

    @Override
    public IPage<TeachingWorkloadStatistics> searchByNaturalYearInPage(PageSearchVo pageSearchVo) {
        return teachingWorkloadStatisticsDao.selectByNaturalYearInPage(pageSearchVo.getNaturalYear(), pageSearchVo.getPageNumber(), pageSearchVo.getPageSize());
    }

    @Override
    public IPage<TeachingWorkloadStatistics> searchByTeacherNameInPage(PageSearchVo pageSearchVo) {
        return teachingWorkloadStatisticsDao.selectByTeacherNameInPage(pageSearchVo.getNaturalYear(),pageSearchVo.getTeacherName(), pageSearchVo.getPageNumber(), pageSearchVo.getPageSize());
    }

    @Override
    public IPage<TeachingWorkloadStatistics> searchByCourseNameInPage(PageSearchVo pageSearchVo) {
        return teachingWorkloadStatisticsDao.selectByCourseNameInPage(pageSearchVo.getNaturalYear(), pageSearchVo.getCourseName(),pageSearchVo.getPageNumber(), pageSearchVo.getPageSize());

    }

    @Override
    public IPage<TeachingWorkloadStatistics> searchByStudentGradeInPage(PageSearchVo pageSearchVo) {
        return teachingWorkloadStatisticsDao.selectByStudentGradeInPage(pageSearchVo.getNaturalYear(),pageSearchVo.getStudentGrade(), pageSearchVo.getPageNumber(), pageSearchVo.getPageSize());

    }

    @Override
    public IPage<TeachingWorkloadStatistics> searchByMajorInPage(PageSearchVo pageSearchVo) {
        return null;
    }
}

