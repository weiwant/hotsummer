package com.example.sprint2.service.impl;

import com.example.sprint2.dao.MessageDao;
import com.example.sprint2.models.vo.MessageVo;
import com.example.sprint2.mybatis.entity.Message;
import com.example.sprint2.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author hy
 * @Date 2022/12/28
 * @Description
 */
@Service("messageHandleService")
public class MessageServiceImpl implements MessageService {
    @Autowired
    MessageDao messageDao;

    @Override
    public int insertMessage(MessageVo messageVo) {   //插入新消息
        Message message = new Message();
        message.setYear(messageVo.getYear());
        message.setSender(messageVo.getSender());
        message.setSendContent(messageVo.getSendContent());
        message.setSendDate(messageVo.getSendDate());
        message.setTitle(messageVo.getTitle());
        int id = messageDao.insertMessage(message);
        if (id != -1){
            return id;
        }else {
            return -1;
        }
    }

    @Override
    public boolean responseMessage(MessageVo messageVo) {   //回复已有信息
        Message message = new Message();
        message.setId(messageVo.getId());
        message.setResponder(messageVo.getResponder());
        message.setResponseContent(messageVo.getResponseContent());
        message.setResponseStatus(1);
        message.setResponseDate(messageVo.getResponseDate());
        boolean flag = false;
        flag = messageDao.responseMessage(message);
        return flag;
    }
}
