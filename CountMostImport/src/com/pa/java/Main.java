package com.pa.java;

import java.util.Scanner;

/**
 * Created by George on 2016-12-19.
 */
public class Main {
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        System.out.print("请输入java源文件目录：");
        String path = scanner.nextLine();
        MostImportClass mostImportClass = new MostImportClass(path);
        System.out.println("统计结果如下：");
        mostImportClass.getMostImportClazzName();
    }
}
