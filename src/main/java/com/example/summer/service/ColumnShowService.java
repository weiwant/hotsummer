package com.example.summer.service;

import com.example.summer.models.pojo.TeachingWorkloadRecord;
import com.example.summer.models.pojo.WorkloadData;
import com.example.summer.models.vo.TableShowDetailVo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author:wwq
 * @Date：2022/6/25：9:28
 */
@Service
public interface ColumnShowService {
    List<String> getColumn(List<String> names);

    List<WorkloadData[]> getColumnCustom(List<String> names, List<String> ChineseNames, String teacherName);

    List<WorkloadData[]> getColumnCustomByYear(List<String> names, List<String> chineseNamesList, TableShowDetailVo tableShowVo);

    List<TeachingWorkloadRecord> getColumnIndeed(List<String> names, List<String> ChineseNames, TableShowDetailVo tableShowDetailVo);

}
