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
    public Map<String, Object> TeachingWorkloadToView(List<String> names, TeachingWorkloadStatistics statistics) {
        /**
         * @author 24047
         * @date 2022/6/27
         * @param [java.util.List<java.lang.String>, com.example.summer.entity.TeachingWorkloadStatistics]
         * @return java.util.Map<java.lang.String, java.lang.Object>
         * 根据提供的一个需要的字段列表，将这条记录转为map
         */
        Map<String, Object> myMap = new HashMap<>();
        for (String item : names) {
            String outName = CaseUtils.toCamelCase(item, false, '_');//item转为驼峰式
            //获取这个对象的该属性的值
            Object outValue = ReflexUtil.getFieldValueByFieldName(outName, statistics);
            myMap.put(item, outValue);
        }
        return myMap;
    }

    public WorkloadData[] TeachingWorkloadToViewChinese(List<String> names, List<String> chineseName, TeachingWorkloadStatistics statistics) {
        /**
         * @author 24047
         * @date 2022/6/27>
         * 根据提供的一个需要的字段列表，将这条记录转为WorkloadData数组
         */
        WorkloadData[] workloadData = new WorkloadData[names.size()];
        for (int i = 0; i < names.size(); i++) {
            //item转为驼峰式,以便查询对象的该字段对应数值
            String outName = CaseUtils.toCamelCase(names.get(i), false, '_');
            //获取这个对象的该属性的值
            Object outValue = ReflexUtil.getFieldValueByFieldName(outName, statistics);

            workloadData[i] = new WorkloadData(chineseName.get(i), outValue);
        }
        return workloadData;
    }
}
