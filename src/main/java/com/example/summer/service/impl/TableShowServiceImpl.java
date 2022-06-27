package com.example.summer.service.impl;

import com.example.summer.dao.TableShowDao;
import com.example.summer.entity.TeachingWorkloadStatistics;
import com.example.summer.models.vo.TableShowVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author wwq
 * @Date 2022/6/23 14:38
 */
@Service
public class TableShowServiceImpl {
    @Autowired
    TableShowDao tableShowDao;

    /**
     * @Author： wwq
     * @Return：
     * @Description：获取数据库表所有内容
     */
    public List<TeachingWorkloadStatistics> getAllTables() {
        List<TeachingWorkloadStatistics> WorkList = tableShowDao.getAllTables();
        return WorkList;
    }

    /**
     * @Author： wwq
     * @Return：
     * @Description： 根据年份返回对应数据表。数据库表的year字段
     */
    public List<TeachingWorkloadStatistics> getYearTable(String year) {
        List<TeachingWorkloadStatistics> WorkInYear = tableShowDao.getYearTable(year);
        return WorkInYear;
    }

    /**
     * @Author：wwq
     * @Return：
     * @Description：根据年份和学期返回对应信息
     */
    public List<TeachingWorkloadStatistics> getSemesterTable(TableShowVo tableShowVo) {
        List<TeachingWorkloadStatistics> workInSemester = tableShowDao.getSemesterTable(tableShowVo);
        return workInSemester;
    }

    /**
     * @Author：wwq
     * @Return：
     * @Url:
     * @Description：获取表头返回数组
     */
    public Object[] getTableHeader() {
        Object[] workTableHeader=tableShowDao.getWorkTableHeader();
        return  workTableHeader;
    }
}
