package com.smile;

import com.output.OutResult;
import com.read.ReadFile;
import com.read.ReadText;

import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        //输入当前文件路径
        System.out.println("请输入文件路径：");
        Scanner scanner = new Scanner(System.in);
        String path = scanner.nextLine();
//        String path="D://test/jar";
        ReadFile readFile = new ReadFile();  //读取文件实例
        List<String> listpath;

        StrLine strLine = new StrLine();
        TopTen topTen=new TopTen();         //所有文件中的import的TOP10
        HashMap<String,Integer> topimportMap;
        ReadText readText = new ReadText();

        listpath = readFile.getFolder(path);  //读取目录中的文件保存在listpath中
        StringBuffer classStr = new StringBuffer();
        if(listpath.isEmpty()){
            System.out.println("路径中文件为空！！！！！请确认路径");
        }
        else{
            //根据文件路径依次读取内容
            for(int i=0;i<listpath.size();i++){
                //每一个文件路径都做处理，找出import，返回的是只包含类名的String
                String reString = readText.readFileText(listpath.get(i));
                classStr.append(reString);
            }
            topimportMap = topTen.topTen(classStr.toString());
            //输出map中的数据，因为topimportMap中的数据最少是10个
            OutResult outResult = new OutResult();
            outResult.printResult(topimportMap);
        }
    }

}
