package com.example.sprint2.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.sprint2.dao.TeachingWorkloadStatisticsDao;
import com.example.sprint2.models.vo.TeachingWorkloadVo;
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
    public IPage<TeachingWorkloadStatistics> searchByNaturalYearInPage(TeachingWorkloadVo teachingWorkloadVo) {
        return teachingWorkloadStatisticsDao.selectByNaturalYearInPage(teachingWorkloadVo.getNaturalYear(), teachingWorkloadVo.getPageNumber(), teachingWorkloadVo.getPageSize());
    }

    @Override
    public IPage<TeachingWorkloadStatistics> searchByTeacherNameInPage(TeachingWorkloadVo teachingWorkloadVo) {
        return teachingWorkloadStatisticsDao.selectByTeacherNameInPage(teachingWorkloadVo.getNaturalYear(), teachingWorkloadVo.getTeacherName(), teachingWorkloadVo.getPageNumber(), teachingWorkloadVo.getPageSize());
    }

    @Override
    public IPage<TeachingWorkloadStatistics> searchByCourseNameInPage(TeachingWorkloadVo teachingWorkloadVo) {
        return teachingWorkloadStatisticsDao.selectByCourseNameInPage(teachingWorkloadVo.getNaturalYear(), teachingWorkloadVo.getCourseName(), teachingWorkloadVo.getPageNumber(), teachingWorkloadVo.getPageSize());

    }

    @Override
    public IPage<TeachingWorkloadStatistics> searchByStudentGradeInPage(TeachingWorkloadVo teachingWorkloadVo) {
        return teachingWorkloadStatisticsDao.selectByStudentGradeInPage(teachingWorkloadVo.getNaturalYear(), teachingWorkloadVo.getStudentGrade(), teachingWorkloadVo.getPageNumber(), teachingWorkloadVo.getPageSize());

    }

    @Override
    public IPage<TeachingWorkloadStatistics> searchByMajorInPage(TeachingWorkloadVo teachingWorkloadVo) {
        return teachingWorkloadStatisticsDao.selectByMajorInPage(teachingWorkloadVo.getNaturalYear(), teachingWorkloadVo.getMajor(), teachingWorkloadVo.getPageNumber(), teachingWorkloadVo.getPageSize());
    }
}

