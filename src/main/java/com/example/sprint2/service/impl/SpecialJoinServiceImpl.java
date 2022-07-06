package com.example.sprint2.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.sprint2.dao.SpecialJoinDao;
import com.example.sprint2.models.pojo.SpecialJoinResult;
import com.example.sprint2.service.SpecialJoinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author hy
 * @Date 2022/7/5
 * @Description
 */
@Service
public class SpecialJoinServiceImpl implements SpecialJoinService {

    @Autowired
    SpecialJoinDao specialJoinDao;

    /**
     * @author hy
     * @description 所有查询条件封装
     * */
    @Override
    public IPage<SpecialJoinResult> selectByConditions(SpecialJoinResult specialJoinResult){
        IPage<SpecialJoinResult> iPage= specialJoinDao.selectByConditions(specialJoinResult);
        return iPage;
    }

    @Override
    public String setSubmit(SpecialJoinResult specialJoinResult){
            String r = specialJoinDao.setSubmit(specialJoinResult);
            return r;
    }

}
