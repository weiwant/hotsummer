package com.example.sprint2.dao;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.sprint2.models.vo.PaperCoachingVo;
import com.example.sprint2.mybatis.entity.PaperCoachingWorkload;
import com.example.sprint2.mybatis.entity.TotalTable;
import com.example.sprint2.mybatis.mapper.PaperCoachingWorkloadMapper;
import com.example.sprint2.mybatis.mapper.TotalTableMapper;
import com.example.sprint2.utils.annotations.DaoTarget;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author hy
 * @Date 2022/6/29
 * @Description
 */
@DaoTarget(PaperCoachingWorkload.class)
@Repository
public class PaperCoachingDao {
    @Autowired
    PaperCoachingWorkloadMapper mapper;
    @Autowired
    TotalTableMapper totalTableMapper;


    /**
     * @author hy
     * @description 不分页，查看某自然年的所有记录。
     */
    public List<PaperCoachingWorkload> selectYear(PaperCoachingVo paperCoachingVo) {
        QueryWrapper<PaperCoachingWorkload> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("natural_year", paperCoachingVo.getNatural_year());
        List<PaperCoachingWorkload> list = mapper.selectList(queryWrapper);
        return list;
    }

    /**
     * @author hy
     * @description 分页，查询某自然年的所有记录。
     */
    public IPage<PaperCoachingWorkload> selectYearPage(PaperCoachingVo paperCoachingVo) {
        int currentPage = paperCoachingVo.getPagenumber();
        QueryWrapper<PaperCoachingWorkload> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("natural_year", paperCoachingVo.getNatural_year());
        Page<PaperCoachingWorkload> page = new Page<>(currentPage, 2);
        IPage<PaperCoachingWorkload> iPage = mapper.selectPage(page, queryWrapper);
        return iPage;
    }

    /**
     * @author hy
     * @description 不分页，查询指定教师 某自然年的论文指导记录
     */
    public List<PaperCoachingWorkload> selectTeacher(PaperCoachingVo paperCoachingVo) {
        QueryWrapper<PaperCoachingWorkload> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("main_teacher_name", paperCoachingVo.getTeacherName());
        queryWrapper.eq("natural_year", paperCoachingVo.getNatural_year());
        List<PaperCoachingWorkload> list = mapper.selectList(queryWrapper);
        return list;
    }

    /**
     * @author hy
     * @description 不分页，查询指定课程名称 某自然年的论文指导记录
     */
    public List<PaperCoachingWorkload> selectCourse(PaperCoachingVo paperCoachingVo) {
        QueryWrapper<PaperCoachingWorkload> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("course_name", paperCoachingVo.getCourseName());
        queryWrapper.eq("natural_year", paperCoachingVo.getNatural_year());
        List<PaperCoachingWorkload> list = mapper.selectList(queryWrapper);
        return list;
    }

    /**
     * @author hy
     * @description 不分页 论文指导工作量表 查询所有记录。
     * @date 2022/6/29
     */

    public List<PaperCoachingWorkload> selectAll() {
        List<PaperCoachingWorkload> list = mapper.selectList(null);
        return list;
    }

    /**
     * @author hy
     * @description 带分页  论文指导工作量表  查询所有记录。
     */

    public IPage<PaperCoachingWorkload> selectAllPage(PaperCoachingVo paperCoachingVo) {
        int currentPage = paperCoachingVo.getPagenumber();
        Page<PaperCoachingWorkload> page = new Page<>(currentPage, 2);
        IPage<PaperCoachingWorkload> iPage = mapper.selectPage(page, null);
        return iPage;
    }

    public void insertEntity(PaperCoachingWorkload paperCoachingWorkload) {
        mapper.insert(paperCoachingWorkload);
        TotalTable totalTable = new TotalTable();
        totalTable.setTeacherName(paperCoachingWorkload.getMainTeacherName());
        totalTable.setNaturalYear(paperCoachingWorkload.getNuturalYear());
        totalTable.setPaperWorkId(paperCoachingWorkload.getId());
        totalTableMapper.insert(totalTable);
    }

    public void deleteByNaturalYear(PaperCoachingWorkload paperCoachingWorkload) {
        QueryWrapper<PaperCoachingWorkload> wrapper = new QueryWrapper<>();
        wrapper.eq("nutural_year", paperCoachingWorkload.getNuturalYear());
        mapper.delete(wrapper);
        QueryWrapper<TotalTable> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("natural_year", paperCoachingWorkload.getNuturalYear());
        queryWrapper.isNotNull("paper_work_id");
        totalTableMapper.delete(queryWrapper);
    }

}
