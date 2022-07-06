package com.example.sprint2.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.sprint2.models.pojo.SpecialJoinResult;
import org.springframework.stereotype.Service;

/**
 * @author hy
 * @Date 2022/7/5
 * @Description
 */

public interface SpecialJoinService {


    public IPage<SpecialJoinResult> selectByConditions(SpecialJoinResult specialJoinResult);

    public String setSubmit(SpecialJoinResult specialJoinResult);
}
