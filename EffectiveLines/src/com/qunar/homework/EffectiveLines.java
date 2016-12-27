package com.qunar.homework;

import com.google.common.base.Optional;
import com.google.common.util.concurrent.ExecutionError;

import java.io.*;
import java.util.concurrent.ExecutionException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by joe on 2016/11/11.
 * 统计java代码的有效行数，不考虑多行注释，只考虑空行和单行注释
 */
public class EffectiveLines {
    public static void main(String[] args) {

        InputStream inputStream = EffectiveLines.class.getResourceAsStream("test.txt");
        //获取文件输入流
        Optional<Integer> optional = count(inputStream);
        //统计代码行数
        try {
            inputStream.close();
//            关闭文件流
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (optional.isPresent()) {
            System.out.println("有效行" + optional.get());
        } else {
            System.out.println("读取文件出错！");
        }

    }

    /**
     * 输入java文件
     *
     * @param in
     * @return 有效行数的Optional，如果为空则是读取文件出错
     */
    public static Optional<Integer> count(InputStream in) {
        int effectiveLines = 0;
        try {
            StringBuilder code = new StringBuilder();
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            String buffer;
            while ((buffer = reader.readLine()) != null) {
                buffer = buffer.trim();
                if (!(buffer.equals("") //为空行
                        || buffer.startsWith("//")  //为单行注释
                        || buffer.startsWith("/*") && buffer.endsWith("*/") //在单行使用多行注释
                )) {
                    effectiveLines++;
                }
            }
        } catch (Exception e) {
            return Optional.absent();
        } finally {

        }
        return Optional.of(effectiveLines);
    }
}
