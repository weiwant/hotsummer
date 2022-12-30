package com.example.sprint2.service.impl;

import com.example.sprint2.dao.MessageDao;
import com.example.sprint2.dao.UserDao;
import com.example.sprint2.dao.UserInfoDao;
import com.example.sprint2.models.enumerate.impl.ResponseCode;
import com.example.sprint2.models.vo.MessageVo;
import com.example.sprint2.mybatis.entity.Message;
import com.example.sprint2.service.MessageService;
import com.example.sprint2.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.ResponseCache;
import java.util.ArrayList;
import java.util.List;

/**
 * @author hy
 * @Date 2022/12/28
 * @Description
 */
@Service("messageHandleService")
public class MessageServiceImpl implements MessageService {
    @Autowired
    MessageDao messageDao;
    @Autowired
    UserDao userDao;
    @Autowired
    UserInfoDao userInfoDao;



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


    /**
     * @Author：wwq
     * @Date：2022/6/29
     * @Url:
     * @Description：教师查询消息。参数为教师id和年份。返回的responder要求有管理员id，管理员姓名和管理员院系。
     */
    @Override
    public String getMyMessage(MessageVo messageVo) {
        Message message=new Message();
        message.setSender(messageVo.getSender());//传递教师id
        message.setYear(messageVo.getYear());//传递自然年
        //查询符合的记录列表
        List<Message> messageList=messageDao.getTeacherMessage(message);
        if(messageList.size()==0){//如果没有对应消息
            return new Result(ResponseCode.NoContentFailure).toString();
        }else{ //对列表内容处理
            for (Message message1 : messageList) {
                String responderId=message1.getResponder();
                //获取管理员姓名
                String responderName=userDao.getNameById(responderId);
                //获取管理员院系
                String responderFaculty=userInfoDao.getFacultyById(responderId);
                //封装在responder中。
                message1.setResponder("[responderId:"+responderId+",responderName:"+responderName+",responderFaculty:"+responderFaculty+"]");
            }
            return new Result(ResponseCode.SUCCESS,messageList).toString();
        }



    }


    /**
     * @Author：wwq
     * @Description：管理员获取消息。参数为管理员responder和年份year。
     * 返回的sender要求有id，name，和faculty；
     * 返回的responder要求有id，name，和faculty
     */
    @Override
    public String getAllMessage(MessageVo messageVo) {
        Message message=new Message();
        message.setResponder(messageVo.getResponder());//传递管理员id
        message.setYear(messageVo.getYear());//传递年份
        //查询符合的记录列表
        List<Message> messageList=messageDao.getMonitorMessage(message);
        if(messageList.size()==0){//如果没有内容返回
            return new Result(ResponseCode.NoContentFailure).toString();
        }else{//有内容对数据进行处理
            for (Message message2 : messageList) {
                //处理responder
                String responderId=message2.getResponder();
                //获取管理员姓名
                String responderName=userDao.getNameById(responderId);
                //获取管理员院系
                String responderFaculty=userInfoDao.getFacultyById(responderId);
                //封装在responder中。
                message2.setResponder("[responderId:"+responderId+",responderName:"+responderName+",responderFaculty:"+responderFaculty+"]");
                //处理sender
                String senderId=message2.getSender();
                //获取教师姓名
                String senderName=userDao.getNameById(senderId);
                //获取教师院系
                String senderFaculty=userInfoDao.getFacultyById(senderId);
                //封装在sender中
                message2.setSender("[senderId:"+senderId+",senderName:"+senderName+",senderFaculty:"+senderFaculty+"]");
            }
            return new Result(ResponseCode.SUCCESS,messageList).toString();
        }
    }
}
