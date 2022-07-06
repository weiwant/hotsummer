package com.example.sprint2.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.sprint2.models.vo.SpecialVo;

import java.lang.reflect.InvocationTargetException;

/**
 * @author hy
 * @Date 2022/7/5
 * @Description
 */

public interface SpecialJoinService {


    public IPage<SpecialVo> selectByConditions(SpecialVo specialVo) throws InvocationTargetException, IllegalAccessException;

    public String setSubmit(SpecialVo specialVo) throws InvocationTargetException, IllegalAccessException;
}
