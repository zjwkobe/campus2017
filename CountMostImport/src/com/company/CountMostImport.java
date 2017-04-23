package com.company;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by ZJW on 2017/4/22.
 */
public class CountMostImport {

    String dirname;
    HashMap<String,Integer> record;
    public CountMostImport(String dirname) throws FileNotFoundException {
        this.dirname=dirname;
        record=new HashMap<String,Integer>();
        this.statisticsClazz(new File(this.dirname));
    }
    public int get(String classname){
        Integer value=record.get(classname);
        if(value==null)
          return 0;
        else
          return value;
    }
    public void dealFile(File file) throws FileNotFoundException {
        BufferedReader reader=null;
        reader=new BufferedReader(new FileReader(file));
        String line=null;
        try{
            while((line=reader.readLine())!=null){
                line=line.trim();
                if(line.startsWith("public")||line.startsWith("class")){
                    break;
                }
                if(line.startsWith("import")){
                    String classname=line.substring(6,line.length()-1).trim();
                    Integer value=record.get(classname);
                    if(value==null){
                        record.put(classname,1);
                    }else{
                        record.put(classname,value+1);
                    }
                 }
            }
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    public void statisticsClazz(File file) throws FileNotFoundException {
        if(!file.isDirectory()){
            dealFile(file);
        }else{
            File[] files=file.listFiles();
            for(File tmp:files){
                statisticsClazz(tmp);
            }
        }
    }
    public String getMostImportClassName(){
        int max=Integer.MIN_VALUE;
        String classname=null;
        for(Map.Entry item:this.record.entrySet()){
            String key=(String) item.getKey();
            int value=(Integer)item.getValue();
            if(value>max){
                max=value;
                classname=key;
            }
        }
        return classname;
    }
}
