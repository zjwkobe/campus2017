package com.firstwork.qunar;

/**
 * Created by admin on 2017/1/17.
 */

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Created by Administrator on 2016/12/23.
 */
public class ExchangeRate {
    public static void main(String[] args) throws Exception {
        Map<String, String> map = new HashMap<String, String>();
        map.put("1315", "港币");
        map.put("1316", "美元");
        map.put("1326", "欧元");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        StringBuilder allData = new StringBuilder();
        for (String str : map.keySet()) {
            Calendar calendar = Calendar.getInstance();
            for (int i = 0; i < 30; i++) {
                calendar.add(Calendar.DAY_OF_WEEK, -1);
                try {
                    String nowDate = sdf.format(calendar.getTime());
                    String data = getData(nowDate, str);
                    if (data != null) {
                        allData.append(formatData(data, map.get(str), nowDate));
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        exportToExcel(allData.toString());
//        System.out.println(allData.toString());
        // exportExcel(allData.toString());
    }

    public static String getData(String date, String currency) throws Exception {
        String path = "http://srh.bankofchina.com/search/whpj/search.jsp";
        URL url = new URL(path);

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        connection.setDoOutput(true);
        connection.setDoInput(true);
        connection.setRequestMethod("POST");
        connection.setUseCaches(false);
        connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.87 Safari/537.36");
        OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream(), "UTF-8");

        out.write("erectDate=" + date + "&nothing=" + date + "&pjname=" + currency);
        out.flush();
        out.close();

        //connection.connect();

        System.out.println(connection.getResponseCode());
        InputStream in = connection.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();
        String str = br.readLine();
        while (str != null) {
            sb.append(str + "\r\n");
            str = br.readLine();
        }
        br.close();
        in.close();

        return sb.toString();
    }

    public static String formatData(String origin, String currency, String date) {
        StringBuilder stringBuilder = new StringBuilder();
        Pattern pattern = Pattern.compile("<tr>[\\s]*<td>" + currency + "</td>[\\s]*<td>.*</td>[\\s]*<td>.*</td>[\\s]*<td>.*</td>[\\s]*<td>.*</td>[\\s]*<td>.*</td>[\\s]*<td>(.*)</td>[\\s]*<td>(.*)</td>[\\s]*</tr>");
        Matcher matcher = pattern.matcher(origin);
        if (matcher.find()) {
            stringBuilder.append(currency + "|" + matcher.group(1) + "|" + matcher.group(2) + "\r\n");
        }
        System.out.println(stringBuilder.toString());
        return stringBuilder.toString();
    }

    public static void exportToExcel(String data) throws Exception {
        String[] title = {"汇率表","美元", "欧元", "港币", "汇率发布时间"};
        WritableWorkbook book = Workbook.createWorkbook(new File("C:\\Users\\admin\\Desktop\\1.xlsx"));
        WritableSheet sheet = book.createSheet("Sheet1", 0);
        for (int i = 0; i < title.length; i++) {
            sheet.addCell(new Label(i , 0, title[i]));
        }

        int x = 0; //代表导入到的Excel中的横坐标
        int y = 0; //代表导入到的Excel中的纵坐标
        String[] SingleRow = data.split("\n");
            x++;
            for (int i = 0; i < SingleRow.length; i++) {
                y++;
                if(i%30==0 && i!=0)
                {
                    y=1;
                    x++;
                }
                String[] temp = SingleRow[i].split("\\|");
                if(i>=60)  //打印时间
                    sheet.addCell(new Label(x+1, y, temp[2]));
                sheet.addCell(new Label(x, y, temp[1]));


            }


            book.write();
            book.close();

    }
}
