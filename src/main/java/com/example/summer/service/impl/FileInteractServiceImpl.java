package com.example.summer.service.impl;

import com.example.summer.dao.DataTableDao;
import com.example.summer.dao.TeachingWorkloadStatisticsDao;
import com.example.summer.entity.TeachingWorkloadStatistics;
import com.example.summer.models.pojo.ResponseCode;
import com.example.summer.models.vo.FileReceiveVo;
import com.example.summer.service.FileInteractService;
import com.example.summer.utils.Result;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author ZWForum
 * @description
 * @date 2022/6/23
 */
@Service
public class FileInteractServiceImpl implements FileInteractService {
    @Autowired
    DataTableDao dataTableDao;
    @Autowired
    TeachingWorkloadStatisticsDao teachingWorkloadStatisticsDao;

    @Override
    public String writeIntoDataBase(FileReceiveVo fileReceiveVo) throws IOException, InstantiationException, IllegalAccessException {
        BufferedInputStream bufferedInputStream = new BufferedInputStream(fileReceiveVo.getFile().getInputStream());
        Workbook workbook = WorkbookFactory.create(bufferedInputStream);
        if (workbook.getNumberOfSheets() > 0) {
            /*String tableName = "table" + fileReceiveVo.getYear();
            DataTableBo tableBo = new DataTableBo(tableName);
            Sheet sheet = workbook.getSheetAt(0);
            int rows = sheet.getLastRowNum();
            int start = sheet.getFirstRowNum();
            for (int i = 0; i <= rows; i++) {
                if (i == start) {
                    Row startRow = sheet.getRow(i);
                    for (int j = 0; j <= startRow.getLastCellNum(); j++) {
                        Cell cell = startRow.getCell(j);
                        if (cell != null) {
                            String tableField = BaseConvert.toBase26(j + 1) + (i + 1);
                            DataTableItem item = new DataTableItem(tableField, MysqlDataType.VARCHAR);
                            switch (cell.getCellType()) {
                                case STRING:
                                    item.setComment(cell.getStringCellValue());
                                    break;
                                case NUMERIC:
                                    item.setComment(String.valueOf(cell.getNumericCellValue()));
                                    break;
                                case BOOLEAN:
                                    item.setComment(String.valueOf(cell.getBooleanCellValue()));
                                    break;
                                default:
                                    break;
                            }
                            tableBo.getColumns().add(item);
                        }
                    }
                    dataTableDao.createTable(tableBo);
                    tableBo.getAClass();
                }
            }*/
            Sheet sheet = workbook.getSheetAt(0);
            int rows = sheet.getLastRowNum();
            List<Method> methods = Arrays.asList(TeachingWorkloadStatistics.class.getMethods());
            methods = methods.stream().filter((method) -> {
                if (method.getName().contains("set")) {
                    return true;
                }
                return false;
            }).collect(Collectors.toList());
            List<Field> fields = Arrays.asList(TeachingWorkloadStatistics.class.getDeclaredFields());
            List<String> hasDeleted = new ArrayList<>();
            for (int i = 0; i <= rows; i++) {
                if (i == 0 || i == 1) {
                    continue;
                }
                Row row = sheet.getRow(i);
                if (row != null) {
                    TeachingWorkloadStatistics teachingWorkloadStatistics = new TeachingWorkloadStatistics();
                    for (int j = 0; j < methods.size(); j++) {
                        if (row.getCell(j) != null) {
                            int finalJ = j;
                            if (row.getCell(j).getCellType() == CellType.STRING || row.getCell(j).getCellType() == CellType.BLANK) {
                                methods.stream().forEach(method -> {
                                    if (method.getName().toUpperCase().equals("SET" + fields.get(finalJ + 1).getName().toUpperCase())) {
                                        try {
                                            if (row.getCell(finalJ).getCellType() == CellType.BLANK) {
                                                if (method.getParameterTypes()[0] == Integer.class) {
                                                    method.invoke(teachingWorkloadStatistics, 0);
                                                } else if (method.getParameterTypes()[0] == Double.class) {
                                                    method.invoke(teachingWorkloadStatistics, 0.0);
                                                } else {
                                                    method.invoke(teachingWorkloadStatistics, "");
                                                }
                                            } else {
                                                if (method.getParameterTypes()[0] == Integer.class) {
                                                    method.invoke(teachingWorkloadStatistics, Integer.parseInt(row.getCell(finalJ).getStringCellValue()));
                                                } else if (method.getParameterTypes()[0] == Double.class) {
                                                    method.invoke(teachingWorkloadStatistics, Double.parseDouble(row.getCell(finalJ).getStringCellValue()));
                                                } else {
                                                    method.invoke(teachingWorkloadStatistics, row.getCell(finalJ).getStringCellValue());
                                                }
                                            }
                                        } catch (IllegalAccessException | InvocationTargetException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                });
                            } else {
                                methods.stream().forEach(method -> {
                                    if (method.getName().toUpperCase().equals("SET" + fields.get(finalJ + 1).getName().toUpperCase())) {
                                        try {
                                            if (method.getParameterTypes()[0] == Integer.class) {
                                                method.invoke(teachingWorkloadStatistics, ((Double) row.getCell(finalJ).getNumericCellValue()).intValue());
                                            } else if (method.getParameterTypes()[0] == Double.class) {
                                                method.invoke(teachingWorkloadStatistics, row.getCell(finalJ).getNumericCellValue());
                                            } else {
                                                if (method.getName().contains("TeachingClass")) {
                                                    method.invoke(teachingWorkloadStatistics, String.valueOf(((Double) row.getCell(finalJ).getNumericCellValue()).longValue()));
                                                } else {
                                                    method.invoke(teachingWorkloadStatistics, String.valueOf(row.getCell(finalJ).getNumericCellValue()));
                                                }
                                            }
                                        } catch (IllegalAccessException | InvocationTargetException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                });
                            }
                        }
                    }
                    if (!hasDeleted.contains(teachingWorkloadStatistics.getAcademicYear())) {
                        teachingWorkloadStatisticsDao.deleteByYear(teachingWorkloadStatistics);
                        hasDeleted.add(teachingWorkloadStatistics.getAcademicYear());
                    }
                    teachingWorkloadStatisticsDao.insertEntity(teachingWorkloadStatistics);
                }
            }
            return new Result(ResponseCode.SUCCESS).toString();
        } else {
            return new Result(ResponseCode.NoContentFailure).toString();
        }
    }
}
