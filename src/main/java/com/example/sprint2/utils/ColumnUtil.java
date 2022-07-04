package com.example.sprint2.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author hy
 * @Date 2022/7/4
 * @Description
 */
public class ColumnUtil {

    public static void setMap(Map<String,String> map){
        map.put("type","type");
        map.put("declarantName","declarant_name");
        map.put("teacherName","teacher_name");
        map.put("id","id");
        map.put("year","report_time");
        Set<String> keyset = map.keySet();

    }

}
