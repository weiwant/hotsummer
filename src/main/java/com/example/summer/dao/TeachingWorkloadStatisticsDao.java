package com.example.summer.dao;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.summer.entity.TeachingWorkloadStatistics;
import com.example.summer.mapper.TeachingWorkloadStatisticsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TeachingWorkloadStatisticsDao {
    @Autowired
    TeachingWorkloadStatisticsMapper teachingWorkloadStatisticsMapper;

    public List<TeachingWorkloadStatistics> searchByName(String teacherName) {
        QueryWrapper<TeachingWorkloadStatistics> teacherMap = new QueryWrapper<>();
        teacherMap.eq("main_teacher_name", teacherName);
        return teachingWorkloadStatisticsMapper.selectList(teacherMap);
    }

    public List<TeachingWorkloadStatistics> likeSearchByName(String likeTeacherName) {
//        创建一个QueryWrapper对象
        QueryWrapper<TeachingWorkloadStatistics> teacherMap = new QueryWrapper<>();
//        构造查询条件
        teacherMap.like("main_teacher_name", likeTeacherName);
        return teachingWorkloadStatisticsMapper.selectList(teacherMap);

    }

    public List<TeachingWorkloadStatistics> searchInScope(String startYear, String endYear, String teacherName) {

        QueryWrapper<TeachingWorkloadStatistics> teacherMap = new QueryWrapper<>();
//        teacherMap.between("semester",startSemester,endSemester);
        //如果是某一年的，那么传一个startYear就可以
        teacherMap.between("academic_year", startYear, endYear);//限定学年范围
        teacherMap.eq("main_teacher_name", teacherName);

        return teachingWorkloadStatisticsMapper.selectList(teacherMap);
    }

    public List<TeachingWorkloadStatistics> searchIndeed(String startYear, int semester, String teacherName) {

        QueryWrapper<TeachingWorkloadStatistics> teacherMap = new QueryWrapper<>();
//        teacherMap.between("semester",startSemester,endSemester);
        //如果是某一年的，那么传一个startYear就可以
        teacherMap.eq("academic_year", startYear);//限定学年范围
        teacherMap.eq("semester",semester);
        teacherMap.eq("main_teacher_name", teacherName);

        return teachingWorkloadStatisticsMapper.selectList(teacherMap);
    }


}
