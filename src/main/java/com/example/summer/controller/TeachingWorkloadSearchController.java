package com.example.summer.controller;

import com.example.summer.entity.TeachingWorkloadStatistics;
import com.example.summer.models.pojo.ResponseCode;
import com.example.summer.service.mybatis.impl.TeachingWorkloadStatisticsServiceImpl;
import com.example.summer.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "teachingWorkloadSearch")
public class TeachingWorkloadSearchController {
    @Autowired
    TeachingWorkloadStatisticsServiceImpl teacherService;

    /**
     * @Author：theLastNYF
     * @Date: 2022/06/24
     * @Param: teacherName
     * @Return:
     * @Description:按名查询
     */
    @RequestMapping("/searchPerson")
    public String searchPerson(@RequestParam(value = "name", required = false) String teacherName) {
        List<TeachingWorkloadStatistics> teacherList = teacherService.searchTeachingWorkloadByName(teacherName);
//        System.out.println(new Result(ResponseCode.SUCCESS,teacherList));
        if(teacherList==null||teacherList.isEmpty()){
            return new Result(ResponseCode.NoContent, null).toString();
        }
        return new Result(ResponseCode.SUCCESS, teacherList).toString();

    }

    /**
     * @Author：theLastNYF
     * @Date: 2022/06/24
     * @Param: teacherName
     * @Return:
     * @Description:按名模糊查询
     */
    @RequestMapping("/searchPersonLike")
    public String searchPersonLike(@RequestParam(value = "name", required = false) String teacherName) {
        List<TeachingWorkloadStatistics> teacherList = teacherService.searchTeachingWorkloadByNameLike(teacherName);
//        System.out.println(new Result(ResponseCode.SUCCESS,teacherList));
        if(teacherList==null||teacherList.isEmpty()){
            return new Result(ResponseCode.NoContent, null).toString();
        }
        return new Result(ResponseCode.SUCCESS, teacherList).toString();
    }

    /**
     * @Author：theLastNYF
     * @Date: 2022/06/24
     * @Param: teacherName
     * @Return:
     * @Description:按年度范围、学期范围和模糊姓名查询
     */
    @RequestMapping("/searchRecordInScope")
    public String searchRecordInScope(@RequestParam(value="startYear",required = false) String startYear,
                                        @RequestParam(value = "endYear",required = false) String endYear,
                                        @RequestParam(value = "name",required = false) String teacherName)
    {
        List<TeachingWorkloadStatistics> teacherList = teacherService.searchTeachingWorkloadInScope(startYear,endYear,teacherName);
        if(teacherList==null||teacherList.isEmpty()){
            return new Result(ResponseCode.NoContent, null).toString();
        }
        return new Result(ResponseCode.SUCCESS, teacherList).toString();
    }
}
