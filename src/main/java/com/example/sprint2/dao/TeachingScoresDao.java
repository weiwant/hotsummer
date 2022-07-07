package com.example.sprint2.dao;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.sprint2.models.vo.TeachingScoresVo;
import com.example.sprint2.mybatis.entity.TotalStatistics;
import com.example.sprint2.mybatis.mapper.TotalStatisticsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author hy
 * @Date 2022/7/7
 * @Description
 */
@Repository
public class TeachingScoresDao {

    @Autowired
    TotalStatisticsMapper mapper;








    /**
     * @author hy
     * @Receive TeachingSoresVo
     * @Param String teacherName，String year
     * @description  查询教分总记录
     */

    public List<TotalStatistics> selectScores(TeachingScoresVo teachingScoresVo){
        QueryWrapper<TotalStatistics> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(teachingScoresVo.getTeacherName()!=null,"teacher_name", teachingScoresVo.getTeacherName());
        queryWrapper.eq(teachingScoresVo.getYear()!=null,"year", teachingScoresVo.getYear());
        List<TotalStatistics> list= mapper.selectList(queryWrapper);
        return list;
    }


}
