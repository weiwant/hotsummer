package com.example.sprint2.models.enumerate.impl;

import com.example.sprint2.models.enumerate.IdentityEnumerate;
import lombok.Getter;

/**
 * @author ZWForum
 * @description
 * @date 2022/7/8
 */
@Getter
public enum Identities implements IdentityEnumerate {
    TEACHER("教师", 0),
    MAIN_ADMIN("主管理员", 1),
    ASSIST_ADMIN_EXAM("辅管理员", 2),
    ASSIST_ADMIN_PAPER("辅管理员", 3);
    private final Integer identityCode;
    private final String des;

    Identities(String des, Integer identityCode) {
        this.des = des;
        this.identityCode = identityCode;
    }
}
