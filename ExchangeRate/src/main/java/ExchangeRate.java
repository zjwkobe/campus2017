import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.DomNodeList;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import org.apache.poi.hssf.usermodel.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Lihaochuan on 2016/11/23.
 */
public class ExchangeRate {
    public static void main(String[] args) {
        final BlockingDeque<String[]> bq=new LinkedBlockingDeque<>();
        final Map<String,String> map=new ConcurrentHashMap<>();

        final WebClient client = new WebClient(BrowserVersion.CHROME);
        client.getOptions().setJavaScriptEnabled(true);
        client.getOptions().setDoNotTrackEnabled(true);
        client.getOptions().setCssEnabled(false);//禁用css支持
        client.getOptions().setTimeout(100000);//设置连接超时时间


        final ExecutorService executorService = Executors.newFixedThreadPool(20);
        final Worker worker=new Worker(bq,map);
        for (int i = 0; i <10 ; i++) {
            executorService.execute(worker);
        }

        String url = "http://www.pbc.gov.cn/zhengcehuobisi/125207/125217/125925/index.html";
        final int targetCount=30;
        int curCount=0;
        final long minTime=System.currentTimeMillis()-targetCount*24*3600*1000l;
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy年MM月dd日");
        boolean done=false;
        do{
            HtmlPage page=null;
            try {
                page = client.getPage(url);
                DomNodeList<DomElement> font = page.getElementsByTagName("font");
                for (int i = 0; i < font.getLength()-1; i++) {
                    DomElement element = font.get(i).getChildElements().iterator().next();
                    String Stime=element.getTextContent().substring(0,11);
                    try {
                        long time=simpleDateFormat.parse(Stime).getTime();
                        if(time<minTime){
                            done=true;
                            break;
                        }else{
                            curCount++;
                            String[] in=new String[2];
                            in[0]=String.valueOf(curCount);
                            in[1]=element.getAttribute("href");
                            bq.put(in);
                        }
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                break;
            }
            DomElement domElement =((List<DomElement>) page.getByXPath("//td[@nowrap='true']")).get(0)
                    .getElementsByTagName("a").get(2);
            url="http://www.pbc.gov.cn"+domElement.getAttribute("tagname");
        }while (!done);
        while(map.size()<curCount){
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        client.close();
        executorService.shutdownNow();
        if(writeToExcel(map)) System.out.println("写入到excel成功！");
        else System.out.println("写入失败！");
    }
    private static boolean writeToExcel(Map<String,String> map){
        HSSFWorkbook workbook=new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("人民币汇率中间价");
        HSSFRow row0 = sheet.createRow(0);
        row0.setHeight((short) 500) ;
        sheet.setColumnWidth(0, 4000) ;

        HSSFCellStyle cellStyle = workbook.createCellStyle() ;
        cellStyle.setWrapText(true);
        cellStyle.setAlignment((short)2) ;
        cellStyle.setVerticalAlignment((short)1) ;


        HSSFCell cell0 = row0.createCell(0) ;
        cell0.setCellValue("日期            汇率") ;
        cell0.setCellStyle(cellStyle) ;

        //画线(由左上到右下的斜线)
        HSSFPatriarch patriarch = sheet.createDrawingPatriarch();
        HSSFClientAnchor a = new HSSFClientAnchor(0, 0, 1023, 255, (short)0, 0, (short)0, 0);
        HSSFSimpleShape shape1 = patriarch.createSimpleShape(a);
        shape1.setShapeType(HSSFSimpleShape.OBJECT_TYPE_LINE);
        shape1.setLineStyle(HSSFSimpleShape.LINESTYLE_SOLID) ;
        String[] head={"美元","欧元","日元","港元"};
        for (int i = 1; i < 5; i++) {
            HSSFCell cell = row0.createCell(i);
            cell.setCellValue(head[i-1]);
        }
        for(int i=1;i<=map.size();i++){
            HSSFRow row = sheet.createRow(i);
            String[] data = map.get(String.valueOf(i)).split(",");
            for (int j = 0; j <data.length ; j++) {
                HSSFCell cell = row.createCell(j);
                cell.setCellValue(data[j]);
            }
        }
        File file=new File("H:/人民币汇率.xls");
        FileOutputStream fileOutputStream=null;
        try {
            if(!file.exists()) file.createNewFile();
            fileOutputStream=new FileOutputStream(file);
            workbook.write(fileOutputStream);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(fileOutputStream!=null) fileOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }
    static class Worker implements Runnable{
        private final BlockingDeque<String[]> bq;
        private final Map<String,String> map;
        private final Pattern pattern=Pattern.compile("\\d\\.\\d+");
        private String host="http://www.pbc.gov.cn";
        @Override
        public void run() {
            final WebClient client = new WebClient(BrowserVersion.CHROME);
            client.getOptions().setJavaScriptEnabled(true);
            client.getOptions().setDoNotTrackEnabled(true);
            client.getOptions().setCssEnabled(false);//禁用css支持
            client.getOptions().setTimeout(100000);//设置连接超时时间
            try {
                while(true){
                    String[] s = bq.take();
                    String index=s[0];
                    String url=s[1];
                    HtmlPage page=null;
                    while(page==null){
                        try {
                            page = client.getPage(host+url);
                            String text = page.getElementById("zoom").getFirstElementChild().getTextContent();
                            Matcher matcher = pattern.matcher(text);
                            StringBuilder sb=new StringBuilder();
                            int start=text.indexOf('，')+1;
                            int end=text.indexOf("银行",start);
                            sb.append(text.substring(start,end)).append(",");
                            int i=0;
                            while(matcher.find()&&i<3){
                                sb.append(matcher.group()).append(",");
                                i++;
                            }
                            if(matcher.find()) sb.append(matcher.group());
                            map.put(index,sb.toString());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }

            } catch (InterruptedException e) {

            }
        }

        public Worker(BlockingDeque<String[]> bq, Map<String, String> map) {
            this.bq = bq;
            this.map = map;
        }
    }
}