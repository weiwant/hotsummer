package com.example.summer.controller;


import com.example.summer.models.pojo.ExcelFormat;
import com.example.summer.service.impl.ExportExcelImpl;
import com.example.summer.service.impl.TeachingWorkloadServiceImpl;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@RestController
public class TeachingWorkloadDownloadController {

    @Autowired
    private TeachingWorkloadServiceImpl exportService;
    @Autowired
    private ExportExcelImpl exportExcel;

//    @RequestMapping("/test01")
//    public void exportTest01(@RequestParam String startYear,@RequestParam String endYear,@RequestParam(value = "name") String teacherName, HttpServletResponse response) {
//        //获取数据源
//        List<TeachingWorkloadStatistics> list = exportService.searchTeachingWorkloadInScope(startYear,endYear,teacherName);
////        GregorianCalendar calendar=new GregorianCalendar();
//
//        Date date = new Date();//当前日期
//        SimpleDateFormat dateFormat = new SimpleDateFormat("y-M-d H:m:s");
//        String format = dateFormat.format(date);
//        // Excel标题
//        String[] title = {"学年", "学期", "课程号"};
//        // Excel文件名
//        String fileName = "教学工作量表（"+format+"）.xls";
//        // sheet名
//        String sheetName = "教学工作量";
//        // 将数据放到数组中
//        String[][] content = new String[list.size()][title.length];
//        for (int i = 0; i < list.size(); i++) {
//            TeachingWorkloadStatistics monthReportModel = list.get(i);
//
//            content[i][0] = monthReportModel.getAcademicYear();
//            content[i][1] = monthReportModel.getAssistant();
//            content[i][2] = monthReportModel.getMainTeacherName();
//        }
//        // 导出Excel
//        try {
//            HSSFWorkbook hssfWorkbook = getHSSFWorkbook(sheetName, title, content, null);
//            this.setResponseHeader(response,fileName);
//            OutputStream os=response.getOutputStream();
//            hssfWorkbook.write(os);
//            os.flush();
//            os.close();
//        }
//        catch (Exception e){
//            e.printStackTrace();
//
//        }
//    }

    /**
     * @Author theLastNYF
     * @Date 2022/6/25
     * @Description Excel总表导出控制类
     * @Param startYear 开始学年
     * @Param endYear 结束学年
     * @Param name 教师姓名
     */
    @RequestMapping("/excelDownload")
    public void test(@RequestParam String startYear, @RequestParam String endYear, @RequestParam(value = "name") String teacherName, HttpServletResponse response) {
        ExcelFormat myExcel = exportExcel.getContentInScope(startYear, endYear, teacherName);
        try {
            HSSFWorkbook hssfWorkbook = getHSSFWorkbook(myExcel.sheetName, myExcel.title, myExcel.content, null);
            this.setResponseHeader(response, myExcel.fileName);
            OutputStream os = response.getOutputStream();
            hssfWorkbook.write(os);
            os.flush();
            os.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @Author theLastNYF
     * @Date 2022/6/25
     * @Description 为文件导出方法提供建Excel表支持
     */
    public HSSFWorkbook getHSSFWorkbook(String sheetName, String[] title, String[][] values, HSSFWorkbook workbook) {
        // 创建一个HSSFWorkbook，对应一个Excel文件
        if (workbook == null) {
            workbook = new HSSFWorkbook();
        }
        // 在workbook中添加一个sheet,对应Excel文件中的sheet
        HSSFSheet sheet = workbook.createSheet(sheetName);
        // 在sheet中添加表头第0行
        HSSFRow row = sheet.createRow(0);
        // 创建单元格，并设置值表头 设置表头居中
        HSSFCellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        // 声明列对象
        HSSFCell cell;
        // 创建标题
        for (int i = 0; i < title.length; i++) {
            cell = row.createCell(i);
            cell.setCellValue(title[i]);
            cell.setCellStyle(cellStyle);
        }
        // 创建内容
        for (int i = 0; i < values.length; i++) {
            row = sheet.createRow(i + 1);
            for (int j = 0; j < values[i].length; j++) {
                // 将内容按顺序赋给对应的列对象
                row.createCell(j).setCellValue(values[i][j]);
            }
        }
        return workbook;

    }


    /**
     * @Author theLastNYF
     * @Date 2022/6/25
     * @Description 为文件导出方法提供数据连接支持
     */
    public void setResponseHeader(HttpServletResponse response, String fileName) {
        try {
            fileName = new String(fileName.getBytes(), StandardCharsets.UTF_8);
            response.setContentType("application/vnd.ms-excel");
            response.setHeader("Content-disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }


}
