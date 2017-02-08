/**
 * Created by mumu462 on 2017/1/11.
 */
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.util.ArrayList;

public class Main {
    public static Document getDocument(String url)
    {
        try
        {
                return Jsoup.connect(url).get();
        }
        catch (IOException e)
        {
                     e.printStackTrace();
        }
        return null;
    }
    public static void main(String arg[]) throws Exception {
        Document us=getDocument("http://www.kuaiyilicai.com/huilv/d-boc-usd.html");
        Document hk=getDocument("http://www.kuaiyilicai.com/huilv/d-boc-hkd.html");
        Document eu=getDocument("http://www.kuaiyilicai.com/huilv/d-boc-eur.html");
        ArrayList<String> datadate=new ArrayList<String>();
        ArrayList<String> dataus=new ArrayList<String>();
        ArrayList<String> datahk=new ArrayList<String>();
        ArrayList<String> dataeu=new ArrayList<String>();
        filldata(datadate,us,0);
        filldata(dataus,us,5);
        filldata(datahk,hk,5);
        filldata(dataeu,eu,5);
        ExcelWrite excel =new ExcelWrite("D:\\去哪儿\\ExchangeRate\\Rate.xls");
        excel.createExcel();
        excel.createRowCol(dataeu.size() + 1, 5);
        excel.fillLabel(0, 1, "人民币");
        excel.fillLabel(0,2,"港币");
        excel.fillLabel(0,3,"美元");
        excel.fillLabel(0,4,"欧元");
        fillExcel(datadate, excel, 0);
        fillExcel(datahk,excel,1);
        fillExcel(datahk,excel,2);
        fillExcel(dataus,excel,3);
        fillExcel(dataeu,excel,4);
        excel.save();

    }
    public static void  fillExcel(ArrayList<String> data, ExcelWrite excel,int col)
    {
        for(int i=1;i<data.size()+1;i++)
        {
            if(col==1)
            {
                excel.fillLabel(i,col,"1.0000");
            }
            else {
                excel.fillLabel(i, col, data.get(i-1));
            }
        }
    }
    public static void  filldata(ArrayList<String> data,Document doc,int col)
    {
        Elements trs=doc.select("table").select("tr");
        for(int i=2;i<trs.size();i++)
        {
            Elements tds = trs.get(i).select("td");
            data.add(tds.get(col).text());
        }
    }

}
