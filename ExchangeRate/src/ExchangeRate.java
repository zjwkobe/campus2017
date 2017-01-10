import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;
import java.io.File;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by wenzh on 2016/12/16.
 */
public class ExchangeRate
{
    static ArrayList<StringBuilder> htmlList = new ArrayList<StringBuilder>();
    static ArrayList<String> allDate = new ArrayList<String>();
    static ArrayList<String> allDollar = new ArrayList<String>();
    static ArrayList<String> allEuro = new ArrayList<String>();
    static ArrayList<String> allHK = new ArrayList<String>();
    static ArrayList<File> dateFiles = new ArrayList<File>();

    static int pages = 0;

    static String patternDate = "([\\d]{4}年[\\d]{1,2}月[\\d]{1,2}日)";
    static String patternDoallar = "美元对人民币([\\d]\\.[\\d]+)";
    static String patternEuro = "欧元对人民币([\\d]\\.[\\d]+)";
    static String patternHK = "港元对人民币([\\d]\\.[\\d]+)";

    public static void main(String[] args) throws IOException
    {
        String html = "http://www.pbc.gov.cn/zhengcehuobisi/125207/125217/125925/17105/index1.html";
        getHtml(html);
        createFile();
        parseContent();
        createExcle();
    }

    private static void createExcle()
    {
        WritableWorkbook book = null;
        try
        {
            book = Workbook.createWorkbook(new File("test.xls"));
            WritableSheet sheet = book.createSheet("first sheet", 0);


            Label label0 = new Label(0, 0, "日期");
            sheet.addCell(label0);
            Label label1 = new Label(1, 0, "美元");
            sheet.addCell(label1);
            Label label2 = new Label(2, 0, "欧元");
            sheet.addCell(label2);
            Label label3 = new Label(3, 0, "港元");
            sheet.addCell(label3);

            for (int i = 0; i < 30; i++)
            {
                String date = allDate.get(i);
                Label dataLabel = new Label(0, i + 1, date);
                sheet.addCell(dataLabel);

                String dollar = allDollar.get(i);
                Label dollarLabel = new Label(1, i + 1, dollar);
                sheet.addCell(dollarLabel);

                String euro = allEuro.get(i);
                Label euroLabel = new Label(2, i + 1, euro);
                sheet.addCell(euroLabel);

                String hk = allHK.get(i);
                Label hkLabel = new Label(3, i + 1, hk);
                sheet.addCell(hkLabel);
            }

            sheet.setColumnView(0,17);
            book.write();
        } catch (Exception e)
        {
            e.printStackTrace();
        } finally
        {
            if (book != null)
            {
                try
                {
                    book.close();
                    book = null;
                } catch (WriteException e)
                {
                    e.printStackTrace();
                } catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        }
    }

    private static void parseContent() throws IOException
    {
        for (File f : dateFiles)
        {
            String content = "";
            String line = "";
            BufferedReader br = new BufferedReader(new FileReader(f));
            while ((line = br.readLine()) != null)
            {
                content += line;
            }

            String date = getExRate(patternDate, content);
            allDate.add(date);
            String dollar = getExRate(patternDoallar, content);
            allDollar.add(dollar);
            String euro = getExRate(patternEuro, content);
            allEuro.add(euro);
            String hk = getExRate(patternHK, content);
            allHK.add(hk);
        }
    }

    private static void createFile() throws IOException
    {
        File file = new File("近30天人民币汇率中间价");
        file.mkdir();

        for (int i = 0; i < htmlList.size(); i++)
        {
            String content = getContent(String.valueOf(htmlList.get(i)));
            String fileName = getExRate(patternDate, content);

            String filePath = file.getAbsolutePath();
            filePath += "\\" + fileName + ".txt";
            File dateFile = new File(filePath);
            dateFile.createNewFile();

            dateFiles.add(dateFile);

            FileWriter fw = new FileWriter(dateFile);
            fw.write(content);
            fw.flush();
            fw.close();
        }
    }

    private static String getExRate(String pattern, String content)
    {
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(content);
        m.find();
        String str = m.group(1);
        m.reset();
        return str;
    }

    private static void getHtml(String html)
    {
        try
        {

            java.util.logging.Logger.getLogger("com.gargoylesoftware").setLevel(java.util.logging.Level.OFF);
            WebClient web = new WebClient(BrowserVersion.CHROME);
            HtmlPage htmlPage = web.getPage(html);
            String pageXml = htmlPage.asXml();

            Document doc = Jsoup.parse(pageXml);
            Elements divs = doc.select("div[opentype]");
            Elements links = divs.get(0).select("a");


            for (Element link : links)
            {
                StringBuilder s = new StringBuilder("http://www.pbc.gov.cn");
                s.append(link.attr("href"));
                htmlList.add(s);

                pages++;
                if (pages > 30)
                {
                    return;
                }
            }

            Elements nextPageLinks = doc.select("a");
            String nextPageHtml = "http://www.pbc.gov.cn";

            int i = 0;
            for (i = 0; i < nextPageLinks.size(); i++)
            {
                if (nextPageLinks.get(i).text().equals("下一页"))
                {
                    nextPageHtml += nextPageLinks.get(i).attr("tagname");
                    break;
                }
            }

            if (i != nextPageLinks.size())
            {
                getHtml(nextPageHtml);
            }
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    private static String getContent(String html) throws IOException
    {
        String content = "";
        try
        {
            WebClient web = new WebClient();
            HtmlPage htmlPage = web.getPage(html);

            String pageXml = htmlPage.asXml();
            Document doc = Jsoup.parse(pageXml);

            Elements paragraphs = doc.select("p");
            for (Element para : paragraphs)
            {
                content += para.text();
            }
            return content;

        } catch (IOException e)
        {
            e.printStackTrace();
        }
        return content;
    }
}
