package com.example.summer.controller;

import com.example.summer.models.vo.FileReceiveVo;
import com.example.summer.service.FileInteractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.sql.SQLException;

/**
 * @author ZWForum
 * @description
 * @date 2022/6/23
 */
@RestController
@RequestMapping("/file")
public class FileInteractController {
    @Autowired
    FileInteractService fileInteractService;

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @ResponseBody
    public String fileUpload(FileReceiveVo fileReceiveVo) throws IOException, InstantiationException, IllegalAccessException, SQLException {
        return fileInteractService.writeIntoDataBase(fileReceiveVo);
    }
}
