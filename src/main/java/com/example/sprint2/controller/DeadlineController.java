package com.example.sprint2.controller;

import com.example.sprint2.service.DeadlineService;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;

/**
 * @author ZWForum
 * @description
 * @date 2022/7/6
 */
@RestController
@RequestMapping("/deadline")
public class DeadlineController {
    @Autowired
    DeadlineService deadlineService;

    @RequestMapping(value = "/set", method = RequestMethod.POST)
    public String setDeadline(@RequestParam String year, @RequestParam String date) throws SchedulerException {
        return deadlineService.setDeadline(year, Date.from(LocalDate.parse(date).atStartOfDay(ZoneId.systemDefault()).toInstant()));
    }

    @RequestMapping(value = "/get", method = RequestMethod.POST)
    public String getDeadline(@RequestParam String year) {
        return deadlineService.getDeadline(year);
    }
}
