package com.example.summer.dao;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.summer.entity.TeachingWorkloadStatistics;
import com.example.summer.mapper.TeachingWorkloadStatisticsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;



/**
 * @author theLastNYF
 * @Date 2022/6/25
 * @Description 为Excel导出提供数据源
 */
@Repository
public class ExportExcelDao {

    @Autowired
    TeachingWorkloadStatisticsMapper teachingWorkloadStatisticsMapper;

    public List<TeachingWorkloadStatistics> searchInScopeAccurate(String startYear, String endYear, String teacherName){

        QueryWrapper<TeachingWorkloadStatistics> teacherMap = new QueryWrapper<>();
//        teacherMap.between("semester",startSemester,endSemester);
        teacherMap.between("academic_year",startYear,endYear);//限定学年范围
        teacherMap.eq("main_teacher_name",teacherName);//不模糊的查询
        return teachingWorkloadStatisticsMapper.selectList(teacherMap);
    }
//    public List<TeachingWorkloadStatistics> searchByName(String teacherName) {
//        QueryWrapper<TeachingWorkloadStatistics> teacherMap = new QueryWrapper<>();
//        teacherMap.eq("main_teacher_name", teacherName);
//        return teachingWorkloadStatisticsMapper.selectList(teacherMap);
//    }
}
