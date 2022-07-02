package com.example.sprint2.utils.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author ZWForum
 * @description
 * @date 2022/7/1
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface ExcludeField {
    boolean value() default true;//是否排除

    String method() default "";//调用解决方法的名字

    int param() default 0;//参数在列表中逻辑位置
}
