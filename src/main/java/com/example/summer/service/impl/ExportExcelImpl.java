package com.example.summer.service.impl;

import com.example.summer.dao.ExportExcelDao;
import com.example.summer.entity.TeachingWorkloadStatistics;
import com.example.summer.models.pojo.ExcelFormat;
import com.example.summer.service.ExportExcel;
import com.example.summer.utils.ReflexUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class ExportExcelImpl implements ExportExcel {
    @Autowired
    ExportExcelDao excelDao;

    @Override
    public ExcelFormat getContentInScope(String startYear, String endYear, String teacherName){

        ExcelFormat excelFormat=new ExcelFormat();
        String[][] content;

        Date date = new Date();//当前日期
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String format = dateFormat.format(date);
        // Excel文件名
        String fileName = "教学工作量表（"+format+"）.xls";
        // sheet名
        String sheetName = "教学工作量";
        //返回查询到的记录
        List<TeachingWorkloadStatistics> list=excelDao.searchInScopeAccurate(startYear,endYear,teacherName);

        if(list==null){
            return null;
        }
        else {
            //获取第一条记录
            TeachingWorkloadStatistics teachingWorkloadStatistics=list.get(0);
            // Excel台头，不过目前全部都是英文名，也许应该做一些转换
            Object[] titleObject = ReflexUtil.readAllAttribute(teachingWorkloadStatistics);
            String [] title = new String[titleObject.length];//设置表头
            for (int i=0;i<titleObject.length;i++) {
                title[i]=(String) titleObject[i];
            }
            content = new String[list.size()][title.length-1];//设置单元格内容
            for (int i = 0; i < list.size(); i++) {
             //第i行记录
                Object monthReportModel = list.get(i);
                Object[] contentObject=ReflexUtil.readAllValue(monthReportModel);
                for (int j = 0; j < title.length-1; j++) {
                //为content逐一赋值,但是注意到第一个属性不是我们想要的属性
                    content[i][j]=(String)contentObject[j+1];
                }
            }
            excelFormat.setContent(content);
            excelFormat.setFileName(fileName);
            excelFormat.setSheetName(sheetName);
            excelFormat.setTitle(title);
            return excelFormat;

        }
    }
}
