package com.example.sprint2.models.vo;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author ZWForum
 * @description
 * @date 2022/7/3
 */
@Getter
@Setter
public class WorkloadPageVo {
    private long itemNum;
    private long pageNum;
    private List<WorkloadVo> records;
}
