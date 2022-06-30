package com.example.sprint2.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.sprint2.models.enumerate.impl.ResponseCode;
import com.example.sprint2.models.vo.ExaminationBackVo;
import com.example.sprint2.models.vo.ExaminationVo;
import com.example.sprint2.mybatis.entity.ExaminationWorkload;
import com.example.sprint2.service.ExaminationService;
import com.example.sprint2.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author:wwq
 * @Date：2022/6/29：16:12
 */
@RestController
@RequestMapping("/examination")
public class ExaminationController {
    @Autowired
    ExaminationService examinationService;

    /**
     * @Author：wwq
     * @Return：
     * @Url:/examination/yearpage
     * @Description：获取年份考务工作量。分页查询
     */
    @RequestMapping(value = "/yearpage",method = RequestMethod.POST)
    public String getPageYearWorkload(@RequestBody ExaminationVo exam){
        IPage<ExaminationWorkload> examinationIPage= examinationService.getPageYearWorkload(exam);
        ExaminationVo examinationVo=new ExaminationVo();
        examinationVo.setExampage(examinationIPage);
        if(examinationIPage.getTotal()==0){
            return new Result(ResponseCode.NoContentFailure).toString();
        }else{
            return new Result(ResponseCode.SUCCESS,examinationIPage).toString();
        }
    }

    /**
     * @Author：wwq
     * @Return：
     * @Url:examination/year
     * @Description：管理员自然年不分页查询
     */
    @RequestMapping(value = "year",method = RequestMethod.POST)
    public String getYearWorkload(@RequestBody ExaminationVo examinationVo){
        List<ExaminationWorkload> examinationWorkloadList=examinationService.getYearWorkload(examinationVo);
        if(examinationWorkloadList.size()==0){
            return new Result(ResponseCode.NoContentFailure).toString();
        }else{
            return new Result(ResponseCode.SUCCESS,examinationWorkloadList).toString();
        }
    }

    /**
     * @Author：wwq
     * @Return：
     * @Url:examination/teacher
     * @Description：管理员/教师不分页查询上课教师
     */
    @RequestMapping(value = "teacher",method = RequestMethod.POST)
    public String getTeacherWorkload(@RequestBody ExaminationVo examinationVo){
        List<ExaminationWorkload> examinationWorkloadList=examinationService.getTeacherWorkload(examinationVo);
        if(examinationWorkloadList.size()==0){
            return new Result(ResponseCode.NoContentFailure).toString();
        }else{
            return new Result(ResponseCode.SUCCESS,examinationWorkloadList).toString();
        }
    }

    /**
     * @Author：wwq
     * @Return：
     * @Url:examination/course
     * @Description：管理员不分页查询课程名称（监考课程名）
     */
    @RequestMapping(value = "course",method = RequestMethod.POST)
    public String getCourseWorkload(@RequestBody ExaminationVo examinationVo){
        List<ExaminationWorkload> examinationWorkloadList=examinationService.getCourseWorkload(examinationVo);
        if(examinationWorkloadList.size()==0){
            return new Result(ResponseCode.NoContentFailure).toString();
        }else{
            return new Result(ResponseCode.SUCCESS,examinationWorkloadList).toString();
        }
    }

    // TODO: 2022/6/30 double 转化为 int

}
