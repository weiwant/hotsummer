package com.example.sprint2.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.sprint2.models.vo.TeachingScoresVo;
import com.example.sprint2.mybatis.entity.TotalStatistics;

import java.util.List;

/**
 * @author hy
 * @Date 2022/7/7
 * @Description
 */
public interface TeachingScoresService {

    List<TotalStatistics> selectScores(TeachingScoresVo teachingScoresVo);

    IPage<TotalStatistics> insertScores(TeachingScoresVo teachingScoresVo);
}
