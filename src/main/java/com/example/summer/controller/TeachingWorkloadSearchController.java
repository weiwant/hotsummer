package com.example.summer.controller;

import com.example.summer.entity.TeachingWorkloadStatistics;
import com.example.summer.models.pojo.ResponseCode;
import com.example.summer.models.vo.SearchPersonVo;
import com.example.summer.models.vo.TableShowDetailVo;
import com.example.summer.service.TeachingWorkloadService;
import com.example.summer.service.impl.TeachingWorkloadServiceImpl;
import com.example.summer.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/search")
public class TeachingWorkloadSearchController {
    //    主要是按名查询的Controller
    @Autowired
    TeachingWorkloadService teacherService;

    /**
     * @Author：theLastNYF
     * @Date: 2022/06/24
     * @Param: teacherName
     * @Return:
     * @Description:按名查询
     */
    @RequestMapping(value = "/searchPerson",method = RequestMethod.POST)                 //可以重用到全部查询部分
    public String searchPerson(@RequestBody(required = false) SearchPersonVo searchPersonVo) {
        List<TeachingWorkloadStatistics> teacherList = teacherService.searchTeachingWorkloadByName(searchPersonVo.getName());
//        System.out.println(new Result(ResponseCode.SUCCESS,teacherList));
        if (teacherList == null || teacherList.isEmpty()) {
            return new Result(ResponseCode.NoContentFailure, null).toString();
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
    @RequestMapping(value = "/searchPersonLike",method = RequestMethod.POST)
    public String searchPersonLike(@RequestBody(required = false) SearchPersonVo searchPersonVo) {
        List<TeachingWorkloadStatistics> teacherList = teacherService.searchTeachingWorkloadByNameLike(searchPersonVo.getName());
//        System.out.println(new Result(ResponseCode.SUCCESS,teacherList));
        if (teacherList == null || teacherList.isEmpty()) {
            return new Result(ResponseCode.NoContentFailure, null).toString();
        }
        return new Result(ResponseCode.SUCCESS, teacherList).toString();
    }

    /**
     * @Author：theLastNYF
     * @Date: 2022/06/24
     * @Param: teacherName
     * @Return:
     * @Description:按年度范围、学期范围和姓名查询
     */
    @RequestMapping(value = "/searchRecordInScope",method = RequestMethod.POST)
    public String searchRecordInScope(@RequestBody SearchPersonVo searchPersonVo ) {
        List<TeachingWorkloadStatistics> teacherList = teacherService.searchTeachingWorkloadInScope(searchPersonVo.getStartYear(), searchPersonVo.getEndYear(), searchPersonVo.getName());
        if (teacherList == null || teacherList.isEmpty()) {
            return new Result(ResponseCode.NoContentFailure, null).toString();
        }
        return new Result(ResponseCode.SUCCESS, teacherList).toString();
    }

    @RequestMapping(value = "/searchIndeed",method = RequestMethod.POST)
    public String searchRecordIndeed(@RequestBody TableShowDetailVo searchPersonVo ) {
        List<TeachingWorkloadStatistics> teacherList = teacherService.searchIndeed(searchPersonVo.getYear(), searchPersonVo.getSemester(), searchPersonVo.getTeacherName());
        if (teacherList == null || teacherList.isEmpty()) {
            return new Result(ResponseCode.NoContentFailure, null).toString();
        }
        return new Result(ResponseCode.SUCCESS, teacherList).toString();
    }
}
