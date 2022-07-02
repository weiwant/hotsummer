package com.example.sprint2.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.sprint2.models.enumerate.impl.ResponseCode;
import com.example.sprint2.models.vo.SpecialReceiveVo;
import com.example.sprint2.service.SpecialReceiveService;
import com.example.sprint2.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author:wwq
 * @Date：2022/7/1：9:54
 *
 */

// TODO: 2022/7/1 对于附件，生成对应文件夹，将附件信息存入文件夹
// TODO: 2022/7/1 对于一个请求路径，能找到对应文件并返回；。以什么形式?


@RestController
@RequestMapping("/special-workload")
public class SpecialSaveController {
    @Autowired
    SpecialReceiveService specialReceiveService;

    /**
     * @Author：wwq
     * @Return：
     * @Url:special-workload/upload
     * @Description：接受前端json数据，存入数据库。
     */
    @RequestMapping(value = "upload",method = RequestMethod.POST)
    public String uploadSpecialWorkload(@RequestBody String json){
        if(specialReceiveService.saveJson(json)){
        return new Result(ResponseCode.SUCCESS).toString();
        }else{
            return new Result(ResponseCode.UnknownFailure).toString();
        }
    }

    /**
     * @Author：wwq
     * @Return：
     * @Url:
     * @Description：jsonObject
     */
    @RequestMapping(value = "/json",method = RequestMethod.POST)
    public String getJson(@RequestBody JSONArray jsonObject){
        return "";
    }
}
