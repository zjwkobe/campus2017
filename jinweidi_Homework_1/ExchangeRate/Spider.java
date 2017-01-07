
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import java.text.SimpleDateFormat;
import java.util.*;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Created by Readdy on 2017/1/3.
 * @version 0.0.1 2017/01/03
 * @author  weidi.jin
 */
public class Spider {
    public static void main(String[] args) {
        HtmlSpider hs = new HtmlSpider();

        Document doc = hs.getDocFromUrl();
        hs.parseHtmlDoc(doc);
        hs.saveExcelDoc("C:\\Users\\Readdy\\Desktop\\Writesheet.xlsx");
    }
}

class HtmlSpider {
    private static final String targetURL = "http://www.chinamoney.com.cn/fe-c/historyParity.do";

    private static Map<String, String> translation = new TreeMap<>();

    // 需要的汇率所在的列
    private static List<Integer> columnSet = new LinkedList<>();

    //
    private XSSFWorkbook aWorkbook = new XSSFWorkbook();

    public HtmlSpider() {
        translation.put("日期", "日期");
        translation.put("USD/CNY", "美元/人民币");
        translation.put("EUR/CNY", "欧元/人民币");
        translation.put("HKD/CNY", "港元/人民币");
    }

    /**
     *
     * @return Document
     */
    public Document getDocFromUrl() {
        Document doc;
        Connection connToServ = Jsoup.connect(targetURL);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println(sdf.format(new Date()));
        System.out.println(sdf.format(new Date(System.currentTimeMillis()-1000*3600*24*29L)));

        // fill form args
        connToServ.data("startDate", sdf.format(new Date(System.currentTimeMillis()-1000*3600*24*29L)));
        connToServ.data("endDate", sdf.format(new Date()));
        connToServ.data("flagMessage", "");
        connToServ.timeout(10000); // timeout=10s

        // use post method
        try {
            doc = connToServ.post();
        } catch (IOException e) {
            System.out.println("[ Error ] "+e.getLocalizedMessage());
            return null;
        }

        return doc;
    }

    /**
     *
     * @param aDoc
     */
    public void parseHtmlDoc(Document aDoc) {
        if (aDoc == null) {
            System.out.println("[ Error ] param error: aDoc == null");
            return ;
        }

        Elements matchedElems = aDoc.getElementsByAttributeValue("style", "table-layout:fixed;");

        int matchedNum = matchedElems.size();
        if (matchedNum == 0) {
            System.out.println("[ Warning ] not found target content in html doc");
        } else if (matchedNum > 1 ) {
            System.out.println("[ Warning ] too much target content");
        }

        /*
         * if success, someElems has one element.
         * In html, the element is a <table>.
         * It has only one child-element: <tbody>.
         */
        Elements trs = matchedElems.first().child(0).children();
        matchedNum = trs.size();
        if (matchedNum == 0) {
            System.out.println("[ Error ] target table is empty");
            return ;
        }

        XSSFSheet aSheet = aWorkbook.createSheet("人民币汇率中间价");
        XSSFCell aCell = null;

        XSSFCellStyle headRowStyle = aWorkbook.createCellStyle();
        headRowStyle.setAlignment(HorizontalAlignment.CENTER);
        Font ft = aWorkbook.createFont();
        ft.setBold(true);
        headRowStyle.setFont(ft);

        Integer rowId = 0;
        for (Element tr: trs) {
            // create a row
            XSSFRow aRow = aSheet.createRow(rowId);

            // get all "td"es
            Elements tds = tr.children();

            // fill celles of the row
            Integer cellId = 0;
            Integer cellIndex = 0;
            for (Element td: tds) {
                String str = td.text();

                if (rowId == 0) {

                    //System.out.println("row["+rowId+"]col["+cellIndex+"]=<"+str+">");

                    if ( translation.containsKey(str) ) {
                        columnSet.add(cellIndex);

                        aCell = aRow.createCell(cellId);

                        aCell.setCellValue(translation.get(str));

                        // 设置标题行居中加粗
                        aCell.setCellStyle(headRowStyle);

                        // 设置列宽
                        aSheet.setColumnWidth(cellId, 256*12);

                        ++cellId;
                    }

                } else {
                    if ( columnSet.contains(cellIndex) ) {

                        aCell = aRow.createCell(cellId);
                        aCell.setCellValue(td.text());

                        ++cellId;
                    }
                }

                ++cellIndex;
            }

            ++rowId;
        }
    }

    /**
     *
     * @param filePath
     */
    public void saveExcelDoc(String filePath) {
        //Write the workbook in file system
        FileOutputStream out = null;

        try {
            out = new FileOutputStream(new File(filePath));
            aWorkbook.write(out);
            out.close();

        } catch (FileNotFoundException e) {
            System.out.println(e.getLocalizedMessage());
            return ;

        } catch (IOException e) {
            System.out.println(e.getLocalizedMessage());
            return ;
        }

        System.out.println(filePath+" written successfully" );
    }
}