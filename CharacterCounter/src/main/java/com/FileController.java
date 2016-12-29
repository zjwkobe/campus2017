package com;

import com.google.common.collect.Multisets;
import com.google.common.collect.TreeMultiset;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.Collator;
import java.util.Locale;

/**
 * @author Lihaochuan
 * @description
 */
@Controller
public class FileController {
    private static final char punctuation=0;
    private static final char chinese=1;
    private static final char english=2;
    private static final char number=3;
    private static final char special=4;//include 空格' '回车'\r' '\n' 制表'\t'
    static char[] charlist=new char[65536];
    static {
        charlist[' ']=special;
        charlist['\n']=special;
        charlist['\t']=special;
        charlist['\r']=special;
        for (int i = 48; i <58; i++) {
            charlist[i]=number;
        }
        for (int i = 65; i < 91; i++) {
            charlist[i]=english;
        }
        for (int i = 97; i < 123; i++) {
            charlist[i]=english;
        }
        //基本汉字 4E00-9FA5
        for (int i = 0x4e00; i <= 0x9fa5; i++) {
            charlist[i]=chinese;
        }
//        //CJK标点符号：3000-303F
//        for (int i = 0x3000; i <= 0x303f; i++) {
//            charlist[i]=punctuation;
//        }
//        //中文竖排标点：FE10-FE1F
//        for (int i = 0xfe10; i <= 0xfe1f; i++) {
//            charlist[i]=punctuation;
//        }
//        //英文标点
    }
    @RequestMapping("/file")
    public @ResponseBody
    JsonData handleFile(@RequestParam MultipartFile file) {
        if(file.getSize()>1024*1024){
            JsonData jsonData=new JsonData();
            jsonData.setSuccess(false);
            return jsonData;
        }
        try {
            return characterCounter(new String(file.getBytes(),"utf-8"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    @RequestMapping(value = "/text")
    public @ResponseBody
    JsonData handletext(String text) {
        return characterCounter(text);
    }

    private JsonData characterCounter(String str){
        int[] count=new int[5];
        TreeMultiset<String> set=TreeMultiset.create(Collator.getInstance(Locale.CHINA));
        int size=str.length();
        for (int i = 0; i < size; i++) {
            char c=str.charAt(i);
            char type=charlist[c];
            count[type]++;
            if(type==1){
                set.add(String.valueOf(c));
            }
        }
        JsonData data=new JsonData();
        data.setChinese(count[1]);
        data.setEnglish(count[2]);
        data.setNumber(count[3]);
        data.setPunctuation(size-count[1]-count[2]-count[3]-count[4]);
        String[][] top=new String[3][2];
        int k=0;
        for(String ch:Multisets.copyHighestCountFirst(set).elementSet()){
            top[k][0]=ch;
            top[k][1]=String.valueOf(set.count(ch));
            if(k==2) break;
            k++;
        }
        data.setTop(top);
        return data;
    }
}
