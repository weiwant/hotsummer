package com.example.summer.controller;

import com.example.summer.entity.TeachingWorkloadStatistics;
import com.example.summer.models.pojo.ResponseCode;
import com.example.summer.models.vo.TableShowVo;
import com.example.summer.service.impl.TableShowServiceImpl;
import com.example.summer.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    @RequestMapping(value = "/alltable", method = RequestMethod.GET)
    public String showAllTables() {
        List<TeachingWorkloadStatistics> works = tableShowService.getAllTables();
        if (works.size() == 0) {
            return new Result(ResponseCode.NoContentFailure).toString();
        } else {
            return new Result(ResponseCode.SUCCESS, works).toString();
        }
    }

    /**
     * @Author：wwq
     * @Return：
     * @Description：根据年份返回对应数据表。数据库表的year字段
     */
    @RequestMapping(value = "/tableinyear", method = RequestMethod.GET)
    public String showTableInYear(@RequestParam(value = "year") String year) {
        List<TeachingWorkloadStatistics> worksInYear = tableShowService.getYearTable(year);
        if (worksInYear.size() == 0) {
            return new Result(ResponseCode.NoContentFailure).toString();
        } else {
            return new Result(ResponseCode.SUCCESS, worksInYear).toString();
        }
    }

    /**
     * @Author：wwq
     * @Return：
     * @Description：根据年份和学期返回对应信息
     */

    @RequestMapping(value = "/tableinsemester", method = RequestMethod.POST)
    public String showTableInSemester(@RequestBody TableShowVo tableShowVo) {
        //获取表头：workloadTableHeader
        Object[] workLoadTableHeader=tableShowService.getTableHeader();
        Arrays.sort(workLoadTableHeader);
        /*//静态表头：staticTableHeader
        String[] tableHeader={"assistant","calculatingClassHours","course_name","teaching_class","plan_school","credit","course_nature","student_grade","major",};
        Arrays.sort(tableHeader);*/
        //获取数据：workloadTableData
        List<TeachingWorkloadStatistics> worksInSemester = tableShowService.getSemesterTable(tableShowVo);
        Map<String,Object> workTable=new HashMap<>();
        workTable.put("workloadTableHeader",workLoadTableHeader);
        //workTable.put("staticTableHeader",tableHeader);
        workTable.put("workloadTableData",worksInSemester);
        if (worksInSemester.size() == 0) {
            return new Result(ResponseCode.NoContentFailure).toString();
        } else {
            return new Result(ResponseCode.SUCCESS, workTable).toString();
        }
    }
}
