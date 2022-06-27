package com.example.summer.controller;

import com.example.summer.models.pojo.ResponseCode;
import com.example.summer.service.SendStatisticTitleService;
import com.example.summer.service.impl.SendStatisticTitleServiceImpl;
import com.example.summer.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author 24047
 * @date 2022/6/25
 */
@RestController
public class SendStatisticsTitleController {
    @Autowired
    SendStatisticTitleService service;

    @RequestMapping(value = "sendTitle")
    public String sendTitle() {
        String[] title = {"学年", "学期", "课程号", "课程名称", "教学班", "开课学院", "计划学院", "学分", "课程性质", "年级", "专业", "上课老师", "职称", "上课人数", "理论", "上机", "实验", "实践", "计划用学时", "合课单位", "备注", "实验安排", "其他教师", "教分（BA1\\ 3\\ 15）原始分", "BA1系数", "教分（BA1\\ 3\\ 15）", "辅助", "课程性质说明", "是否卓工或弘毅", "是否全英文", "是否打折", "BA1系数（未打折前）", "实验室核对结果"};
        Map<String, String> myMap = service.getTitleFormat(title);

        return new Result(ResponseCode.SUCCESS, myMap).toString();
    }
}
