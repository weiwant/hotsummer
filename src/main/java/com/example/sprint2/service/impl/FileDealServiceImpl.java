package com.example.sprint2.service.impl;

import com.example.sprint2.dao.SpecialProjectDao;
import com.example.sprint2.dao.SpecialTeacherDao;
import com.example.sprint2.mybatis.entity.SpecialProject;
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
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * @author 24047
 * @date 2022/7/1
 */
@Service
public class FileDealServiceImpl implements FileDealService {

    @Autowired
    SpecialProjectDao specialProjectDao;

    @Autowired
    SpecialTeacherDao specialTeacherDao;


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
        /**
         * @author 24047
         * @date 2022/7/5
         * @param [javax.servlet.http.HttpServletResponse, java.lang.String, java.lang.String]
         * @description 根据文件名和绝对路径即可下载
         * @return java.lang.String
         */
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
        String fileFolderPath = projectPath + "\\" + specialProjectDao.selectFilePath(id);
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
    public List<String> uploadFile(MultipartFile[] files) throws IOException {
        /**
         * @author 24047
         * @date 2022/7/5
         * @param [org.springframework.web.multipart.MultipartFile[]]
         * @description 在保存的时候调用此方法，上传多个文件，这个方法只负责文件传输
         * @return java.util.List<java.lang.String>
         */
        List<String> msg = new ArrayList<>();
        if (files.length > 0) {
            String id = "id";//实际上需要从某个位置来获取
            //新建一个文件夹，理论上讲需要调用dao方法来确定这个文件夹的路径
//            String projectPath = "D:\\myTest";//实际上是System.getProperty("user.dir")
            String projectPath = System.getProperty("user.dir");
            String comparativeFilePath = this.setPathInt();//在这里设置文件路径,应该和
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
        return msg.get(0);//返回文件夹路径
    }

    @Override
    public String setPathInt() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        return formatter.format(new Date());
    }








    @Override
    public String setPath(Integer id){
        /**
         * @author 24047
         * @date 2022/7/5
         * @param [java.lang.Integer]
         * @description 根据id在服务器上新建这个文件夹，并且设置filepath
         * @return java.lang.String
         */
        //获取这条记录，然后给他设置path
        SpecialProject specialProject = specialProjectDao.selectSpecialProjectById(id);
        if(specialProject==null){
            return "error1";//设置失败，不存在此项目的记录
        }
        String projectPath = System.getProperty("user.dir");//获取根目录
        Calendar calendar=Calendar.getInstance();
        //在根目录下新建一个文件夹
        String yearFileFolderPath = specialProject.getAwardDate();//实际上是getYear
        if(yearFileFolderPath==null||yearFileFolderPath.equals("")){
            yearFileFolderPath=String.valueOf(calendar.get(Calendar.YEAR));//如果为空，就默认为当前年份
        }
        File yearFile =new File(projectPath + "\\"+yearFileFolderPath);
        if(!yearFile.exists()){
            yearFile.mkdirs();//新建文件夹
        }
        String typeFileFolderPath=yearFileFolderPath+"\\"+specialProject.getType();
        File typeFile=new File(projectPath + "\\"+typeFileFolderPath);
        if(!typeFile.exists()){
            yearFile.mkdirs();
        }
        List<String>names=specialTeacherDao.getTeacherNamesById(id);
        String projectName=specialProject.getProjectName();
        for (String name : names) {
            projectName += name;
        }
        projectName+=specialProject.getId();
        String teacherFileFolderPath=typeFileFolderPath+"\\"+projectName;//实际上还需要所有教师的姓名
        File teacherFile=new File(projectPath + "\\"+teacherFileFolderPath);
        if(!teacherFile.exists()){
            teacherFile.mkdirs();
        }
        if(specialProjectDao.setFilePath(teacherFileFolderPath,id)){
            return teacherFileFolderPath;//只返回相对路径，到时候需要修改数据库内容
        }
        else return "error2";//设置失败，无法写入数据库

    }
    @Override
    public List<String> uploadFileById(MultipartFile[] files,Integer id) throws IOException {
        /**
         * @author 24047
         * @date 2022/7/5
         * @param [org.springframework.web.multipart.MultipartFile[]]
         * @description 在保存的时候调用此方法，上传一个文件或者多个文件，这个方法只负责文件传输，所以前提是新建了文件夹
         * @return java.util.List<java.lang.String>
         */
        List<String> msg = new ArrayList<>();
        if (files.length > 0) {
            String projectPath = System.getProperty("user.dir");
            String comparativeFilePath = specialProjectDao.selectFilePath(id);//在这里设置文件路径
            if(comparativeFilePath==null){
                msg.add("error1");//数据库中不存在此记录或者不存在文件夹，请先保存
                return msg;
            }
            if(comparativeFilePath.equals("")){
                msg.add("error2");//数据库中不存在文件夹记录，请先保存
                return msg;
            }
            String filePath = projectPath + "\\" + comparativeFilePath;
            msg.add(comparativeFilePath);
            File file = new File(filePath);
            if (!file.exists()) {
                file.mkdirs();//不存在则新建并且修改数据库，存在则写入，虽然实际上应该存在
            }
            //遍历文件
            for (MultipartFile multipartFile : files) {
                if (!multipartFile.isEmpty()) {
                    String originalFilename = multipartFile.getOriginalFilename();
                    multipartFile.transferTo(new File(filePath + "\\" + originalFilename));
                    msg.add(multipartFile.getOriginalFilename());//上传成功
                }
            }
            return msg;
        } else {
            msg.add("error3");//没有上传文件
            return msg;
        }
    }

    @Override
    public List<String> getFilePath(Integer id) {
        /**
         * @author 24047
         * @date 2022/7/5
         * @param [java.lang.String]
         * @description 返回该id对应的filepath下的所有的文件路径（其实也就包括了文件名）在查看历史申报的时候使用
         * @return java.util.List<java.lang.String>
         */
        String projectPath = System.getProperty("user.dir");
        String comparativeFilePath = specialProjectDao.selectFilePath(id);
        List<String> msg = new ArrayList<>();
        if (comparativeFilePath == null) {
            msg.add("error1");//没有相对路径就返回称没有附件
            return msg;
        }
        String fileFolderPath = projectPath + "\\" + specialProjectDao.selectFilePath(id);//具体到文件夹
        File fileFolder = new File(fileFolderPath);
        if (!fileFolder.exists()) {
            msg.add("error2" );//文件不存在！请检查数据库或者文件地址
            return msg;
        }
        File[] files = fileFolder.listFiles();//进一步具体到各个文件
        for (File item:files ) {
            msg.add(comparativeFilePath+"/"+item.getName());
//            msg.add(item.getPath());//返回绝对路径

//            实际上还需要做一些处理
        }
        return msg;

    }


    @Override
    public Boolean deleteFileByPath(String uri){
        /**
         * @author 24047
         * @date 2022/7/5
         * @param [java.lang.String]
         * @description 删除成功则为true，否则为false
         * @return java.lang.Boolean
         */
        File file=new File(uri);
        if(!file.exists()||file.isDirectory()){
            return false;
        }
        else {
            return file.delete();
//            return true;
        }
    }

    @Override
    public String downloadByPath(HttpServletResponse response, String path) {
        /**
         * @author 24047
         * @date 2022/7/5
         * @param [javax.servlet.http.HttpServletResponse, java.lang.String, java.lang.String]
         * @description 根据文件名和绝对路径即可下载单个文件
         * @return java.lang.String
         */
        String msg = new String();
            FileInputStream is = null;
            BufferedInputStream bs = null;
            OutputStream os = null;
            try {
                File file = new File(path);
                if (file.exists()) {
                    //设置Headers
                    response.setHeader("Content-Type", "application/octet-stream");
                    //设置下载的文件的名称-该方式已解决中文乱码问题
                    response.setHeader("Content-Disposition", "attachment;filename=" + new String(file.getName().getBytes("gb2312"), "ISO8859-1"));
                    is = new FileInputStream(file);
                    bs = new BufferedInputStream(is);
                    os = response.getOutputStream();
                    byte[] buffer = new byte[1024];
                    int len = 0;
                    while ((len = bs.read(buffer)) != -1) {
                        os.write(buffer, 0, len);
                    }
                    msg = "true";
                } else {
                    String error = Base64Util.encode("下载的文件资源不存在");
                    response.sendRedirect("/file/download-by-path?error=" + error);
                    msg = "error1";
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
        return msg;
    }

    @Override
    public List<String> compressedDownload(int id, HttpServletResponse response) {
        /**
         * @author 24047
         * @date 2022/7/5
         * @param [int, javax.servlet.http.HttpServletResponse]
         * @description 打包为压缩包下载
         * @return java.util.List<java.lang.String>
         */
//        String projectPath = "D:\\myTest";//实际上是System.getProperty("user.dir")
        String projectPath = System.getProperty("user.dir");
        String comparativeFilePath = specialProjectDao.selectFilePath(id);
        List<String> msg = new ArrayList<>();
        if (comparativeFilePath == null) {
            msg.add("error1");
            return msg;
        }
        String fileFolderPath = projectPath + "\\" + specialProjectDao.selectFilePath(id);
        //实际上fileFolderPath还需要加上根目录路径
        File fileFolder = new File(fileFolderPath);
        if (!fileFolder.exists()) {
            msg.add("error2");
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
        String test=comparativeFilePath.replace('\\','-');
        String secondTest=test.replace('/','-');
        String zipFileName = secondTest+ ".zip";//这里实际上需要用自然年+BB类别+教师名+成果名
        String strZipPath = fileFolderPath + "\\" + zipFileName;
/*
        if(!new File(zipFileName).exists()){
            msg.add("error3");
            return msg;
        }
*/

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
            msg.add(this.downloadByPath(response, strZipPath));
            zipFile.delete();
        }
        return msg;
    }

    @Override
    public List<String> compressedDownloadByYear(String year, HttpServletResponse response) throws Exception {
        /**
         * @author 24047
         * @date 2022/7/5
         * @param [int, javax.servlet.http.HttpServletResponse]
         * @description 查找某年的文件夹，打包为压缩包下载
         * @return java.util.List<java.lang.String>
         */
        String projectPath = System.getProperty("user.dir");
        String comparativeFilePath=year;
        List<String> msg = new ArrayList<>();
        String fileFolderPath = projectPath + "\\" + comparativeFilePath;//找到这个文件夹
        File fileFolder = new File(fileFolderPath);//找到这个文件夹
        if (!fileFolder.exists()||!fileFolder.isDirectory()) {
            msg.add("error1");//文件夹不存在，请检查数据库或者服务器文件系统
            return msg;
        }
        String zipFileName =  comparativeFilePath+ ".zip";//2022.zip
//        String zipPath = "D:\\myTest\\test2"+".zip";//
        String zipPath=projectPath+"\\"+zipFileName;
        FileOutputStream fos = new FileOutputStream(zipPath);//必须是真实存在的zip文件作为输出文件
        ZipOutputStream zos = new ZipOutputStream(fos);//将压缩文件输入到这个文件中

        this.compress(new File(fileFolderPath),zos,comparativeFilePath,true);
        File zipFile = new File(zipPath);
        if (zipFile.exists()) {
            msg.add(zipPath);
            fos.close();
            msg.add(this.downloadByPath(response, zipPath));//将这个压缩包以单文件的形式发送
//            我发现完全无法把它删除，实在是太困难了。只能在另外的进程里把它删除。
            System.out.println("zip删除情况："+zipFile.delete());
        }
        return msg;
    }

    public Boolean deleteByFileName(Integer id,String fileName){
    /**
     * @author 24047
     * @date 2022/7/6
     * @param [java.lang.Integer, java.lang.String]
     * @description 根据项目的id和具体的文件名删除具体的文件
     * @return java.lang.Boolean
     */
        SpecialProject specialProject= specialProjectDao.selectSpecialProjectById(id);
        return this.deleteFileByPath(specialProject.getFilePath()+"\\"+fileName);
    }

    @Override
    public void compress(File sourceFile, ZipOutputStream zos, String name,
                         boolean KeepDirStructure) throws Exception{
        byte[] buf = new byte[1024*2];
        if(sourceFile.isFile()){
            // 向zip输出流中添加一个zip实体，构造器中name为zip实体的文件的名字
            zos.putNextEntry(new ZipEntry(name));
            // copy文件到zip输出流中
            int len;
            FileInputStream in = new FileInputStream(sourceFile);
            while ((len = in.read(buf)) != -1){
                zos.write(buf, 0, len);
            }
            // Complete the entry
            zos.closeEntry();
            in.close();
        } else {
            File[] listFiles = sourceFile.listFiles();
            if(listFiles == null || listFiles.length == 0){
                // 需要保留原来的文件结构时,需要对空文件夹进行处理
                if(KeepDirStructure){
                    // 空文件夹的处理
                    zos.putNextEntry(new ZipEntry(name + "/"));
                    // 没有文件，不需要文件的copy
                    zos.closeEntry();
                }

            }else {
                for (File file : listFiles) {
                    // 判断是否需要保留原来的文件结构
                    if (KeepDirStructure) {
                        // 注意：file.getName()前面需要带上父文件夹的名字加一斜杠,
                        // 不然最后压缩包中就不能保留原来的文件结构,即：所有文件都跑到压缩包根目录下了
                        compress(file, zos, name + "/" + file.getName(),KeepDirStructure);
                    } else {
                        compress(file, zos, file.getName(),KeepDirStructure);
                    }

                }
            }
//            zos.closeEntry();
//            zos.flush();

//            zos.close();
        }
    }

    @Override
    public void deleteDir(File src) {
        //先删掉这个文件夹里面所有的内容.
        //递归 方法在方法体中自己调用自己.
        //注意: 可以解决所有文件夹和递归相结合的题目
        //2.遍历这个File对象,获取它下边的每个文件和文件夹对象
        File[] files = src.listFiles();
        if(files==null){
            src.delete();
            return;
        }
        //3.判断当前遍历到的File对象是文件还是文件夹
        for (File file : files) {
            //4.如果是文件,直接删除
            if(file.isFile()){
                file.delete();
            }else{
                //5.如果是文件夹,递归调用自己,将当前遍历到的File对象当做参数传递
                deleteDir(file);//参数一定要是src文件夹里面的文件夹File对象
            }
        }
        //6.参数传递过来的文件夹File对象已经处理完成,最后直接删除这个空文件夹
        src.delete();
    }

}
