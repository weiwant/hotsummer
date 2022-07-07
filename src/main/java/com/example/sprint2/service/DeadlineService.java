package com.example.sprint2.service;

import org.quartz.SchedulerException;

import java.util.Date;

/**
 * @author ZWForum
 * @description
 * @date 2022/7/6
 */
public interface DeadlineService {
    String getDeadline(String year);

    String setDeadline(String year, Date date) throws SchedulerException;
}
