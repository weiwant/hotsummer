package com.example.summer.models.pojo;

import lombok.Getter;
import lombok.Setter;

/**
 * @author theLastNYF
 * @description 和Excel文件导出有关，不过现在已经被废弃了
 * @date 2022/6/25
 */
@Getter
@Setter
public class ExcelFormat {
    public String[][] content;
    public String fileName;//文件名
    public String sheetName;//页名
    public String[] title;
}
