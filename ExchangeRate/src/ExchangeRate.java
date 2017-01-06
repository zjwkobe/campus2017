import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Administrator on 2016/12/28.
 */
public class ExchangeRate {

    static  List<String> date=new LinkedList<>();

    public static void main(String[] args) throws IOException {

        String url1="http://www.kuaiyilicai.com/huilv/d-safe-usd.html";
        String url2="http://www.kuaiyilicai.com/huilv/d-safe-eur.html";
        String url3="http://www.kuaiyilicai.com/huilv/d-safe-hkd.html";

        //获取汇率
        List<String > usa=getRate(url1);
        List<String > eu=getRate(url2);
        List<String > hk=getRate(url3);

        //将结果写入Excel文件
        writeToExcel(usa,eu,hk);

    }

    static  List<String>  getRate(String url) throws IOException {
        List<String > media=new LinkedList<>();
        Document doc = Jsoup.connect(url).get();
        Elements elements =doc.getElementsByClass("table");
        int i;
        Elements es=elements.select("tr");
        for (i=0;i<es.size();i++)
        {
            Elements es1=es.get(i).select("td");
            for (int j=0;j<es1.size();j++){
                if (date.size()<es.size()-1&&j==0) date.add(es1.get(j).text());
                if(j==1) media.add(es1.get(j).text());
            }
        }
        return  media;
    }

    static void writeToExcel(List<String> usa,List<String> eu,List<String> hk){

        int i;
        try{
            File file=new File("exchangeRate.xls");
            if (file.exists()){
                file.delete();
            }
            WritableWorkbook book= Workbook.createWorkbook(new File("exchangeRate.xls"));
            WritableSheet sheet=book.createSheet("first",0);
            Label label1=new Label(0,0,"Date");
            sheet.addCell(label1);
            Label label2=new Label(1,0,"USA");
            sheet.addCell(label2);
            Label label3=new Label(2,0,"EU");
            sheet.addCell(label3);
            Label label4=new Label(3,0,"HK");
            sheet.addCell(label4);
            Label label5,label6,label7,label8;
            for (i=0;i<date.size();i++){
                label5=new Label(0,i+1,date.get(i));
                label6=new Label(1,i+1,usa.get(i));
                label7=new Label(2,i+1,eu.get(i));
                label8=new Label(3,i+1,hk.get(i));

                sheet.addCell(label5);
                sheet.addCell(label6);
                sheet.addCell(label7);
                sheet.addCell(label8);
            }
            book.write();
            book.close();

        }catch (Exception e){
            System.out.println(e);}
    }
}
