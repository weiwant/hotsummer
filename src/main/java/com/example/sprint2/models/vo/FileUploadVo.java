package com.example.sprint2.models.vo;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author ZWForum
 * @description 接收上传Excel
 * @date 2022/7/1
 */
@Data
public class FileUploadVo {
    private MultipartFile file;
    private String naturalYear;
}
