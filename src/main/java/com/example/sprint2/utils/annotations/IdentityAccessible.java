package com.example.sprint2.utils.annotations;

import com.example.sprint2.models.enumerate.impl.Identities;

import java.lang.annotation.*;

/**
 * @author ZWForum
 * @description
 * @date 2022/7/8
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
@Repeatable(IdentitiesContainer.class)
public @interface IdentityAccessible {
    Identities value();
}
