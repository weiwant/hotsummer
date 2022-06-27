package com.example.summer.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.summer.entity.TeachingWorkloadStatistics;
import com.example.summer.models.vo.TableShowVo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author wwq
 * @Date 2022/6/23 16:11
 */

@Service
public interface TableShowService {
    List<TeachingWorkloadStatistics> getAllTables();

    List<TeachingWorkloadStatistics> getYearTable(String year);

    IPage<TeachingWorkloadStatistics> getSemesterTable(TableShowVo tableShowVo);

    Object[] getTableHeader();
}


