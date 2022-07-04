package com.example.sprint2.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.sprint2.models.enumerate.impl.ResponseCode;
import com.example.sprint2.models.vo.SpecialWorkloadVo;
import com.example.sprint2.mybatis.entity.SpecialWorkload;
import com.example.sprint2.service.SpecialWorkloadTableService;
import com.example.sprint2.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author hy
 * @Date 2022/7/1
 * @Description 特殊工作量控制器类
 */

@RestController
@RequestMapping("/special")
public class SpecialWorkloadController {
    @Autowired
    SpecialWorkloadTableService service;

    @RequestMapping(value = "/bb", method = RequestMethod.POST)         //分页  按照BB类别和年份查询
    public String selectBB(@RequestBody SpecialWorkloadVo specialWorkloadVo) {
        IPage<SpecialWorkload> iPage = service.selectBB(specialWorkloadVo);
        if (iPage.getTotal() == 0) {
            return new Result(ResponseCode.NoContentFailure).toString();
        } else {
            return new Result(ResponseCode.SUCCESS, iPage).toString();
        }
    }

    @RequestMapping(value = "/declarant", method = RequestMethod.POST)      //分页  申报人姓名查询
    public String selectDeclarantName(@RequestBody SpecialWorkloadVo specialWorkloadVo) {
        IPage<SpecialWorkload> iPage = service.selectDeclarantName(specialWorkloadVo);
        if (iPage.getTotal() == 0) {
            return new Result(ResponseCode.NoContentFailure).toString();
        } else {
            return new Result(ResponseCode.SUCCESS, iPage).toString();
        }
    }

    @RequestMapping(value = "/teacher", method = RequestMethod.POST)     //分页  教师姓名查询
    public String selectTeacherName(@RequestBody SpecialWorkloadVo specialWorkloadVo) {
        IPage<SpecialWorkload> iPage = service.selectTeacherName(specialWorkloadVo);
        if (iPage.getTotal() == 0) {
            return new Result(ResponseCode.NoContentFailure).toString();
        } else {
            return new Result(ResponseCode.SUCCESS, iPage).toString();
        }
    }

    @RequestMapping(value = "/year", method = RequestMethod.POST)      //分页 按年份查询
    public String selectYear(@RequestBody SpecialWorkloadVo specialWorkloadVo) {
        IPage<SpecialWorkload> iPage = service.selectYear(specialWorkloadVo);
        if (iPage.getTotal() == 0) {
            return new Result(ResponseCode.NoContentFailure).toString();
        } else {
            return new Result(ResponseCode.SUCCESS, iPage).toString();
        }
    }

    @RequestMapping(value = "/id", method = RequestMethod.POST)       //按主键查询
    public String selectId(@RequestBody SpecialWorkloadVo specialWorkloadVo) {
        SpecialWorkload specialWorkload = service.selectId(specialWorkloadVo);
        if (specialWorkload == null) {
            return new Result(ResponseCode.NoContentFailure).toString();
        } else {
            return new Result(ResponseCode.SUCCESS, specialWorkload).toString();
        }
    }

    @RequestMapping(value = "/conditions",method = RequestMethod.POST)
    public String selectByConditions(@RequestBody SpecialWorkloadVo specialWorkloadVo){
        IPage<SpecialWorkload> iPage =service.selectByConditions(specialWorkloadVo);
        if (iPage.getTotal() == 0) {
            return new Result(ResponseCode.NoContentFailure).toString();
        } else {
            return new Result(ResponseCode.SUCCESS, iPage).toString();
        }
    }
}
