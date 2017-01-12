package com.smile;



import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入你要查询有效行数的文件地址或目录地址：");
        String path = scanner.nextLine();
//        String path = "E:\\java\\demo\\08_Frame";

        ReadText readText = new ReadText();

        //判断路径是java文件则直接读取
        if(path.endsWith(".java")){
            int CountNum = readText.readFileText(path);

            System.out.println("总有效行数："+CountNum);

            if(CountNum!=0){
                System.out.println(path+":有效行数"+CountNum);
            }
        }
        else{
            List<String> listpath ;
            Map<String,Integer> map = new HashMap<>();
            ReadFile readFile = new ReadFile();
            listpath = readFile.getPath(path);
            for(int i=0;i<listpath.size();i++){
                map.put(listpath.get(i),readText.readFileText(listpath.get(i)));
            }
            Set<Map.Entry<String,Integer>> entries = map.entrySet();
            int countNum = 0;
            for(Map.Entry<String,Integer> it : entries){
                countNum += it.getValue();
                System.out.println(it.getKey()+"有效行数:"+it.getValue());
            }

        }

    }

}
