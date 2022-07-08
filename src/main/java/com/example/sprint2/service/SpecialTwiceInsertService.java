package com.example.sprint2.service;

import com.example.sprint2.models.vo.SpecialVo;

import java.io.IOException;

/**
 * @author:wwq
 * @Date：2022/7/5：15:57
 */
public interface SpecialTwiceInsertService {
    Boolean specialSave(SpecialVo specialVo) throws IOException;

    boolean specialUpload(SpecialVo specialVo, Boolean willDelete) throws IOException;

    boolean specialMark(SpecialVo specialVo) throws IOException;
}
