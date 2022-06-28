package com.example.summer.service;

import com.example.summer.models.vo.FileReceiveVo;

import java.io.IOException;

/**
 * @author ZWForum
 * @description
 * @date 2022/6/23
 */
public interface FileInteractService {
    String writeIntoDataBase(FileReceiveVo fileReceiveVo) throws IOException, InstantiationException, IllegalAccessException;
}
