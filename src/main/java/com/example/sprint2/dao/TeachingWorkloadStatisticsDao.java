package com.example.sprint2.dao;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.sprint2.mybatis.entity.TeachingWorkloadStatistics;
import com.example.sprint2.mybatis.mapper.TeachingWorkloadStatisticsMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author ZWForum
 * @description 教学工作量表操作
 * @date 2022/6/29
 */
public class TeachingWorkloadStatisticsDao {
    @Autowired
    TeachingWorkloadStatisticsMapper teachingWorkloadStatisticsMapper;

    /**
     * @param teachingWorkloadStatistics
     * @return List<TeachingWorkloadStatistics>
     * @author ZWForum
     * @description 根据自然年查询
     * @date 2022/6/29
     */
    public List<TeachingWorkloadStatistics> selectByNaturalYear(TeachingWorkloadStatistics teachingWorkloadStatistics) {
        QueryWrapper<TeachingWorkloadStatistics> wrapper = new QueryWrapper<>();
        wrapper.eq("natural_year", teachingWorkloadStatistics.getNaturalYear());
        return teachingWorkloadStatisticsMapper.selectList(wrapper);
    }

    /**
     * @param teachingWorkloadStatistics
     * @return
     * @author ZWForum
     * @description 插入一条记录
     * @date 2022/6/29
     */
    public void insertEntity(TeachingWorkloadStatistics teachingWorkloadStatistics) {
        teachingWorkloadStatisticsMapper.insert(teachingWorkloadStatistics);
    }

    /**
     * @param teachingWorkloadStatistics
     * @return
     * @author ZWForum
     * @description 根据自然年删除记录
     * @date 2022/6/29
     */
    public void deleteByNaturalYear(TeachingWorkloadStatistics teachingWorkloadStatistics) {
        QueryWrapper<TeachingWorkloadStatistics> wrapper = new QueryWrapper<>();
        wrapper.eq("natural_year", teachingWorkloadStatistics.getNaturalYear());
        teachingWorkloadStatisticsMapper.delete(wrapper);
    }
}
