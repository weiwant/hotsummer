package com.example.sprint2.dao;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.sprint2.models.vo.SpecialWorkloadVo;
import com.example.sprint2.mybatis.entity.SpecialWorkload;
import com.example.sprint2.mybatis.mapper.SpecialWorkloadMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author hy
 * @Date 2022/7/1
 * @Description
 */

@Repository
public class SpecialWorkloadDao {

    @Autowired
    SpecialWorkloadMapper specialWorkloadMapper;

    /**
     * @author hy
     * @description 根据BB类型查询  分页
     */
    public IPage<SpecialWorkload> selectBB(SpecialWorkloadVo specialWorkloadVo) {
        int currentPage = specialWorkloadVo.getPagenumber();
        QueryWrapper<SpecialWorkload> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("type", specialWorkloadVo.getType());
        String s = specialWorkloadVo.getType();
        queryWrapper.eq("type", s);
        Page<SpecialWorkload> page = new Page<>(currentPage, 5);
        IPage<SpecialWorkload> iPage = specialWorkloadMapper.selectPage(page, queryWrapper);
        return iPage;
    }

    /**
     * @author hy
     * @description 根据申报人姓名查询  分页
     */
    public IPage<SpecialWorkload> selectDeclarantName(SpecialWorkloadVo specialWorkloadVo) {
        int currentPage = specialWorkloadVo.getPagenumber();
        QueryWrapper<SpecialWorkload> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("declarant_name", specialWorkloadVo.getDeclarantName());
        Page<SpecialWorkload> page = new Page<>(currentPage, 5);
        IPage<SpecialWorkload> iPage = specialWorkloadMapper.selectPage(page, queryWrapper);
        return iPage;
    }

    /**
     * @author hy
     * @description 根据教师姓名查询  分页
     */
    public IPage<SpecialWorkload> selectTeacherName(SpecialWorkloadVo specialWorkloadVo) {
        int currentPage = specialWorkloadVo.getPagenumber();
        QueryWrapper<SpecialWorkload> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("teacher_name", specialWorkloadVo.getTeacherName());
        Page<SpecialWorkload> page = new Page<>(currentPage, 5);
        IPage<SpecialWorkload> iPage = specialWorkloadMapper.selectPage(page, queryWrapper);
        return iPage;
    }

    /**
     * @author hy
     * @description 根据主键查询
     */
    public SpecialWorkload selectById(SpecialWorkloadVo specialWorkloadVo) {
        SpecialWorkload specialWorkload = specialWorkloadMapper.selectById(specialWorkloadVo.getId());
        return specialWorkload;
    }

    /**
     * @author hy
     * @description 按照年份查询
     */
    public IPage<SpecialWorkload> selectByYear(SpecialWorkloadVo specialWorkloadVo) {
        int currentPage = specialWorkloadVo.getPagenumber();
        QueryWrapper<SpecialWorkload> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("report_time", specialWorkloadVo.getYear());
        Page<SpecialWorkload> page = new Page<>(currentPage, 5);
        IPage<SpecialWorkload> iPage = specialWorkloadMapper.selectPage(page, queryWrapper);
        return iPage;

    }
}
