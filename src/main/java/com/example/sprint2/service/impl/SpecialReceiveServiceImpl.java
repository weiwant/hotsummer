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
     * @Description：将json键值对数据赋给实体类，并插入。成功返回true，失败false。
     */
    @Override
    public boolean saveJson(String json) {
        boolean flag=true;
        JSONArray jsonArray= JSON.parseArray(json);
        for (Object o : jsonArray) {
            JSONObject jsOb=(JSONObject) o;//每次一个jsonObject对象
            SpecialWorkload specialWorkload=new SpecialWorkload();//一个实体类对象

            specialWorkload.setReportTime(LocalDate.parse(jsOb.getString("reportTime")));//申报时间
            specialWorkload.setDeclarantName(jsOb.getString("declarantName"));//申报人
            specialWorkload.setTeacherName(jsOb.getString("teacherName"));//教师姓名
            specialWorkload.setType(jsOb.getString("type"));//类型
            specialWorkload.setTeachingScores(Double.valueOf(jsOb.getString("teachingScores")));//教分
            specialWorkload.setAchievementName(jsOb.getString("achievementName"));//成果/作品/参赛项目/参评项目/论文/专著名称
            specialWorkload.setLevel(jsOb.getString("level"));//级别
            specialWorkload.setProjectCategory(jsOb.getString("projectCategory"));//课程/项目/奖项/竞赛/论文指导类别
            specialWorkload.setProjectName(jsOb.getString("projectName"));//课程/项目/奖项/竞赛详细名称
            specialWorkload.setAwardLevel(jsOb.getString("awardLevel"));//获奖等级
            specialWorkload.setAwardDate(jsOb.getString("awardDate"));//获奖/获评/出版日期
            specialWorkload.setProjectStatus(jsOb.getString("projectStatus"));//项目进展
            specialWorkload.setAwardApartment(jsOb.getString("awardApartment"));//授奖单位
            specialWorkload.setPublicationName(jsOb.getString("publicationName"));//刊物/出版社名称
            specialWorkload.setPublicationsNumber(jsOb.getString("publicationsNumber"));//刊物期数/出版版次
            specialWorkload.setIsbn(jsOb.getString("isbn"));//ISBN号
            specialWorkload.setBriefIntroduction(jsOb.getString("briefIntroduction"));//内容简介
            specialWorkload.setReceivingHonor(jsOb.getString("receivingHonor"));//所获荣誉
            specialWorkload.setGuidingStudentTeam(jsOb.getString("guidingStudentTeam"));//指导学生团队名
            specialWorkload.setGuidingStudentName(jsOb.getString("guidingStudentName"));//指导学生姓名
            specialWorkload.setGuidingStudentId(jsOb.getString("guidingStudentId"));//指导学生学号
            specialWorkload.setStatus(jsOb.getString("status"));//审核状态

            //插入数据库
            if(!specialSaveDao.save(specialWorkload)){
                flag=false;
            }
        }

        return flag;
    }
}
