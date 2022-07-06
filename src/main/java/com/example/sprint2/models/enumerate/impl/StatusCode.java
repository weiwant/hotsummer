package com.example.sprint2.models.enumerate.impl;

import com.example.sprint2.models.enumerate.AuditStatus;
import lombok.Getter;

/**
 * @author ZWForum
 * @description
 * @date 2022/7/6
 */
@Getter
public enum StatusCode implements AuditStatus {
    COMMITTED("已提交"),
    SAVED("已保存"),
    AUDITED("已审核");
    private final String msg;

    StatusCode(String msg) {
        this.msg = msg;
    }
}
