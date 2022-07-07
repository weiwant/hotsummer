package com.example.sprint2.dao;

import com.example.sprint2.mybatis.entity.YearlyDeadline;
import com.example.sprint2.mybatis.mapper.YearlyDeadlineMapper;
import com.example.sprint2.mybatis.service.YearlyDeadlineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

/**
 * @author ZWForum
 * @description
 * @date 2022/7/6
 */
@Repository
public class DeadlineDao {
    @Autowired
    YearlyDeadlineMapper yearlyDeadlineMapper;
    @Autowired
    YearlyDeadlineService yearlyDeadlineService;

    public Boolean saveOrUpdate(YearlyDeadline yearlyDeadline) {
        return yearlyDeadlineService.saveOrUpdate(yearlyDeadline);
    }

    public LocalDate getDeadline(YearlyDeadline yearlyDeadline) {
        YearlyDeadline deadline = yearlyDeadlineMapper.selectById(yearlyDeadline.getYear());
        return deadline != null ? deadline.getDeadline() : null;
    }
}
