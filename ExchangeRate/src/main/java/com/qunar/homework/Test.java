package com.qunar.homework;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import java.io.File;
import java.net.URL;
import java.util.*;

/**
 * Created by joe on 2016/11/11.
 */
public class Test {
    public static void main(String[] args) throws Exception {

        String urlstr = "http://www.pbc.gov.cn/zhengcehuobisi/125207/125217/125925/index.html";
        Optional<Document> documentOptional = new NetAccess().access(urlstr);
        List<String> list = new DocumentParser().parseList(documentOptional.get());

        ArrayList<RMBRate> rmbRates = new ArrayList<RMBRate>();
        for (int i = 0; i < list.size(); i++) {
            urlstr = "http://www.pbc.gov.cn/"+list.get(i);
            System.out.println(urlstr);
            //获取页面内容
            documentOptional = new NetAccess().access(urlstr);
            //把页面内容解析为RMBRate对象
            RMBRate rate = new DocumentParser().parseData(documentOptional.get());
            rmbRates.add(rate);
        }
        //写出到excel
        new Out().printToExcel(rmbRates,new File("d://.xls"));
//        String urlstr = "http://www.pbc.gov.cn/zhengcehuobisi/125207/125217/125925/3164002/index.html";
//        Optional<Document> documentOptional = new NetAccess().access(urlstr);
//        System.out.println(documentOptional.get());
//        RMBRate rate = new DocumentParser().parseData(documentOptional.get());
//        System.out.println(rate);

    }

}
