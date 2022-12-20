package com.example.sprint2.service;

import com.example.sprint2.models.vo.ConfirmVo;

/**
 * @author ZWForum
 * @description
 * @date 2022/7/6
 */
public interface ConfirmService {
    public String updateConfirm(ConfirmVo confirmVo);
    public String confirmRecord(ConfirmVo confirmVo);
    public int getConfirmStatus(ConfirmVo confirmVo);
    public int[] getCount(ConfirmVo confirmVo);
}
