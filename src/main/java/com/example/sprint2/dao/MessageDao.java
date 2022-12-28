package com.example.sprint2.dao;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.sprint2.mybatis.entity.Message;
import com.example.sprint2.mybatis.mapper.MessageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author hy
 * @Date 2022/12/23
 * @Description
 */
@Repository
public class MessageDao {

    @Autowired
    MessageMapper messageMapper;

    /**
     * @author hy
     * @Receive Message对象
     * @Param sender_id,sendContent,sendDate,
     * @description新建消息,返回消息id
     */
    public int insertMessage(Message message){
        boolean flag = (messageMapper.insert(message) != -1);
        QueryWrapper<Message> queryWrapper = new QueryWrapper<>();
        queryWrapper.setEntity(message);
        if (flag == true){
            int id = messageMapper.selectOne(queryWrapper).getId();
            return id;
        }else {
            return -1;
        }
    }

    /**
     * @author hy
     * @Receive Message对象
     * @Param 消息id,回复人id：responder,回复内容responseContent,回复状态responseStatus
     * @description回复已有消息，写入数据库
     */
    public boolean responseMessage(Message message){
        QueryWrapper<Message> wrapper = new QueryWrapper<>();
        if (messageMapper.selectById(message.getId()) != null){
            wrapper.eq("id",message.getId());
            return (messageMapper.update(message,wrapper) != -1);
        }else {
            return false;
        }
    }


}
