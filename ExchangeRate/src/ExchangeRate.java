/**
 * Created by GL on 2016/12/23.
 */

import org.jsoup.*;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class ExchangeRate {
    private static Document getAllData() throws IOException {
        String user_agent = "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/55.0.2883.87 Safari/537.36";
        String host = "www.safe.gov.cn";
        String url = "http://www.safe.gov.cn/AppStructured/view/project_RMBQuery.action";
        String refer = "http://www.safe.gov.cn/AppStructured/view/project_RMBQuery.action";

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar1=new GregorianCalendar();
        calendar1.add(Calendar.DATE, -1);
        Date now = calendar1.getTime();
        System.out.print(df.format(now));
        Calendar calendar2=new GregorianCalendar();
        calendar2.add(Calendar.DATE, -30);
        Date end = calendar2.getTime();
        System.out.print(df.format(end));

        Map headers = new HashMap();
        headers.put("User-Agent", user_agent);
        headers.put("Host", host);
        headers.put("Referer", refer);

        Map data = new HashMap();
        data.put("projectBean.endDate", df.format(now));
        data.put("projectBean.startDate", df.format(end));
        data.put("queryYN", "true");
        Document doc = Jsoup.connect(url).headers(headers).data(data).post();
        String title = doc.title();
//        System.out.print(doc);
        return doc;
    }

    private static ArrayList extractDate(Document doc){
        ArrayList<ArrayList<String>> all_data = new ArrayList<ArrayList<String>>();
        Element content = doc.getElementById("InfoTable");
        Elements trs = content.getElementsByTag("tr");
        for (Element tr : trs) {
            Elements tds = tr.getElementsByTag("td");
            ArrayList rates = new ArrayList();
            for (Element td : tds) {
                rates.add(td.text().trim());
            }
            all_data.add(rates);
        }
       return all_data;
    }

    private static void wirteToExcel(ArrayList all_data){

    }
    public static void main(String[] args) throws IOException {
        ArrayList<ArrayList<String>> all_data = extractDate(getAllData());
        WriteToExcel.writeToExcel(all_data);

    }
}
