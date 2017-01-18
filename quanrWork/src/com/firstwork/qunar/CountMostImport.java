package com.firstwork.qunar;

import java.io.*;
import java.util.*;

/**
 * Created by admin on 2017/1/11.
 */
public class CountMostImport {
    private Map<String, Integer> map = new HashMap<String, Integer>();

    public static void main(String arg[]){
        CountMostImport cmi = new CountMostImport();
        String path = "C:\\Users\\admin\\Desktop\\JavaIO";

        cmi.GetFile(path);
        cmi.findTopTenImportFile();

    }

    public void GetFile(String path){
        DepthSearch(new File(path));
    }

    /*  传入一个地址 通过递归的方式将地址中的目录进行解析
    * */
    public String  DepthSearch(File f){
        if(f.isDirectory()){
            File[] fileList = f.listFiles();
            if(fileList!=null){
                for (File file:fileList) {
                    DepthSearch(file);
                }
            }
        }else {
            findNumberOfImport(f);
        }
        return null;
    }
    //将 包的内容存储到 HashMap中
    private void findNumberOfImport(File f){
        try {
            InputStream in = new FileInputStream(f);
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            String str = "";
            try {
                while((str = reader.readLine())!=null){
                    //  str.trim();
                    str = str.replaceAll("\\s*", "");
                    if(str.startsWith("import")){
                        str = str.replaceAll("\\s*", "");
                        str = str.substring(6);   //import的长度
                        Integer value = map.get(str);
                        //返回值为null 代表其中不包含键值映射关系 返回为null
                        if(value ==null){
                            map.put(str, 1);
                        }else{
                            map.put(str, value+1);
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    //用于统计HashMap中导入
    //使用堆排序进行排序
    public void findTopTenImportFile(){
        HashMap<String, Integer>dmap  = hashMapSort();
        int index = 1;
        Iterator<String> iter = dmap.keySet().iterator();
        while(iter.hasNext()){
            String key = iter.next();
            System.out.println("top"+index+": "+"import "+ key);
            index++;
            if(index>10)
                break;
        }
    }
    public  HashMap<String, Integer>  hashMapSort(){
        //1、按顺序保存map中的元素，使用LinkedList类型
        List<Map.Entry<String, Integer>> keyList = new LinkedList<Map.Entry<String, Integer>>(map.entrySet());
        //2、按照自定义的规则排序
        Collections.sort(keyList, new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1,
                               Map.Entry<String, Integer> o2) {
                if(o2.getValue().compareTo(o1.getValue())>0){
                    return 1;
                }else if(o2.getValue().compareTo(o1.getValue())<0){
                    return -1;
                }  else {
                    return 0;
                }
            }

        });
        //3、将LinkedList按照排序好的结果，存入到HashMap中
        HashMap<String,Integer> result=new LinkedHashMap<>();
        for(Map.Entry<String, Integer> entry:keyList){
            result.put(entry.getKey(),entry.getValue());
        }
        return result;
    }
}
