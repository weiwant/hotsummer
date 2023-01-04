package com.example.sprint2.service;

import com.example.sprint2.models.vo.MessageVo;
import com.example.sprint2.mybatis.entity.Message;
import org.springframework.stereotype.Service;

/**
 * @author hy
 * @Date 2022/12/28
 * @Description
 */
@Service
public interface MessageService {

    public String getMyMessage(MessageVo messageVo) ;

    public int insertMessage(MessageVo messageVo);

    public int readResponse(MessageVo messageVo);

    public boolean responseMessage(MessageVo messageVo);

    public String getAllMessage(MessageVo messageVo);
}
