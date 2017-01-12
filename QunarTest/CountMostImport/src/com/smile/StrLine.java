package com.smile;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * str==null 判断的是是否到文本末尾
 * str.length 判断的是是否存在字符，其中只有空格返回0，如果有字符，则字符前面的空格也做统计
 * return 返回值是类名
 * 正则表达式匹配import头文件
 */
public class StrLine {
    public String getLine(String str){

        if(judjeStr(str)){
            String spliteStr[] = str.split("\\.");//分割import字符串，取类名
            if(spliteStr[spliteStr.length - 1] == "*"){
                return spliteStr[spliteStr.length - 2];
            }
            return spliteStr[spliteStr.length - 1];
        }else{
            return null;
        }

    }

    public boolean judjeStr(String str){
        //正则表达式匹配，过滤掉注释的import，及包含import的字符
        Pattern pattern = Pattern.compile("^\\s*import\\s.*");
        Matcher matcher = pattern.matcher(str);
        return matcher.matches();
    }
}
