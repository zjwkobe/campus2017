package com.read;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by smile on 2017/1/6.
 */
public class ReadText {
    public int readFileText(String path){
        int countLine=0;
        File file = new File(path);
        try {
            FileInputStream inputStream = new FileInputStream(file);
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
            String str;
            while ((str=br.readLine())!=null){
                //判断当前行是否为有效行
                if(judgeText(str)){
                    countLine++;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return countLine;
    }
    private boolean judgeText(String str){
        Pattern pattern = Pattern.compile("^\\s*//");
        Matcher matcher = pattern.matcher(str);
        return matcher.lookingAt();
    }
}
