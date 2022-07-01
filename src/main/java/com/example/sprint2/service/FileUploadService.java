package com.example.sprint2.service;

import com.example.sprint2.models.vo.FileUploadVo;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

/**
 * @author ZWForum
 * @description
 * @date 2022/7/1
 */
public interface FileUploadService {
    String fileUpload(FileUploadVo fileUploadVo, Class<?> table, Object... args) throws IOException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException;
}
