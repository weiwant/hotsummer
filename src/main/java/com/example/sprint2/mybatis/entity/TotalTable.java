package com.example.sprint2.mybatis.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.example.sprint2.mybatis.mapper.ExaminationWorkloadMapper;
import com.example.sprint2.mybatis.mapper.PaperCoachingWorkloadMapper;
import com.example.sprint2.mybatis.mapper.TeachingWorkloadStatisticsMapper;
import com.example.sprint2.utils.annotations.ForeignKey;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author ZWForum
 * @since 2022-06-29
 */
@Getter
@Setter
@TableName("total_table")
public class TotalTable implements Serializable {

    private static final long serialVersionUID = 1L;

    @ForeignKey(value = TeachingWorkloadStatistics.class, fieldName = "id", tableMapper = TeachingWorkloadStatisticsMapper.class)
    @TableField("academic_work_id")
    private Integer academicWorkId;

    @ForeignKey(value = PaperCoachingWorkload.class, fieldName = "id", tableMapper = PaperCoachingWorkloadMapper.class)
    @TableField("paper_work_id")
    private Integer paperWorkId;

    @ForeignKey(value = ExaminationWorkload.class, fieldName = "id", tableMapper = ExaminationWorkloadMapper.class)
    @TableField("examination_work_id")
    private Integer examinationWorkId;

    @TableField("natural_year")
    private String naturalYear;

    @TableField("teacher_name")
    private String teacherName;


}
