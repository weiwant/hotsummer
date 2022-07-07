package com.example.sprint2.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.sprint2.models.enumerate.impl.ResponseCode;
import com.example.sprint2.models.vo.TeachingWorkloadVo;
import com.example.sprint2.mybatis.entity.TeachingWorkloadStatistics;
import com.example.sprint2.service.TeachingWorkloadTableService;
import com.example.sprint2.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author 24047
 * @date 2022/6/29
 */

@RestController
@RequestMapping("/teaching-workload")
public class TeachingWorkloadTableController {
    @Autowired
    TeachingWorkloadTableService teachingWorkloadTableService;


    @RequestMapping(value = "all", method = RequestMethod.POST)
    public String getAll(@RequestBody TeachingWorkloadVo teachingWorkloadVo) {
        /**
         * @author 24047
         * @date 2022/6/29
         * @param [com.example.sprint2.models.vo.TeachingWorkloadVo]
         * @description 前端提供naturalYear，pageNumber，pageSize即可对教学工作量表进行分页查询
         * @return java.lang.String
         */
        IPage<TeachingWorkloadStatistics> workloadPage = teachingWorkloadTableService.searchByNaturalYearInPage(teachingWorkloadVo);

        if (workloadPage.getTotal() <= (teachingWorkloadVo.getPageNumber() - 1) * teachingWorkloadVo.getPageSize()) {
            return new Result(ResponseCode.NoContentFailure).toString();
//            return new Result(ResponseCode.NoContentFailure,workloadPage.getTotal()).toString();
        } else {
            return new Result(ResponseCode.SUCCESS, workloadPage).toString();
        }
    }


    @RequestMapping(value = "teacher-name", method = RequestMethod.POST)
    public String searchTeacherName(@RequestBody TeachingWorkloadVo teachingWorkloadVo) {
        /**
         * @author 24047
         * @date 2022/6/29
         * @param [com.example.sprint2.models.vo.TeachingWorkloadVo]
         * @description 前端提供naturalYear，teacherName，pageNumber，pageSize即可对教学工作量表进行分页查询
         * @return java.lang.String
         */
        IPage<TeachingWorkloadStatistics> workloadPage = teachingWorkloadTableService.searchByTeacherNameInPage(teachingWorkloadVo);

        if (workloadPage.getTotal() <= (teachingWorkloadVo.getPageNumber() - 1) * teachingWorkloadVo.getPageSize()) {
            return new Result(ResponseCode.NoContentFailure).toString();
        } else {
            return new Result(ResponseCode.SUCCESS, workloadPage).toString();
        }
    }


    @RequestMapping(value = "course-name", method = RequestMethod.POST)
    public String searchCourseName(@RequestBody TeachingWorkloadVo teachingWorkloadVo) {
        /**
         * @author 24047
         * @date 2022/6/29
         * @param [com.example.sprint2.models.vo.TeachingWorkloadVo]
         * @description 前端提供naturalYear，courseName，pageNumber，pageSize即可对教学工作量表进行分页查询
         * @return java.lang.String
         */
        IPage<TeachingWorkloadStatistics> workloadPage = teachingWorkloadTableService.searchByCourseNameInPage(teachingWorkloadVo);

        if (workloadPage.getTotal() <= (teachingWorkloadVo.getPageNumber() - 1) * teachingWorkloadVo.getPageSize()) {
            return new Result(ResponseCode.NoContentFailure).toString();
        } else {
            return new Result(ResponseCode.SUCCESS, workloadPage).toString();
        }
    }


    @RequestMapping(value = "major", method = RequestMethod.POST)
    public String searchMajor(@RequestBody TeachingWorkloadVo teachingWorkloadVo) {
        /**
         * @author 24047
         * @date 2022/6/29
         * @param [com.example.sprint2.models.vo.TeachingWorkloadVo]
         * @description 前端提供naturalYear，pageNumber，pageSize即可对教学工作量表进行分页查询
         * @return java.lang.String
         */
        IPage<TeachingWorkloadStatistics> workloadPage = teachingWorkloadTableService.searchByMajorInPage(teachingWorkloadVo);

        if (workloadPage.getTotal() <= (teachingWorkloadVo.getPageNumber() - 1) * teachingWorkloadVo.getPageSize()) {
            return new Result(ResponseCode.NoContentFailure).toString();
        } else {
            return new Result(ResponseCode.SUCCESS, workloadPage).toString();
        }
    }

    @RequestMapping(value = "student-grade", method = RequestMethod.POST)
    public String searchStudentGrade(@RequestBody TeachingWorkloadVo teachingWorkloadVo) {
        /**
         * @author 24047
         * @date 2022/6/29
         * @param [com.example.sprint2.models.vo.TeachingWorkloadVo]
         * @description 前端提供naturalYear，pageNumber，pageSize即可对教学工作量表进行分页查询
         * @return java.lang.String
         */
        IPage<TeachingWorkloadStatistics> workloadPage = teachingWorkloadTableService.searchByStudentGradeInPage(teachingWorkloadVo);

        if (workloadPage.getTotal() <= (teachingWorkloadVo.getPageNumber() - 1) * teachingWorkloadVo.getPageSize()) {
            return new Result(ResponseCode.NoContentFailure).toString();
        } else {
            return new Result(ResponseCode.SUCCESS, workloadPage).toString();
        }
    }

    @RequestMapping(value = "teacher",method = RequestMethod.POST)
    public String selectByTeacher(@RequestBody TeachingWorkloadVo teachingWorkloadVo){
        List<TeachingWorkloadStatistics> list = teachingWorkloadTableService.selectByTeacher(teachingWorkloadVo);
        if (list.size() ==0){
            return new Result(ResponseCode.NoContentFailure).toString();
        } else {
            return new Result(ResponseCode.SUCCESS, list).toString();
        }
    }
}
