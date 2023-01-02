package com.example.sprint2.controller;

import com.example.sprint2.models.enumerate.impl.ResponseCode;
import com.example.sprint2.models.vo.ConfirmVo;
import com.example.sprint2.service.ConfirmService;
import com.example.sprint2.utils.Result;
import org.checkerframework.checker.units.qual.C;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ZWForum
 * @description
 * @date 2022/7/6
 */
@RestController
@RequestMapping("/confirm")
public class ConfirmController {
    @Autowired
    ConfirmService confirmService;

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String updateConfirm(@RequestBody ConfirmVo confirmRecord) {
        return confirmService.updateConfirm(confirmRecord);
    }

    @RequestMapping(value = "/do",method = RequestMethod.POST)
    public String confirmRecord(@RequestBody ConfirmVo confirmVo){
        return confirmService.confirmRecord(confirmVo);
    }

    @RequestMapping(value = "/status",method = RequestMethod.POST)
    public String getStatus(@RequestBody ConfirmVo confirmVo){
        ConfirmVo confirmVo1 = new ConfirmVo();
        confirmVo1.setYear(confirmVo.getYear());
        confirmVo1.setId(confirmVo.getId());
        int status = confirmService.getConfirmStatus(confirmVo);
        if (status ==0 || status==1 || status==2){
            confirmVo1.setConfirm(status);
            return new Result(ResponseCode.SUCCESS,confirmVo1).toString();
        }else if (status == -1){
            return new Result(ResponseCode.NoContentFailure).toString();
        }else {
            return new Result(ResponseCode.UnknownFailure,"获取确认状态失败").toString();
        }
    }

    @RequestMapping(value = "/count",method = RequestMethod.POST)
    public String getCount(@RequestBody ConfirmVo confirmVo){
        ConfirmVo confirmVo1 = new ConfirmVo();
        int[] array = confirmService.getCount(confirmVo);
        if(array!=null){
            confirmVo1.setYear(confirmVo.getYear());
            confirmVo1.setConfirmCount(array[0]);
            confirmVo1.setNotConfirmCount(array[1]);
            return new Result(ResponseCode.SUCCESS,confirmVo1).toString();
        }else {
            return new Result(ResponseCode.UnknownFailure,"获取人数失败").toString();
        }
    }
}
