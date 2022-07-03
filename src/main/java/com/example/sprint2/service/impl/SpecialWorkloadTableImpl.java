package com.example.sprint2.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.sprint2.dao.SpecialWorkloadDao;
import com.example.sprint2.models.vo.SpecialWorkloadVo;
import com.example.sprint2.mybatis.entity.SpecialWorkload;
import com.example.sprint2.service.SpecialWorkloadTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author hy
 * @Date 2022/7/1
 * @Description
 */
@Service
public class SpecialWorkloadTableImpl implements SpecialWorkloadTableService {

    @Autowired
    SpecialWorkloadDao specialWorkloadDao;

    /**
     * @author hy
     * @description 根据BB类型和自然年份查询  分页
     */
    @Override
    public IPage<SpecialWorkload> selectBB(SpecialWorkloadVo specialWorkloadVo) {
        IPage<SpecialWorkload> iPage = specialWorkloadDao.selectBB(specialWorkloadVo);
        return iPage;
    }

    /**
     * @author hy
     * @description 根据申报人姓名查询  分页
     */
    @Override
    public IPage<SpecialWorkload> selectDeclarantName(SpecialWorkloadVo specialWorkloadVo) {
        IPage<SpecialWorkload> iPage = specialWorkloadDao.selectDeclarantName(specialWorkloadVo);
        return iPage;
    }

    /**
     * @author hy
     * @description 根据教师姓名查询  分页
     */
    @Override
    public IPage<SpecialWorkload> selectTeacherName(SpecialWorkloadVo specialWorkloadVo) {
        IPage<SpecialWorkload> iPage = specialWorkloadDao.selectTeacherName(specialWorkloadVo);
        return iPage;
    }

    @Override
    public IPage<SpecialWorkload> selectYear(SpecialWorkloadVo specialWorkloadVo) {
        IPage<SpecialWorkload> iPage = specialWorkloadDao.selectByYear(specialWorkloadVo);
        return iPage;
    }

    @Override
    public SpecialWorkload selectId(SpecialWorkloadVo specialWorkloadVo) {
        SpecialWorkload specialWorkload = specialWorkloadDao.selectById(specialWorkloadVo);
        return specialWorkload;
    }
}
