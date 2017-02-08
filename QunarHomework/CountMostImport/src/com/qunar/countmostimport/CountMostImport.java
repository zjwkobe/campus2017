package com.qunar.countmostimport;

import com.google.api.client.util.Maps;
import java.io.*;
import java.util.Map;

public class CountMostImport {

    static Map<String, Integer> classNameCountMap = Maps.newHashMap();

    public static final String KEY = "import ";

    public static void main(String[] args) {
        try {
            readfile("/Users/xavier/letv/lepay-core/src/main/java/com/letv/lepay/core/utils");
        } catch (Exception ex) {
            System.out.println("Error");
        }
        int init = 0;
        String target  = "";
        System.out.println(classNameCountMap);
        for (Map.Entry<String, Integer> entry: classNameCountMap.entrySet()) {
            if (entry.getValue() > init) {
                target = entry.getKey();
                init = entry.getValue();
            }
        }
        System.out.println("target : "+ target + " count : " + init);
    }

    public static boolean readfile(String filepath) throws FileNotFoundException, IOException {
        try {
            File file = new File(filepath);
            if (!file.isDirectory()) {
                System.out.println("文件");
                System.out.println("path=" + file.getPath());
                System.out.println("absolutepath=" + file.getAbsolutePath());
                readTxtFile(file.getPath());
            } else if (file.isDirectory()) {
                System.out.println("文件夹");
                String[] fileList = file.list();
                for (int i = 0; i < fileList.length; i++) {
                    File readfile = new File(filepath + "/" + fileList[i]);
                    if (!readfile.isDirectory()) {
                        System.out.println("path=" + readfile.getPath());
                        System.out.println("absolutepath=" + readfile.getAbsolutePath());
                        readTxtFile(readfile.getPath());
                    } else if (readfile.isDirectory()) {
                        readfile(filepath + "\\" + fileList[i]);
                    }
                }

            }
        } catch (FileNotFoundException e) {
            System.out.println("readfile()   Exception:" + e.getMessage());
        }
        return true;
    }

    public static void readTxtFile(String filePath){
        try {
            String encoding="UTF-8";
            File file=new File(filePath);
            if(file.isFile() && file.exists()){ //判断文件是否存在
                InputStreamReader read = new InputStreamReader(
                        new FileInputStream(file),encoding);//考虑到编码格式
                BufferedReader bufferedReader = new BufferedReader(read);
                String lineTxt = null;
                while((lineTxt = bufferedReader.readLine()) != null){
                    if (lineTxt.contains(KEY)) {
                        String className = lineTxt.split(" ")[1];
                        if (classNameCountMap.keySet().contains(className)) {
                            int originalCount = classNameCountMap.get(className);
                            classNameCountMap.put(className, ++originalCount);
                        } else {
                            classNameCountMap.put(className, 1);
                        }
                    }
                }
                read.close();
            }else{
                System.out.println("找不到指定的文件");
            }
        } catch (Exception e) {
            System.out.println("读取文件内容出错");
            e.printStackTrace();
        }

    }
}