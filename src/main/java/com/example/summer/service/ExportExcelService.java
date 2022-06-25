package com.example.summer.service;

import com.example.summer.models.pojo.ExcelFormat;

public interface ExportExcelService {

    ExcelFormat getContentInScope(String startYear, String endYear, String teacherName);
}
