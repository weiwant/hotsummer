package com.example.sprint2.models.vo;

import com.example.sprint2.mybatis.entity.SpecialWorkload;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author:wwq
 * @Date：2022/7/1：10:03
 */
@Getter
@Setter
public class SpecialReceiveVo {
    private List<SpecialWorkload> data;
}
