package com.company;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args){
	// write your code here
        System.out.println("请输入java文件绝对路径：");
        Scanner cin=new Scanner(System.in);
        String filePath=cin.next();
        File filename=new File(filePath);
        int countlines=countEffectiveLines(filename);
        System.out.println("该java文件有效代码行数为："+countlines);
    }
    public static int countEffectiveLines(File filename){
        BufferedReader br = null;
        int count=0;
        int whitecount=0;
        int commentcount=0;
        boolean flag=false;
        try {
            br=new BufferedReader(new FileReader(filename));
            String line="";
            while((line=br.readLine())!=null){
                line = line.trim();
                if(line.matches("^[\\s&&[^\\n]]*$")) {
                    whitecount ++;
                } else if (line.startsWith("/*") && !line.endsWith("*/")) {
                    commentcount ++;
                    flag = true;
                } else if (line.startsWith("/*") && line.endsWith("*/")) {
                    commentcount ++;
                } else if (true == flag) {
                    commentcount ++;
                    if(line.endsWith("*/")) {
                        flag = false;
                    }
                } else if (line.startsWith("//")) {
                    commentcount ++;
                } else {
                    count ++;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(br != null) {
                try {
                    br.close();
                    br = null;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return count;
    }
}
