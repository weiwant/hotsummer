package com.example.summer.controller;

import com.example.summer.entity.TeachingWorkloadStatistics;
import com.example.summer.service.mybatis.impl.TeachingWorkloadStatisticsServiceImpl;
import com.example.summer.utils.ResponseCode;
import com.example.summer.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value ="teachingWorkloadSearch" )
public class TeachingWorkloadSearchController {
@Autowired
TeachingWorkloadStatisticsServiceImpl teacherService;
    @RequestMapping("/searchPerson")
    public String searchPerson(@RequestParam(value="name",required = false) String teacherName){
        List<TeachingWorkloadStatistics> teacherList=teacherService.searchTeachingWorkloadByName(teacherName);
//        System.out.println(new Result(ResponseCode.SUCCESS,teacherList));

        return new Result(ResponseCode.SUCCESS,teacherList).toString();

    }
    @RequestMapping("/searchPersonLike")
    public String searchPersonLike(@RequestParam(value="name",required = false) String teacherName){
        List<TeachingWorkloadStatistics> teacherList=teacherService.searchTeachingWorkloadByNameLike(teacherName);
//        System.out.println(new Result(ResponseCode.SUCCESS,teacherList));
        return new Result(ResponseCode.SUCCESS,teacherList).toString();
    }

}
