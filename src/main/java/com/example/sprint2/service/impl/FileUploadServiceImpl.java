package com.example.sprint2.service.impl;

import com.example.sprint2.dao.ExaminationDao;
import com.example.sprint2.dao.PaperCoachingDao;
import com.example.sprint2.dao.TeachingWorkloadStatisticsDao;
import com.example.sprint2.models.enumerate.impl.ResponseCode;
import com.example.sprint2.models.pojo.TableResolution;
import com.example.sprint2.models.vo.FileUploadVo;
import com.example.sprint2.service.FileUploadService;
import com.example.sprint2.utils.Result;
import com.example.sprint2.utils.annotations.DaoTarget;
import com.example.sprint2.utils.annotations.ExcludeField;
import com.example.sprint2.utils.annotations.StartRow;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * @author ZWForum
 * @description
 * @date 2022/7/1
 */
@Service
public class FileUploadServiceImpl implements FileUploadService {
    @Autowired
    TeachingWorkloadStatisticsDao teachingWorkloadStatisticsDao;
    @Autowired
    ExaminationDao examinationDao;
    @Autowired
    PaperCoachingDao paperCoachingDao;

    public String fileUpload(FileUploadVo fileUploadVo, Class<?> table, Object... args) throws IOException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        //获取工作簿
        BufferedInputStream bufferedInputStream = new BufferedInputStream(fileUploadVo.getFile().getInputStream());
        Workbook workbook = WorkbookFactory.create(bufferedInputStream);
        if (workbook.getNumberOfSheets() > 0) {
            //获取表单
            Sheet sheet = workbook.getSheetAt(0);
            int rows = sheet.getLastRowNum();
            //获取数据起始行
            int start = table.getAnnotation(StartRow.class).value();
            //获取表的属性
            List<Field> fields = Arrays.stream(table.getDeclaredFields()).filter(field -> !field.getName().equals("serialVersionUID") && field.getAnnotation(ExcludeField.class) == null).collect(Collectors.toList());
            List<String> hasDeleted = new ArrayList<>();
            //获取表的dao
            List<Field> daoField = Arrays.stream(getClass().getDeclaredFields()).filter(field -> field.getAnnotation(Autowired.class) != null).collect(Collectors.toList()).stream().filter(field -> field.getType().getAnnotation(DaoTarget.class).value() == table).collect(Collectors.toList());
            Field dao = daoField.isEmpty() ? null : daoField.get(0);
            //获取表中排除的属性
            List<Field> exclude = Arrays.stream(table.getDeclaredFields()).filter(field -> field.getAnnotation(ExcludeField.class) != null && field.getAnnotation(ExcludeField.class).value()).collect(Collectors.toList());
            Pattern pattern = Pattern.compile("^\\d+.0+$");
            for (int i = 0; i <= rows; i++) {
                if (i >= start - 1) {
                    //获取一行
                    Row row = sheet.getRow(i);
                    //是否空行
                    boolean isNotNull = false;
                    if (row != null) {
                        Object tableRow = table.newInstance();
                        String naturalYear = null;
                        //读取每一列
                        for (int j = 0; j < fields.size(); j++) {
                            if (row.getCell(j) != null) {
                                //从文件获取未排除的属性值
                                if (!exclude.contains(fields.get(j))) {
                                    //判断单元格类型
                                    if (row.getCell(j).getCellType() == CellType.BLANK || row.getCell(j).getCellType() == CellType.STRING) {
                                        Field field = fields.get(j);
                                        field.setAccessible(true);
                                        if (row.getCell(j).getCellType() == CellType.BLANK) {
                                            if (field.getType() == Double.class) {
                                                field.set(tableRow, null);
                                            } else {
                                                field.set(tableRow, "");
                                            }
                                        } else {
                                            if (field.getType() == Double.class) {
                                                field.set(tableRow, Double.parseDouble(row.getCell(j).getStringCellValue()));
                                            } else {
                                                field.set(tableRow, row.getCell(j).getStringCellValue());
                                            }
                                        }
                                    } else if (row.getCell(j).getCellType() == CellType.NUMERIC) {
                                        Field field = fields.get(j);
                                        field.setAccessible(true);
                                        if (field.getType() == Double.class) {
                                            field.set(tableRow, row.getCell(j).getNumericCellValue());
                                        } else {
                                            String temp = new DecimalFormat(".##").format(row.getCell(j).getNumericCellValue());
                                            field.set(tableRow, pattern.matcher(temp).matches() ? String.valueOf(((Double) row.getCell(j).getNumericCellValue()).longValue()) : temp);
                                        }
                                    }
                                }
                                fields.get(j).setAccessible(true);
                                if (!isNotNull && fields.get(j).get(tableRow) != null && !Objects.equals(fields.get(j).get(tableRow), "")) {
                                    isNotNull = true;
                                }
                            }
                        }
                        if (!isNotNull) {
                            continue;
                        }
                        //对排除的属性处理
                        if (!exclude.isEmpty()) {
                            for (Field excludeField : exclude) {
                                ExcludeField annotation = excludeField.getAnnotation(ExcludeField.class);
                                //判断通过解决方法还是参数列表获取值
                                if (Objects.equals(annotation.method(), "") && annotation.param() > 0) {
                                    excludeField.setAccessible(true);
                                    if (annotation.param() > 0) {
                                        excludeField.set(tableRow, Arrays.asList(args).get(annotation.param() - 1));
                                    }
                                } else if (!Objects.equals(annotation.method(), "") && annotation.param() == 0) {
                                    Method method = TableResolution.class.getDeclaredMethod(annotation.method(), table);
                                    excludeField.setAccessible(true);
                                    excludeField.set(tableRow, method.invoke(null, table.cast(tableRow)));
                                    if (method.getName().equals("getNaturalYear")) {
                                        naturalYear = (String) excludeField.get(tableRow);
                                    }
                                }
                            }
                        }
                        //删除已有记录
                        if (naturalYear != null && !hasDeleted.contains(naturalYear)) {
                            if (dao != null) {
                                dao.setAccessible(true);
                                dao.getType().getDeclaredMethod("deleteByNaturalYear", table).invoke(dao.get(this), tableRow);
                                hasDeleted.add(naturalYear);
                            }
                        }
                        //插入记录
                        if (dao != null) {
                            dao.setAccessible(true);
                            dao.getType().getDeclaredMethod("insertEntity", table).invoke(dao.get(this), tableRow);
                        }
                    }
                }
            }
            return new Result(ResponseCode.SUCCESS).toString();
        } else {
            return new Result(ResponseCode.NoContentFailure).toString();
        }
    }
}
