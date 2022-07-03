package com.example.sprint2.controller;

import com.example.sprint2.models.enumerate.impl.ResponseCode;
import com.example.sprint2.service.FileDealService;
import com.example.sprint2.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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

/*
    @RequestMapping(value = "download")
    public void download(@RequestParam("fileName") String filename  ) throws IOException {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletResponse response = requestAttributes.getResponse();
        // 设置信息给客户端不解析
        String type = new MimetypesFileTypeMap().getContentType(filename);
        // 设置contenttype，即告诉客户端所发送的数据属于什么类型
        assert response != null;
        response.setHeader("Content-type",type);
        // 设置编码
        String hehe = new String(filename.getBytes("utf-8"), "iso-8859-1");
        // 设置扩展头，当Content-Type 的类型为要下载的类型时 , 这个信息头会告诉浏览器这个文件的名字和类型。
        response.setHeader("Content-Disposition", "attachment;filename=" + hehe);
        FileUtil.download(filename, response);
    }
*/

    @RequestMapping(value = "test")
    public String test() {
        return "Hello World!";
    }


    @RequestMapping(value = "/download", method = RequestMethod.GET)
//    @RequiresPermissions("update:manage:download")
    public String downloadFile(@RequestParam int id, HttpServletResponse response) throws UnsupportedEncodingException {
//        List<String> msg=fileDealService.downloadById(fileUtil.getId(),response);//事实上需要一个fileUtil对象
        List<String> msg = fileDealService.downloadById(id, response);
        return new Result(ResponseCode.SUCCESS, msg).toString();

    }


    //文件打包下载测试
    @GetMapping("/download-package")
    public String compressedDownload(@RequestParam int id, HttpServletResponse response) {
        List<String> msg = fileDealService.compressedDownload(id, response);
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
