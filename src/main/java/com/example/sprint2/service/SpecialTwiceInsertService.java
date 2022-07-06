package com.example.sprint2.service;

import com.example.sprint2.models.vo.SpecialVo;

/**
 * @author:wwq
 * @Date：2022/7/5：15:57
 */
public interface SpecialTwiceInsertService {
    Boolean specialSave(SpecialVo specialVo);

    boolean specialUpload(SpecialVo specialVo);

    boolean specialMark(SpecialVo specialVo);
}
