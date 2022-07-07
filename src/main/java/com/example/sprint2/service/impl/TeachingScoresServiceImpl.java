package com.example.sprint2.service.impl;

import com.example.sprint2.dao.*;
import com.example.sprint2.models.vo.TeachingScoresVo;
import com.example.sprint2.mybatis.entity.SpecialTeacher;
import com.example.sprint2.mybatis.entity.TotalStatistics;
import com.example.sprint2.service.TeachingScoresService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author hy
 * @Date 2022/7/7
 * @Description
 */
@Service
public class TeachingScoresServiceImpl implements TeachingScoresService {

    @Autowired
    TeachingScoresDao teachingScoresDao;//总教分记录表
    @Autowired
    SpecialTeacherDao specialTeacherDao;//特殊工作量 BB
    @Autowired
    TeachingWorkloadStatisticsDao teachingWorkloadStatisticsDao;//教学工作量 BA1
    @Autowired
    PaperCoachingDao paperCoachingDao;//论文工作量 BA2
    @Autowired
    ExaminationDao examinationDao;//考务工作量 BA3

    /**
     * @author hy
     * @description 根据年份和教师姓名查询教分记录
     */
    @Override
    public List<TotalStatistics> selectScores(TeachingScoresVo teachingScoresVo){
        List<TotalStatistics> list=teachingScoresDao.selectScores(teachingScoresVo);
        return list;
    }

    @Override
    public boolean insertScores(List<String> teacherNames){
        boolean flag=true;
        TotalStatistics statistics=new TotalStatistics();
        Map<String,Double> BBScores=new HashMap<>();
        for (String teacherName : teacherNames) {
            statistics.setBa1(teachingWorkloadStatisticsDao.getTeachingScore(teacherName));//BA1
            statistics.setBa2(paperCoachingDao.getScore(teacherName));//BA2
            statistics.setBa3(examinationDao.getExaminationScore(teacherName)); //BA3
            BBScores=specialTeacherDao.getSpecialScores(teacherName);//BB类
            statistics.setBb1(BBScores.get("BB1"));
            statistics.setBb2(BBScores.get("BB2"));
            statistics.setBb3(BBScores.get("BB3"));
            statistics.setBb4(BBScores.get("BB4"));
            statistics.setBb5(BBScores.get("BB5"));
            statistics.setBb6(BBScores.get("BB6"));
            statistics.setBb7(BBScores.get("BB7"));
            statistics.setBb8(BBScores.get("BB8"));
            statistics.setBb9(BBScores.get("BB9"));
            statistics.setBb10(BBScores.get("BB10"));
            statistics.setBb11(BBScores.get("BB11"));
            statistics.setBb12(BBScores.get("BB12"));
            statistics.setBb13(BBScores.get("BB13"));
            statistics.setBb14(BBScores.get("BB14"));
            statistics.setBb15(BBScores.get("BB15"));
            flag=teachingScoresDao.insertScores(statistics);
        }
        return flag;
    }

}
