package com.example.summer.service.impl;

import com.example.summer.dao.TeachingWorkloadStatisticsDao;
import com.example.summer.entity.TeachingWorkloadStatistics;
import com.example.summer.service.TeachingWorkloadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeachingWorkloadServiceImpl implements TeachingWorkloadService {
    @Autowired
    public TeachingWorkloadStatisticsDao teachingWorkloadStatisticsDao;

    @Override
    public List<TeachingWorkloadStatistics> searchTeachingWorkloadByName(String teacherName) {
        return teachingWorkloadStatisticsDao.searchByName(teacherName);
    }

    @Override
    public List<TeachingWorkloadStatistics> searchTeachingWorkloadByNameLike(String teacherName) {
        return teachingWorkloadStatisticsDao.likeSearchByName(teacherName);
    }

    @Override
    public List<TeachingWorkloadStatistics> searchTeachingWorkloadInScope(String startYear, String endYear, String teacherName) {
        return teachingWorkloadStatisticsDao.searchInScope(startYear, endYear, teacherName);
    }
}
