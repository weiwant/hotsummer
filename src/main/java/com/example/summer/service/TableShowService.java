package com.example.summer.service;

import com.example.summer.entity.Work;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author wwq
 * @Date 2022/6/23 16:11
 */

@Service
public interface TableShowService {
    List<Work> getAllTables();
    List<Work> getYearTable(String year);
    List<Work> getSemesterTable(String year,int semester);
}


