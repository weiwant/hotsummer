package com.example.sprint2.controller;

import com.example.sprint2.models.vo.WorkloadVo;
import com.example.sprint2.service.TotalWorkLoadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ZWForum
 * @description
 * @date 2022/7/2
 */
@RestController
@RequestMapping("/total")
public class TotalWorkloadController {
    @Autowired
    TotalWorkLoadService totalWorkLoadService;

    @RequestMapping(value = "/records", method = RequestMethod.POST)
    public String getTotalTable(WorkloadVo workloadVo) throws IllegalAccessException {
        return totalWorkLoadService.getCombineWorkLoad(workloadVo);
    }

    @RequestMapping(value = "/records/page", method = RequestMethod.POST)
    public String getTotalTable(WorkloadVo workloadVo, @RequestParam("page") Integer page) throws IllegalAccessException {
        return totalWorkLoadService.getCombineWorkLoadPage(workloadVo, page);
    }
}
