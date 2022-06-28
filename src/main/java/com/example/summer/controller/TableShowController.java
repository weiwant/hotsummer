package com.example.summer.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.summer.entity.TeachingWorkloadStatistics;
import com.example.summer.models.pojo.ResponseCode;
import com.example.summer.models.vo.TableShowVo;
import com.example.summer.service.impl.TableShowServiceImpl;
import com.example.summer.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
        //静态表头：staticTableHeader
        String[] tableHeader = {"学年", "辅助", "计算用学时", "课程性质解释", "计算机用时", "课程名称", "课程性质", "课程号", "学分", "折扣", "实验安排", "实验课时", "教分", "合课单位", "实验室核对结果", "上课教师名字", "教师职称", "专业", "折扣前BA1系数", "原始教分", "其他教师名", "计划学院", "实践课时", "备注", "学期", "是否为特殊班级", "是否全英教学", "上课人数", "年级", "教学班", "BA1系数", "开课学院", "理论课时"};
        //获取数据：workloadTableData
        IPage<TeachingWorkloadStatistics> worksInSemester = tableShowService.getSemesterTable(tableShowVo);

        if (worksInSemester.getTotal() == 0) {
            return new Result(ResponseCode.NoContentFailure).toString();
        } else {
            return new Result(ResponseCode.SUCCESS, worksInSemester).toString();
        }
    }

    /**
     * @Author：wwq
     * @Return：
     * @Url:
     * @Description：导出数据获取
     */
    @RequestMapping(value = "tabledownload", method = RequestMethod.POST)
    public String downloadTable(@RequestBody TableShowVo tsv) {
        List<TeachingWorkloadStatistics> teachTable = tableShowService.getDownloadTable(tsv);
        if (teachTable.size() == 0) {
            return new Result(ResponseCode.NoContentFailure).toString();
        } else {
            return new Result(ResponseCode.SUCCESS, teachTable).toString();
        }
    }
}
