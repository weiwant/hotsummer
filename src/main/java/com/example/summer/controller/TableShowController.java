package com.example.summer.controller;

import com.example.summer.entity.TeachingWorkloadStatistics;
import com.example.summer.models.pojo.ResponseCode;
import com.example.summer.service.impl.TableShowServiceImpl;
import com.example.summer.models.pojo.ResponseCode;
import com.example.summer.utils.Result;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author：wwq
 * @Date 2022/6/23 14:38
 */

@RestController
@RequestMapping("/resource")
public class TableShowController {
    @Autowired
    TableShowServiceImpl tableShowService;
    /**
     * @Author：wwq
     * @Return：
     * @Description：获取数据库表所有内容
     */
    @RequestMapping(value = "/alltable",method = RequestMethod.GET)
    public String showAllTables(){
        List<TeachingWorkloadStatistics> works=tableShowService.getAllTables();
        return new Result(ResponseCode.SUCCESS,works).toString();
    }

    /**
     * @Author：wwq
     * @Return：
     * @Description：根据年份返回对应数据表。数据库表的year字段
     */
    @RequestMapping(value = "/tableinyear",method = RequestMethod.GET)
    public String showTableInYear(@RequestParam(value = "year") String year){
        List<TeachingWorkloadStatistics> worksInYear=tableShowService.getYearTable(year);
        if(worksInYear.equals(null)) {
            return new Result(ResponseCode.SUCCESS, worksInYear).toString();
        }else
        {
            return new Result(ResponseCode.NoContentFailure).toString();
        }
    }

    /**
     * @Author：wwq
     * @Return：
     * @Description：根据年份和学期返回对应信息
     */

    @RequestMapping(value = "tableinsemester",method = RequestMethod.GET)
    public String showTableInSemester(@RequestParam(value = "year") String year,@RequestParam(value = "semester") int semester){
        List<TeachingWorkloadStatistics> worksInSemester=tableShowService.getSemesterTable(year,semester);
        return new Result(ResponseCode.SUCCESS,worksInSemester).toString();
    }
}
