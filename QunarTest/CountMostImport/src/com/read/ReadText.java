package com.read;

import com.smile.StrLine;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

/**
 * 将文件中的import类以字符串的方式加入到String中并返回
 * 最终在TOP10中处理的所有的import类
 */
public class ReadText {
    public String readFileText(String path){
        File file = new File(path);
        StringBuilder imStr=new StringBuilder();
        StrLine strLine = new StrLine();        //判断及提取import中的类
        try {
            FileInputStream inputStream = new FileInputStream(file);
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));

            String str;
            while ((str=br.readLine())!=null){
                String brStr=strLine.getLine(str);   //每一个import都做截取类的处理并返回
                if(brStr!=null){
                    imStr.append(brStr);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return imStr.toString();
    }

}
