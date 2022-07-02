package com.example.sprint2.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.sprint2.models.vo.SpecialWorkloadVo;
import com.example.sprint2.mybatis.entity.SpecialWorkload;

import java.util.List;

/**
 * @author hy
 * @Date 2022/7/1
 * @Description 特殊工作量查询
 */

public interface SpecialWorkloadTableService {

     IPage<SpecialWorkload> selectBB(SpecialWorkloadVo specialWorkloadVo);
     IPage<SpecialWorkload> selectDeclarantName(SpecialWorkloadVo specialWorkloadVo);
     IPage<SpecialWorkload> selectTeacherName(SpecialWorkloadVo specialWorkloadVo);
     IPage<SpecialWorkload> selectYear(SpecialWorkloadVo specialWorkloadVo);
     SpecialWorkload selectId(SpecialWorkloadVo specialWorkloadVo);
}
