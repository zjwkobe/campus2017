package com.qunar.effectiveLines;

import java.io.*;
import java.util.Scanner;

/**
 * Created by Administrator on 2016/11/11 0011.
 */
public class StatisticCodeLines {
    public static int normalLines = 0;
    public static int whiteLines = 0;
    public static int commentLines = 0;

    public static void main(String[] args) throws IOException {
        System.out.println("请输入文件路径");
        Scanner sc = new Scanner(System.in);
        String fileName = sc.next();
        sc.close();
        File file = new File(fileName);
        if (file.exists()) {
            statistic(file);
        }
        System.out.println("总有效代码行数: " + normalLines);
        System.out.println("总空白行数：" + whiteLines);
        System.out.println("总注释行数：" + commentLines);
        System.out.println("总行数：" + (normalLines + whiteLines + commentLines));
    }


    private static void statistic(File file) throws IOException {
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            for (int i = 0; i < files.length; i++) {
                statistic(files[i]);
            }
        }
        if (file.isFile()) {
            if (file.getName().matches(".*\\.java")) {
                parse(file);
            }
        }
    }

    public static void parse(File file) {
        BufferedReader br = null;
        boolean comment = false;
        int temp_whiteLines = 0;
        int temp_commentLines = 0;
        int temp_normalLines = 0;
        try {
            br = new BufferedReader(new FileReader(file));
            String line = "";
            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (line.matches("^[\\s&&[^\\n]]*$")) {  // \s表示任意空白字符，\n表示回车
                    //空行
                    whiteLines++;
                    temp_whiteLines++;
                } else if (line.startsWith("/*") && !line.endsWith("*/")) {
                    //判断此行为"/*"开头的注释行
                    commentLines++;
                    comment = true;
                } else if (comment == true && !line.endsWith("*/")) {
                    //为多行注释中的一行
                    commentLines++;
                    temp_commentLines++;
                } else if (line.startsWith("*/")) {
                    //为多行注释的结束航
                    commentLines++;
                    temp_commentLines++;
                    comment = false;
                } else if (line.startsWith("//")) {
                    //单行注释
                    commentLines++;
                    temp_commentLines++;
                } else {
                    //正常代码行
                    normalLines++;
                    temp_normalLines++;
                }
            }
            System.out.println(
                    "文件名称：" + file.getName() + " " +
                    "有效行数：" + temp_normalLines + " " +
                    "空白行数：" + temp_whiteLines + " " +
                    "注释行数：" + temp_commentLines + " " +
                    "总行数：" + (temp_commentLines + temp_normalLines + temp_whiteLines) +
                    "  ");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                    br = null;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
