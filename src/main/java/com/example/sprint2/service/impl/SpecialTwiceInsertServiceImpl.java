package com.example.sprint2.service.impl;

import com.example.sprint2.dao.SpecialProjectDao;
import com.example.sprint2.dao.SpecialTeacherDao;
import com.example.sprint2.models.pojo.TeacherAndOrder;
import com.example.sprint2.models.vo.SpecialVo;
import com.example.sprint2.mybatis.entity.SpecialProject;
import com.example.sprint2.mybatis.entity.SpecialTeacher;
import com.example.sprint2.service.FileDealService;
import com.example.sprint2.service.SpecialTwiceInsertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

/**
 * @author:wwq
 * @Date：2022/7/5：15:58
 */
@Service
public class SpecialTwiceInsertServiceImpl implements SpecialTwiceInsertService {
    @Autowired
    SpecialProjectDao specialProjectDao;
    @Autowired
    SpecialTeacherDao specialTeacherDao;

    @Autowired
    FileDealService fileDealService;

    /**
     * @Author：wwq
     * @Return：
     * @Url:
     * @Description：特殊工作量插入数据库。把specialVo分成两个实体类，调用对应Dao层插入方法
     */
    @Override
    public Boolean specialSave(SpecialVo specialVo) {
        boolean flag = true;

        SpecialProject specialProject = new SpecialProject();//Project实体类。

        //处理Project
        specialProject.setReportTime(LocalDate.now());//申报时间
        specialProject.setDeclarantName(specialVo.getDeclarantName());//申报人

        specialProject.setType(specialVo.getType());//bb类型-----》Teacher表需要

        specialProject.setAchievementName(specialVo.getAchievementName());//成果/作品/参赛项目/参评项目/论文/专著名称
        specialProject.setLevel(specialVo.getLevel());//级别
        specialProject.setProjectCategory(specialVo.getProjectCategory());//课程/项目/奖项/竞赛/论文指导类别
        specialProject.setProjectName(specialVo.getProjectName());//课程/项目/奖项/竞赛详细名称
        specialProject.setAwardLevel(specialVo.getAwardLevel());//获奖等级
        specialProject.setAwardDate(specialVo.getAwardDate());//获奖/获评/出版日期
        specialProject.setProjectStatus(specialVo.getProjectStatus());//项目进展
        specialProject.setAwardApartment(specialVo.getAwardApartment());//授奖单位
        specialProject.setPublicationName(specialVo.getPublicationName());//刊物/出版社名称
        specialProject.setPublicationsNumber(specialVo.getPublicationsNumber());//刊物期数/出版版次
        specialProject.setIsbn(specialVo.getIsbn());//ISBN号
        specialProject.setBriefIntroduction(specialVo.getBriefIntroduction());//内容简介
        specialProject.setReceivingHonor(specialVo.getReceivingHonor());//所获荣誉
        specialProject.setGuidingStudentTeam(specialVo.getGuidingStudentTeam());//指导学生团队名
        specialProject.setGuidingStudentName(specialVo.getGuidingStudentName());//指导学生姓名
        specialProject.setGuidingStudentId(specialVo.getGuidingStudentId());//指导学生学号

        specialProject.setStatus("已保存");//审核状态
        specialProject.setRemarks(specialVo.getRemarks());//备注

        // TODO: 2022/7/5 文件路径

        Integer projectId = specialProjectDao.saveSpecialProject(specialProject);


        List<TeacherAndOrder> teachers = specialVo.getSomePeople();
        for (TeacherAndOrder teacher : teachers) {
            SpecialTeacher specialTeacher = new SpecialTeacher();//Teacher实体类。
            specialTeacher.setProjectId(projectId);//项目id
            specialTeacher.setType(specialVo.getType());//bb类型
            specialTeacher.setTeacherName(teacher.getTeacherName());//教师姓名
            specialTeacher.setAuthorOrder(teacher.getAuthorOrder());//排序情况
            // TODO: 2022/7/5 教分

            flag = specialTeacherDao.saveSpecialTeacher(specialTeacher);

        }

        fileDealService.setPath(projectId);


        return flag;
    }

    /**
     * @Author：wwq
     * @Return：
     * @Url:
     * @Description：特殊工作量更新。根据项目id，更新Project表和Teacher表（有可能有插入操作）。
     */
    @Override
    public boolean specialUpload(SpecialVo specialVo) throws IOException {
        boolean flag = true;

        SpecialProject specialProject = new SpecialProject();//Project实体类。

        specialProject.setId(specialVo.getId());//项目id
        //处理Project
        specialProject.setReportTime(specialVo.getReportTime());//申报时间
        specialProject.setDeclarantName(specialVo.getDeclarantName());//申报人

        specialProject.setType(specialVo.getType());//bb类型-----》Teacher表需要

        specialProject.setAchievementName(specialVo.getAchievementName());//成果/作品/参赛项目/参评项目/论文/专著名称
        specialProject.setLevel(specialVo.getLevel());//级别
        specialProject.setProjectCategory(specialVo.getProjectCategory());//课程/项目/奖项/竞赛/论文指导类别
        specialProject.setProjectName(specialVo.getProjectName());//课程/项目/奖项/竞赛详细名称
        specialProject.setAwardLevel(specialVo.getAwardLevel());//获奖等级
        specialProject.setAwardDate(specialVo.getAwardDate());//获奖/获评/出版日期
        specialProject.setProjectStatus(specialVo.getProjectStatus());//项目进展
        specialProject.setAwardApartment(specialVo.getAwardApartment());//授奖单位
        specialProject.setPublicationName(specialVo.getPublicationName());//刊物/出版社名称
        specialProject.setPublicationsNumber(specialVo.getPublicationsNumber());//刊物期数/出版版次
        specialProject.setIsbn(specialVo.getIsbn());//ISBN号
        specialProject.setBriefIntroduction(specialVo.getBriefIntroduction());//内容简介
        specialProject.setReceivingHonor(specialVo.getReceivingHonor());//所获荣誉
        specialProject.setGuidingStudentTeam(specialVo.getGuidingStudentTeam());//指导学生团队名
        specialProject.setGuidingStudentName(specialVo.getGuidingStudentName());//指导学生姓名
        specialProject.setGuidingStudentId(specialVo.getGuidingStudentId());//指导学生学号

        specialProject.setStatus(specialVo.getStatus());//审核状态
        specialProject.setRemarks(specialVo.getRemarks());//备注
        // TODO: 2022/7/5 文件路径
//        fileDealService.setPath(specialVo.getId());
//        specialProject.setFilePath(msg);

        flag = specialProjectDao.uploadSpecialProject(specialProject);


        //删除ProjectId里的所有教师
        specialTeacherDao.deleteByProjectId(specialVo.getId());
        //重新插入
        List<TeacherAndOrder> teachers = specialVo.getSomePeople();
        for (TeacherAndOrder teacher : teachers) {
            SpecialTeacher specialTeacher = new SpecialTeacher();//Teacher实体类。
            specialTeacher.setProjectId(specialVo.getId());//项目id
            specialTeacher.setType(specialVo.getType());//bb类型
            specialTeacher.setTeacherName(teacher.getTeacherName());//教师姓名
            specialTeacher.setAuthorOrder(teacher.getAuthorOrder());//排序情况
            // TODO: 2022/7/5 教分

            specialTeacherDao.saveSpecialTeacher(specialTeacher);
        }

        fileDealService.renameFile(specialVo.getId());
        if (specialVo.getFiles() != null) {
            fileDealService.uploadFileById(specialVo.getFiles(), specialVo.getId());
        }
        return flag;
    }

    /**
     * @Author：wwq
     * @Return：
     * @Url:
     * @Description：管理员打分。
     */
    @Override
    public boolean specialMark(SpecialVo specialVo) {
        boolean flag = true;
        List<TeacherAndOrder> teachers = specialVo.getSomePeople();
        for (TeacherAndOrder teacher : teachers) {
            SpecialTeacher specialTeacher = new SpecialTeacher();
            specialTeacher.setProjectId(specialVo.getId());//项目id
            specialTeacher.setType(specialVo.getType());//BB类型
            specialTeacher.setTeacherName(teacher.getTeacherName());//教师姓名
            specialTeacher.setAuthorOrder(teacher.getAuthorOrder());//教师排名
            specialTeacher.setTeachingScores(teacher.getTeachingScores());//教分

            specialTeacherDao.markSpecialTeacher(specialTeacher);
        }

        return flag;
    }


}
