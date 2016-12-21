/**
 * @Copyright   BitingWind  2016/11/19. email: zhang_sx2013@126.com
 *
 *   A Qunar.com Examination of Fresh Graduate 2017 :
 *   Get the RMB's Currency Exchange Rate to dollar, European dollar,HK dollar,,etc, within 30 days
 *
 *   Note: This Java project depend on POI library that is configured in pom.xml for Maven project.
 *
 *   API :
 *          Static Method :
 *              void  exportExchangeRateBySelectDir(int within = 30)
 *              void  exportExchangeRate(int within = 30, String dir = ".")
 *
 *   Reference  http://docs.oracle.com/javase/8/docs/api/
 *              http://www.cnblogs.com/zhuawang/archive/2012/12/08/2809380.html
 *              http://poi.apache.org/apidocs/index.html
 *              http://www.cnblogs.com/bmbm/archive/2011/12/08/2342261.html
 */
import java.net.URLConnection;
import java.net.URL;
import java.io.PrintWriter;
import java.io.File;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.ArrayList;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import javax.swing.JFileChooser;

public class ExchangeRate {

    /**
     * overload default within value : 30
     */
    public static void exportExchangeRateBySelectDir(){
        exportExchangeRateBySelectDir(30);
    }

    /**
     * select directory to store Excel file
     * @param within
     * @throws IllegalArgumentException
     */
    public static void exportExchangeRateBySelectDir(int within) throws IllegalArgumentException{
        String dir = selectRootDir();
        if(dir == null)
            throw new IllegalStateException("您未选择目录 !");
        exportExchangeRate(within,dir + "\\");
    }

    /**
     * overload default value : within = 30, dir = "."
     */
    public static void exportExchangeRate(){
        exportExchangeRate(30,".");
    }
    /**
     * export exchange rate to specify directory  as Excel file
     * @param within
     *          the number that within specify days
     * @param dir
     *          the directory to store the Excel file
     * @throws IllegalArgumentException
     */
    public static void exportExchangeRate(int within,String dir) throws IllegalArgumentException{
        final String ACTION_URL_STRING =
                "http://www.safe.gov.cn/AppStructured/view/project_RMBQuery.action";
        if(within <= 0)
            throw new IllegalArgumentException("请提供大于零的天数 !");
        if(dir.equals(".")) dir = "";
        else if(!new File(dir).isDirectory())
            throw new IllegalArgumentException("请提供正确的目录 ！");
        // make POST parameter
        String day_ago_30 = getDateString(within);
        String yesterday = getDateString(1);
        // the format POST parameter is got from google chrome
        String postParam = "projectBean.startDate=" + day_ago_30 + "&projectBean.endDate=" + yesterday + "&queryYN=true";

        // get remote data
        String dataRemote = sendPost(ACTION_URL_STRING, postParam);
        // extract rate data
        ArrayList<ArrayList<String>> rateTable = getRate(dataRemote);
        // export rate data by selecting the currencies and naming the excel file for
        exportExcel(rateTable, "美元；日元；港元；欧元；英镑", dir + "人民币汇率中间价.xls");
    }

    /**
     * Get the Date String
     * @param ago
     *          the number of specify day before today
     * @return the data format "YYYY-MM-dd"
     */
    private static String getDateString(long ago) {
        long MS_PRE_DAY = 24 * 3600 * 1000;
        // static factory constructor
        Calendar calendar = Calendar.getInstance();
        // set the value of the static fields in Calendar  by TimeStamp
        calendar.setTime(new Date(System.currentTimeMillis() - MS_PRE_DAY * ago));

        String result = new String("");
        // get the day month year
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        // !!!!!!!!! NOTE "+1"
        int month = calendar.get(Calendar.MONTH) + 1;
        int year = calendar.get(Calendar.YEAR);
        result = result + year + "-" + month + "-" + day;

        return result;
    }

    /**
     * Extract the Rate Information from the Specify Text.
     * @param  txt
     *            the text of static html page(!!! can not contain  line feeds such as "\n")
     * @return  the rate data table contains :
     *              1> a String list of currencies (contain "日期") correspond to Header row
     *              2> some String lists of data correspond to a few columns of table (contain date list)
     */
    private static ArrayList<ArrayList<String>> getRate(String txt) {
        ArrayList<ArrayList<String>> result = new ArrayList<ArrayList<String>>();
        ArrayList<String> currencies = new ArrayList<String>(Arrays.asList(
                "日期", "美元", "欧元", "日元", "港元", "英镑", "林吉特", "卢布", "兰特", "韩元",
                "迪拉姆", "里亚尔", "澳元", "加元", "新西兰元", "新加坡元", "瑞士法郎"));
        result.add(currencies);
        for (int i = 0; i < currencies.size(); i++)
            result.add(new ArrayList<String>());
        Pattern p = Pattern.compile("人民币汇率中间价列表.*</table>");
        Matcher m = p.matcher(txt);
        String extract = null;
        if (m.find()) extract = m.group();

        // get date list
        p = Pattern.compile("[0-9]{4}-[0-9]{2}-[0-9]{2}");
        m = p.matcher(extract);
        while (m.find()) result.get(1).add(m.group());

        // get all rate data list contain 16 currencies
        p = Pattern.compile("[0-9]+\\.[0-9]+");
        m = p.matcher(extract);
        for (int i = 0; i < result.get(1).size(); i++)
            for (int j = 0; j < currencies.size() - 1; j++) {
                m.find();
                result.get(j + 2).add(m.group());
            }
        return result;
    }

    /**
     * Export the Rate Data as Excel Table
     * @param  rate
     *          the source rate data
     * @param  choice
     *          the string that contain 16 currencies'  name in chinese separated by "；",
     *          such as a substring of  "美元；日元；港元；欧元；英镑；林吉特；卢布；兰特；韩元；
     *          迪拉姆；里亚尔；澳元；加元；新西兰元；新加坡元；瑞士法郎"
     * @param  filename
     *          the name of Excel file that would be exported
     */
    private static void exportExcel(ArrayList<ArrayList<String>> rate, String choice, String filename) {
        // new a book ,follow create sheet, follow create row(int),next create cell(short limit)
        HSSFWorkbook book = new HSSFWorkbook();
        HSSFSheet sheet = book.createSheet("Sheet0");

        // select currency
        ArrayList<Boolean> boolList = new ArrayList<Boolean>();
        boolList.add(true);
        for (int i = 1; i < rate.get(0).size(); i++)
            boolList.add(choice.matches(".*" + rate.get(0).get(i) + ".*"));

        // make header
        int column = 0;
        HSSFRow row = sheet.createRow((int) 0);
        for (int i = 0; i < rate.get(0).size(); i++)
            if (boolList.get(i)) row.createCell((short) column++).setCellValue(rate.get(0).get(i));


        // the number of data row
        int rowNum = rate.get(1).size();
        // make rate data
        int j;
        for (int i = 0; i < rowNum; i++) {
            // reset column
            column = 0;
            // create a new row
            row = sheet.createRow(i + 1);
            // the row begin with a date data cell
            row.createCell((short) column++).setCellValue(rate.get(1).get(i));
            // create some cells for currency that is selected !
            for (j = 1; j < rate.get(0).size(); j++) {
                if (boolList.get(j)) row.createCell((short) column++).setCellValue(rate.get(j + 1).get(i));
            }
        }
        // export data
        try {
            FileOutputStream fout = new FileOutputStream(filename);
            book.write(fout);
            fout.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * send POST request and get the response string without "\n" !!
     * @param  url
     *          action url that receive the request
     * @param  param
     *          POST parameter string
     * @return  static html page string without line feeds symbol ("\n")
     */
    private static String sendPost(String url, String param) {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            URL realUrl = new URL(url);
            // open the connection to URL
            URLConnection conn = realUrl.openConnection();
            // set the request properties
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // necessary !!!
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // get output stream to remote
            out = new PrintWriter(conn.getOutputStream());
            // transport the parameter to remote
            out.print(param);
            // cache of flush stream
            out.flush();
            // create the inout stream
            in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送 POST 请求出现异常！" + e);
            e.printStackTrace();
        }
        // close the input & output stream in finally stack
        finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return result;
    }
    /**
     * set rootDir by visual file chooser
     * @return  1 : select complete,
     *           0 : select fail (may cause by closing the window)
     */
    private static String selectRootDir(){
        String result = null;
        JFileChooser chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int returnVal = chooser.showOpenDialog(null);
        if(returnVal == JFileChooser.APPROVE_OPTION) {
            result = chooser.getSelectedFile().getAbsolutePath();
        }
        return result;
    }
    public static void main(String[] args) {
        // exportExchangeRate(30,"H:\\com\\");
        // exportExchangeRate();
        exportExchangeRateBySelectDir();

    }
}