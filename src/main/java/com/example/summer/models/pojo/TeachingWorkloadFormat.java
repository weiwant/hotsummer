package com.example.summer.models.pojo;


import com.example.summer.service.SendStatisticTitleService;
import com.example.summer.service.impl.SendStatisticTitleServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author 24047
 * @date 2022/6/27
 */
public class TeachingWorkloadFormat {
    /**
     * @author 24047
     * @date 2022/6/27
     * @param 教师端查询数据需要的参数最多包括：一串中文名，一串英文名，一个教师姓名，一个开始学年，一个结束学年，一个学期
     * @Description 值得注意的是，这个类将来会被扩展，我相信它会的
     */
    private List<String> EnglishName = new ArrayList<>();
    private List<String> ChineseName = new ArrayList<>();
    private String teacherName;
    private String startYear;
    private String endYear;
    private int semester;

    /**
     * @return
     * @author 24047
     * @date 2022/6/28
     * @description 静态地为ChineseName和EnglishName赋值
     */
    public TeachingWorkloadFormat() {

        this.ChineseName.add("课程号");
        this.ChineseName.add("课程名称");
        this.ChineseName.add("教学班");
        this.ChineseName.add("开课学院");
        this.ChineseName.add("学分");
        this.ChineseName.add("课程性质");
        this.ChineseName.add("年级");
        this.ChineseName.add("专业");
        this.ChineseName.add("上课老师");
        this.ChineseName.add("职称");
        this.ChineseName.add("上课人数");
        this.ChineseName.add("计算用学时");
        this.ChineseName.add("合课单位");
        this.ChineseName.add("备注");
        this.ChineseName.add("实验安排");
        this.ChineseName.add("其他教师");
        this.ChineseName.add("辅助");
        this.ChineseName.add("课程性质说明");
        this.ChineseName.add("是否卓工或弘毅");
        this.ChineseName.add("是否全英文");
        SendStatisticTitleService service = new SendStatisticTitleServiceImpl();
        String[] title = {"学年", "学期", "课程号", "课程名称", "教学班", "开课学院", "计划学院", "学分", "课程性质", "年级", "专业", "上课老师", "职称", "上课人数", "理论", "上机", "实验", "实践", "计划用学时", "合课单位", "备注", "实验安排", "其他教师", "教分（BA1\\ 3\\ 15）原始分", "BA1系数", "教分（BA1\\ 3\\ 15）", "辅助", "课程性质说明", "是否卓工或弘毅", "是否全英文", "是否打折", "BA1系数（未打折前）", "实验室核对结果"};
        Map<String, String> myMap = service.getTitleFormat(title);
        for (String item : ChineseName) {
            this.EnglishName.add(myMap.get(item));
        }
    }

    /**
     * @param teacherName、学年、学期即可
     * @return
     * @author 24047
     * @date 2022/6/27
     */
    public TeachingWorkloadFormat(String teacherName, String startYear, int semester) {
        this.teacherName = teacherName;
        this.startYear = startYear;
        this.semester = semester;
    }

    public TeachingWorkloadFormat(List<String> englishName, List<String> chineseName, String teacherName, String startYear, String endYear, int semester) {
        EnglishName = englishName;
        ChineseName = chineseName;
        this.teacherName = teacherName;
        this.startYear = startYear;
        this.endYear = endYear;
        this.semester = semester;
    }

    public List<String> getEnglishName() {
        return EnglishName;
    }

    public void setEnglishName(List<String> englishName) {
        EnglishName = englishName;
    }

    public List<String> getChineseName() {
        return ChineseName;
    }

    public void setChineseName(List<String> chineseName) {
        ChineseName = chineseName;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getStartYear() {
        return startYear;
    }

    public void setStartYear(String startYear) {
        this.startYear = startYear;
    }

    public String getEndYear() {
        return endYear;
    }

    public void setEndYear(String endYear) {
        this.endYear = endYear;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }
}
