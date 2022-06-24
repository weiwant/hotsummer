package com.example.summer.service.mybatis;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.summer.entity.TeachingWorkloadStatistics;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author 24047
 * @since 2022-06-24
 */
public interface TeachingWorkloadStatisticsService extends IService<TeachingWorkloadStatistics> {
    List<TeachingWorkloadStatistics> searchTeachingWorkloadByName(String teacherName);

    List<TeachingWorkloadStatistics> searchTeachingWorkloadByNameLike(String teacherName);

    List<TeachingWorkloadStatistics> searchTeachingWorkloadInScope(String startYear,  String endYear, String teacherName);
}
