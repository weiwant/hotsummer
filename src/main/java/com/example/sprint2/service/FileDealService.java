package com.example.sprint2.service;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.zip.ZipOutputStream;

/**
 * @author 24047
 * @date 2022/7/1
 */
public interface FileDealService {
    String download(File file, HttpServletResponse response) throws UnsupportedEncodingException;


    String downloadWithPath(HttpServletResponse response, String fileName, String path);

    List<String> downloadById(int id, HttpServletResponse response) throws UnsupportedEncodingException;

    List<String> compressedDownload(int id, HttpServletResponse response);


    List<String> uploadFile(MultipartFile[] files) throws IOException;

    String uploadFileWithPath(MultipartFile[] files) throws IOException;

    List<String> getFilePath(Integer id);

    String setPathInt();

    String setPath(Integer id);

    List<String> uploadFileById(MultipartFile[] files, Integer id) throws IOException;

    Boolean deleteFileByPath( String uri);

    String downloadByPath(HttpServletResponse response, String path);

    List<String> compressedDownloadByYear(String year, HttpServletResponse response) throws Exception;

     void compress(File sourceFile, ZipOutputStream zos, String name,
                  boolean KeepDirStructure) throws Exception;

    void deleteDir(File src);
}
