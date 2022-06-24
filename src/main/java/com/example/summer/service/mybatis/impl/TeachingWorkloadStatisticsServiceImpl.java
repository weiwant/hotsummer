package com.example.summer.service.mybatis.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.summer.dao.TeachingWorkloadStatisticsDao;
import com.example.summer.entity.TeachingWorkloadStatistics;
import com.example.summer.mapper.TeachingWorkloadStatisticsMapper;
import com.example.summer.service.mybatis.TeachingWorkloadStatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author 24047
 * @since 2022-06-24
 */
@Service
public class TeachingWorkloadStatisticsServiceImpl extends ServiceImpl<TeachingWorkloadStatisticsMapper, TeachingWorkloadStatistics> implements TeachingWorkloadStatisticsService {
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
    public List<TeachingWorkloadStatistics> searchTeachingWorkloadInScope(String startYear, String endYear, String teacherName){
        return teachingWorkloadStatisticsDao.searchInScope(startYear,endYear,teacherName);
    }
}
