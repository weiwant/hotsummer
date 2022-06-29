package com.example.sprint2.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.sprint2.models.vo.PageSearchVo;
import com.example.sprint2.mybatis.entity.TeachingWorkloadStatistics;

/**
 * @author 24047
 * @date 2022/6/29
 * @description 教学工作量表所有查询
 */
public interface TeachingWorkloadTableService {
    IPage<TeachingWorkloadStatistics> searchByNaturalYearInPage(PageSearchVo pageSearchVo);
    IPage<TeachingWorkloadStatistics> searchByTeacherNameInPage(PageSearchVo pageSearchVo);
    IPage<TeachingWorkloadStatistics> searchByCourseNameInPage(PageSearchVo pageSearchVo);
    IPage<TeachingWorkloadStatistics> searchByStudentGradeInPage(PageSearchVo pageSearchVo);
    IPage<TeachingWorkloadStatistics> searchByMajorInPage(PageSearchVo pageSearchVo);

}
