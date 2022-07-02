package com.example.sprint2.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.sprint2.dao.SpecialSaveDao;
import com.example.sprint2.models.vo.SpecialReceiveVo;
import com.example.sprint2.mybatis.entity.SpecialWorkload;
import com.example.sprint2.service.SpecialReceiveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

/**
 * @author:wwq
 * @Date：2022/7/1：10:14
 */
@Service
public class SpecialReceiveServiceImpl implements SpecialReceiveService {
    @Autowired
    SpecialSaveDao specialSaveDao;

    /**
     * @Author：wwq
     * @Return：
     * @Url:
     * @Description：
     */
    @Override
    public boolean save(SpecialReceiveVo data) {
        boolean flag=true;
        //获取List《》
        List<SpecialWorkload> list=data.getData();
        for (SpecialWorkload workload:list ) {

            SpecialWorkload specialWorkload=new SpecialWorkload();//一个实体类对象

            specialWorkload.setReportTime(workload.getReportTime());//申报时间
            specialWorkload.setDeclarantName(workload.getDeclarantName());//申报人
            specialWorkload.setTeacherName(workload.getTeacherName());//教师姓名
            specialWorkload.setType(workload.getType());//类型
            specialWorkload.setTeachingScores(workload.getTeachingScores());//教分
            specialWorkload.setAchievementName(workload.getAchievementName());//成果/作品/参赛项目/参评项目/论文/专著名称
            specialWorkload.setLevel(workload.getLevel());//级别
            specialWorkload.setProjectCategory(workload.getProjectCategory());//课程/项目/奖项/竞赛/论文指导类别
            specialWorkload.setProjectName(workload.getProjectName());//课程/项目/奖项/竞赛详细名称
            specialWorkload.setAwardLevel(workload.getAwardLevel());//获奖等级
            specialWorkload.setAwardDate(workload.getAwardDate());//获奖/获评/出版日期
            specialWorkload.setProjectStatus(workload.getProjectStatus());//项目进展
            specialWorkload.setAwardApartment(workload.getAwardApartment());//授奖单位
            specialWorkload.setPublicationName(workload.getPublicationName());//刊物/出版社名称
            specialWorkload.setPublicationsNumber(workload.getPublicationsNumber());//刊物期数/出版版次
            specialWorkload.setIsbn(workload.getIsbn());//ISBN号
            specialWorkload.setBriefIntroduction(workload.getBriefIntroduction());//内容简介
            specialWorkload.setReceivingHonor(workload.getReceivingHonor());//所获荣誉
            specialWorkload.setGuidingStudentTeam(workload.getGuidingStudentTeam());//指导学生团队名
            specialWorkload.setGuidingStudentName(workload.getGuidingStudentName());//指导学生姓名
            specialWorkload.setGuidingStudentId(workload.getGuidingStudentId());//指导学生学号
            specialWorkload.setStatus(workload.getStatus());//审核状态

            //插入数据库
            if(!specialSaveDao.save(workload)){
                flag=false;
            }
        }

        return flag;
    }
}
