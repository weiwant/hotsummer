package com.example.sprint2.service;

import com.example.sprint2.models.vo.SpecialReceiveVo;
import org.springframework.stereotype.Service;

/**
 * @author:wwq
 * @Date：2022/7/1：10:13
 */
@Service
public interface SpecialReceiveService {
    boolean saveJson(String json);
}
