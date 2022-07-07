package com.example.sprint2.controller;

import com.example.sprint2.models.enumerate.impl.ResponseCode;
import com.example.sprint2.models.vo.TeachingScoresVo;
import com.example.sprint2.mybatis.entity.TotalStatistics;
import com.example.sprint2.service.TeachingScoresService;
import com.example.sprint2.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author hy
 * @Date 2022/7/7
 * @Description
 */

@RestController
@RequestMapping("/scores")
public class TeachingScoresController {

    @Autowired
    TeachingScoresService service;



    /**
     * @author hy
     * @Receive  TeachingSoresVo
     * @Param String teacherName，String year
     * @description 查询教分总记录
     */
    @RequestMapping(value = "/select",method = RequestMethod.POST)
    public String selectScores(@RequestBody TeachingScoresVo teachingScoresVo){
        List<TotalStatistics> list = service.selectScores(teachingScoresVo);
        if (list.size() == 0) {
            return new Result(ResponseCode.NoContentFailure).toString();
        } else {
            return new Result(ResponseCode.SUCCESS, list).toString();
        }
    }

}
