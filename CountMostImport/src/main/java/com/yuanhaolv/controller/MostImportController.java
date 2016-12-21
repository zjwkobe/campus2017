package com.yuanhaolv.controller;

import com.yuanhaolv.api.IAnalyzeImport;
import com.yuanhaolv.api.ILoadFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.TreeMap;

/**
 * Created by 元浩 on 2016/12/21.
 */
@Controller("mostImport")
public class MostImportController {
    @Autowired
    ILoadFile loadFile;
    @Autowired
    IAnalyzeImport analyzeImport;

    public TreeMap<String, Integer> mostImport() throws IOException {
        TreeMap<String, Integer> result = new TreeMap<String, Integer>();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please input a project's absolute path:");
        String path = scanner.nextLine();

        List<File> files = new ArrayList<File>();
        File file = new File(path);
        if (!file.exists()){
            throw new FileNotFoundException("The project is not exist!");
        }else {
            files = loadFile.loadJavaSourceFile(file);
        }

        //首先尝试输出所有的Java源文件
        for (File temp : files){
            List<String> bags = analyzeImport.countImport(temp);
            for (String bag : bags){
                Integer count = result.get(bag);
                if (count == null){
                    result.put(bag, 1);
                }else{
                    result.put(bag, count+1);
                }
            }

        }

        return result;
    }
}
