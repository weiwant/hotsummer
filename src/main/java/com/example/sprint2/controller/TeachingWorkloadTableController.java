package com.example.sprint2.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.sprint2.models.enumerate.impl.ResponseCode;
import com.example.sprint2.models.vo.PageSearchVo;
import com.example.sprint2.mybatis.entity.TeachingWorkloadStatistics;
import com.example.sprint2.service.TeachingWorkloadTableService;
import com.example.sprint2.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author 24047
 * @date 2022/6/29
 */

@RestController
@RequestMapping("/manager/teaching-workload")
public class TeachingWorkloadTableController {
    @Autowired
    TeachingWorkloadTableService teachingWorkloadTableService;


    @RequestMapping(value = "get-all",method= RequestMethod.POST)
    public String getAll(@RequestBody PageSearchVo pageSearchVo){
        /**
         * @author 24047
         * @date 2022/6/29
         * @param [com.example.sprint2.models.vo.PageSearchVo]
         * @description 前端提供naturalYear，pageNumber，pageSize即可对教学工作量表进行分页查询
         * @return java.lang.String
         */
        IPage<TeachingWorkloadStatistics> workloadPage = teachingWorkloadTableService.searchByNaturalYearInPage(pageSearchVo);

        if (workloadPage.getTotal() == 0) {
            return new Result(ResponseCode.NoContentFailure).toString();
        } else {
            return new Result(ResponseCode.SUCCESS, workloadPage).toString();
        }
    }


    @RequestMapping(value = "search-teacher-name",method= RequestMethod.POST)
    public String searchTeacherName(@RequestBody PageSearchVo pageSearchVo){
        /**
         * @author 24047
         * @date 2022/6/29
         * @param [com.example.sprint2.models.vo.PageSearchVo]
         * @description 前端提供naturalYear，teacherName，pageNumber，pageSize即可对教学工作量表进行分页查询
         * @return java.lang.String
         */
        IPage<TeachingWorkloadStatistics> workloadPage = teachingWorkloadTableService.searchByTeacherNameInPage(pageSearchVo);

        if (workloadPage.getTotal() == 0) {
            return new Result(ResponseCode.NoContentFailure).toString();
        } else {
            return new Result(ResponseCode.SUCCESS, workloadPage).toString();
        }
    }


    @RequestMapping(value = "search-course-name",method= RequestMethod.POST)
    public String searchCourseName(@RequestBody PageSearchVo pageSearchVo){
        /**
         * @author 24047
         * @date 2022/6/29
         * @param [com.example.sprint2.models.vo.PageSearchVo]
         * @description 前端提供naturalYear，courseName，pageNumber，pageSize即可对教学工作量表进行分页查询
         * @return java.lang.String
         */
        IPage<TeachingWorkloadStatistics> workloadPage = teachingWorkloadTableService.searchByCourseNameInPage(pageSearchVo);

        if (workloadPage.getTotal() == 0) {
            return new Result(ResponseCode.NoContentFailure).toString();
        } else {
            return new Result(ResponseCode.SUCCESS, workloadPage).toString();
        }
    }


    @RequestMapping(value = "search-major",method= RequestMethod.POST)
    public String searchMajor(@RequestBody PageSearchVo pageSearchVo){
        /**
         * @author 24047
         * @date 2022/6/29
         * @param [com.example.sprint2.models.vo.PageSearchVo]
         * @description 前端提供naturalYear，pageNumber，pageSize即可对教学工作量表进行分页查询
         * @return java.lang.String
         */
        IPage<TeachingWorkloadStatistics> workloadPage = teachingWorkloadTableService.searchByMajorInPage(pageSearchVo);

        if (workloadPage.getTotal() == 0) {
            return new Result(ResponseCode.NoContentFailure).toString();
        } else {
            return new Result(ResponseCode.SUCCESS, workloadPage).toString();
        }
    }

    @RequestMapping(value = "search-student-grade",method= RequestMethod.POST)
    public String searchStudentGrade(@RequestBody PageSearchVo pageSearchVo){
        /**
         * @author 24047
         * @date 2022/6/29
         * @param [com.example.sprint2.models.vo.PageSearchVo]
         * @description 前端提供naturalYear，pageNumber，pageSize即可对教学工作量表进行分页查询
         * @return java.lang.String
         */
        IPage<TeachingWorkloadStatistics> workloadPage = teachingWorkloadTableService.searchByStudentGradeInPage(pageSearchVo);

        if (workloadPage.getTotal() == 0) {
            return new Result(ResponseCode.NoContentFailure).toString();
        } else {
            return new Result(ResponseCode.SUCCESS, workloadPage).toString();
        }
    }
}
