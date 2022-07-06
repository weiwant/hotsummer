package com.example.sprint2.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.sprint2.models.pojo.SpecialJoinResult;
import com.example.sprint2.mybatis.entity.SpecialProject;
import com.example.sprint2.mybatis.entity.SpecialTeacher;
import com.example.sprint2.mybatis.mapper.SpecialJoinMapper;
import com.example.sprint2.mybatis.mapper.SpecialProjectMapper;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @author hy
 * @Date 2022/7/5
 * @Description
 */
@Repository
public class SpecialJoinDao {
    @Autowired
    SpecialJoinMapper specialJoinMapper;

    @Autowired
    SpecialProjectMapper specialProjectMapper;

    public List<SpecialJoinResult> selectListByConditions(SpecialJoinResult specialJoinResult) {
        List<SpecialJoinResult> results = specialJoinMapper.selectJoinList(SpecialJoinResult.class, new MPJLambdaWrapper<SpecialProject>()
                .selectAll(SpecialProject.class)
                .select(SpecialTeacher::getTeacherName, SpecialTeacher::getAuthorOrder, SpecialTeacher::getTeachingScores)
                .innerJoin(SpecialTeacher.class, SpecialTeacher::getProjectId, SpecialProject::getId)
                .like(specialJoinResult.getYear() != null, SpecialProject::getReportTime, specialJoinResult.getYear())
                .eq(specialJoinResult.getTeacherName() != null, SpecialTeacher::getTeacherName, specialJoinResult.getTeacherName())
                .eq(specialJoinResult.getType() != null, SpecialTeacher::getType, specialJoinResult.getType())
                .eq(specialJoinResult.getStatus() != null, SpecialProject::getStatus, specialJoinResult.getStatus())
                .eq(specialJoinResult.getDeclarantName() != null, SpecialProject::getDeclarantName, specialJoinResult.getDeclarantName()));
        results.forEach(specialJoinResult1 -> {
            String filePath = specialJoinResult1.getFilePath();
            File fileFolder = new File(filePath);
            File[] files = fileFolder.listFiles();
            List<String> fileNames = new ArrayList<>();
            for (File file : files) {
                String fileName = file.getName();
                fileNames.add(fileName);
            }
            specialJoinResult1.setFileName(fileNames);
            for (String name : specialJoinResult1.getFileName()) {
                System.out.println(name);
            }
        });
        return results;
    }

    /**
     * @author hy
     * @description 动态连表查询 所有查询条件封装 （管理员查询记录）
     */
    public IPage<SpecialJoinResult> selectByConditions(SpecialJoinResult specialJoinResult) {
        IPage<SpecialJoinResult> iPage = specialJoinMapper.selectJoinPage(new Page<>(specialJoinResult.getPageNumber(), 5), SpecialJoinResult.class, new MPJLambdaWrapper<SpecialProject>()
                .selectAll(SpecialProject.class)
                .select(SpecialTeacher::getTeacherName, SpecialTeacher::getAuthorOrder, SpecialTeacher::getTeachingScores)
                .innerJoin(SpecialTeacher.class, SpecialTeacher::getProjectId, SpecialProject::getId)
                .like(specialJoinResult.getYear() != null, SpecialProject::getReportTime, specialJoinResult.getYear())
                .eq(specialJoinResult.getTeacherName() != null, SpecialTeacher::getTeacherName, specialJoinResult.getTeacherName())
                .eq(specialJoinResult.getType() != null, SpecialTeacher::getType, specialJoinResult.getType())
                .eq(specialJoinResult.getStatus() != null, SpecialProject::getStatus, specialJoinResult.getStatus())
                .eq(specialJoinResult.getDeclarantName() != null, SpecialProject::getDeclarantName, specialJoinResult.getDeclarantName())

        );
        //获取文件路径下所有附件的文件名，放进List并赋值给返回对象
        List<SpecialJoinResult> list = iPage.getRecords();
        for (SpecialJoinResult specialJoinResult1 : list) {
            String filePath = specialJoinResult1.getFilePath();
            File fileFolder = new File(filePath);
            File[] files = fileFolder.listFiles();
            List<String> fileNames = new ArrayList<>();
            for (File file : files) {
                String fileName = file.getName();
                fileNames.add(fileName);
            }
            specialJoinResult1.setFileName(fileNames);
        }
        iPage.setRecords(list);
        return iPage;
    }

    /**
     * @author hy
     * @description 提交：修改该教师该年度所有已保存的记录状态为已提交
     */
    public String setSubmit(SpecialJoinResult specialJoinResult) {
        List<SpecialJoinResult> list = specialJoinMapper.selectJoinList(SpecialJoinResult.class, new MPJLambdaWrapper<SpecialProject>()
                .select(SpecialProject::getId, SpecialProject::getStatus)
                .innerJoin(SpecialTeacher.class, SpecialTeacher::getProjectId, SpecialProject::getId)
                .like(SpecialProject::getReportTime, specialJoinResult.getYear())
                .eq(SpecialProject::getStatus, "已保存")
                .eq(SpecialProject::getDeclarantName, specialJoinResult.getDeclarantName())
        );
        String submitResult = null;
        int flag1;

        if (list.size() == 0) {
            submitResult = "无内容";      //查找无内容，无法提交
        } else {                         //有内容
            boolean flag = true;
            SpecialProject specialProject = new SpecialProject();
            for (SpecialJoinResult s : list) {
                int id1 = s.getId();
                specialProject.setId(id1);
                specialProject.setStatus("已提交");
                flag1 = specialProjectMapper.updateById(specialProject);     //反映修改结果
                if (flag1 == 0) {
                    flag = false;
                }
            }
            if (flag) {
                submitResult = "提交成功";
            } else {
                submitResult = "提交失败";
            }
        }
        return submitResult;
    }

    /**
     * @author hy
     * @description 管理员：通过动态更新特殊工作量表
     */
    /*public String updateManager(SpecialJoinResult specialJoinResult){

    }*/

}

