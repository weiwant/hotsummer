package com.example.sprint2.service.impl;

import com.example.sprint2.config.QuartzConfig;
import com.example.sprint2.dao.DeadlineDao;
import com.example.sprint2.models.abc.sub.AutoCommitTask;
import com.example.sprint2.models.enumerate.impl.ResponseCode;
import com.example.sprint2.mybatis.entity.YearlyDeadline;
import com.example.sprint2.service.DeadlineService;
import com.example.sprint2.utils.Result;
import org.quartz.JobKey;
import org.quartz.SchedulerException;
import org.quartz.TriggerKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

/**
 * @author ZWForum
 * @description
 * @date 2022/7/6
 */
@Service
public class DeadlineServiceImpl implements DeadlineService {
    @Autowired
    DeadlineDao deadlineDao;

    @Override
    public String getDeadline(String year) {
        YearlyDeadline yearlyDeadline = new YearlyDeadline();
        yearlyDeadline.setYear(year);
        LocalDate localDate = deadlineDao.getDeadline(yearlyDeadline);
        if (localDate != null) {
            return new Result(ResponseCode.SUCCESS, localDate).toString();
        } else {
            return new Result(ResponseCode.NoContentFailure).toString();
        }
    }

    @Override
    public String setDeadline(String year, Date date) throws SchedulerException {
        YearlyDeadline yearlyDeadline = new YearlyDeadline();
        yearlyDeadline.setYear(year);
        yearlyDeadline.setDeadline(date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        deadlineDao.saveOrUpdate(yearlyDeadline);
        AutoCommitTask.getInstance().setDeadline(date);
        if (QuartzConfig.getScheduler().checkExists(JobKey.jobKey(AutoCommitTask.getInstance().getJobName()))
                || QuartzConfig.getScheduler().checkExists(TriggerKey.triggerKey(AutoCommitTask.getInstance().getJobName()))) {
            QuartzConfig.getScheduler().rescheduleJob(TriggerKey.triggerKey(AutoCommitTask.getInstance().getJobName()), AutoCommitTask.getInstance().getTrigger());
        } else {
            QuartzConfig.getScheduler().scheduleJob(AutoCommitTask.getInstance().getJobDetail(), AutoCommitTask.getInstance().getTrigger());
        }
        return new Result(ResponseCode.SUCCESS).toString();
    }
}
