package com.smile;

import com.excel.OutputExcel;
import com.getcookie.GetCookie;
import com.html.AnalasisHtml;
import com.html.HTMLData;

import java.util.ArrayList;
import java.util.List;

/**
 * 中国人民银行网站有反爬虫处理，在http请求中需加入Cookie，cookie是动态的，cookie是通过第一次请求返回的代码运行得到的
 * 且cookie有时效，大概5分钟刷新一次，所以每次请求都需要新的cookie，所以运行时间会长一点
 */
public class Test {
    public static void main(String arg[]){

        System.out.println("汇率清单会在当前目录下生成，请注意查询！！！,运行时间大概10s");

        GetCookie getCookie =new GetCookie();
        HTMLData htmlData  = new HTMLData();  //http网络数据，在得到cookie之后使用
        AnalasisHtml analasisHtml = new AnalasisHtml(); //解析html
        OutputExcel outputExcel = new OutputExcel();//输出excel表格
        String Cookie;
        try {
            Cookie = getCookie.myConnect("http://www.pbc.gov.cn/");
            //分俩页请求
            String allHreafData1 = htmlData.getData("http://www.pbc.gov.cn/zhengcehuobisi/125207/125217/" +
                            "125925/index.html",
                    Cookie);
            String allHreafData2 = htmlData.getData("http://www.pbc.gov.cn/zhengcehuobisi/125207/125217/" +
                            "125925/17105/index2.html",
                    Cookie);

            List<String> hrefList1;
            List<String> hrefList2;
            hrefList1 = analasisHtml.getHref(allHreafData1);
            hrefList2 = analasisHtml.getHref(allHreafData2);

            List<List<String>> allrata = new ArrayList<>();
            List<String> onerate = new ArrayList<>();
            for(String strurl : hrefList1){
                String dayrate = htmlData.getData(strurl,Cookie);
                onerate = analasisHtml.analasisRate(dayrate);
                allrata.add(onerate);
            }
            for(String strurl : hrefList2){
                String dayrate = htmlData.getData(strurl,Cookie);
                onerate = analasisHtml.analasisRate(dayrate);
                allrata.add(onerate);
            }
            outputExcel.exportTable(allrata);

        }
        catch (Exception e){

        }
    }
}
