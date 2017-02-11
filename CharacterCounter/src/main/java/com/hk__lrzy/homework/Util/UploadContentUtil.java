package com.hk__lrzy.homework.Util;

import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;


/**
 * Created by hk__lrzy on 2017/2/10.
 */
public class UploadContentUtil {
    public static String readUploadContent(MultipartFile uploadContent){
        StringBuffer content = new StringBuffer();
        if (!uploadContent.isEmpty()){
            InputStreamReader in = null;
            try{
                in = new InputStreamReader(uploadContent.getInputStream());
                char[] buf = new char[8*1024];
                int b;
                while((b = in.read(buf,0,buf.length)) != -1){
                    content.append(String.valueOf(buf,0,b));
                }
                in.close();
            }catch (Exception e){
                System.out.println("error");
                e.printStackTrace();
            }
        }
        return content.toString();
    }
}
