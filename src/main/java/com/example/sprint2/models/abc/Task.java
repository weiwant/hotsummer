package com.example.sprint2.models.abc;

import org.quartz.JobDetail;
import org.quartz.Trigger;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * @author ZWForum
 * @description 定时任务抽象类
 * @date 2022/7/5
 */
public abstract class Task extends QuartzJobBean {
    public abstract String getJobName();

    public abstract Trigger getTrigger();

    public abstract JobDetail getJobDetail();
}
