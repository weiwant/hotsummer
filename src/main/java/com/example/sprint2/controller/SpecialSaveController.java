package com.example.sprint2.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.example.sprint2.models.enumerate.impl.ResponseCode;
import com.example.sprint2.models.vo.SpecialReceiveVo;
import com.example.sprint2.mybatis.entity.SpecialWorkload;
import com.example.sprint2.service.SpecialReceiveService;
import com.example.sprint2.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Nullable;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * @author:wwq
 * @Date：2022/7/1：9:54
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
    @RequestMapping(value = "upload", method = RequestMethod.POST)
    public String uploadSpecialWorkload(@RequestParam("data") @Nullable String data, @RequestPart MultipartFile[] files) throws IOException {
        SpecialReceiveVo specialReceiveVo = new SpecialReceiveVo();
        specialReceiveVo.setFiles(files);
        JSONArray jsonArray = JSONArray.parseArray(data);
        specialReceiveVo.setData(jsonArray.toJavaList(SpecialWorkload.class));
        if (specialReceiveService.save(specialReceiveVo)) {
            return new Result(ResponseCode.SUCCESS).toString();
        } else {
            return new Result(ResponseCode.UnknownFailure).toString();
        }
    }


}
