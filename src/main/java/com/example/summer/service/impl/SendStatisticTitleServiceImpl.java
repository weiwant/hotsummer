package com.example.summer.service.impl;

import com.example.summer.entity.TeachingWorkloadStatistics;
import com.example.summer.service.SendStatisticTitleService;
import com.example.summer.utils.ReflexUtil;
import com.google.common.base.CaseFormat;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 24047
 * @date 2022/6/25
 */
@Service
public class SendStatisticTitleServiceImpl implements SendStatisticTitleService {
    /**
     * @author 24047
     * @date 2022/6/25
     * @description 传入一个字符串数组，返回一个字符串数组和表头的映射,
     * 目前只针对TeachingWorkloadStatistics,并且全部映射都被包含了
     * @param titleShow
     * @return java.util.Map<java.lang.String,java.lang.Object>
     */
    @Override
    public Map<String, Object> getTitle(String[] titleShow){
        Object[] titleObject = ReflexUtil.readAllAttribute(new TeachingWorkloadStatistics());
        Map<String,Object>myMap=new HashMap<>();
        int range;
        if(titleShow.length> titleObject.length-1){
            range=titleObject.length;
        }
        else{
            range=titleShow.length;
        }
        for (int i=0;i< range;i++) {
            myMap.put(titleShow[i],titleObject[i+1]);//因为反射机制得出的属性名第一个是用不上的哈哈哈哈哈哈哈
        }
        return myMap;
    }
    /**
     * @author 24047
     * @date 2022/6/27
     * @param titleShow 和上面那个唯一的区别就是返回值类型不同了
     * @description 传入一个字符串数组，返回一个字符串数组和表头的映射,虽然没有什么很大的用处
     * @return java.util.Map<java.lang.String,java.lang.String>
     */
    @Override
    public Map<String, String> getTitleFormat(String[] titleShow){
        //获取一个教师工作量实体类的所有属性名
        Object[] titleObject = ReflexUtil.readAllAttribute(new TeachingWorkloadStatistics());
        //将教师工作量实体类的所有属性名转为实在的String类型
        String[] titleAttribute=new String[titleObject.length];
        for (int i=0;i< titleObject.length;i++) {
            titleAttribute[i]= CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE,(String) titleObject[i]);
        }
        Map<String,String>myMap=new HashMap<>();
        int range;
        if(titleShow.length> titleObject.length-1){
            range=titleObject.length;
        }
        else{
            range=titleShow.length;
        }
        for (int i=0;i< range;i++) {
            //因为反射机制得出的属性名第一个是用不上的哈哈哈哈哈哈哈
            myMap.put(titleShow[i],titleAttribute[i+1]);}
        return myMap;
    }
}
