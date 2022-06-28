package com.example.summer.models.vo;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author ZWForum
 * @description
 * @date 2022/6/25
 */
@Data
public class FileReceiveVo {
    private MultipartFile file;
    private String year;
    private String semester;
}
