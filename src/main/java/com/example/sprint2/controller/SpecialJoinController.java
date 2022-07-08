package com.example.sprint2.controller;

import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.sprint2.models.enumerate.impl.ResponseCode;
import com.example.sprint2.models.vo.SpecialVo;
import com.example.sprint2.service.SpecialJoinService;
import com.example.sprint2.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * @author hy
 * @Date 2022/7/5
 * @Description
 */

@RestController
@RequestMapping("/special-join")
public class SpecialJoinController {

    @Autowired
    SpecialJoinService service;

    /**
     * @param specialVo
     * @author hy
     * @description 分页 动态连表查询
     * @Receive String year, String teacherName(userName),String status,String,String type,String declarantName
     * @Return Result ：String
     */
    @RequestMapping(value = "/select", method = RequestMethod.POST)             //分页 动态条件 连表查询(FormData)
    public String selectByConditions(SpecialVo specialVo, String statusList) throws InvocationTargetException, IllegalAccessException {
        List<String> strings = JSONArray.parseArray(statusList).toJavaList(String.class);
        IPage<SpecialVo> iPage = service.selectByConditions(specialVo, strings);
        if (iPage.getTotal() == 0) {
            return new Result(ResponseCode.NoContentFailure).toString();
        } else {
            return new Result(ResponseCode.SUCCESS, iPage).toString();
        }
    }

    /**
     * @param specialVo
     * @author hy
     * @description 不分页 教师端动态连表查询
     * @Receive String year, String teacherName(userName),String status,String,String type,String declarantName
     * @Return Result ：String
     */
    @RequestMapping(value = "/select/teacher", method = RequestMethod.POST)
    public String selectBySingleConditions(SpecialVo specialVo, String statusList) throws InvocationTargetException, IllegalAccessException {
        List<String> strings = JSONArray.parseArray(statusList).toJavaList(String.class);
        List<SpecialVo> list = service.selectListByConditions(specialVo, strings);
        if (list.size() == 0) {
            return new Result(ResponseCode.NoContentFailure).toString();
        } else {
            return new Result(ResponseCode.SUCCESS, list).toString();
        }
    }

    /**
     * @param specialVo
     * @author hy
     * @description 提交：修改该教师该年度所有已保存的记录状态为已提交
     * @Receive String year, String declarantName(userName)
     * @Return Result ：String
     */
    @RequestMapping(value = "/submit", method = RequestMethod.POST)
    public String setSubmit(@RequestBody SpecialVo specialVo) throws InvocationTargetException, IllegalAccessException {
        String result1 = service.setSubmit(specialVo);
        if (result1.equals("无内容")) {
            return new Result(ResponseCode.NoContentFailure).toString();
        } else if (result1.equals("提交成功")) {
            return new Result(ResponseCode.SUCCESS).toString();
        } else {
            return new Result(ResponseCode.SubmitFailure).toString();
        }
    }


}
