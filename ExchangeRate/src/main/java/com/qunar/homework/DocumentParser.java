package com.qunar.homework;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Created by joe on 2016/11/13.
 */
public class DocumentParser {
    public RMBRate parseData(Document document) {
        RMBRate rate = new RMBRate();
        Elements elements = document.getElementById("zoom").getElementsByTag("p");
        Element element = elements.get(elements.size()-1);
        String dateStr = element.html().trim();
        dateStr = dateStr.substring(dateStr.indexOf("2"));
        try {
            Date date = new SimpleDateFormat("yyyy年mm月dd日").parse((dateStr));
            rate.setDate(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }catch (Exception e){

        }
        String s = elements.get(0).html().trim();
        String[] strings = s.substring(s.indexOf("：") + 1).split("，");
        double meiyuan = Double.parseDouble(strings[0].substring(strings[0].indexOf("币")+1,strings[0].lastIndexOf("元")));
        double ouyuan = Double.parseDouble(strings[1].substring(strings[1].indexOf("币")+1,strings[1].lastIndexOf("元")));
        double gangyuan = Double.parseDouble(strings[3].substring(strings[3].indexOf("币")+1,strings[3].lastIndexOf("元")));

        meiyuan  = (100/meiyuan);
        ouyuan = 100/ouyuan;
        gangyuan = 100/gangyuan;
        HashMap<String, Double> map = new HashMap<String, Double>();
        map.put("美元",meiyuan);
        map.put("欧元",ouyuan);
        map.put("港元",gangyuan);
        rate.setRate(map);
        return rate;

    }
    public List<String> parseList(Document document){
        List<String> list = new ArrayList<String>(20);
        Elements elements = document.getElementsByTag("font");
        for (int i = 0; i <elements.size() -1; i++) {
            String href = (elements.get(i).getElementsByTag("a")).get(0).attr("href");
            list.add(href);
        }
        return list;
    }

}
