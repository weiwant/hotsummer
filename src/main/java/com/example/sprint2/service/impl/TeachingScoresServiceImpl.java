package com.example.sprint2.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.sprint2.dao.*;
import com.example.sprint2.models.vo.TeachingScoresVo;
import com.example.sprint2.mybatis.entity.TotalStatistics;
import com.example.sprint2.mybatis.mapper.TotalStatisticsMapper;
import com.example.sprint2.service.TeachingScoresService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author hy
 * @Date 2022/7/7
 * @Description
 */
@Service
public class TeachingScoresServiceImpl implements TeachingScoresService {

    @Autowired
    UserDao userDao;//获取用户列表

    @Autowired
    TotalStatisticsMapper totalStatisticsMapper;

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
    public List<TotalStatistics> selectScores(TeachingScoresVo teachingScoresVo) {
        List<TotalStatistics> list = teachingScoresDao.selectScores(teachingScoresVo);
        return list;
    }

    /**
     * @Author：wwq
     * @Return：
     * @Url:
     * @Description：从4个表查询所有教师教分，并汇总到总教分表。
     */
    @Override
    public IPage<TotalStatistics> insertScores(TeachingScoresVo teachingScoresVo) throws IllegalAccessException {
        List<String> teacherNames = userDao.getList();
        boolean flag = true;
        TotalStatistics statistics = new TotalStatistics();
        Map<String, Double> BBScores = new HashMap<>();
        for (String teacherName : teacherNames) {
            statistics.setTeacherName(teacherName);
            statistics.setYear(teachingScoresVo.getYear());//年份
            statistics.setBa1(teachingWorkloadStatisticsDao.getTeachingScore(teacherName));//BA1
            statistics.setBa2(paperCoachingDao.getScore(teacherName));//BA2
            statistics.setBa3(examinationDao.getExaminationScore(teacherName)); //BA3
            BBScores = specialTeacherDao.getSpecialScores(teacherName);//BB类
            statistics.setBb1(BBScores.get("BB1") != null ? BBScores.get("BB1") : new Double(0));
            statistics.setBb2(BBScores.get("BB2") != null ? BBScores.get("BB2") : new Double(0));
            statistics.setBb3(BBScores.get("BB3") != null ? BBScores.get("BB3") : new Double(0));
            statistics.setBb4(BBScores.get("BB4") != null ? BBScores.get("BB4") : new Double(0));
            statistics.setBb5(BBScores.get("BB5") != null ? BBScores.get("BB5") : new Double(0));
            statistics.setBb6(BBScores.get("BB6") != null ? BBScores.get("BB6") : new Double(0));
            statistics.setBb7(BBScores.get("BB7") != null ? BBScores.get("BB7") : new Double(0));
            statistics.setBb8(BBScores.get("BB8") != null ? BBScores.get("BB8") : new Double(0));
            statistics.setBb9(BBScores.get("BB9") != null ? BBScores.get("BB9") : new Double(0));
            statistics.setBb10(BBScores.get("BB10") != null ? BBScores.get("BB10") : new Double(0));
            statistics.setBb11(BBScores.get("BB11") != null ? BBScores.get("BB11") : new Double(0));
            statistics.setBb12(BBScores.get("BB12") != null ? BBScores.get("BB12") : new Double(0));
            statistics.setBb13(BBScores.get("BB13") != null ? BBScores.get("BB13") : new Double(0));
            statistics.setBb14(BBScores.get("BB14") != null ? BBScores.get("BB14") : new Double(0));
            statistics.setBb15(BBScores.get("BB15") != null ? BBScores.get("BB15") : new Double(0));
            Double total = new Double(0);
            total = (statistics.getBa1() != null ? statistics.getBa1() : 0)
                    + (statistics.getBa2() != null ? statistics.getBa2() : 0) + (statistics.getBa3() != null ? statistics.getBa3() : 0)
                    + (statistics.getBb1() != null ? statistics.getBb1() : 0) + (statistics.getBb2() != null ? statistics.getBb2() : 0)
                    + (statistics.getBb3() != null ? statistics.getBb3() : 0) + (statistics.getBb4() != null ? statistics.getBb4() : 0)
                    + (statistics.getBb5() != null ? statistics.getBb5() : 0) + (statistics.getBb6() != null ? statistics.getBb6() : 0)
                    + (statistics.getBb7() != null ? statistics.getBb7() : 0) + (statistics.getBb8() != null ? statistics.getBb8() : 0)
                    + (statistics.getBb9() != null ? statistics.getBb9() : 0) + (statistics.getBb10() != null ? statistics.getBb10() : 0)
                    + (statistics.getBb11() != null ? statistics.getBb11() : 0) + (statistics.getBb12() != null ? statistics.getBb12() : 0)
                    + (statistics.getBb13() != null ? statistics.getBb13() : 0) + (statistics.getBb14() != null ? statistics.getBb14() : 0)
                    + (statistics.getBb15() != null ? statistics.getBb15() : 0);
            statistics.setTotal(total);
            flag = teachingScoresDao.insertScores(statistics);
        }

        IPage<TotalStatistics> totalStatisticsIPage = teachingScoresDao.select(teachingScoresVo);
        List<Field> fields = Arrays.stream(TotalStatistics.class.getDeclaredFields()).filter(field -> field.getType() == Double.class).collect(Collectors.toList());
        for (TotalStatistics totalStatistics : totalStatisticsIPage.getRecords()) {
            for (Field field : fields) {
                field.setAccessible(true);
                field.set(totalStatistics, BigDecimal.valueOf((Double) field.get(totalStatistics)).setScale(2, RoundingMode.HALF_UP).doubleValue());
            }
        }
        return totalStatisticsIPage;

    }

}
