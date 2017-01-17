package com.company;

import java.io.*;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * Created by yeluo on 12/26/16.
 */
import java.io.*;
import java.util.regex.Pattern;

/**
 * Created by yeluo on 12/26/16.
 */
public class Main {

    public static void main(String[] args) {
        System.out.println("输入读取文件路径:");
        Scanner in = new Scanner(System.in);
        String path = "";
        path = in.next();
//        String path = "/home/yeluo/test.java";
        long codeLinesCount = GetEffectiveLinesNum(path);

        System.out.println("Effective Lines Count: " + codeLinesCount);
    }

    private static long GetEffectiveLinesNum(String path) {
        long annotationLines = 0;
        long spaceLines = 0;
        long totalLines = 0;
        long codeLines = 0;

        BufferedReader br = null;
        File file = new File(path);

        try {
            br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        // 正则表达式判断每行为 注释、代码、空行
        Pattern singleAnnotationLinePattern = Pattern.compile("(//) | (/\\*+(.)*\\*+/)", Pattern.MULTILINE + Pattern.DOTALL);
//      Pattern mutiAnnotationLinePattern = Pattern.compile("((/\\*+)|(^\\s*\\*)|((^\\s)*\\*+/))+", Pattern.MULTILINE);
        Pattern spaceLinePattern = Pattern.compile("^\\s*$", Pattern.MULTILINE);
        Pattern totalLinePattern = Pattern.compile("^(.)*$", Pattern.MULTILINE + Pattern.DOTALL);



        try {
            String line = "";

            while ((line = br.readLine()) != null) {
                if (singleAnnotationLinePattern.matcher(line).find()) {
                    annotationLines ++;
                }

                if (spaceLinePattern.matcher(line).find()) {
                    spaceLines ++;
                }

                if (totalLinePattern.matcher(line).matches()) {
                    totalLines ++;
                }
            }

            // 有效代码行数为：总行数 - 注释行数 - 空行数
            codeLines = totalLines - spaceLines - annotationLines;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return codeLines;
    }

}
