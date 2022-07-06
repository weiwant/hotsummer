package com.example.sprint2.controller;

import com.example.sprint2.models.enumerate.impl.ResponseCode;
import com.example.sprint2.service.FileDealService;
import com.example.sprint2.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Nullable;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * @author 24047
 * @date 2022/7/1
 */
@RequestMapping("/file")
@RestController
public class FileDealController {

    //    private static final Logger LOGGER = LoggerFactory.getLogger(FileDealController.class);
    @Autowired
    FileDealService fileDealService;


    //一个测试
    @RequestMapping(value = "test")
    public String test() {
        return "Hello World!";
    }

//    设置filePath
    @RequestMapping(value = "/set-path",method = RequestMethod.POST)
    public String setPath(@RequestParam Integer id){
        String path=fileDealService.setPath(id);
        if(path.equals("error1")){
            return new Result(ResponseCode.UnknownFailure,"error1：数据库中不存在这条记录").toString();
        }else if(path.equals("error2")){
            return new Result(ResponseCode.UnknownFailure,"error2：无法插入数据库").toString();
        }
        else return new Result(ResponseCode.SUCCESS,path).toString();

    }
    //在新增了记录的基础上上传文件
    @RequestMapping(value = "/upload-by-id",method = RequestMethod.POST)
    public String uploadById(@RequestParam Integer id,@RequestPart("files")  MultipartFile[] files) throws IOException {
        List<String> msg= fileDealService.uploadFileById(files,id);
        if(msg.get(0).equals("error1")){
            return new Result(ResponseCode.UnknownFailure,"数据库中不存在此记录或者不存在文件夹，请先保存").toString();
        }else if(msg.get(0).equals("error2")){
            return new Result(ResponseCode.UnknownFailure,"数据库中不存在文件夹记录，请先保存").toString();
        }else if(msg.get(0).equals("error3")){
            return new Result(ResponseCode.UnknownFailure,"error3：未选择文件，请重新上传").toString();
        }
        return new Result(ResponseCode.SUCCESS,msg).toString();
    }
    @RequestMapping(value = "/get-file-path",method = RequestMethod.POST)
    public String getFilePath(@RequestParam Integer id){
        List<String> msg=fileDealService.getFilePath(id);
        if(msg.get(0).equals("error1")){
            return new Result(ResponseCode.UnknownFailure,"没有附件").toString();
        }else if(msg.get(0).equals("error2")){
            return new Result(ResponseCode.UnknownFailure,"文件不存在！请检查数据库或者文件地址").toString();
        }else return new Result(ResponseCode.SUCCESS,msg).toString();

    }

    @RequestMapping(value = "/delete-by-path",method = RequestMethod.POST)
    public String deleteById(@RequestParam String uri){
        if(fileDealService.deleteFileByPath(uri)){
            return new Result(ResponseCode.SUCCESS,uri).toString();
        }
        else return new Result(ResponseCode.UnknownFailure,uri).toString();
    }
    //按照返回来的uri下载文件
    @RequestMapping(value = "/download-by-path",method = RequestMethod.POST)
    public String downloadByPath(@RequestParam String uri,HttpServletResponse response) throws IOException {
        String msg=fileDealService.downloadByPath(response,uri);
        if(msg.equals("true")){
            return new Result(ResponseCode.SUCCESS,"文件下载成功").toString();
        }
        else return new Result(ResponseCode.UnknownFailure,"文件资源不存在").toString();
    }
    //按照id下载一条记录的所有附件，打包为压缩包
    @RequestMapping(value = "/download-package",method = RequestMethod.POST)
    public String compressedDownload(@RequestParam int id, HttpServletResponse response) {
        List<String> msg = fileDealService.compressedDownload(id, response);
        return new Result(ResponseCode.SUCCESS, msg).toString();
    }
//    批量下载某一年，打包为一个压缩包
    @RequestMapping(value="/download-by-year",method = RequestMethod.POST)
    public String downloadByYear(@RequestParam String year,HttpServletResponse response) throws Exception {
        List<String> msg = fileDealService.compressedDownloadByYear(year, response);
        if(msg.get(0).equals("error1")){
            return new Result(ResponseCode.UnknownFailure, msg).toString();
        }
//        System.out.println(fileDealService.deleteFileByPath(msg.get(0)));
        return new Result(ResponseCode.SUCCESS, msg).toString();
    }


    /**
     * 以下两个方法都是弃用的方法
     * @param id
     * @param response
     * @return
     * @throws UnsupportedEncodingException
     */
    @RequestMapping(value = "/download", method = RequestMethod.GET)
    public String downloadFile(@RequestParam int id, HttpServletResponse response) throws UnsupportedEncodingException {
//        List<String> msg=fileDealService.downloadById(fileUtil.getId(),response);//事实上需要一个fileUtil对象
        List<String> msg = fileDealService.downloadById(id, response);
        return new Result(ResponseCode.SUCCESS, msg).toString();
    }


    @PostMapping("/upload")
    public String upload(@RequestPart(value = "files") MultipartFile[] files) throws IOException {
        List<String> msg = fileDealService.uploadFile(files);
        if (msg.get(0) == "没有上传文件") {
            return new Result(ResponseCode.UnknownFailure, msg).toString();
        }
        return new Result(ResponseCode.SUCCESS, msg).toString();
    }
}
