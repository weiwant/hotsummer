package com.example.summer.service.impl;

import com.example.summer.dao.ColumnShowDao;
import com.example.summer.entity.TeachingWorkloadStatistics;
import com.example.summer.service.ColumnShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author:wwq
 * @Date：2022/6/25：9:28
 */

/**
 * @Author：wwq
 * @Return：
 * @Description：根据列名返回对应内容
 */
@Service
public class ColumnShowServiceImpl implements ColumnShowService {
    @Autowired
    ColumnShowDao columnShowDao;

    public List<TeachingWorkloadStatistics> getColumn(List<String> names){

        List<TeachingWorkloadStatistics> someWork=columnShowDao.getWorkInColumn(names);
       return someWork;
    }
}
