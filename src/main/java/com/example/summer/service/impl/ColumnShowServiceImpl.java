package com.example.summer.service.impl;

import com.alibaba.fastjson.JSON;
import com.example.summer.dao.ColumnShowDao;
import com.example.summer.entity.TeachingWorkloadStatistics;
import com.example.summer.models.pojo.EntityToView;
import com.example.summer.service.ColumnShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author:wwq
 * @Date：2022/6/25：9:28
 */

/**
 * @Author：wwq
 * @Return：
 * @Description：根据列名返回对应内容
 */
@Service
public class ColumnShowServiceImpl implements ColumnShowService {
    @Autowired
    ColumnShowDao columnShowDao;

    public List<String> getColumn(List<String> names){
        /**
         * @author 24047
         * @date 2022/6/25
         * @param [java.util.List<java.lang.String>]
         * @return java.util.List<java.lang.String>
         */

        List<TeachingWorkloadStatistics> someWork=columnShowDao.getWorkInColumn(names);//获取
        //实体类转为Map
        EntityToView entityToView =new EntityToView();
        //存储了一些列Map的List<String>
        List<String> workloadRecord=new ArrayList<>();
        for (TeachingWorkloadStatistics item:someWork) {
            Map<String, Object> myMap= entityToView.TeachingWorkloadToView(names,item);//每一条记录转为一个map
//            String mapString= JSON.toJSONString(myMap);//每个map转为一个JSON字符串
            workloadRecord.add(myMap.toString());
        }
        return workloadRecord;
    }
}
