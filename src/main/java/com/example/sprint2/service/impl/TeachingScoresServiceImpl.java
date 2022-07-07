package com.example.sprint2.service.impl;

import com.example.sprint2.dao.TeachingScoresDao;
import com.example.sprint2.models.vo.TeachingScoresVo;
import com.example.sprint2.mybatis.entity.TotalStatistics;
import com.example.sprint2.service.TeachingScoresService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author hy
 * @Date 2022/7/7
 * @Description
 */
@Service
public class TeachingScoresServiceImpl implements TeachingScoresService {

    @Autowired
    TeachingScoresDao teachingScoresDao;

    /**
     * @author hy
     * @description 根据年份和教师姓名查询教分记录
     */
    @Override
    public List<TotalStatistics> selectScores(TeachingScoresVo teachingScoresVo){
        List<TotalStatistics> list=teachingScoresDao.selectScores(teachingScoresVo);
        return list;
    }

}
