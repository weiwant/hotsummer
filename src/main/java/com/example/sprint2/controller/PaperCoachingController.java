package com.example.sprint2.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.sprint2.models.enumerate.impl.ResponseCode;
import com.example.sprint2.models.vo.PaperCoachingVo;
import com.example.sprint2.mybatis.entity.PaperCoachingWorkload;
import com.example.sprint2.service.impl.PaperServiceImpl;
import com.example.sprint2.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author hy
 * @Date 2022/6/29
 * @Description
 */

@RestController
@RequestMapping("manager/paper")
public class PaperCoachingController {

    @Autowired
    PaperServiceImpl paperService;

    @RequestMapping(value = "/all-page", method = RequestMethod.POST)
    public String showAllByPage(@RequestBody PaperCoachingVo paperCoachingVo) {    //带分页的控制层方法，返回所有数据
        IPage<PaperCoachingWorkload> iPage = paperService.selectAllPage(paperCoachingVo);
        if (iPage.getTotal() == 0) {
            return new Result(ResponseCode.NoContentFailure).toString();
        } else {
            return new Result(ResponseCode.SUCCESS, iPage).toString();
        }
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public String showAll() {                                       //不带分页的控制层方法，返回所有数据
        List<PaperCoachingWorkload> list = paperService.selectAll();
        if (list.size() == 0) {
            return new Result(ResponseCode.NoContentFailure).toString();
        } else {
            return new Result(ResponseCode.SUCCESS, list).toString();
        }

    }

    @RequestMapping(value = "/year", method = RequestMethod.POST)  //不带分页，查询某自然年的数据
    public String showYear(@RequestBody PaperCoachingVo paperCoachingVo) {
        List<PaperCoachingWorkload> list = paperService.selectYear(paperCoachingVo);
        if (list.size() == 0) {
            return new Result(ResponseCode.NoContentFailure).toString();
        } else {
            return new Result(ResponseCode.SUCCESS, list).toString();
        }
    }

    @RequestMapping(value = "/year-page", method = RequestMethod.POST)   //带分页，查询某自然年的数据
    public String showYearByPage(@RequestBody PaperCoachingVo paperCoachingVo) {
        IPage<PaperCoachingWorkload> iPage = paperService.selectYearPage(paperCoachingVo);
        if (iPage.getTotal() == 0) {
            return new Result(ResponseCode.NoContentFailure).toString();
        } else {
            return new Result(ResponseCode.SUCCESS, iPage).toString();
        }
    }

    @RequestMapping(value = "/teacher", method = RequestMethod.POST)   //不分页，查询某教师 某自然年的记录
    public String showTeacher(@RequestBody PaperCoachingVo paperCoachingVo) {
        List<PaperCoachingWorkload> list = paperService.selectTeacher(paperCoachingVo);
        if (list.size() == 0) {
            return new Result(ResponseCode.NoContentFailure).toString();
        } else {
            return new Result(ResponseCode.SUCCESS, list).toString();
        }
    }

    @RequestMapping(value = "/course", method = RequestMethod.POST)    //不分页，查询某指定课程名称 某自然年的记录
    public String showCourse(@RequestBody PaperCoachingVo paperCoachingVo) {
        List<PaperCoachingWorkload> list = paperService.selectCourse(paperCoachingVo);
        if (list.size() == 0) {
            return new Result(ResponseCode.NoContentFailure).toString();
        } else {
            return new Result(ResponseCode.SUCCESS, list).toString();
        }
    }


}
