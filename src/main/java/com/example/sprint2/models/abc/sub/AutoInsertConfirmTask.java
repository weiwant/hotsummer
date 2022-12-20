package com.example.sprint2.models.abc.sub;

import com.example.sprint2.dao.UserDao;
import com.example.sprint2.dao.WorkloadConfirmDao;
import com.example.sprint2.models.abc.Task;
import com.example.sprint2.mybatis.entity.UserLogin;
import com.example.sprint2.mybatis.entity.WorkloadConfirm;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

/**
 * @author ZWForum
 * @description
 * @date 2022/7/6
 */
@Component
public class AutoInsertConfirmTask extends Task {
    @Autowired
    WorkloadConfirmDao workloadConfirmDao;
    @Autowired
    UserDao userDao;
    private final String jobName;
    private final Trigger trigger;
    private final JobDetail jobDetail;
    private static volatile AutoInsertConfirmTask instance;
    private static final Log logger = LogFactory.getLog(AutoInsertConfirmTask.class);

    @Override
    public String getJobName() {
        return this.jobName;
    }

    @Override
    public Trigger getTrigger() {
        return this.trigger;
    }

    @Override
    public JobDetail getJobDetail() {
        return this.jobDetail;
    }

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        WorkloadConfirm workloadConfirm = new WorkloadConfirm();
        workloadConfirm.setNaturalYear(String.valueOf(calendar.get(Calendar.YEAR)));
        Boolean isExists = workloadConfirmDao.selectIfExist(workloadConfirm);
        if (!isExists) {
            List<UserLogin> userLogins = userDao.selectAll();
            for (UserLogin userLogin : userLogins) {
                WorkloadConfirm confirm = new WorkloadConfirm();
                confirm.setTeacherId(userLogin.getUserid());
                confirm.setTeacherName(userLogin.getUsername());
                confirm.setNaturalYear(String.valueOf(calendar.get(Calendar.YEAR)));
                confirm.setConfirm(0);
                workloadConfirmDao.insertEntity(confirm);
            }
            logger.info("插入成功");
        } else {
            logger.info("已有记录");
        }
    }

    private AutoInsertConfirmTask() {
        this.jobName = "autoInsertConfirmTask";

        this.jobDetail = JobBuilder.newJob()
                .ofType(this.getClass())
                .withIdentity(this.jobName)
                .build();

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(calendar.get(Calendar.YEAR), Calendar.JANUARY, 1, 0, 0);
        this.trigger = TriggerBuilder.newTrigger()
                .withIdentity(this.jobName)
                .withSchedule(CalendarIntervalScheduleBuilder.calendarIntervalSchedule()
                        .withIntervalInYears(1)
                        .inTimeZone(TimeZone.getTimeZone("Asia/Shanghai")))
                .startAt(calendar.getTime())
                .build();

        instance = this;
    }

    public AutoInsertConfirmTask getInstance() {
        if (instance == null) {
            synchronized (AutoInsertConfirmTask.class) {
                if (instance == null) {
                    instance = new AutoInsertConfirmTask();
                }
            }
        }
        return instance;
    }
}
