package com.qunar.ryan.hao.charactercounter.utils;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

import com.google.common.base.Charsets;
import com.google.common.io.Files;

/**
 * 文件功能工具类
 * Created by ryan.hao on 16-12-16.
 */
public class FileUtils {

    /**
     * 文件上传路径
     */
    private static final String UPLOAD_PATH = "uploadfiles";

    public static String getRealPath(HttpServletRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("参数为空");
        }
        return request.getSession().getServletContext().getRealPath(UPLOAD_PATH);
    }

    /**
     * 保存上传的文件
     * @param file
     * @param request
     * @return
     */
    public static String saveUploadFile(MultipartFile file, HttpServletRequest request) {
        if (file == null || request == null) {
            throw new IllegalArgumentException("参数为空");
        }
        String path = getRealPath(request);
        String fileName = UUID.randomUUID().toString();
        File targetFile = new File(path, fileName);
        if(!targetFile.exists()){
            targetFile.mkdirs();
        }
        //保存
        try {
            file.transferTo(targetFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return fileName;
    }

    /**
     * 读取文件上传目录内的文件内容
     * @param fileName
     * @param request
     * @return
     */
    public static List<String> readUploadFile (String fileName, HttpServletRequest request) {
        if (fileName == null || request == null) {
            throw new IllegalArgumentException("参数为空");
        }
        String path = getRealPath(request);
        File file = new File(path, fileName);
        try {
            return Files.readLines(file, Charsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 删除上传的文件
     * @param fileName
     * @param request
     * @return
     */
    public static boolean deleteUploadFile(String fileName, HttpServletRequest request) {
        if (fileName == null || request == null) {
            throw new IllegalArgumentException("参数为空");
        }
        String path = getRealPath(request);
        File file = new File(path, fileName);
        return file.delete();
    }

}
