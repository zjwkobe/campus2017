import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

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
    private static int TIME = 30;

    public static void main(String[] args) throws Exception {
        String path = "D:\\1.xls";
        Map<String, String> map = new HashMap<String, String>();
        //目标网站上的币种及其编码
        map.put("1315", "港币");
        map.put("1316", "美元");
        map.put("1326", "欧元");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        StringBuilder allData = new StringBuilder();
        Calendar calendar = Calendar.getInstance();
        for (int i = 0; i < TIME; i++) {
            //日期递减
            calendar.add(Calendar.DAY_OF_WEEK, -1);
            for (String str : map.keySet()) {
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
        //导出Excel
        exportExcel(allData.toString(), path);
        System.out.println("导出成功！");
    }

    public static String getData(String date, String currency) throws Exception {
        String path = "http://srh.bankofchina.com/search/whpj/search.jsp";
        URL url = new URL(path);

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        //设置连接属性
        connection.setDoOutput(true);
        connection.setDoInput(true);
        connection.setRequestMethod("POST");
        connection.setUseCaches(false);
        connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.87 Safari/537.36");

        OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream(), "UTF-8");
        //发送请求
        out.write("erectDate=" + date + "&nothing=" + date + "&pjname=" + currency);
        out.flush();
        out.close();

        connection.connect();
        //获取返回数据
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
        Pattern pattern = Pattern.compile("<tr>[\\s]*<td>" + currency + "</td>[\\s]*<td>.*</td>[\\s]*<td>.*</td>[\\s]*<td>.*</td>[\\s]*<td>.*</td>[\\s]*<td>.*</td>[\\s]*<td>(.*)</td>[\\s]*<td>(.*) (.*)</td>[\\s]*</tr>");
        Matcher matcher = pattern.matcher(origin);
        //获取汇率值并格式化保存
        if (matcher.find()) {
            Float d = Float.parseFloat(matcher.group(1)) / 100;
            float result = (float) (Math.round(d * 10000)) / 10000;
            stringBuilder.append(matcher.group(2) + "|" + currency + "|" + result + "\r\n");
        }
        return stringBuilder.toString();
    }

    public static void exportExcel(String origin, String path) throws Exception {
        String[] title = {"美元", "港币", "欧元"};
        OutputStream out = new FileOutputStream(new File(path));
        //创建workbook，对应一个Excel文件
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook();
        //创建sheet，对应Excel的一个sheet
        HSSFSheet hssfSheet = hssfWorkbook.createSheet("汇率表");
        //设置第一列宽度
        hssfSheet.setColumnWidth(0, 3000);
        //创建第一行
        HSSFRow hssfRow = hssfSheet.createRow(0);
        //设置第一行的数据
        for (int i = 0; i < title.length; i++) {
            HSSFCell hssfCell = hssfRow.createCell(i + 1);
            hssfCell.setCellValue(title[i]);
        }
        //获取所有单行数据
        String[] singleDatas = origin.split("\n");
        //标识所处的列
        int index = 1;
        int lastRowNum = hssfSheet.getLastRowNum();
        //创建新行
        HSSFRow lastRow = hssfSheet.createRow(++lastRowNum);
        for (int i = 0; i < singleDatas.length; i++) {
            //将已格式化的数据放入数组
            String[] temp = singleDatas[i].split("\\|");
            HSSFCell cell;
            //新行无日期
            if (lastRow.getLastCellNum() == -1) {
                cell = lastRow.createCell(0);
                cell.setCellType(HSSFCell.CELL_TYPE_STRING);
                cell.setCellValue(temp[0]);
            }
            //日期已填入并且日期匹配
            if (lastRow.getCell(0).getStringCellValue().equals(temp[0])) {
                cell = lastRow.createCell(index);
                cell.setCellType(HSSFCell.CELL_TYPE_STRING);
                cell.setCellValue(temp[temp.length - 1]);
                index++;
            }
            //日期已填入并且日期不匹配
            else {
                index = 1;
                i--;
                lastRow = hssfSheet.createRow(++lastRowNum);
            }

        }
        hssfWorkbook.write(out);
        hssfWorkbook.close();
    }
}