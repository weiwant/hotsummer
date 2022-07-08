package com.example.sprint2.models.abc.sub;

import com.example.sprint2.dao.SpecialJoinDao;
import com.example.sprint2.models.abc.Task;
import com.example.sprint2.models.enumerate.impl.StatusCode;
import com.example.sprint2.models.pojo.SpecialJoinResult;
import com.example.sprint2.mybatis.entity.SpecialProject;
import com.example.sprint2.mybatis.mapper.SpecialProjectMapper;
import lombok.Getter;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @author ZWForum
 * @description
 * @date 2022/7/5
 */
@Getter
@Component
public class AutoCommitTask extends Task {
    @Autowired
    SpecialJoinDao specialJoinDao;
    @Autowired
    SpecialProjectMapper specialProjectMapper;

    private final String jobName;
    private Trigger trigger;
    private JobDetail jobDetail;
    private Date deadline;
    private static volatile AutoCommitTask instance;
    private static final Log logger = LogFactory.getLog(AutoCommitTask.class);

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
        SpecialJoinResult specialJoinResult = new SpecialJoinResult();
        specialJoinResult.setStatus(StatusCode.SAVED.getMsg());
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        specialJoinResult.setYear(String.valueOf(calendar.get(Calendar.YEAR)));
        List<SpecialJoinResult> specialJoinResults = specialJoinDao.selectListByConditions(specialJoinResult, null);
        SpecialProject specialProject = new SpecialProject();
        specialProject.setStatus(StatusCode.COMMITTED.getMsg());
        for (SpecialJoinResult result : specialJoinResults) {
            specialProject.setId(result.getId());
            specialProjectMapper.updateById(specialProject);
            logger.info("项目-id:" + result.getId() + " 提交成功");
        }
    }

    private AutoCommitTask() {
        this.jobName = "autoCommitTask";

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.setLenient(true);
        calendar.set(calendar.get(Calendar.YEAR), Calendar.DECEMBER, 31, 23, 59);
        this.deadline = calendar.getTime();

        this.jobDetail = JobBuilder.newJob()
                .ofType(this.getClass())
                .withIdentity(this.jobName)
                .build();

        this.trigger = TriggerBuilder.newTrigger()
                .withIdentity(this.jobName)
                .withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(1))
                .startAt(this.deadline)
                .build();

        instance = this;
    }

    public AutoCommitTask setDeadline(Date date) {
        this.deadline = date;
        this.jobDetail = JobBuilder.newJob()
                .ofType(this.getClass())
                .withIdentity(this.jobName)
                .build();
        this.trigger = TriggerBuilder.newTrigger()
                .withIdentity(this.jobName)
                .withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(1))
                .startAt(this.deadline)
                .build();
        return this;
    }

    public static AutoCommitTask getInstance() {
        if (instance == null) {
            synchronized (AutoCommitTask.class) {
                if (instance == null) {
                    instance = new AutoCommitTask();
                }
            }
        }
        return instance;
    }
}
