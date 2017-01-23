package Java.ExchangeRate;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by hk__lrzy on 2017/1/20.
 */
public class Test {

    public static void main(String []args){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = new GregorianCalendar();
        Date start = new Date();
        calendar.setTime(start);
        calendar.add(calendar.DATE,-30);
        Date end = calendar.getTime();
        //System.out.println(end);
        String sDate = simpleDateFormat.format(start);
        //System.out.println(sDate);
        String eDate = simpleDateFormat.format(end);
        //System.out.println(eDate);
        Map<String,Object> pageParam = new HashMap<>();
        Map<String,Object> targetParam = new HashMap<>();
        Map<String,Object> data = new HashMap<>();
        Map<String,Object> cookies = new HashMap<>();
        Map<String,Object> result = new HashMap<>();
        pageParam.put("cookies",cookies);
        cookies.put("_gscu_507870342","84922739p8g3rw98");
        cookies.put("JSESSIONID2","0000EeS1RIf2qkUOFelYbBmctco:168ptcj00");
        pageParam.put("data",data);
        data.put("projectBean.startDate",eDate);
        data.put("projectBean.endDate",sDate);
        pageParam.put("url","http://www.safe.gov.cn/AppStructured/view/project_RMBQuery.action");
        pageParam.put("timeout",3000);
        pageParam.put("userAgent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/55.0.2883.87 Safari/537.36");

        //数据结构 树
        targetParam.put("result",result);
        result.put("USD","美元");
        result.put("EUR","欧元");
        result.put("HKD","港元");
        result.put("date","日期");
        //System.out.println(pageParam);
        CountExangeRate.execute(pageParam,targetParam);

        /*String path = "E:/demo.xls";

        try {
            WritableWorkbook workbook = Workbook.createWorkbook(new File(path));
            WritableSheet sheet = workbook.createSheet("汇率",0);
            Label date = new Label(0,0,"日期");
            sheet.addCell(date);
            Label country = new Label(1,0,"美元");
            sheet.addCell(country);

            workbook.write();
            workbook.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        */


    }
}
