package com.example.sprint2.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.sprint2.dao.PaperCoachingDao;
import com.example.sprint2.models.vo.PaperCoachingVo;
import com.example.sprint2.mybatis.entity.PaperCoachingWorkload;
import com.example.sprint2.service.PaperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author hy
 * @Date 2022/6/29
 * @Description
 */
@Service
public class PaperServiceImpl implements PaperService {
    @Autowired
    PaperCoachingDao paperCoachingDao;

    /**
     * @author hy
     * @description 不带分页的查询所有
     */
    @Override
    public List<PaperCoachingWorkload> selectAll() {
        List<PaperCoachingWorkload> list = paperCoachingDao.selectAll();
        return list;
    }

    /**
     * @author hy
     * @description 带分页的查询所有
     */
    @Override
    public IPage<PaperCoachingWorkload> selectAllPage(PaperCoachingVo paperCoachingVo) {
        IPage<PaperCoachingWorkload> iPage = paperCoachingDao.selectAllPage(paperCoachingVo);
        return iPage;
    }

    /**
     * @author hy
     * @description 不带分页查询所有某自然年记录
     */
    @Override
    public List<PaperCoachingWorkload> selectYear(PaperCoachingVo paperCoachingVo) {
        List<PaperCoachingWorkload> list = paperCoachingDao.selectYear(paperCoachingVo);
        return list;
    }

    /**
     * @author hy
     * @description 带分页的查询所有某自然年记录
     */
    @Override
    public IPage<PaperCoachingWorkload> selectYearPage(PaperCoachingVo paperCoachingVo) {
        IPage<PaperCoachingWorkload> iPage = paperCoachingDao.selectYearPage(paperCoachingVo);
        return iPage;
    }

    /**
     * @author hy
     * @description 不分页，按教师名称和自然年查询记录
     */
    @Override
    public List<PaperCoachingWorkload> selectTeacher(PaperCoachingVo paperCoachingVo) {
        List<PaperCoachingWorkload> list = paperCoachingDao.selectTeacher(paperCoachingVo);
        return list;
    }

    /**
     * @author hy
     * @description 不分页，按课程名称和自然年查询记录
     */
    @Override
    public List<PaperCoachingWorkload> selectCourse(PaperCoachingVo paperCoachingVo) {
        List<PaperCoachingWorkload> list = paperCoachingDao.selectCourse(paperCoachingVo);
        return list;
    }

    @Override
    public int updateByID(PaperCoachingWorkload paperCoachingWorkload) {
        return paperCoachingDao.updateByID(paperCoachingWorkload);
    }
}
