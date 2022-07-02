package com.example.sprint2.dao;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.sprint2.models.vo.WorkloadVo;
import com.example.sprint2.mybatis.entity.TotalTable;
import com.example.sprint2.mybatis.mapper.TotalTableMapper;
import com.example.sprint2.utils.annotations.ForeignKey;
import com.example.sprint2.utils.annotations.ReferenceField;
import com.example.sprint2.utils.annotations.References;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author ZWForum
 * @description 总表数据库操作
 * @date 2022/6/29
 */
@Repository
public class TotalTableDao {
    @Autowired
    TotalTableMapper totalTableMapper;
    @Autowired
    SqlSession sqlSession;

    /**
     * @param totalTable
     * @return
     * @author ZWForum
     * @description 插入一条数据
     * @date 2022/6/29
     */
    public void insertEntity(TotalTable totalTable) throws IllegalAccessException {
        List<Field> fields = Arrays.stream(TotalTable.class.getDeclaredFields()).filter(field -> field.getAnnotation(ForeignKey.class) != null).collect(Collectors.toList());
        Boolean isNotNull = false;
        for (Field field : fields) {
            field.setAccessible(true);
            if (field.get(totalTable) != null) {
                fields.remove(field);
                isNotNull = true;
                break;
            }
        }
        if (isNotNull) {
            for (Field field : fields) {
                field.setAccessible(true);
                field.set(totalTable, null);
            }
            totalTableMapper.insert(totalTable);
        }
    }

    /**
     * @param totalTable
     * @return List<TotalTable>
     * @author ZWForum
     * @description 根据自然年获取总表记录
     * @date 2022/6/29
     */
    public List<TotalTable> selectByNaturalYear(TotalTable totalTable) {
        QueryWrapper<TotalTable> wrapper = new QueryWrapper<>();
        wrapper.eq("natural_year", totalTable.getNaturalYear());
        wrapper.orderByAsc("teacher_name");
        wrapper.orderByAsc(getColumns());
        return totalTableMapper.selectList(wrapper);
    }

    public IPage<TotalTable> selectPageByNatualYear(TotalTable totalTable, Integer page) {
        QueryWrapper<TotalTable> wrapper = new QueryWrapper<>();
        wrapper.eq("natural_year", totalTable.getNaturalYear());
        wrapper.orderByAsc("teacher_name");
        wrapper.orderByAsc(getColumns());
        Integer size = 3;
        Page<TotalTable> totalTablePage = new Page<>(page, size);
        return totalTableMapper.selectPage(totalTablePage, wrapper);
    }

    /**
     * @param
     * @return List<String>
     * @author ZWForum
     * @description 获取字段列名
     * @date 2022/6/30
     */
    private List<String> getColumns() {
        List<Field> fields = Arrays.stream(TotalTable.class.getDeclaredFields()).filter(field -> (field.getName().contains("WorkId"))).collect(Collectors.toList());
        List<String> columns = new ArrayList<>();
        fields.forEach(field -> {
            if (field.getAnnotation(TableField.class) != null) {
                columns.add(field.getAnnotation(TableField.class).value());
            }
        });
        return columns;
    }

    public List<WorkloadVo> combine(List<TotalTable> totalTables, WorkloadVo workloadVo) throws IllegalAccessException {
        //获取总表中外键字段
        List<Field> fields = Arrays.stream(TotalTable.class.getDeclaredFields()).filter(field -> field.getAnnotation(ForeignKey.class) != null).collect(Collectors.toList());
        //获取Vo对象中引用字段
        List<Field> fieldList = Arrays.stream(WorkloadVo.class.getDeclaredFields()).filter(singleField -> singleField.getAnnotation(ReferenceField.class) != null || singleField.getAnnotation(References.class) != null).collect(Collectors.toList());
        //判断带不带筛选条件
        boolean select = false;
        for (Field field : fieldList) {
            field.setAccessible(true);
            if (field.get(workloadVo) != null) {
                select = true;
                break;
            }
        }
        List<WorkloadVo> output = new ArrayList<>();
        for (TotalTable singleRecord : totalTables) {
            for (Field field : fields) {
                field.setAccessible(true);
                //判断外键值选表
                if (field.get(singleRecord) != null) {
                    ForeignKey foreignKey = field.getAnnotation(ForeignKey.class);
                    //获取mapper
                    BaseMapper<?> mapper = (BaseMapper<?>) sqlSession.getMapper(foreignKey.tableMapper());
                    Map<String, Object> map = new HashMap<>();
                    //添加查询id
                    map.put(Objects.equals(foreignKey.fieldName(), "") ? field.getName() : foreignKey.fieldName(), field.get(singleRecord));
                    //查找该表在Vo中引用字段
                    Map<Field, ReferenceField> fieldReferenceFieldMap = new HashMap<>();
                    fieldList.forEach(singleField -> {
                        for (Annotation referenceField : singleField.getAnnotations()) {
                            if (referenceField instanceof ReferenceField) {
                                if (((ReferenceField) referenceField).value() == foreignKey.value()) {
                                    fieldReferenceFieldMap.put(singleField, (ReferenceField) referenceField);
                                }
                            } else if (referenceField instanceof References) {
                                for (ReferenceField reference : ((References) referenceField).value()) {
                                    if (reference.value() == foreignKey.value()) {
                                        fieldReferenceFieldMap.put(singleField, reference);
                                    }
                                }
                            }
                        }
                    });
                    if (select) {
                        if (fieldReferenceFieldMap.isEmpty()) {
                            break;
                        }
                        //添加条件
                        fieldReferenceFieldMap.forEach((singleField, singleReference) -> {
                            try {
                                singleField.setAccessible(true);
                                if (singleField.get(workloadVo) != null) {
                                    map.put(Objects.equals(singleReference.dataBaseName(), "") ? singleField.getName() : singleReference.dataBaseName(), singleField.get(workloadVo));
                                }
                            } catch (IllegalAccessException e) {
                                e.printStackTrace();
                            }
                        });
                    }
                    List<?> result = mapper.selectByMap(map);
                    if (!result.isEmpty()) {
                        WorkloadVo vo = new WorkloadVo();
                        vo.setNaturalYear(workloadVo.getNaturalYear());
                        //给对象赋值
                        fieldReferenceFieldMap.forEach((singleField, singleReference) -> {
                            try {
                                Field temp = foreignKey.value().getDeclaredField(Objects.equals(singleReference.fieldName(), "") ? singleField.getName() : singleReference.fieldName());
                                temp.setAccessible(true);
                                singleField.setAccessible(true);
                                singleField.set(vo, temp.getType() == Double.class && singleField.getType() == Integer.class ? ((Double) temp.get(result.get(0))).intValue() : temp.get(result.get(0)));
                            } catch (NoSuchFieldException | IllegalAccessException e) {
                                e.printStackTrace();
                            }
                        });
                        output.add(vo);
                    }
                    break;
                }
            }
        }
        return output;
    }
}
