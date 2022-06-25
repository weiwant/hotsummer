package com.example.summer.dao;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.summer.entity.TeachingWorkloadStatistics;
import com.example.summer.mapper.TeachingWorkloadStatisticsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author wwq
 * @Date 2022/6/23 14:38
 */
@Repository
public class TableShowDao {
    @Autowired
    TeachingWorkloadStatisticsMapper teacherMapper;

    /**
     * @Author： wwq
     * @Return：
     * @Description：获取数据库表所有内容
     */
    public List<TeachingWorkloadStatistics> getAllTables() {
        List<TeachingWorkloadStatistics> works = teacherMapper.selectList(null);
        return works;
    }

    /**
     * @Author： wwq
     * @Return：
     * @Description： 根据年份返回对应数据表。数据库表的year字段
     */
    public List<TeachingWorkloadStatistics> getYearTable(String year) {
        QueryWrapper<TeachingWorkloadStatistics> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("academic_year", year);
        List<TeachingWorkloadStatistics> works = teacherMapper.selectList(queryWrapper);
        return works;
    }

    public List<TeachingWorkloadStatistics> getSemesterTable(String year, int semester) {
        QueryWrapper<TeachingWorkloadStatistics> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("academic_year", year);
        queryWrapper.eq("semester", semester);
        List<TeachingWorkloadStatistics> works = teacherMapper.selectList(queryWrapper);
        return works;
    }
}
