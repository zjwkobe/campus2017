import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import javax.xml.xpath.*;
import javax.xml.parsers.*;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.formula.functions.Rate;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

/**
 * Created by yeluo on 1/17/17.
 */
public class ExchangeRate {
    private static  int DAY_NUM = 30;
    private static List<RateBean> rate_list = new ArrayList<RateBean>();

    /*
     * 使用Jsoup抓取并解析html标签数据
     * 抓取指定货币汇率数据
     */
    public static void crawlRateData(String currencyName) throws Exception {
        String url = "http://www.kuaiyilicai.com/huilv/d-safe-" + currencyName + ".html";
        Document doc = Jsoup.connect(url).post();
        Elements datas = doc.body().getElementsByTag("table").first().getElementsByTag("tr");

        for (int i = 1; i < datas.size(); i ++) {
            String date = datas.get(i).getElementsByTag("td").get(0).text();
            String rate = datas.get(i).getElementsByTag("td").get(1).text();
            String exchange = datas.get(i).getElementsByTag("td").get(2).text();

            rate_list.add(new RateBean(date, rate, exchange));
        }
        DAY_NUM = datas.size() - 1;     // 该网站过去30天只有DAY_NUM天有汇率数据
        System.out.println("Get " + currencyName + " Rate.");
        System.out.println("Total: " + DAY_NUM);

    }


    public static void outputAsXls(String path) throws IOException {
        String[] title = { "美元", "备注", "欧元", "备注", "港币", "备注" };
        OutputStream output = new FileOutputStream(new File(path));
        HSSFWorkbook hw = new HSSFWorkbook();
        HSSFSheet hs = hw.createSheet("人民币兑美元欧元港币汇率表");
        HSSFRow r = hs.createRow(0);
        hs.setColumnWidth(0, 5000);

        r.createCell(0).setCellValue("日期");
        for (int i = 0; i < 6; i ++) {
            r.createCell(i + 1).setCellValue(title[i]);
        }

        for (int i = 0; i < DAY_NUM; i ++) {
            HSSFRow rw = hs.createRow(i+1);
            rw.createCell(0).setCellValue(rate_list.get(i).getDate());
            rw.createCell(1).setCellValue(rate_list.get(i).getRate());
            rw.createCell(2).setCellValue(rate_list.get(i).getExchange());
            rw.createCell(3).setCellValue(rate_list.get(i+DAY_NUM).getRate());
            rw.createCell(4).setCellValue(rate_list.get(i+DAY_NUM).getExchange());
            rw.createCell(5).setCellValue(rate_list.get(i+2*DAY_NUM).getRate());
            rw.createCell(6).setCellValue(rate_list.get(i+2*DAY_NUM).getExchange());
        }

        hw.write(output);
        hw.close();
    }
}
