package com.example.summer.service;

import com.example.summer.entity.TeachingWorkloadStatistics;

import java.util.List;

public interface TeachingWorkloadService {
    List<TeachingWorkloadStatistics> searchTeachingWorkloadByName(String teacherName);

    List<TeachingWorkloadStatistics> searchTeachingWorkloadByNameLike(String teacherName);

    List<TeachingWorkloadStatistics> searchTeachingWorkloadInScope(String startYear, String endYear, String teacherName);

}
