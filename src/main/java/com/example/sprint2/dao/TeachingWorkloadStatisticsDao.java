package com.example.sprint2.dao;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.sprint2.mybatis.entity.TeachingWorkloadStatistics;
import com.example.sprint2.mybatis.entity.TotalTable;
import com.example.sprint2.mybatis.mapper.TeachingWorkloadStatisticsMapper;
import com.example.sprint2.mybatis.mapper.TotalTableMapper;
import com.example.sprint2.utils.annotations.DaoTarget;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author ZWForum
 * @description 教学工作量表操作
 * @date 2022/6/29
 */
@DaoTarget(TeachingWorkloadStatistics.class)
@Repository
public class TeachingWorkloadStatisticsDao {
    @Autowired
    TeachingWorkloadStatisticsMapper teachingWorkloadStatisticsMapper;
    @Autowired
    TotalTableMapper totalTableMapper;

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
        TotalTable totalTable = new TotalTable();
        totalTable.setTeacherName(teachingWorkloadStatistics.getMainTeacherName());
        totalTable.setNaturalYear(teachingWorkloadStatistics.getNaturalYear());
        totalTable.setAcademicWorkId(teachingWorkloadStatistics.getId());
        totalTableMapper.insert(totalTable);
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
        QueryWrapper<TotalTable> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("natural_year", teachingWorkloadStatistics.getNaturalYear());
        queryWrapper.isNotNull("academic_work_id");
        totalTableMapper.delete(queryWrapper);
    }


    public IPage<TeachingWorkloadStatistics> selectByNaturalYearInPage(String naturalYear, int pageNumber, int pageSize) {
        /**
         * @param teachingWorkloadStatistics
         * @return IPage<TeachingWorkloadStatistics>
         * @author theLastNYF
         * @description 根据自然年分页查询
         * @date 2022/6/29
         */
        QueryWrapper<TeachingWorkloadStatistics> wrapper = new QueryWrapper<>();
        wrapper.eq("natural_year", naturalYear);
        Page<TeachingWorkloadStatistics> teachPage = new Page<>(pageNumber, pageSize);
        IPage<TeachingWorkloadStatistics> teachIPage = teachingWorkloadStatisticsMapper.selectPage(teachPage, wrapper);
        return teachIPage;
    }

    public IPage<TeachingWorkloadStatistics> selectByStudentGradeInPage(String naturalYear, String studentGrade, int pageNumber, int pageSize) {
        /**
         * @author 24047
         * @date 2022/6/29
         * @param [java.lang.String, int, int]
         * @return com.baomidou.mybatisplus.core.metadata.IPage<com.example.sprint2.mybatis.entity.TeachingWorkloadStatistics>
         */
        QueryWrapper<TeachingWorkloadStatistics> wrapper = new QueryWrapper<>();
        wrapper.eq("natural_year", naturalYear);
        wrapper.eq("student_grade", studentGrade);
        Page<TeachingWorkloadStatistics> teachPage = new Page<>(pageNumber, pageSize);
        IPage<TeachingWorkloadStatistics> teachIPage = teachingWorkloadStatisticsMapper.selectPage(teachPage, wrapper);
        return teachIPage;
    }


    public IPage<TeachingWorkloadStatistics> selectByTeacherNameInPage(String naturalYear, String teacherName, int pageNumber, int pageSize) {
        QueryWrapper<TeachingWorkloadStatistics> wrapper = new QueryWrapper<>();
        wrapper.eq("natural_year", naturalYear);
        wrapper.eq("main_teacher_name", teacherName);
        Page<TeachingWorkloadStatistics> teachPage = new Page<>(pageNumber, pageSize);
        IPage<TeachingWorkloadStatistics> teachIPage = teachingWorkloadStatisticsMapper.selectPage(teachPage, wrapper);
        return teachIPage;
    }

    public IPage<TeachingWorkloadStatistics> selectByCourseNameInPage(String naturalYear, String courseName, int pageNumber, int pageSize) {
        QueryWrapper<TeachingWorkloadStatistics> wrapper = new QueryWrapper<>();
        wrapper.eq("natural_year", naturalYear);
        wrapper.eq("course_name", courseName);
        Page<TeachingWorkloadStatistics> teachPage = new Page<>(pageNumber, pageSize);
        IPage<TeachingWorkloadStatistics> teachIPage = teachingWorkloadStatisticsMapper.selectPage(teachPage, wrapper);
        return teachIPage;
    }

    public IPage<TeachingWorkloadStatistics> selectByMajorInPage(String naturalYear, String major, int pageNumber, int pageSize) {
        QueryWrapper<TeachingWorkloadStatistics> wrapper = new QueryWrapper<>();
        wrapper.eq("natural_year", naturalYear);
        wrapper.eq("major", major);
        Page<TeachingWorkloadStatistics> teachPage = new Page<>(pageNumber, pageSize);
        IPage<TeachingWorkloadStatistics> teachIPage = teachingWorkloadStatisticsMapper.selectPage(teachPage, wrapper);
        return teachIPage;
    }
}
