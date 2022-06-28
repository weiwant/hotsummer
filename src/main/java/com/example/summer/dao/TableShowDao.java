package com.example.summer.dao;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.summer.entity.TeachingWorkloadStatistics;
import com.example.summer.mapper.TeachingWorkloadStatisticsMapper;
import com.example.summer.models.vo.TableShowVo;
import com.example.summer.utils.ReflexUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author wwq
 * @Date 2022/6/23 14:38
 */
@Repository
public class TableShowDao {
    @Autowired
    TeachingWorkloadStatisticsMapper teacherMapper;

    /**
     * @Author： wwq
     * @Return：
     * @Description：获取数据库表所有内容
     */
    public List<TeachingWorkloadStatistics> getAllTables() {
        List<TeachingWorkloadStatistics> works = teacherMapper.selectList(null);
        return works;
    }

    /**
     * @Author： wwq
     * @Return：
     * @Description： 根据年份返回对应数据表。数据库表的year字段
     */
    public List<TeachingWorkloadStatistics> getYearTable(String year) {
        QueryWrapper<TeachingWorkloadStatistics> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("academic_year", year);
        List<TeachingWorkloadStatistics> works = teacherMapper.selectList(queryWrapper);
        return works;
    }

    /**
     * @Author：wwq
     * @Return：
     * @Url:
     * @Description：根据年份和学期返回数据。包括表头。
     */
    public IPage<TeachingWorkloadStatistics> getSemesterTable(TableShowVo tableShowVo) {
        QueryWrapper<TeachingWorkloadStatistics> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("academic_year", tableShowVo.getYear());
        queryWrapper.eq("semester", tableShowVo.getSemester());
        int currentPage=tableShowVo.getPageNumber();
        Page<TeachingWorkloadStatistics> teachPage=new Page<>(currentPage,40);
        IPage<TeachingWorkloadStatistics> teachIPage=teacherMapper.selectPage(teachPage,queryWrapper);
//        System.out.println("总页数"+teachIPage.getPages());
//        System.out.println("总记录数"+teachIPage.getTotal());
//        System.out.println("当前页"+currentPage);
        return teachIPage;
    }

    public Object[] getWorkTableHeader() {
        TeachingWorkloadStatistics t=new TeachingWorkloadStatistics();
        Object[] workTableHeader=ReflexUtil.readAllAttribute(t);
        return  workTableHeader;
    }

    public List<TeachingWorkloadStatistics> getDownLoadTables(TableShowVo tableShowVo) {
        QueryWrapper<TeachingWorkloadStatistics> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("academic_year",tableShowVo.getYear());
        queryWrapper.eq("semester",tableShowVo.getSemester());
        List<TeachingWorkloadStatistics> works = teacherMapper.selectList(queryWrapper);
        return works;
    }
}
