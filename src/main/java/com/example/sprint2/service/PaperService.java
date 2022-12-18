package com.example.sprint2.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.sprint2.models.vo.PaperCoachingVo;
import com.example.sprint2.mybatis.entity.PaperCoachingWorkload;

import java.util.List;

/**
 * @author hy
 * @Date 2022/6/29
 * @Description
 */
public interface PaperService {
    public List<PaperCoachingWorkload> selectAll();

    public IPage<PaperCoachingWorkload> selectAllPage(PaperCoachingVo paperCoachingVo);

    public List<PaperCoachingWorkload> selectYear(PaperCoachingVo paperCoachingVo);

    public IPage<PaperCoachingWorkload> selectYearPage(PaperCoachingVo paperCoachingVo);

    public List<PaperCoachingWorkload> selectTeacher(PaperCoachingVo paperCoachingVo);

    public List<PaperCoachingWorkload> selectCourse(PaperCoachingVo paperCoachingVo);
    public int updateByID(PaperCoachingWorkload paperCoachingWorkload);
}
