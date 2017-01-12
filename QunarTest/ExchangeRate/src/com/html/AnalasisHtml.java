package com.html;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 解析HTML
 */
public class AnalasisHtml {
    //得到每一天汇率的超链接
    public List<String> getHref(String html) {
        List<String> list = new ArrayList<>();
        Document document = Jsoup.parse(html);
        Elements elements = document.select("a[href]");

        for (Element el : elements) {
            String title = el.attr("title");
            Pattern pattern = Pattern.compile("20\\d{1,2}年\\d{1,2}月\\d{1,2}日中" +
                    "国外汇交易中心受权公布人民币汇率中间价公告");//正则表达式匹配
            Matcher matcher = pattern.matcher(title);
            if (matcher.matches()) {
                String text = el.attr("href");
                text = "http://www.pbc.gov.cn" + text;
                list.add(text);
            }

        }
        return list;

    }

    //解析每一天的汇率
    public List<String> analasisRate(String html){

        Document document = Jsoup.parse(html);
        Elements elements = document.select("p");

        Element element = elements.get(0);

        String[] daterate = (element.text()).split("：");

        List<String> returnlist = new ArrayList<>();
        String data = daterate[0];
        String rate = daterate[1];
        String[] onedata = data.split("，");
        String[] d = onedata[1].split("日");
        returnlist.add(d[0]+"日");
        String[] listrate = rate.split("，");
        returnlist.add(getNumRate(listrate[0]));
        returnlist.add(getNumRate(listrate[1]));
        returnlist.add(getNumRate(listrate[3]));
        return returnlist;

    }

    public String getNumRate(String str){
        str = str.replace("对","=");
        str = str.replace("人民币","");
        str = str + "人民币";
        str = str.replace("元","");
        StringBuffer buff = new StringBuffer(str);
        buff.insert(2,"元");
        return buff.toString();
    }


}
