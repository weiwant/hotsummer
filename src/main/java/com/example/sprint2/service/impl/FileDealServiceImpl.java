package com.example.sprint2.service.impl;

import com.example.sprint2.dao.FileDealDao;
import com.example.sprint2.service.FileDealService;
import org.apache.logging.log4j.util.Base64Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * @author 24047
 * @date 2022/7/1
 */
@Service
public class FileDealServiceImpl implements FileDealService {

    @Autowired
    FileDealDao fileDealDao;

    @Override
    public String download(File file, HttpServletResponse response) throws UnsupportedEncodingException {
//        String rootPath = "D:/test/";
//        String FullPath = rootPath + fileFullName;//将文件的统一储存路径和文件名拼接得到文件全路径
//        File packetFile = new File(FullPath);
//        String fileName = packetFile.getName(); //下载的文件名
//        File file = new File(FullPath);
        // 如果文件名存在，则进行下载
        if (file.exists()) {
            // 配置文件下载
            response.setHeader("content-type", "application/octet-stream");
            response.setContentType("application/octet-stream");
            // 下载文件能正常显示中文
            response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(file.getName(), "UTF-8"));
            // 实现文件下载
            byte[] buffer = new byte[1024];//内存中开辟缓冲区
            FileInputStream fis = null;
            BufferedInputStream bis = null;
            try {
                fis = new FileInputStream(file);
                bis = new BufferedInputStream(fis);
                OutputStream os = response.getOutputStream();
                int i = bis.read(buffer);
                while (i != -1) {
                    os.write(buffer, 0, i);
                    i = bis.read(buffer);
                }
                System.out.println("Download the song successfully!");
                if (os != null) {
                    try {
                        os.close();//fis不为空则关闭
                        System.out.println("os.close()");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
//                fis.close();
//                os.close();
//                return file.getName();
            } catch (Exception e) {
                System.out.println("Download the song failed!");
            } finally {
                if (bis != null) {
                    try {
                        bis.close();//bis不为空则关闭
                        System.out.println("bis.close()");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (fis != null) {
                    try {
                        fis.close();//fis不为空则关闭
                        System.out.println("fis.close()");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        } else {//对应文件不存在
            try {
                //设置响应的数据类型是html文本，并且告知浏览器，使用UTF-8 来编码。
                response.setContentType("text/html;charset=UTF-8");

                //String这个类里面， getBytes()方法使用的码表，是UTF-8，  跟tomcat的默认码表没关系。 tomcat iso-8859-1
                String csn = Charset.defaultCharset().name();

                System.out.println("默认的String里面的getBytes方法使用的码表是： " + csn);

                //1. 指定浏览器看这份数据使用的码表
                response.setHeader("Content-Type", "text/html;charset=UTF-8");
                OutputStream os = response.getOutputStream();

                os.write("对应文件不存在".getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
            return "文件不存在";
        }

        return file.getName();
    }

    @Override
    public String downloadWithPath(HttpServletResponse response, String fileName, String path) {
        String msg = new String();
        if (fileName != null) {
            FileInputStream is = null;
            BufferedInputStream bs = null;
            OutputStream os = null;
            try {
                File file = new File(path);
                if (file.exists()) {
                    //设置Headers
                    response.setHeader("Content-Type", "application/octet-stream");
                    //设置下载的文件的名称-该方式已解决中文乱码问题
                    response.setHeader("Content-Disposition", "attachment;filename=" + new String(fileName.getBytes("gb2312"), "ISO8859-1"));
                    is = new FileInputStream(file);
                    bs = new BufferedInputStream(is);
                    os = response.getOutputStream();
                    byte[] buffer = new byte[1024];
                    int len = 0;
                    while ((len = bs.read(buffer)) != -1) {
                        os.write(buffer, 0, len);
                    }
                    msg = "文件下载成功";
                } else {
                    String error = Base64Util.encode("下载的文件资源不存在");
                    response.sendRedirect("/imgUpload/imgList?error=" + error);
                    msg = error;
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            } finally {
                try {
                    if (is != null) {
                        is.close();
                    }
                    if (bs != null) {
                        bs.close();
                    }
                    if (os != null) {
                        os.flush();
                        os.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return msg;
    }

    @Override
    public List<String> downloadById(int id, HttpServletResponse response) throws UnsupportedEncodingException {
//        String projectPath = "D:\\myTest";//实际上是System.getProperty("user.dir")
        String projectPath = System.getProperty("user.dir");
        String fileFolderPath = projectPath + "\\" + fileDealDao.selectFilePath(id);
        //实际上fileFolderPath还需要加上根目录路径
        File fileFolder = new File(fileFolderPath);
        File[] files = fileFolder.listFiles();
        List<String> msg = new ArrayList<>();
        assert files != null;
        for (File item : files) {
            msg.add(this.download(item, response));
            //遍历输出，事实证明只能下载第一个文件
        }
        return msg;
    }

    @Override
    public List<String> compressedDownload(int id, HttpServletResponse response) {
//        String projectPath = "D:\\myTest";//实际上是System.getProperty("user.dir")
        String projectPath = System.getProperty("user.dir");
        String comparativeFilePath = fileDealDao.selectFilePath(id);
        List<String> msg = new ArrayList<>();
        if (comparativeFilePath == null) {
            msg.add("没有附件！");
            return msg;
        }
        String fileFolderPath = projectPath + "\\" + fileDealDao.selectFilePath(id);
        //实际上fileFolderPath还需要加上根目录路径
        File fileFolder = new File(fileFolderPath);
        if (!fileFolder.exists()) {
            msg.add("文件不存在！请检查数据库或者文件地址：" + fileFolderPath);
            return msg;
        }
        File[] files = fileFolder.listFiles();
        String[] names = new String[files.length];
        //伪造文件所在路径数组
        String[] paths = new String[files.length];
        for (int i = 0; i < files.length; i++) {
            names[i] = files[i].getName();
            paths[i] = files[i].getPath();
        }
        //伪造文件名数组

        //设置最终输出zip文件的目录+文件名
//        SimpleDateFormat formatter  = new SimpleDateFormat("yyyy年MM月dd日HH时mm分ss秒");
//        String zipFileName = formatter.format(new Date())+".zip";
        String zipFileName = id + ".zip";
        String strZipPath = fileFolderPath + "\\" + zipFileName;

        //创建流
        ZipOutputStream zipStream = null;
        FileInputStream zipSource = null;
        BufferedInputStream bufferStream = null;
        File zipFile = new File(strZipPath);
        try {
            //构造最终压缩包的输出流
            zipStream = new ZipOutputStream(new FileOutputStream(zipFile));
            for (int i = 0; i < paths.length; i++) {
                //解码获取真实路径与文件名
                String realFileName = java.net.URLDecoder.decode(names[i], "UTF-8");
                String realFilePath = java.net.URLDecoder.decode(paths[i], "UTF-8");
                File file = new File(realFilePath);
                if (file.exists()) {
                    zipSource = new FileInputStream(file);//将需要压缩的文件格式化为输入流
                    ZipEntry zipEntry = new ZipEntry(realFileName);//在压缩目录中文件的名字
                    zipStream.putNextEntry(zipEntry);//定位该压缩条目位置，开始写入文件到压缩包中
                    bufferStream = new BufferedInputStream(zipSource, 1024 * 10);
                    int read = 0;
                    byte[] buf = new byte[1024 * 10];
                    while ((read = bufferStream.read(buf, 0, 1024 * 10)) != -1) {
                        zipStream.write(buf, 0, read);
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //关闭流
            try {
                if (null != bufferStream) bufferStream.close();
                if (null != zipStream) {
                    zipStream.flush();
                    zipStream.close();
                }
                if (null != zipSource) zipSource.close();
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }

        if (zipFile.exists()) {
            msg.add(this.downloadWithPath(response, zipFileName, strZipPath));
            zipFile.delete();
        }
        return msg;
    }

    @Override
    public List<String> uploadFile(MultipartFile[] files) throws IOException {
        List<String> msg = new ArrayList<>();
        /*
        if(!file.isEmpty()){

            //新建一个文件夹，理论上讲需要调用dao方法
            String filePath="D:\\second";
            //文件名
            String originalFilename = file.getOriginalFilename();
            //springboot独特的写出操作，很方便
            file.transferTo(new File("D:\\"+originalFilename));
            msg.add(file.getName()+"上传成功！");
        }
        */
        if (files.length > 0) {
            String id = "id";//实际上需要从某个位置来获取
            //新建一个文件夹，理论上讲需要调用dao方法来确定这个文件夹的路径
//            String projectPath = "D:\\myTest";//实际上是System.getProperty("user.dir")
            String projectPath = System.getProperty("user.dir");
            String comparativeFilePath = this.setPathInt();
            String filePath = projectPath + "\\" + comparativeFilePath;
            msg.add(comparativeFilePath);
            File file = new File(filePath);
            if (!file.exists()) {
                file.mkdirs();
            }
            //遍历文件
            for (MultipartFile multipartFile : files) {
                if (!multipartFile.isEmpty()) {
                    String originalFilename = multipartFile.getOriginalFilename();
                    multipartFile.transferTo(new File(filePath + "\\" + originalFilename));
                    msg.add(multipartFile.getOriginalFilename() + "上传成功\n");
                }
            }
            return msg;
        } else {
            msg.add("没有选择文件");
            return msg;
        }
    }


    @Override
    public String uploadFileWithPath(MultipartFile[] files) throws IOException {
        List<String> msg = this.uploadFile(files);
        return msg.get(0);

        /*
        if(!file.isEmpty()){

            //新建一个文件夹，理论上讲需要调用dao方法
            String filePath="D:\\second";
            //文件名
            String originalFilename = file.getOriginalFilename();
            //springboot独特的写出操作，很方便
            file.transferTo(new File("D:\\"+originalFilename));
            msg.add(file.getName()+"上传成功！");
        }
        */
        /*
        if (files.length > 0) {
            int id = 1;//实际上需要从某个位置来获取
            //新建一个文件夹，理论上讲需要调用dao方法来确定这个文件夹的路径
            String projectPath = "D:\\myTest";//实际上是System.getProperty("user.dir")
            String filePath = projectPath + "\\" + this.setPath(id);
            File file = new File(filePath);
            if (!file.exists()) {
                file.mkdirs();
            }
            //遍历文件
            for (MultipartFile multipartFile : files) {
                if (!multipartFile.isEmpty()) {
                    String originalFilename = multipartFile.getOriginalFilename();
                    multipartFile.transferTo(new File(filePath + "\\" + originalFilename));
                    msg.add(multipartFile.getOriginalFilename() + "上传成功\n");
                }
            }
            return msg;
        } else {
            msg.add("没有选择文件");
            return msg;
        }*/
    }

    @Override
    public String setPath(String id) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        return formatter.format(new Date()) + " id " + id;
    }


    @Override
    public String setPathInt() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        return formatter.format(new Date());
    }
}
