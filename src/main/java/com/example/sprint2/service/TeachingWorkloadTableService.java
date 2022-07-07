package com.example.sprint2.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.sprint2.models.vo.TeachingWorkloadVo;
import com.example.sprint2.mybatis.entity.TeachingWorkloadStatistics;

import java.util.List;

/**
 * @author 24047
 * @date 2022/6/29
 * @description 教学工作量表所有查询
 */
public interface TeachingWorkloadTableService {
    IPage<TeachingWorkloadStatistics> searchByNaturalYearInPage(TeachingWorkloadVo teachingWorkloadVo);

    IPage<TeachingWorkloadStatistics> searchByTeacherNameInPage(TeachingWorkloadVo teachingWorkloadVo);

    IPage<TeachingWorkloadStatistics> searchByCourseNameInPage(TeachingWorkloadVo teachingWorkloadVo);

    IPage<TeachingWorkloadStatistics> searchByStudentGradeInPage(TeachingWorkloadVo teachingWorkloadVo);

    IPage<TeachingWorkloadStatistics> searchByMajorInPage(TeachingWorkloadVo teachingWorkloadVo);

    List<TeachingWorkloadStatistics> selectByTeacher(TeachingWorkloadVo teachingWorkloadVo);

}
