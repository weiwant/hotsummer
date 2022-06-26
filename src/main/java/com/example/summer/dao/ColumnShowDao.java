package com.example.summer.dao;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.summer.entity.TeachingWorkloadStatistics;
import com.example.summer.mapper.TeachingWorkloadStatisticsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author:wwq
 * @Date：2022/6/25：9:29
 */
@Repository
public class ColumnShowDao {
    @Autowired
    TeachingWorkloadStatisticsMapper workloadStatisticsMapper;

    /**
     * @Author：wwq
     * @Return：
     * @Description：根据列名返回数据
     */
    public List<TeachingWorkloadStatistics> getWorkInColumn(List<String> names) {
        //把list转为String
        String st = "";
        for (int i = 0; i < names.size(); i++) {
            if (i == 0) {
                st += names.get(i);
            } else {
                st = st + "," + names.get(i);
            }

        }
        QueryWrapper<TeachingWorkloadStatistics> queryWrapper = new QueryWrapper<>();
        queryWrapper.select(st);
        List<TeachingWorkloadStatistics> workInColumn = workloadStatisticsMapper.selectList(queryWrapper);
        return workInColumn;

    }

}
