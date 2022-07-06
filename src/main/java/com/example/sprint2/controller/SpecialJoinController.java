package com.example.sprint2.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.sprint2.models.enumerate.impl.ResponseCode;
import com.example.sprint2.models.pojo.SpecialJoinResult;
import com.example.sprint2.service.SpecialJoinService;
import com.example.sprint2.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
     * @author hy
     * @description 分页 动态连表查询
     * 接收参数： String year, String teacherName(userName),String status,String,String type,String declarantName
     * @Return Result ：String
     */
    @RequestMapping(value = "/select",method = RequestMethod.POST)             //分页 动态条件 连表查询
    public String selectByConditions(@RequestBody SpecialJoinResult specialJoinResult){
        IPage<SpecialJoinResult> iPage =service.selectByConditions(specialJoinResult);
        if (iPage.getTotal() == 0) {
            return new Result(ResponseCode.NoContentFailure).toString();
        } else {
            return new Result(ResponseCode.SUCCESS, iPage).toString();
        }
    }

    /**
     * @author hy
     * @description 提交：修改该教师该年度所有已保存的记录状态为已提交
     * 接收参数： String year, String teacherName(userName)
     * @Return Result ：String
     */
    @RequestMapping(value = "/submit",method = RequestMethod.POST)
    public String setSubmit(@RequestBody SpecialJoinResult specialJoinResult){
        String result1 = service.setSubmit(specialJoinResult);
        if (result1 .equals("无内容") ){
            return new Result(ResponseCode.NoContentFailure).toString();
        }else if (result1.equals("提交成功")){
            return new Result(ResponseCode.SUCCESS).toString();
        }else {
            return new Result(ResponseCode.SubmitFailure).toString();
        }
    }



}
