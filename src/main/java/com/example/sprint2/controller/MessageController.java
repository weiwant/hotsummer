package com.example.sprint2.controller;

import com.example.sprint2.models.enumerate.impl.ResponseCode;
import com.example.sprint2.models.vo.MessageVo;
import com.example.sprint2.service.MessageService;
import com.example.sprint2.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author hy
 * @Date 2022/12/28
 * @Description消息处理
 */
@RestController
@RequestMapping("/message")
public class MessageController {
    @Autowired
    private MessageService messageService;

    @RequestMapping(value = "/insert",method = RequestMethod.POST)
    public String insertMessage(@RequestBody MessageVo messageVo){
        int flag1 = messageService.insertMessage(messageVo);
        if (flag1 != -1){
            MessageVo messageVo1 = new MessageVo();
            messageVo1.setId(flag1);
            return new Result(ResponseCode.SUCCESS,messageVo1).toString();
        }else {
            return new Result(ResponseCode.SubmitFailure).toString();
        }
    }

    @RequestMapping(value = "/response",method = RequestMethod.POST)
    public String responseMessage(@RequestBody MessageVo messageVo){
        boolean flag = messageService.responseMessage(messageVo);
        if (flag == true){
            return new Result(ResponseCode.SUCCESS).toString();
        }else {
            return new Result(ResponseCode.SubmitFailure).toString();
        }
    }

    /**
     * @Author：wwq
     * @Date：2022/12/30
     * @Url:
     * @Description：教师端获取消息
     */
    @RequestMapping(value = "/get/teacher",method = RequestMethod.POST)
    public String getTeacherMessage(@RequestBody MessageVo messageVo){
        return messageService.getMyMessage(messageVo);
    }

    /**
     * @Author：wwq
     * @Description：管理员端获取消息
     * sender对象和responder详细信息都需要
     */
    @RequestMapping(value = "/get/monitor",method = RequestMethod.POST)
    public String getMonitorMessage(@RequestBody MessageVo messageVo){
        return messageService.getAllMessage(messageVo);
    }

}
