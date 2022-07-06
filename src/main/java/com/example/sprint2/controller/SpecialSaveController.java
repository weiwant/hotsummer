package com.example.sprint2.controller;

import com.alibaba.fastjson.JSONArray;
import com.example.sprint2.models.enumerate.impl.ResponseCode;
import com.example.sprint2.models.pojo.TeacherAndOrder;
import com.example.sprint2.models.vo.SpecialVo;
import com.example.sprint2.service.SpecialTwiceInsertService;
import com.example.sprint2.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Nullable;
import java.io.IOException;

/**
 * @author:wwq
 * @Date：2022/7/5：16:47
 */

@RestController
@RequestMapping("/special-workload")
public class SpecialSaveController {
    @Autowired
    SpecialTwiceInsertService service;//保存和更新操作（insert or update）

    /**
     * @Author：wwq
     * @Return：
     * @Url:/special-workload/save/teacher
     * @Description：教师申报特殊工作量，将记录插入数据库
     */
    @RequestMapping(value = "/save/teacher", method = RequestMethod.POST)
    public String saveSpecialWorkload(@Nullable SpecialVo specialVo, @RequestParam("teachers") String teachers, @RequestParam("files") @Nullable MultipartFile[] files) throws IOException {
        JSONArray jsonArray = JSONArray.parseArray(teachers);
        specialVo.setSomePeople(jsonArray.toJavaList(TeacherAndOrder.class));
        specialVo.setFiles(files);

        if (service.specialSave(specialVo)) {
            return new Result(ResponseCode.SUCCESS).toString();
        } else {
            return new Result(ResponseCode.UnknownFailure).toString();
        }
    }

    /**
     * @Author：wwq
     * @Return：
     * @Url:/special-workload/upload/teacher
     * @Description：教师修改其已保存的工作量。更新数据库
     */
    @RequestMapping(value = "/upload/teacher", method = RequestMethod.POST)
    public String uploadSpecialWorkload(@Nullable SpecialVo specialVo, @RequestParam("teachers") String teachers) {
        JSONArray jsonArray = JSONArray.parseArray(teachers);
        specialVo.setSomePeople(jsonArray.toJavaList(TeacherAndOrder.class));
        if (service.specialUpload(specialVo)) {
            return new Result(ResponseCode.SUCCESS).toString();
        } else {
            return new Result(ResponseCode.UnknownFailure).toString();
        }
    }
}
