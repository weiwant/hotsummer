package com.example.sprint2.service;

import com.example.sprint2.models.vo.WorkloadVo;

/**
 * @author ZWForum
 * @description
 * @date 2022/6/30
 */
public interface TotalWorkLoadService {
    String getCombineWorkLoad(WorkloadVo workloadVo) throws IllegalAccessException;

    String getCombineWorkLoadPage(WorkloadVo workloadVo, Integer page) throws IllegalAccessException;
}
