package com.example.sprint2.utils.annotations;

import java.lang.annotation.*;

/**
 * @author ZWForum
 * @description 对应字段注解
 * @date 2022/7/1
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
@Repeatable(References.class)
public @interface ReferenceField {
    Class<?> value();//引用的表的类

    String dataBaseName() default "";//字段在数据库的列名

    String fieldName() default "";//字段在引用表中的名字
}

