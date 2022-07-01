package com.example.sprint2.utils.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author ZWForum
 * @description 外键注解
 * @date 2022/6/30
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface ForeignKey {
    Class<?> value();//引用的表的类

    String fieldName() default "";//字段在数据库中的列名

    Class<?> tableMapper();//引用的表的mapper
}
