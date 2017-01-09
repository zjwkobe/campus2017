package com.pa.java;

import java.io.*;
import java.util.*;

/**
 * Created by George on 2016-12-19.
 */

public class MostImportClass {
    String dirName;
    HashMap<String, Integer> importClassRecords;
    public MostImportClass(String dir){
        this.dirName = dir;
        importClassRecords = new HashMap<String, Integer>();
        this.statisticsClazz(new File(this.dirName));
    }

    public int get(String clazzName){
        Integer value = importClassRecords.get(clazzName);
        if(value==null) return 0;
        return value;
    }
    public void processFile(File file){
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return;
        }
        String line = null;
        try {
            while((line = reader.readLine()) != null){
                line = line.trim();
                if(line.startsWith("public")||line.startsWith("class")){
                    break;
                }
                if(line.startsWith("import")){
                    String className = line.substring(6, line.length()-1).trim();
                    Integer value = importClassRecords.get(className);
                    if(value==null){
                        importClassRecords.put(className, 1);
                    }else{
                        importClassRecords.put(className, value+1);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //返回排序后的结果
    public MostImportClass sort(){
        ArrayList<Map.Entry<String, Integer>> list = new ArrayList<>(importClassRecords.entrySet());
        Collections.sort(list,
                (o1, o2) ->o2.getValue() - o1.getValue());
        importClassRecords =new LinkedHashMap<>();
        for (int i = 0; i < list.size(); i++) {
            importClassRecords.put(list.get(i).getKey(), list.get(i).getValue());
        }
        return  this;
    }

    public void statisticsClazz(File file){//递归
        if(!file.isDirectory()){//文件
            processFile(file);
        }else{//目录
            File [] files = file.listFiles();
            for(File tmpFile: files){
                statisticsClazz(tmpFile);
            }
        }

    }

    public   void getMostImportClazzName(){
        int i = 1;
        sort();
        for(Map.Entry item: this.importClassRecords.entrySet()) {
            String key = (String) item.getKey();
            int value = (Integer) item.getValue();
            System.out.println(i+"."+key + ":" + value);
            ++i;
            if(i == 11){
                break;
            }
        }
    }
}
