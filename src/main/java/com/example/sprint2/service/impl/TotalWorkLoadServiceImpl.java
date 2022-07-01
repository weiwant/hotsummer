package com.example.sprint2.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.sprint2.dao.TotalTableDao;
import com.example.sprint2.models.enumerate.impl.ResponseCode;
import com.example.sprint2.models.vo.WorkloadVo;
import com.example.sprint2.mybatis.entity.TotalTable;
import com.example.sprint2.service.TotalWorkLoadService;
import com.example.sprint2.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ZWForum
 * @description
 * @date 2022/6/30
 */
@Service
public class TotalWorkLoadServiceImpl implements TotalWorkLoadService {
    @Autowired
    TotalTableDao totalTableDao;

    /**
     * @param workloadVo
     * @return String
     * @author ZWForum
     * @description Vo类添加ReferenceField注解标注引用的表和字段，TotalTable实体添加ForeignKey注解标注引用的表、字段和调用的mapper
     * @date 2022/6/30
     */
    public String getCombineWorkLoad(WorkloadVo workloadVo) throws IllegalAccessException {
        TotalTable totalTable = new TotalTable();
        totalTable.setNaturalYear(workloadVo.getNaturalYear());
        //根据自然年查询总表
        List<TotalTable> totalTables = totalTableDao.selectByNaturalYear(totalTable);
        List<WorkloadVo> output = totalTableDao.combine(totalTables, workloadVo);
        if (output.isEmpty()) {
            return new Result(ResponseCode.NoContentFailure).toString();
        } else {
            return new Result(ResponseCode.SUCCESS, output).toString();
        }
    }

    public String getCombineWorkLoadPage(WorkloadVo workloadVo, Integer page) throws IllegalAccessException {
        TotalTable totalTable = new TotalTable();
        totalTable.setNaturalYear(workloadVo.getNaturalYear());
        IPage<TotalTable> totalTablePage = totalTableDao.selectPageByNatualYear(totalTable, page);
        List<WorkloadVo> output = totalTableDao.combine(totalTablePage.getRecords(), workloadVo);
        if (output.isEmpty()) {
            return new Result(ResponseCode.NoContentFailure).toString();
        } else {
            return new Result(ResponseCode.SUCCESS, output).toString();
        }
    }
}
