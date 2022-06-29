package com.example.sprint2.utils;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class ReflexUtil {
    /**
     * 根据属性名获取属性值
     *
     * @param fieldName
     * @param object
     * @return
     */

    public static Object getFieldValueByFieldName(String fieldName, Object object) {
        try {
            //获取对象的类的指定属性
            Field field = object.getClass().getDeclaredField(fieldName);
            //设置对象的访问权限，保证对private的属性的访问
            field.setAccessible(true);
            return field.get(object);
        } catch (Exception e) {
            return null;
        }
    }


    /**
     * 得到所有属性的键值对
     *
     * @param obj
     */
    public static Map<String, Object> readAttributeValue(Object obj) {
        //得到class
        Class cls = obj.getClass();
        //得到所有属性
        Field[] fields = cls.getDeclaredFields();
        Map<String, Object> nameValues = new HashMap<>();
        for (int i = 0; i < fields.length; i++) {//遍历
            try {
                //得到属性
                Field field = fields[i];
                //打开私有访问
                field.setAccessible(true);
                //获取属性
                String name = field.getName();
                //获取属性值
                Object value = field.get(obj);
                //一个个赋值
                nameValues.put(name, value);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        System.out.println(nameValues.toString());

        return nameValues;
    }


    /**
     * 得到所有属性的名称,但是这里是Object对象，也就是说保留了变量的类型
     *
     * @param obj
     */
    public static Object[] readAllAttribute(Object obj) {
        //得到class
        Class cls = obj.getClass();
        //得到所有属性
        Field[] fields = cls.getDeclaredFields();
        Object nameValues[] = new String[fields.length];
        for (int i = 0; i < fields.length; i++) {//遍历
            try {
                //得到属性
                Field field = fields[i];
                //打开私有访问
                field.setAccessible(true);
                //获取属性
                String name = field.getName();
                //一个个赋值
                nameValues[i] = name;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.out.println(nameValues.toString());
        return nameValues;
    }

    /**
     * 得到所有属性的值，并转为String
     *
     * @param obj
     */
    public static String[] readAllValue(Object obj) {

        //得到class
        Class<? extends Object> cls = obj.getClass();
        //得到所有属性
        Field[] fields = cls.getDeclaredFields();
        String[] nameValues = new String[fields.length];
        for (int i = 0; i < fields.length; i++) {//遍历
            try {
                //得到属性
                Field field = fields[i];
                //打开私有访问
                field.setAccessible(true);
                //获取属性
                String name = field.getName();
                //获取属性值
                Object value = field.get(obj);
                if (value == null) {
                    nameValues[i] = "";
                } else {
                    nameValues[i] = value.toString();
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return nameValues;
    }
}
