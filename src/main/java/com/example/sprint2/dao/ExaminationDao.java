package com.example.sprint2.dao;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.sprint2.models.vo.ExaminationVo;
import com.example.sprint2.mybatis.entity.ExaminationWorkload;
import com.example.sprint2.mybatis.mapper.ExaminationWorkloadMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author:wwq
 * @Date：2022/6/29：16:12
 */
@Repository
public class ExaminationDao {
    @Autowired
    ExaminationWorkloadMapper examinationWorkloadMapper;

    /**
     * @Author：wwq
     * @Return：
     * @Url:/resource/riyear
     * @Description：获取自然年考务工作量。分页查询
     */
    public IPage<ExaminationWorkload> getPageYearWorkload(ExaminationVo exam) {
        QueryWrapper<ExaminationWorkload> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("natural_year", exam.getNaturalYear());//查询自然年
        Page<ExaminationWorkload> examPage = new Page<>(exam.getPageNumber(), 40);
        IPage<ExaminationWorkload> examYear = examinationWorkloadMapper.selectPage(examPage, queryWrapper);
        return examYear;
    }

    /**
     * @Author：wwq
     * @Return：
     * @Url:
     * @Description：自然年考务工作量。不分页
     */
    public List<ExaminationWorkload> getYearWorkload(ExaminationVo examinationVo) {
        QueryWrapper<ExaminationWorkload> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("natural_year", examinationVo.getNaturalYear());
        List<ExaminationWorkload> resultList = examinationWorkloadMapper.selectList(queryWrapper);
        return resultList;
    }

    /**
     * @Author：wwq
     * @Return：
     * @Url:
     * @Description：在自然年基础上，按教师名称查询。不分页
     */
    public List<ExaminationWorkload> getTeacherWorkload(ExaminationVo examinationVo) {
        QueryWrapper<ExaminationWorkload> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("natural_year", examinationVo.getYear());
        queryWrapper.eq("main_teacher_name", examinationVo.getTeacher());
        List<ExaminationWorkload> resultList = examinationWorkloadMapper.selectList(queryWrapper);
        return resultList;
    }

    /**
     * @Author：wwq
     * @Return：
     * @Url:
     * @Description：在自然年基础上，按课程性质（监考课程名称）不分页查询
     */
    public List<ExaminationWorkload> getCourseWorkload(ExaminationVo examinationVo) {
        QueryWrapper<ExaminationWorkload> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("natural_year", examinationVo.getNaturalYear());
        queryWrapper.eq("course_name", examinationVo.getCourseName());
        List<ExaminationWorkload> resultList = examinationWorkloadMapper.selectList(queryWrapper);
        return resultList;
    }
}
