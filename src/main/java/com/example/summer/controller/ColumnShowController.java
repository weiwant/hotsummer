package com.example.summer.controller;

import com.example.summer.models.pojo.ResponseCode;
import com.example.summer.models.pojo.TeachingWorkloadFormat;
import com.example.summer.models.pojo.TeachingWorkloadRecord;
import com.example.summer.models.pojo.WorkloadData;
import com.example.summer.models.vo.TableShowDetailVo;
import com.example.summer.service.ColumnShowService;
import com.example.summer.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author:wwq
 * @Date：2022/6/25：9:18 专门
 */
@RestController
@RequestMapping("/resource")
public class ColumnShowController {
    @Autowired
    ColumnShowService columnShowService;

    /**
     * @Author：wwq
     * @Return：
     * @Description：根据表头名称获取对应表内容
     */
    @RequestMapping(value = "/Column", method = RequestMethod.GET)
    public String getColumn(@RequestParam(value = "columnname") List<String> names) {
        List<String> teacherSee = columnShowService.getColumn(names);
        if (teacherSee.size() == 0) {
            return new Result(ResponseCode.NoContentFailure).toString();
        } else {
            return new Result(ResponseCode.SUCCESS, teacherSee).toString();
        }
    }

    /**
     * @param
     * @return java.lang.String
     * @author 24047
     * @date 2022/6/27
     */
    @RequestMapping(value = "/custom", method = RequestMethod.POST)
    public String getColumnCustom(@RequestParam(value = "teacherName") String teacherName) {
        //在这里固定了教师用户能查看列的数据
        TeachingWorkloadFormat teachingWorkloadFormat = new TeachingWorkloadFormat();
        List<String> names = teachingWorkloadFormat.getEnglishName();
        List<String> chineseNamesList = teachingWorkloadFormat.getChineseName();
        //在数据库中查询names中每个值代表的字段的数值，并且返回这名老师的对应列的记录
        List<WorkloadData[]> teacherSee = columnShowService.getColumnCustom(names, chineseNamesList, teacherName);
        if (teacherSee.size() == 0) {
            return new Result(ResponseCode.NoContentFailure).toString();
        } else {
            return new Result(ResponseCode.SUCCESS, teacherSee).toString();
        }
    }

    /**
     * @return java.lang.String
     * @author 24047
     * @date 2022/6/28
     * @Description 返回所有字段的值，不过是以ChineseName和value作为返回结果的，现在不用了
     */
    @RequestMapping(value = "/customByYear", method = RequestMethod.POST)
    public String getColumnCustomByYear(@RequestBody TableShowDetailVo tableShowVo) {
        //在这里固定了教师用户能查看列的数据
        TeachingWorkloadFormat teachingWorkloadFormat = new TeachingWorkloadFormat();
        List<String> names = teachingWorkloadFormat.getEnglishName();
        List<String> chineseNamesList = teachingWorkloadFormat.getChineseName();
        //在数据库中查询names中每个值代表的字段的数值，并且返回这名老师的对应列的记录
        List<WorkloadData[]> teacherSee = columnShowService.getColumnCustomByYear(names, chineseNamesList, tableShowVo);
        if (teacherSee.size() == 0) {
            return new Result(ResponseCode.NoContentFailure).toString();
        } else {
            return new Result(ResponseCode.SUCCESS, teacherSee).toString();
        }
    }

    /**
     * @return java.lang.String
     * @author 24047
     * @date 2022/6/28
     * @Description 最终版本的查询
     */
    @RequestMapping(value = "/customIndeed", method = RequestMethod.POST)
    public String getColumnIndeed(@RequestBody TableShowDetailVo tableShowVo) {
        //在这里固定了教师用户能查看列的数据
        TeachingWorkloadFormat teachingWorkloadFormat = new TeachingWorkloadFormat();
        List<String> names = teachingWorkloadFormat.getEnglishName();
        List<String> chineseNamesList = teachingWorkloadFormat.getChineseName();
        //在数据库中查询names中每个值代表的字段的数值，并且返回这名老师的对应列的记录
        List<TeachingWorkloadRecord> teacherSee = columnShowService.getColumnIndeed(names, chineseNamesList, tableShowVo);
        if (teacherSee.size() == 0) {
            return new Result(ResponseCode.NoContentFailure).toString();
        } else {
            return new Result(ResponseCode.SUCCESS, teacherSee).toString();
        }
    }
}