package com.example.summer.models.pojo;

import lombok.Getter;
import lombok.Setter;

/**
 * @author theLastNYF
 * @description
 * @date 2022/6/25
 */
@Getter
@Setter
public class ExcelFormat {
    public String[][]content;
    public String fileName;//文件名
    public String sheetName;//页名
    public String[] title;
}
