package com.qunar;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class EffectiveLines {

    static boolean multiLine = false; //多行注释标记

    public static void main(String []args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Please input the full path of your java file:");
        String filePath=scanner.nextLine();
        scanner.close();
        if (verifyPath(filePath)) {
            File file = new File(filePath);
            int count=0;
            try {
                scanner = new Scanner(file);
                String lineStr = null;
                while (scanner.hasNextLine()) {
                    lineStr = scanner.nextLine().trim();
                    if(isEffectiveLine(lineStr))
                        count++;
                }

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } finally {
                scanner.close();
                System.out.println("Effective lines count : "+count);
            }
        }
    }

    private static boolean verifyPath(String path) {
        boolean flag=true;
        String str=path.substring(path.length() - 5);
        if (!path.substring(path.length() - 5).equals(".java")) {
            flag = false;
            System.out.println("Error : File type should be .java!");
        }

        File file=new File(path);
        if (!file.exists()) {
            flag = false;
            System.out.println("Error : File not exist!");
        }

        return flag;
    }

    private static boolean isEffectiveLine(String line) {
        boolean flag = true;
        if (line.length() == 0) {
            flag=false;//空行
        }

        if (line.length()>=2&&line.substring(0,2).equals("/*")) {
            multiLine=true;
        }

        if(multiLine){
            flag=false;
        }else{
            if (line.length()>=2&&line.substring(0, 2).equals("//")) {
                flag=false;
            }
        }

        if (line.length()>=2&&line.substring(line.length()-2).equals("*/")) {
            multiLine=false;
        }

        return flag;
    }
}
