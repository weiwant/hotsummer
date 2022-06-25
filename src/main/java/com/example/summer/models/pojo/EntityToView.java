package com.example.summer.models.pojo;

import com.example.summer.entity.TeachingWorkloadStatistics;
import com.example.summer.utils.ReflexUtil;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.text.CaseUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
public class EntityToView {
    public Map<String,Object> TeachingWorkloadToView(List<String>names, TeachingWorkloadStatistics statistics){
        Map<String,Object>myMap = new HashMap<>();
        for (String item:names) {
            String outName= CaseUtils.toCamelCase(item,false,'_');//item转为驼峰式
            //获取这个对象的该属性的值
            Object outValue= ReflexUtil.getFieldValueByFieldName(outName,statistics);
            myMap.put(item,outValue);
        }
        return myMap;
    }
}
