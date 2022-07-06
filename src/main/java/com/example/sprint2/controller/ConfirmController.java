package com.example.sprint2.controller;

import com.example.sprint2.models.vo.ConfirmVo;
import com.example.sprint2.service.ConfirmService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public String updateConfirm(ConfirmVo confirmRecord) {
        return confirmService.updateConfirm(confirmRecord);
    }
}
