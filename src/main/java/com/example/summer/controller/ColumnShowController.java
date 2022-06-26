package com.example.summer.controller;

import com.example.summer.models.pojo.ResponseCode;
import com.example.summer.service.ColumnShowService;
import com.example.summer.service.impl.ColumnShowServiceImpl;
import com.example.summer.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author:wwq
 * @Date：2022/6/25：9:18
 */
@RestController
@RequestMapping("/resource")
public class ColumnShowController {
    @Autowired
    ColumnShowService columnShowService = new ColumnShowServiceImpl();

    /**
     * @Author：wwq
     * @Return：
     * @Description：根据表头名称获取对应表内容
     */
    @RequestMapping(value = "/Column", method = RequestMethod.GET)
    public String getColumn(@RequestParam(value = "columnname") List<String> names) {
        List<String> teacherSee = columnShowService.getColumn(names);
        if (teacherSee.size() == 0) {
            return new Result(ResponseCode.NoContentFailure).toString();
        } else {
            return new Result(ResponseCode.SUCCESS, teacherSee).toString();
        }
    }
}