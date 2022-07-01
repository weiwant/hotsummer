package com.example.sprint2.models.pojo;

import com.example.sprint2.mybatis.entity.ExaminationWorkload;
import com.example.sprint2.mybatis.entity.PaperCoachingWorkload;
import com.example.sprint2.mybatis.entity.TeachingWorkloadStatistics;
import com.example.sprint2.utils.TimeParser;

/**
 * @author ZWForum
 * @description
 * @date 2022/7/1
 */
public class TableResolution extends TimeParser {
    public static String getNaturalYear(TeachingWorkloadStatistics teachingWorkloadStatistics) {
        return getNaturalYear(teachingWorkloadStatistics.getAcademicYear(), teachingWorkloadStatistics.getSemester().intValue());
    }

    public static String getNaturalYear(PaperCoachingWorkload paperCoachingWorkload) {
        return getNaturalYear(paperCoachingWorkload.getAcademicYear(), paperCoachingWorkload.getSemester().intValue());
    }

    public static String getNaturalYear(ExaminationWorkload examinationWorkload) {
        return getNaturalYear(examinationWorkload.getAcademicYear(), examinationWorkload.getSemester().intValue());
    }

    public static Object getFieldValue(Object obj) {
        return obj;
    }
}
