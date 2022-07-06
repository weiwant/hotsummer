package com.example.sprint2;

import com.example.sprint2.config.QuartzConfig;
import com.tangzc.mpe.actable.EnableAutoTable;
import org.quartz.SchedulerException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.lang.reflect.InvocationTargetException;

@EnableAutoTable
@EnableScheduling
@SpringBootApplication
public class Sprint2Application {

    public static void main(String[] args) throws SchedulerException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {
        SpringApplication.run(Sprint2Application.class, args);
        QuartzConfig.setJob();
    }

}
