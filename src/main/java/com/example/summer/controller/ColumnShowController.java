package com.example.summer.controller;

import com.example.summer.models.pojo.ResponseCode;
import com.example.summer.models.pojo.TeachingWorkloadFormat;
import com.example.summer.models.pojo.WorkloadData;
import com.example.summer.service.ColumnShowService;
import com.example.summer.service.impl.ColumnShowServiceImpl;
import com.example.summer.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author:wwq
 * @Date：2022/6/25：9:18
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

    @RequestMapping(value = "/custom",method = RequestMethod.GET)
    public String getColumnCustom(@RequestParam(value = "teacherName") String teacherName){
        //在这里固定了教师用户能查看的六列数据
        List<String> names=new ArrayList<>();
        names.add("academic_year");
        names.add("semester");
        names.add("course_number");
        names.add("credit");
        names.add("main_teacher_name");
        names.add("final_teaching_scores");
        //它对应的中文名
        String[] chineseNames={"学年","学期","课程号","学分","上课老师","教分"};
        List<String> chineseNamesList= Arrays.asList(chineseNames);
        //在数据库中查询names中每个值代表的字段的数值，并且返回这名老师的对应列的记录
        List<WorkloadData[]> teacherSee = columnShowService.getColumnCustom(names,chineseNamesList,teacherName);

        if (teacherSee.size() == 0) {
            return new Result(ResponseCode.NoContentFailure).toString();
        } else {
            return new Result(ResponseCode.SUCCESS,teacherSee).toString();
        }
    }
}