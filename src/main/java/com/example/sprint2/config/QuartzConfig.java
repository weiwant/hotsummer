package com.example.sprint2.config;

import cn.hutool.core.lang.ClassScanner;
import cn.hutool.extra.spring.SpringUtil;
import com.example.sprint2.models.abc.Task;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.springframework.stereotype.Component;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Set;

/**
 * @author ZWForum
 * @description
 * @date 2022/7/5
 */
@Component
public class QuartzConfig {
    private static final Scheduler scheduler = SpringUtil.getBean("quartzScheduler");

    public static Scheduler getScheduler() {
        return scheduler;
    }

    public static void setJob() throws InstantiationException, IllegalAccessException, InvocationTargetException, SchedulerException, NoSuchMethodException {
        Set<Class<?>> classes = ClassScanner.scanPackageBySuper("com.example.sprint2.models.abc.sub", Task.class);
        Method trigger = Task.class.getDeclaredMethod("getTrigger");
        Method job = Task.class.getDeclaredMethod("getJobDetail");
        trigger.setAccessible(true);
        job.setAccessible(true);
        for (Class<?> clazz : classes) {
            Constructor<?> constructor = null;
            try {
                constructor = clazz.getDeclaredConstructor();
                constructor.setAccessible(true);
                Object obj = constructor.newInstance();
                scheduler.scheduleJob((JobDetail) job.invoke(obj), (Trigger) trigger.invoke(obj));
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
        }
    }
}
