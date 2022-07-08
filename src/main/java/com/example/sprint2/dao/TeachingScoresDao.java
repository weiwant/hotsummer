package com.example.sprint2.dao;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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
     * @description 查询教分总记录
     */

    public List<TotalStatistics> selectScores(TeachingScoresVo teachingScoresVo) {
        QueryWrapper<TotalStatistics> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(teachingScoresVo.getTeacherName() != null, "teacher_name", teachingScoresVo.getTeacherName());
        queryWrapper.eq(teachingScoresVo.getYear() != null, "year", teachingScoresVo.getYear());
        List<TotalStatistics> list = mapper.selectList(queryWrapper);
        return list;
    }

    /**
     * @Author：wwq
     * @Return：
     * @Url:
     * @Description：将一条教师的教分记录插入数据库。当年的记录先删除，再插入。
     */
    public boolean insertScores(TotalStatistics total) {
        QueryWrapper<TotalStatistics> wrapper = new QueryWrapper<>();
        wrapper.eq("teacher_name", total.getTeacherName());
        wrapper.eq("year", total.getYear());
        try {
            if (mapper.selectList(wrapper).isEmpty()) {
                mapper.insert(total);
                return true;
            } else {
                mapper.update(total, wrapper);
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;


    }

    public IPage<TotalStatistics> select(TeachingScoresVo teachingScoresVo) {
        QueryWrapper<TotalStatistics> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("year", teachingScoresVo.getYear());
        queryWrapper.eq(teachingScoresVo.getTeacherName() != null, "teacher_name", teachingScoresVo.getTeacherName());
        Page<TotalStatistics> page = new Page<>(teachingScoresVo.getPageNumber(), 20);
        IPage<TotalStatistics> iPage = mapper.selectPage(page, queryWrapper);
        return iPage;
    }

}
