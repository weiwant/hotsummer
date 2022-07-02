package com.example.sprint2.controller;

import com.example.sprint2.models.vo.FileUploadVo;
import com.example.sprint2.mybatis.entity.ExaminationWorkload;
import com.example.sprint2.mybatis.entity.PaperCoachingWorkload;
import com.example.sprint2.mybatis.entity.TeachingWorkloadStatistics;
import com.example.sprint2.service.FileUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

/**
 * @author ZWForum
 * @description 工作量表上传
 * @date 2022/7/1
 */
@RestController
@RequestMapping("/file")
public class FileUploadController {
    @Autowired
    FileUploadService fileUploadService;

    @RequestMapping(value = "/upload/academic", method = RequestMethod.POST)
    public String fileUploadAcademic(FileUploadVo fileUploadVo) throws IOException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {
        return fileUploadService.fileUpload(fileUploadVo, TeachingWorkloadStatistics.class, "教学");
    }

    @RequestMapping(value = "/upload/paper", method = RequestMethod.POST)
    public String fileUploadPaper(FileUploadVo fileUploadVo) throws IOException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {
        return fileUploadService.fileUpload(fileUploadVo, PaperCoachingWorkload.class, "论文指导");
    }

    @RequestMapping(value = "/upload/examination", method = RequestMethod.POST)
    public String fileUploadExamination(FileUploadVo fileUploadVo) throws IOException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {
        return fileUploadService.fileUpload(fileUploadVo, ExaminationWorkload.class, "监考");
    }
}
