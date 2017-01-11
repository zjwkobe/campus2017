import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlParagraph;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ExchangeRate {

    public static void main(String[] args){
        ArrayList<DailyReport> drList = getList();
        getPrice(drList);
        save2excel(drList);
    }

    private static ArrayList<DailyReport> getList() {

        ArrayList<DailyReport> list = new ArrayList<DailyReport>();
        int pageCount = 2;/*页数*/
        WebClient webClient = new WebClient();
        try {
            for (int i = 1; i <= pageCount; i++) {
                URL url;
                if (i == 1) {
                    url = new URL("http://www.pbc.gov.cn/zhengcehuobisi/125207/125217/125925/index.html");
                } else {
                    url = new URL("http://www.pbc.gov.cn/zhengcehuobisi/125207/125217/125925/17105/index" + i + ".html");
                }
                HtmlPage page = webClient.getPage(url);
                List<HtmlAnchor> anchorList = (List<HtmlAnchor>) page.getByXPath("//font[@class=\"newslist_style\"]/a");
                for (HtmlAnchor anchor : anchorList) {
                    Pattern pattern = Pattern.compile("\\d+");
                    Matcher m = pattern.matcher(anchor.getAttribute("title"));
                    int[] arg = new int[3];
                    for (int j = 0; m.find(); j++) {
                        arg[j] = Integer.parseInt(m.group());
                    }
                    /**
                     * 注意：
                     * 月份：一月是0，二月是1，以此类推，12月是11
                     * 星期：周日是1，周一是2，。。。。。周六是7
                     */
                    Calendar calendar = new GregorianCalendar(arg[0], arg[1]-1, arg[2]);
                    list.add(new DailyReport(calendar, new URL("http://www.pbc.gov.cn" + anchor.getAttribute("href"))));
                }
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (FailingHttpStatusCodeException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            webClient.close();
        }

        return list;
    }

    private static void getPrice(ArrayList<DailyReport> drList) {
        final WebClient webClient = new WebClient();
        try {
            for (DailyReport dr : drList) {
                HtmlPage page = webClient.getPage(dr.getReleaseUrl());
                String temp = ((HtmlParagraph) page.getByXPath("//div[@id=\"zoom\"]/p[1]").get(0)).getFirstChild().getNodeValue();
                dr.setPrice(parsePrice(temp));
            }
        } catch (FailingHttpStatusCodeException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            webClient.close();
        }
    }

    private static ArrayList<Double> parsePrice(String str) {

        ArrayList<Double> list = new ArrayList<Double>();
        Matcher m = Pattern.compile("\\d+\\.\\d+").matcher(str);
        while (m.find()) {
            list.add(Double.parseDouble(m.group()));
        }
        return list;
    }

    private static void save2excel(ArrayList<DailyReport> drList){
        XSSFWorkbook workbook=new XSSFWorkbook();
        XSSFSheet sheet=workbook.createSheet("汇率");
        XSSFRow headRow=sheet.createRow(0);
//        String []headText={"发布日期","1美元对人民币","1欧元对人民币","100日元对人民币","1港元对人民币","1英镑对人民币","1澳大利亚元对人民币","1新西兰元对人民币","1新加坡元对人民币","1瑞士法郎对人民币","1加拿大元对人民币","人民币1元对林吉特","人民币1元对俄罗斯卢布","人民币1元对南非兰特","人民币1元对韩元","人民币1元对阿联酋迪拉姆","人民币1元对沙特里亚尔","人民币1元对匈牙利福林","人民币1元对波兰兹罗提","人民币1元对丹麦克朗","人民币1元对瑞典克朗","人民币1元对挪威克朗","人民币1元对土耳其里拉","人民币1元对墨西哥比索"};
        String []headText={"发布日期","1美元对人民币","1欧元对人民币","1港元对人民币"};
        for(int i=0;i<headText.length;i++){
            headRow.createCell(i).setCellValue(headText[i]);
            sheet.autoSizeColumn(i);
        }
        int count=1;
        for(DailyReport dr:drList){
            XSSFRow row=sheet.createRow(count++);
            Calendar now=Calendar.getInstance();
            now.add(Calendar.DATE,-30);//距今30天前
            Calendar drdate=dr.getReleaseDate();
            if(drdate.after(now)) {
                row.createCell(0).setCellValue(formatDate(drdate.getTime()));
                ArrayList<Double> list = dr.getPrice();
                for (int i = 0, j = 1; i < list.size(); i++) {
                    if (i == 0 || i == 1 || i == 3) {
                        row.createCell(j++).setCellValue(list.get(i));
                    }
                }
            }
        }
        try {
            FileOutputStream fos=new FileOutputStream(new File("C:\\rate.xlsx"));
            workbook.write(fos);
            fos.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                workbook.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static String formatDate(Date date) {
        String str = "yyyy-MM-dd";
        SimpleDateFormat format = new SimpleDateFormat(str);
        return format.format(date);
    }
}

class DailyReport {
    private Calendar releaseDate;
    private URL releaseUrl;
    private ArrayList<Double> price;

    DailyReport(Calendar releaseDate, URL releaseUrl) {
        super();
        price = null;
        this.releaseDate = releaseDate;
        this.releaseUrl = releaseUrl;
    }

    public Calendar getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Calendar releaseDate) {
        this.releaseDate = releaseDate;
    }

    public URL getReleaseUrl() {
        return releaseUrl;
    }

    public void setReleaseUrl(URL releaseUrl) {
        this.releaseUrl = releaseUrl;
    }

    public ArrayList<Double> getPrice() {
        return price;
    }

    public void setPrice(ArrayList<Double> price) {
        this.price = price;
    }
}
