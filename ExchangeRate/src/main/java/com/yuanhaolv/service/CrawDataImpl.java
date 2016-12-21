package com.yuanhaolv.service;

import com.yuanhaolv.api.ICrawData;
import com.yuanhaolv.model.Area;
import com.yuanhaolv.model.Exchange;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by 元浩 on 2016/12/21.
 */
@Service("crawDataImpl")
public class CrawDataImpl implements ICrawData {
    private static final String FORMAT = "yyyy/MM/dd";//将日期格式化之前的格式，此日期格式为网页上的日期格式
    private static final String EUROPE = "http://cn.exchange-rates.org/history/CNY/EUR/T";
    private static final String USA = "http://cn.exchange-rates.org/history/CNY/USD/T";
    private static final String HK = "http://cn.exchange-rates.org/history/CNY/HKD/T";

    public List<Exchange> crawData(Area area) throws IOException {
        List<Exchange> result = new ArrayList<Exchange>();
        switch (area){
            case EUROPE:
                result = crawDataFromEurope();
                break;
            case USA:
                result = crawDataFromUSA();
                break;
            case HK:
                result = crawDataFromHK();
                break;
        }
        return result;
    }

    private List<Exchange> crawDataFromEurope() throws IOException{
        Area area = Area.EUROPE;
        Document doc = Jsoup.connect(EUROPE).get();
        List<Exchange> result = crawData(doc, area);
        return result;
    }

    private List<Exchange> crawDataFromUSA() throws  IOException{
        Area area = Area.USA;
        Document doc = Jsoup.connect(USA).get();
        List<Exchange> result = crawData(doc, area);
        return  result;
    }

    private List<Exchange> crawDataFromHK() throws IOException{
        Area area = Area.HK;
        Document doc = Jsoup.connect(HK).get();
        List<Exchange> result = crawData(doc, area);
        return result;
    }

    /**
     *
     *   Description: 抽象出来的爬取数据的公共方法
     *   @authour 元浩
     *   @date 2016/12/21
     *
     *   @param doc 根据Jsoup连接的结果得到的Document
     *   @param area 要爬取的区域
     *   @return List<Exchange> 爬取的结果
     *   @throws
     *   @since
     **/
    private List<Exchange> crawData(Document doc, Area area){
        List<Exchange> result = new ArrayList<Exchange>();
        Elements table = doc.getElementsByClass("table-responsive");
        for (int i = 0; i < table.size(); i++){
            Elements rows = table.get(i).select("tr");
            for (int row = 0; row < 30; row++){
                Exchange temp = new Exchange();
                temp.setArea(area);
                Elements cols = rows.get(row).select("td");
                String date = cols.get(0).text();
                String exchange =  cols.get(2).text();
                temp.setDate(translate(date));
                temp.setExchange(Float.parseFloat(exchange.substring(0, 7)));
                result.add(temp);
            }
        }
        return  result;
    }
    /**
     *
     *   Description: 将字符串格式的时间转换成Date 格式:yyyy/mm/dd
     *   @authour 元浩
     *   @date 2016/12/21
     *
     *   @param date 要转换的时间
     *   @return Date 转换之后的日期
     *   @throws
     *   @since
     **/
    private Date translate (String date){
        Date result = null;
        try{
            result = new SimpleDateFormat(FORMAT).parse(date);
        }catch (ParseException e){
            System.out.println("Sorry, wrong date format!");
        }

        return result;
    }

}
