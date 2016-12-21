package com.yuanhaolv.service;

import com.yuanhaolv.api.IAnalyzeImport;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 元浩 on 2016/12/21.
 */
@Service("analyzeImport")
public class AnalyzeImportImpl implements IAnalyzeImport {
    public List<String> countImport(File sourceFile) throws IOException {
        List<String> bags = new ArrayList<String>();
        FileReader reader = new FileReader(sourceFile);
        BufferedReader br = new BufferedReader(reader);
        String temp = null;
        boolean hasMetFirstImport = false;//有没有遇到第一个import语句

        while ((temp = br.readLine()) != null) {
            if (temp.trim().startsWith("import")) {
                hasMetFirstImport = true;
                break;
            }
        }
        boolean isCommentOver = false;//注释是不是已经结束
        if (hasMetFirstImport == true){//说明本文件中import了其他的类
            //注意有可能在import语句中间夹杂着注释语句，这一点要考虑
            //PS:一个import语句可能在一行写不完，占用了若干行
            while (temp != null){
                if (temp.trim().startsWith("/*")){//可能是多行注释，也可能是单行注释
                    isCommentOver = false;
                    while (temp != null){
                        if (temp.trim().endsWith("*/")){
                            isCommentOver = true;
                            break;
                        }
                        temp = br.readLine();
                    }
                }
                if (isCommentOver == true){
                    //当前行是注释，不用处理
                    isCommentOver = false;
                }else{
                    if (temp.trim().startsWith("//")){//单行注释

                    }else {//此时将所有的注释语句已经抛弃掉
                        if (temp.trim() == null || temp.trim().length() == 0){//空行

                        }else{
                            String wholeBag = "";
                            if (temp.trim().startsWith("import")){
                                //此时该行以import开头，那么只要找到与之匹配的";"即可找到这一完整的import语句
                                while (temp != null){
                                    wholeBag = wholeBag + temp.trim();
                                    if (temp.trim().endsWith(";")){
                                        bags.add(wholeBag);
                                        break;
                                    }
                                    temp = br.readLine();
                                }
                            }else {//已经将所有的import组织完
                                break;
                            }
                        }

                    }
                }

                temp = br.readLine();
            }


        }
        return bags;
    }
}
