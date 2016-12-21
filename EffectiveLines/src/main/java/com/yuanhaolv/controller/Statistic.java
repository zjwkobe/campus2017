package com.yuanhaolv.controller;

import com.yuanhaolv.api.IFileLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Created by 元浩 on 2016/12/21.
 */
@Service("enter")
public class Statistic {
    @Autowired
    IFileLoader fileLoader;
    public int getLines (File file) throws FileNotFoundException, IOException {
        int lines = analyze(file);
        return lines;
    }

    private int analyze (File file) throws FileNotFoundException, IOException{
        StringBuffer sb = fileLoader.loadContent(file);

        int result = 0;
        String[] lines = sb.toString().split("\n");

        boolean commnet = false;//该行是不是注释
        for (int line = 0; line < lines.length; line++){
            String temp = lines[line].trim();//去掉行首位的空格
            if (temp.length() != 0){
                if (temp.startsWith("/*")){//本行以/*开头，是注释
                    commnet = true;
                    //下面匹配与之对应的"*/"
                    while (line < lines.length){
                        temp = lines[line].trim();
                        if (temp.endsWith("*/")){
                            break;
                        }else{
                            line++;
                        }
                    }
                }
                if (commnet == false){
                    if (temp.startsWith("//")){//该行是以//开头的注释
                        //do nothing
                    }else{
                        result++;
                    }
                }else{
                    commnet = false;
                }


            }
        }
        return result;
    }
}
