package com.company;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ZJW on 2017/4/21.
 */
public class ExchangeRate {
    private List<String> datetime = new ArrayList<>();
    private List<String> dollarrate = new ArrayList<>();
    private List<String> eurorate = new ArrayList<>();
    private List<String> hkrate = new ArrayList<>();
    //Crawler data
    public void getExchageRateData() throws IOException {
        URL input = new URL("http://www.chinamoney.com.cn/fe-c/historyParity.do");
        Document doc = Jsoup.parse(input.openStream(),"UTF-8","http://www.chinamoney.com.cn/fe-c/historyParity.do");
        Elements table =doc.select("table");
        Elements hang=table.get(2).select("tr");
        for(int i=1;i<hang.size();i++){
            Elements tds = hang.get(i).select("td");
            String date = tds.get(0).text();
            String dollar = tds.get(1).text();
            String euro = tds.get(2).text();
            String hk = tds.get(4).text();
            datetime.add(date);
            dollarrate.add(dollar);
            eurorate.add(euro);
            hkrate.add(hk);
        }
    }
    //Establish Excel output
    public  void getExcel() throws IOException, WriteException {
        String path="C:\\Users\\ZJW\\IdeaProjects\\ExchangeRate\\src\\com\\company\\exchangerate.xls";
        File file=new File(path);
        WritableWorkbook workbook=null;
        try {
            workbook= Workbook.createWorkbook(file);//establish gongzuobu
            WritableSheet sheet = workbook.createSheet("firstsheet",0);//establish diyigegongzuobiao
            Label date = new Label(0,0,"日期");//liehaozaiqian;hanghaozaihou
            sheet.addCell(date);
            Label dollar = new Label(1,0,"人民币对美元");
            sheet.addCell(dollar);
            Label euro = new Label(2,0,"人民币对欧元");
            sheet.addCell(euro);
            Label hk = new Label(3,0,"人民币对港币");
            sheet.addCell(hk);
            int len = datetime.size();

            for(int i=0;i<len;i++){
                Label var1 = new Label(0,i+1,datetime.get(i));
                sheet.addCell(var1);
                Label var2 = new Label(1,i+1,dollarrate.get(i));
                sheet.addCell(var2);
                Label var3 = new Label(2,i+1,eurorate.get(i));
                sheet.addCell(var3);
                Label var4 = new Label(3,i+1,hkrate.get(i));
                sheet.addCell(var4);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (WriteException e) {
            e.printStackTrace();
        }
        workbook.write();
        workbook.close();
    }
}
